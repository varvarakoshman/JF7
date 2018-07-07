package task1.II_concurrent;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Account {
    private String holderName;
    private int balance;
    Lock lock = new ReentrantLock();

    public Account(String holderName, int balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    public void change(int amount) {
        balance += amount;
    }

}
