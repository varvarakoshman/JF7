package task1.I_synchronized;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private String holderName;
    private int balance;

    public void change(int amount) {
        balance += amount;
    }

}
