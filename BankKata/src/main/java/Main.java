import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DateProvider dateProvider=new DateProvider();
        Account account=new Account(dateProvider);
        account.depositWithDate(1000, LocalDate.of(2012, 1, 10));
        account.depositWithDate(2000,LocalDate.of(2012, 1, 13));
        account.withdrawWithDate(500, LocalDate.of(2012, 1, 14));
        account.printStatement();

    }
}
