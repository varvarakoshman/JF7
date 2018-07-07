package task1.I_synchronized;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Operator extends Thread {
    private Account account1;
    private Account account2;
    private int sum;

    public void run() {
        int hashAcc1 = account1.hashCode(); //DEADLOCK safety
        int hashAcc2 = account2.hashCode();

        Account acc1;
        Account acc2;

        if (hashAcc1 < hashAcc2){
            acc1 = account1;
            acc2 = account2;
        }else{
            acc1 = account2;
            acc2 = account1;
        }
        synchronized (acc1) {
            synchronized (acc2) {
                account1.change(-sum);
                account2.change(sum);
                System.out.printf("%d transferred from %s to %s\n",sum, account1.getHolderName(), account2.getHolderName());
                System.out.printf("current balance of %s: %d\n", account1.getHolderName(),account1.getBalance());
                System.out.printf("current balance of %s: %d\n", account2.getHolderName(),account2.getBalance());
            }
        }
    }

}
