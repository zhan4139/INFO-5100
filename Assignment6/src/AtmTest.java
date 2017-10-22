import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.HashMap;

public class AtmTest {


    Atm atm = new Atm();
    AtmUser testUser = new AtmUser("T", 12, "abc", "6120000000",
            "12345678", 200, "abc");
    HashMap<String, AtmUser> users = new HashMap<>();

    @Test
    public void validateUser() throws Exception {
        users.put("12345678", testUser);
        atm.writeUser(users);
        String s = atm.validateUser(testUser.getBankAccountNumber());
        assertEquals(s, testUser.getBankAccountNumber());
    }


    @Test
    public void deposit() throws Exception {
        atm.deposit(testUser, 50);
        assertEquals(testUser.getAvailableBalance(), 247.5);
    }

    @Test
    public void withdraw() throws Exception {
        atm.withdraw(testUser, 47.5);
        assertEquals(testUser.getAvailableBalance(), 150.0);
    }

    @Test
    public void resetPassword() throws Exception {
        atm.resetPassword(testUser, "123");
        assertEquals(testUser.getPassword(), "123");
    }

    @Test
    public void getAvailableAmountInMachine() throws Exception {
        assertEquals(atm.getAvailableAmountInMachine(), 5000);
    }

    @Test
    public void setAvailableAmountInMachine() throws Exception {
        atm.setAvailableAmountInMachine(10000.0);
        assertEquals(atm.getAvailableAmountInMachine(), 10000.0);
    }

    @Test
    public void getTransactionFee() throws Exception {
        assertEquals(atm.getTransactionFee(), 2.5);
    }

    @Test
    public void setTransactionFee() throws Exception {
        atm.setTransactionFee(2.0);
        assertEquals(atm.getTransactionFee(), 2.0);
    }

    @Test
    public void writeUser() throws Exception {
        HashMap<String, AtmUser> map = new HashMap<>();
        map.put("12345678", testUser);
        atm.writeUser(map);
        map = atm.readUser();
        assertEquals(map.get("12345678").getBankAccountNumber(), testUser.getBankAccountNumber());
    }

    @Test
    public void readUser() throws Exception {
        HashMap<String, AtmUser> map = new HashMap<>();
        map.put("12345678", testUser);
        atm.writeUser(map);
        map = atm.readUser();
        assertEquals(map.get("12345678").getBankAccountNumber(), testUser.getBankAccountNumber());
    }

}
