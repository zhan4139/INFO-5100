/* Good Work
 * Score 10 + extra credit 2; Total score 10
 */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Atm {
    private static final Scanner in = new Scanner(System.in);
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final int ACCOUNT_NUMBER_LENGTH = 8;
    private static final double TRANSACTION_FEE = 2.5;
    private static final int RECENT_TRANSACTION = 10;
    private static final  String USER_DATA_PATH = "../";
    private static final String USER_TRANSACTION_PATH = "../";

    private double availableAmountInMachine;
    private double transactionFee;
    private File userData;
    private File userTransaction;
    private HashMap<String, AtmUser> users;
    private HashMap<String, ArrayList<String>> transactions;

    public Atm() {
        availableAmountInMachine = 5000.0;
        transactionFee = Atm.TRANSACTION_FEE;
        userData = new File(Atm.USER_DATA_PATH + "userData.ser");
        userTransaction = new File(Atm.USER_TRANSACTION_PATH + "userTransaction.ser");
        users = new HashMap<>();
        transactions = new HashMap<>();
    }


    /**
     * Welcome panel of ATM machine
     */
    public void welcomePanel() {
        System.out.println("Welcome to ATM, what would you like to do?");
        System.out.println("1. New User\n2. Current User\n3. Exit");
        String input = in.next();
        if (input.equals("1")) {
            createUser();
        } else if (input.equals("2")) {
            userLogin();
        } else if (input.equals("3")) {
            logout();
        }

    }

    /**
     * create new user and save it to the file
     */
    public void createUser() {
        HashMap<String, String> userDetail = new HashMap<>();
        userDetail.put("name", "");
        userDetail.put("age", "");
        userDetail.put("address", "");
        userDetail.put("phoneNumber", "");
        userDetail.put("bankAccountNumber", "");

        for (String s : userDetail.keySet()) {
            System.out.println("Please enter " + s + ":");
            if (s.equals("phoneNumber"))
                System.out.println("Please Note the format should be in number 0-9 of length "
                        + Atm.PHONE_NUMBER_LENGTH);
            if (s.equals("bankAccountNumber"))
                System.out.println("Please Note the format should be in number 0-9 of length "
                        + Atm.ACCOUNT_NUMBER_LENGTH);

            String input = in.next();

            while (validateUser(userDetail.get("bankAccountNumber")) != null) {
                System.out.println("The account number is already exist, please enter another one: ");
                input = in.next();
                userDetail.put(s, input);
            }

            userDetail.put(s, input);
        }

        System.out.println("Please enter the password for your account:");
        String password = in.next();

        System.out.println("You have registered to be a new user!");
        AtmUser newUser = new AtmUser(userDetail.get("name"), Integer.parseInt(userDetail.get("age")),
                                    userDetail.get("address"), userDetail.get("phoneNumber"),
                                    userDetail.get("bankAccountNumber"), 0.0, password);


        users.put(userDetail.get("bankAccountNumber"), newUser);
        writeUser(users);
        transactions.put(userDetail.get("bankAccountNumber"), new ArrayList<String>());
        writeTransaction(transactions);
        userPanel(newUser);
    }

    /**
     * @param accountNum of registering user
     * @return -1 if not find; otherwise return the accountNum
     */
    public String validateUser(String accountNum) {
        HashMap<String, AtmUser> users = readUser();
        if (users == null) return null;
        for (String s : users.keySet()) {
            if (s.equals(accountNum)) return s;
        }

        return null;
    }

    /**
     * Input login info and display other options such as
     * 1.forgot password
     * 2.create new user
     * 3.back to homepage
     */
    public void userLogin() {
        System.out.println("Please enter your bank account number: ");
        String input = in.next();

        if (validateUser(input) != null) {
            AtmUser au = users.get(validateUser(input));
            System.out.println("Please enter your password: ");
            String pw = in.next();
            if (pw.equals(au.getPassword())) userPanel(au);
            else {
                System.out.println("Sorry you password is wrong!");
                System.out.println("You can: \n1. Forgot password? \n2. Back to homepage");
                String option = in.next();
                if (option.equals("1")) forgotPassword(au);
                if (option.equals("2")) welcomePanel();
            }
        } else {
            System.out.println("Your account number does not exist!");
            System.out.println("1. Create New User\n2. Back to homepage");
            String option = in.next();
            if (option.equals("1")) createUser();
            if (option.equals("2")) welcomePanel();
        }

    }

    /**
     * Display user options once successfully login
     * @param user
     */
    public void userPanel(AtmUser user) {
        System.out.println("Welcome " + user.getName());
        String option;

        do {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Check Balance \n2. Withdraw\n3. Deposit\n4. Recent Transactions\n" +
                    "5. Change Password\n6. Exit");
            option = in.next();
            if (option.equals("1")) {
                System.out.println("Your account balance is " + users.get(user.getBankAccountNumber()).getAvailableBalance());
            } else if (option.equals("2")) {
                withdraw(user);
            } else if (option.equals("3")) {
                deposit(user);
            } else if (option.equals("4")) {
                displayTransaction(user);
            } else if (option.equals("5")) {
                resetPassword(user);
            } else if (option.equals("6")) {
                welcomePanel();
            } else {
                System.out.println("Please enter a valid number!");
                continue;
            }
        } while (!option.equals("6"));

        welcomePanel();
    }

    /**
     * Reset user's password once identified, otherwise go back to home page
     * @param user
     */
    public void forgotPassword(AtmUser user) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("age", String.valueOf(user.getAge()));
        map.put("phone number", user.getPhoneNumber());

        System.out.println("Please enter your name, age, and phone number for validation.");
        boolean validated = true;
        for (String s : map.keySet()) {
            System.out.println("Please enter " + s);
            String input = in.next();
            if (!input.equals(map.get(s))) {
                validated = false;
            }
        }

        if (validated) {
            resetPassword(user);
        } else {
            System.out.println("Sorry you are not identified!");
            welcomePanel();
        }
    }

    /**
     * reset password and update user file
     * @param user
     */
    public void resetPassword(AtmUser user) {
        System.out.println("Please enter the new password: ");
        String input = in.next();

        user.setPassword(input);
        users.put(user.getBankAccountNumber(), user);
        writeUser(users);
    }

    /**
     * For unit testing
     * @param user
     * @param password
     */
    public void resetPassword(AtmUser user, String password) {
        user.setPassword(password);
    }

    /**
     * read transaction file and display first 10 transactions or all if less than 10
     * @param user
     */
    public void displayTransaction(AtmUser user) {
        transactions = readTransaction();
        ArrayList<String> myTrans = transactions.get(user.getBankAccountNumber());
        int num;
        if (myTrans.size() > Atm.RECENT_TRANSACTION) num = Atm.RECENT_TRANSACTION;
        else num = myTrans.size();

        for (int i = 0; i < num; i++)
            System.out.print(myTrans.get(i));
    }

    /**
     * withdraw money and update both user and transaction file
     * @param user
     */
    public void withdraw(AtmUser user) {
        System.out.println("How much you would like to withdraw?");
        String input = in.next();
        transactions = readTransaction();
        ArrayList<String> list = transactions.get(user.getBankAccountNumber());
        if (Double.parseDouble(input) > getAvailableAmountInMachine()) {
            System.out.println("Sorry we don't have enough money in machine!");
        } else if (Double.parseDouble(input) + Atm.TRANSACTION_FEE > user.getAvailableBalance()) {
            System.out.println("Sorry your balance is not enough!");
        } else {
            double balance = user.getAvailableBalance() - Double.parseDouble(input) - Atm.TRANSACTION_FEE;
            user.setAvailableBalance(balance);
            users.put(user.getBankAccountNumber(), user);
            writeUser(users);
            setAvailableAmountInMachine(getAvailableAmountInMachine() - Double.parseDouble(input) + Atm.TRANSACTION_FEE);
            list.add("Withdrawal - " + input + "\n");
            transactions.put(user.getBankAccountNumber(), list);
            writeTransaction(transactions);
        }
    }

    /**
     * For unit testing
     * @param user
     * @param amount
     */
    public void withdraw(AtmUser user, double amount) {
        if (amount > getAvailableAmountInMachine()) {
            System.out.println("Sorry we don't have enough money in machine!");
        } else if (amount + Atm.TRANSACTION_FEE > user.getAvailableBalance()) {
            System.out.println("Sorry your balance is not enough!");
        } else {
            double balance = user.getAvailableBalance() - amount - Atm.TRANSACTION_FEE;
            user.setAvailableBalance(balance);
            users.put(user.getBankAccountNumber(), user);
            setAvailableAmountInMachine(getAvailableAmountInMachine() - amount + Atm.TRANSACTION_FEE);
        }
    }

    /**
     * deposit money and update both user and transaction file
     * @param user
     */
    public void deposit(AtmUser user) {
        System.out.println("How much you would like to deposit?");
        String input = in.next();
        transactions = readTransaction();
        ArrayList<String> list = transactions.get(user.getBankAccountNumber());
        if (Double.parseDouble(input) + user.getAvailableBalance() >= Atm.TRANSACTION_FEE) {
            user.setAvailableBalance(Double.parseDouble(input) + user.getAvailableBalance() - Atm.TRANSACTION_FEE);
            setAvailableAmountInMachine(Double.parseDouble(input) + Atm.TRANSACTION_FEE);
            list.add("Deposit - " + input + "\n");
            transactions.put(user.getBankAccountNumber(), list);
            writeTransaction(transactions);
            users.put(user.getBankAccountNumber(), user);
            writeUser(users);
        } else {
            System.out.println("Deposit Failed! (You will not be charged transaction fee)");
        }
    }

    /**
     * For unit testing
     * @param user
     * @param amount
     */
    public void deposit(AtmUser user, double amount) {
        if (amount + user.getAvailableBalance() >= Atm.TRANSACTION_FEE) {
            user.setAvailableBalance(amount + user.getAvailableBalance() - Atm.TRANSACTION_FEE);
            setAvailableAmountInMachine(amount + Atm.TRANSACTION_FEE);
        } else {
            System.out.println("Deposit Failed! (You will not be charged transaction fee)");
        }
    }

    /**
     * logout and exit the system
     */
    public void logout() {
        System.out.println("Thank you for your usage!");
        System.exit(0);
    }

    /**
     * @return HashMap all users' bank account number with their transactions
     */
    public HashMap<String, ArrayList<String>> readTransaction() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            if (!userTransaction.exists()) {
                userTransaction.createNewFile();
                writeTransaction(transactions);
            }
            fileIn = new FileInputStream(userTransaction);
            objectIn = new ObjectInputStream(fileIn);
            if (fileIn.available() > 0)
                transactions = (HashMap) objectIn.readObject();
            else return null;
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return transactions;
    }

    /**
     * write updated transactions to transaction file
     * @param transactions
     */
    public void writeTransaction(HashMap<String, ArrayList<String>> transactions) {
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        try {
            if (!userTransaction.exists()) userTransaction.createNewFile();
            fileOut = new FileOutputStream(userTransaction);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * @return HashMap all users' bank account number with corresponding AtmUser object
     */
    public HashMap<String, AtmUser> readUser() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;
        try {
            if (!userData.exists()) {
                userData.createNewFile();
                writeUser(users);
            }
            fileIn = new FileInputStream(userData);
            objectIn = new ObjectInputStream(fileIn);
            if (fileIn.available() > 0)
                users = (HashMap) objectIn.readObject();
            else return null;
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return users;
    }

    /**
     * write updated users to users file
     * @param users
     */
    public void writeUser(HashMap<String, AtmUser> users) {
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        try {
            if (!userData.exists()) userData.createNewFile();
            fileOut = new FileOutputStream(userData);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
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
