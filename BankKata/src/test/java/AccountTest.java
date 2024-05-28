import exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp(){
        DateProvider dateProvider=new DateProvider();
        account=new Account(dateProvider);
    }

    @Test
    public void testDeposit(){
        account.deposit(1000);
        account.deposit(2000);
        account.deposit(500);
        Assertions.assertEquals(3500, account.getCurrentBalance());
    }
    @Test
    public void testWithdraw() {
        account.deposit(1000);
        account.withdraw(500);
        Assertions.assertEquals(500, account.getCurrentBalance());
    }
    @Test
    public void testWithdrawInsufficientBalance(){
        account.deposit(1000);
        InsufficientBalanceException exception=Assertions.assertThrows(InsufficientBalanceException.class, ()->{
            account.withdraw(1500);
        });
        Assertions.assertEquals("Insufficient balance for withdrawal", exception.getMessage());
    }
    @Test
    public void testPrintStatement() {
        account.depositWithDate(1000, LocalDate.of(2012, 1, 10));
        account.depositWithDate(2000, LocalDate.of(2012, 1, 13));
        account.withdrawWithDate(500, LocalDate.of(2012, 1, 14));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        account.printStatement();

        String expectedOutput = "Date       | Amount  | Balance\r\n" +
                "14/01/2012 |   -500  |   2500\r\n" +
                "13/01/2012 |   2000  |   3000\r\n" +
                "10/01/2012 |   1000  |   1000\r\n";
        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }
}