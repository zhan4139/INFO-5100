/**
 * Created by tingyuanzhang on 20/10/2017.
 */
public class AtmUser extends User {
    private double availableBalance;
    private String password;

    public AtmUser(String name, int age, String address, String phoneNumber,
                   String bankAccountNumber, double availableBalance, String password) {
        super(name, age, address, phoneNumber, bankAccountNumber);
        availableBalance = 0.0;
        this.password = password;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public String getPassword() {
        return password;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
