import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Test Problem 1
        int[] arr = new int[] {1,2,3};
        try {
            for (int i = 0; i < 4; i++) {
                if (i >= 3) throw new MyIndexOutOfBoundException(i, 0, arr.length - 1);
                System.out.println(arr[i]);
            }
        } catch (MyIndexOutOfBoundException e) {
            System.out.println(e);
        }
        //Test Problem 2
        File a = new File("/home/ryan/Desktop/test");
        new Assignment6_p1p2().parse(a);

        //Test Problem 3
        Atm atm = new Atm();
        atm.welcomePanel();
    }
}
