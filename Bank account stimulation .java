/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccountsimulation;

/**
 *
 * @author ELCOT
 */
public class BankAccountSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      BankAccount account = new BankAccount(0);

        // Creating multiple customers
        Customer[] customers = new Customer[5];
        for (int i = 0; i < 5; i++) {
            customers[i] = new Customer(account);
            Thread thread = new Thread(customers[i]);
            thread.start();
        }
    
    }
}
   class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", balance: " + balance);
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", balance: " + balance);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " - Insufficient funds for withdrawal");
            return false;
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized boolean transfer(BankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            destinationAccount.deposit(amount);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " - Transfer failed due to insufficient funds.");
            return false;
        }
    }
}

class Customer implements Runnable {
    final BankAccount account;

    public Customer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.deposit(100);
        account.withdraw(30);
        account.transfer(account, 20);
        System.out.println(Thread.currentThread().getName() + " Account Balance: " + account.getBalance());
    }
}

