import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an action:");
        System.out.println("1. Distribution of shares");
        System.out.println("2. Recovery the secret");

        int met = sc.nextInt();
        if (met == 1)
            new Distribution();
        if (met == 2)
            new Recovery();
    }
}