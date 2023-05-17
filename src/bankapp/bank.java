package bankapp;
import java.sql.ResultSet;
import javax.script.ScriptContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class bank {
    private String name;
    public bank(String name){
        this.name = name;
    }
    public void listAccount(){
        Connection connection = BankingConnection.connection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()){
                System.out.println(results.getString(1)+" "+results.getString(2)+" "+
                        results.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openAccount(account account){
        Connection connection = BankingConnection.connection();
        String sql = "insert into account(accNumber,accName,accBalance)" + "values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt (1,account.getAccountNumber());
            preparedStatement.setString (2,account.getAccoountName());
            preparedStatement.setDouble(3,account.getBalance());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void closeAccount(int accountNumber){
        Connection connection = BankingConnection.connection();
        String sql = "DELETE FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void depositMoney(int accountNumber, double amount){
        account account = getAccount(accountNumber);
        account.deposit(amount);
        Connection connection = BankingConnection.connection();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            System.out.println("Balance: " + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void withdrawMoney(int accountNumber, double amount){
        account account = getAccount(accountNumber);
        account.withdraw(amount);
        Connection connection = BankingConnection.connection();
        String sql = "UPDATE account SET accBalance=? WHERE accNumber=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
            System.out.println("Balance: " + account.getBalance());
        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public account getAccount(int number){
        Connection connection = BankingConnection.connection();
        String sql = "SELECT * FROM account WHERE accNumber=?";
        PreparedStatement preparedStatement;
        account account = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();

            result.next();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new account(number, accName, balance);

        } catch (SQLException ex) {
            Logger.getLogger(bank.class.getName()).log(Level.SEVERE, null, ex);
        }

        return account;
    }

}