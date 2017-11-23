import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select an action:");
        System.out.println("1. Protocol");
        System.out.println("2. Checking");


        int met = sc.nextInt();
        if (met == 1){
            System.out.println("1. 1 step of the protocol");
            System.out.println("2. 2 step of the protocol");
            System.out.println("3. 3 step of the protocol");
            System.out.println("4. 4 step of the protocol");
            System.out.println("5. 5 step of the protocol");
            int met1 = sc.nextInt();
            new Prot(met1);
        }
        if (met == 2){
            System.out.println("1. 1 step verification");
            System.out.println("2. 2 step verification");
            System.out.println("3. 3 step verification");
            int met1 = sc.nextInt();
            new Check(met1);
        }

    }
}
