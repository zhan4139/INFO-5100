import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by tingyuanzhang on 18/10/2017.
 */
public class Atm {
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private static final double TRANSACTION_FEE = 2.5;
    private static final int RECENT_TRANSACTION = 10;
    private static final  String USER_DATA_PATH = "/Users/tingyuanzhang/Desktop";
    private static final String USER_TRANSACTION_PATH = "/Users/tingyuanzhang/Desktop";

    private double availableAmountInMachine;
    private double transactionFee;
    private File userData;
    private File userTransaction;

    public Atm() {
        availableAmountInMachine = 5000.0;
        transactionFee = Atm.TRANSACTION_FEE;
        userData = new File(Atm.USER_DATA_PATH + "userData.txt");
        userTransaction = new File(Atm.USER_TRANSACTION_PATH + "userTransaction.txt");
    }

    public void welcomePanel() {
        Scanner in = new Scanner(System.in);
        int input = Integer.parseInt(in.next());
        System.out.println("Welcome to ATM, what would you like to do?");
        System.out.println("1. New User\n 2. Current User");
        if (input == 1) {
            createUser();
        } else if (input == 2) {
            userLogin();
        }

    }

    public void createUser() {
        HashMap<String, String> userDetail = new HashMap<>();
        userDetail.put("name", "");
        userDetail.put("age", "");
        userDetail.put("address", "");
        userDetail.put("phoneNumber", "");
        userDetail.put("bankAccountNumber", "");

        Scanner in = new Scanner(System.in);
        for (String s : userDetail.keySet()) {
            System.out.print("Please enter " + s);
            if (s.equals("phoneNumber"))
                System.out.println(" , Please Note the format should be in number 0-9 of length "
                        + Atm.PHONE_NUMBER_LENGTH);
            if (s.equals("bankAccountNumber"))
                System.out.println(" , Please Note the format should be in number 0-9 of length "
                        + Atm.ACCOUNT_NUMBER_LENGTH);

            String input = in.next();

            while (validateUser(userDetail.get("bankAccountNumber")) != -1) {
                System.out.println("The account number is already exist, please enter another one: ");
                input = in.next();
            }

            userDetail.put(s, input);
        }

        System.out.print("Please enter the password for your account");
        String password = in.next();

        System.out.println("You have registered to be a new user!");
        User newUser = new AtmUser(userDetail.get("name"), Integer.parseInt(userDetail.get("age")),
                                    userDetail.get("address"), userDetail.get("phoneNumber"),
                                    userDetail.get("bankAccountNumber"), 0.0, password);

        userPanel(newUser);
    }

    /**
     * @param accountNum of registering user
     * @return -1 if not find; otherwise return the accountNum
     */
    public int validateUser(String accountNum) {
        //TODO: read file to check existence of bank account Number
        return -1;
    }

    public void userLogin() {
        //TODO: check login read file
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your bank account number: ");
        String input = in.next();
        if ()

    }

    public void userPanel(User user) {
        //TODO: display options user can take, redirect to different portion of the Atm
    }



    public void setAvailableAmountInMachine(double availableAmountInMachine) {
        this.availableAmountInMachine = availableAmountInMachine;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void setUserData(File userData) {
        this.userData = userData;
    }

    public double getAvailableAmountInMachine() {
        return availableAmountInMachine;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public File getUserData() {
        return userData;
    }

}
