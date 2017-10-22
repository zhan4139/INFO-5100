<<<<<<< HEAD
import java.io.Serializable;

/**
 * Created by tingyuanzhang on 18/10/2017.
 */
public class User implements Serializable {
=======
public class User {
>>>>>>> 6d25ce536a3241944f961b4a22705387c3eddc92
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
<<<<<<< HEAD
    private String bankAccountNumber;

    public User() {
    }

    public  User(String name, int age, String address, String phoneNumber, String bankAccountNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankAccountNumber = bankAccountNumber;
    }
=======
>>>>>>> 6d25ce536a3241944f961b4a22705387c3eddc92

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getName() {
<<<<<<< HEAD
=======

>>>>>>> 6d25ce536a3241944f961b4a22705387c3eddc92
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

<<<<<<< HEAD
=======
    private String bankAccountNumber;
>>>>>>> 6d25ce536a3241944f961b4a22705387c3eddc92
}
