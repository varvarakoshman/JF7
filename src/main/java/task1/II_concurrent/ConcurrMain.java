package task1.II_concurrent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*
В текстовом (или xml) файле содержится информация о переводах средств со счета на
счет. Создайте приложение, позволяющее в параллельном режиме обработать эту информацию
(счета с приложении представляются собой объекты). Синхронизируйте код приложения
используя ключевое слово synchronized (1 вариант) и библиотеку java.util.concurrent (2 вариант).
2 вариант
 */
public class ConcurrMain {
    public static void main(String[] args) {
        HashMap<String, Account> map = new HashMap<>();//let's suppose we have transactions only between 3 clients(as an example)
        Random rand = new Random();//initially balance has a random value
        map.put("1", new Account("Komarov", rand.nextInt(10000)));
        map.put("2", new Account("Petrov", rand.nextInt(10000)));
        map.put("3", new Account("Sidorov", rand.nextInt(10000)));

        ArrayList<Operator> transactions = new ArrayList<>();
        try (BufferedReader filereader = new BufferedReader(new FileReader("Files\\ListOfTransactions.txt"))) {
            Scanner scan = new Scanner(filereader);
            while (scan.hasNextLine()) {
                transactions.add(new Operator(map.get(scan.next()), map.get(scan.next()), scan.nextInt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        transactions.forEach(Thread::start);
    }
}
