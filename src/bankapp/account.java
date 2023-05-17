package bankapp;

public class account {
    private int accountNumber;
    private String accoountName;
    private double balance;

    public account(int accountNumber, String accoountName, double balance) {
        this.accountNumber = accountNumber;
        this.accoountName = accoountName;
        this.balance = balance;
    }
    public void deposit(double amount){
        this.balance = this.balance + amount;
    }
    public void withdraw(double amount){
        this.balance = this.balance - amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccoountName() {
        return accoountName;
    }

    public double getBalance() {
        return balance;
    }
}
