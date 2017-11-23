import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an action:");
        System.out.println("1. Generating prime numbers");
        System.out.println("2. Protocol");
        int met = sc.nextInt();

        if (met == 1) {
            new Generation();
        } else if (met == 2) {
            new Prot();
        }
    }
}
