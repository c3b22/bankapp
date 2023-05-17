package bankapp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option = 0,accountNumber;
        String accountName;
        bank bank = new bank("My bank");
        double amount,balance = 0;
        while (option != 6) {
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Exit");
            System.out.println();
            System.out.println("Enter your choice: ");
            option = scan.nextInt();
            scan.nextLine();
            switch (option){
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    accountNumber = generateAccountNumber();
                    System.out.println("Enter Account Name: ");
                    accountName = scan.nextLine();
                    System.out.println("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    account account = new account(accountNumber,accountName,balance);
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.println("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    bank.closeAccount(accountNumber);
                    break;
                case 4:
                    System.out.println("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(accountNumber,amount);
                    break;
                case 5:
                    System.out.println("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter amount: ");
                    amount = scan.nextDouble();
                    bank.withdrawMoney(accountNumber,amount);
                    break;
            }
            System.out.println();

        }
    }
    public static int generateAccountNumber(){
        Random rand = new Random();
        int accNumber = 100000 + rand.nextInt(9000000);
        return accNumber;
    }
}