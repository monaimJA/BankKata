import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Transaction> transactions;
    private int currentBalance;
    private DateProvider dateProvider;

    public Account(DateProvider dateProvider){
        this.transactions=new ArrayList<>();
        this.currentBalance=0;
        this.dateProvider=dateProvider;
    }

    @Override
    public void deposit(int amount) {

    }

    @Override
    public void withdraw(int amount) {

    }

    @Override
    public void printStatement() {

    }
}
