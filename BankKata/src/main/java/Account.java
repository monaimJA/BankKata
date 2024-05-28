import exceptions.InsufficientBalanceException;
import exceptions.InvalidDepositException;
import exceptions.InvalidWithdrawalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {

    private List<Transaction> transactions;
    private DateProvider dateProvider;

    public Account(DateProvider dateProvider) {
        this.transactions = new ArrayList<>();
        this.dateProvider = dateProvider;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidDepositException("Deposit amount must be positive");
        }
        transactions.add(new Transaction(dateProvider.getCurrentDate(), amount));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidWithdrawalException("Withdrawal amount must be positive");
        }
        int currentBalance = getCurrentBalance();
        if (currentBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        transactions.add(new Transaction(dateProvider.getCurrentDate(), -amount));
    }

    @Override
    public void printStatement() {
        List<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);

        List<Integer> balances = calculateBalances();

        System.out.println("Date       | Amount  | Balance");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < reversedTransactions.size(); i++) {
            Transaction transaction = reversedTransactions.get(i);
            System.out.printf("%s | %6d  | %6d%n", transaction.getDate().format(formatter), transaction.getAmount(), balances.get(i));
        }
    }

    private List<Integer> calculateBalances() {
        List<Integer> balances = new ArrayList<>();
        int balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
            balances.add(balance);
        }
        Collections.reverse(balances);
        return balances;
    }

    public void depositWithDate(int amount, LocalDate date) {
        if (amount <= 0) {
            throw new InvalidDepositException("Deposit amount must be positive");
        }
        transactions.add(new Transaction(date, amount));
    }

    public void withdrawWithDate(int amount, LocalDate date) {
        if (amount <= 0) {
            throw new InvalidWithdrawalException("Withdrawal amount must be positive");
        }
        int currentBalance = getCurrentBalance();
        if (currentBalance < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        transactions.add(new Transaction(date, -amount));
    }

    public int getCurrentBalance() {
        int balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
}
