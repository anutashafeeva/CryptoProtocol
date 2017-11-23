import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an action:");
        System.out.println("1. Key Generation");
        System.out.println("2. Signature of the message");
        System.out.println("3. Signature verification");

        int met = sc.nextInt();
        if (met == 1){
            new KeyGenerator();
        }
        else if (met == 2) {
            System.out.println("1. 1 step");
            System.out.println("2. 2 step");
            System.out.println("3. 3 step");
            int met1 = sc.nextInt();
            new Prot(met1);
        } else if (met == 3) {
            new Check();
        }
    }
}
