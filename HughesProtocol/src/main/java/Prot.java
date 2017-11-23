import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Prot {

    public Prot() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. 1 step of the protocol");
        System.out.println("2. 2 step of the protocol");
        System.out.println("3. 3 step of the protocol");
        System.out.println("4. 4 step of the protocol");

        int met = sc.nextInt();

        if (met == 1) {
            BufferedReader in_pg = new BufferedReader(new FileReader("PrimeNumber.txt"));
            PrintWriter out_x = new PrintWriter(new FileWriter("xx.txt"));
            PrintWriter out_k = new PrintWriter(new FileWriter("k.txt"));

            BigInteger p = new BigInteger(in_pg.readLine());
            BigInteger g = new BigInteger(in_pg.readLine());

            if (p.equals(BigInteger.valueOf(2))) {
                System.out.println("x can not be selected, because 1 < x < n=2");
                System.out.println("Choose another n");
                return;
            }
            BigInteger x = BigInteger.valueOf(Math.abs(new Random().nextInt())).mod(p);
            while (x.compareTo(BigInteger.ONE) != 1)
                x = BigInteger.valueOf(Math.abs(new Random().nextInt())).mod(p);
            BigInteger k = g.modPow(x, p);

            out_x.print(x);
            out_k.print(k);

            System.out.println("A chose a random integer x = " + x + "\n" +
                    "A calculated k = " + k);

            in_pg.close();
            out_x.flush();
            out_x.close();
            out_k.close();
        } else if (met == 2) {
            BufferedReader in_pg = new BufferedReader(new FileReader("PrimeNumber.txt"));
            PrintWriter out_y = new PrintWriter(new FileWriter("yy.txt"));
            PrintWriter out_Y = new PrintWriter(new FileWriter("Y.txt"));

            BigInteger p = new BigInteger(in_pg.readLine());
            BigInteger g = new BigInteger(in_pg.readLine());

            BigInteger y = BigInteger.valueOf(Math.abs(new Random().nextInt())).mod(p);
            while (!(y.gcd(p.subtract(BigInteger.ONE)).equals(BigInteger.ONE)) || y.compareTo(BigInteger.ONE) != 1)
                y = BigInteger.valueOf(Math.abs(new Random().nextInt())).mod(p);
            BigInteger Y = g.modPow(y, p);

            out_y.print(y);
            out_Y.print(Y);

            System.out.println("B chose a random integer y = " + y + "\n" +
                    "B calculated Y = " + Y);

            in_pg.close();
            out_y.close();
            out_Y.close();
        } else if (met == 3) {
            BufferedReader in_pg = new BufferedReader(new FileReader("PrimeNumber.txt"));
            BufferedReader in_x = new BufferedReader(new FileReader("xx.txt"));
            BufferedReader in_Y = new BufferedReader(new FileReader("Y.txt"));
            PrintWriter out_X = new PrintWriter(new FileWriter("X.txt"));

            BigInteger p = new BigInteger(in_pg.readLine());
            BigInteger x = new BigInteger(in_x.readLine());
            BigInteger Y = new BigInteger(in_Y.readLine());

            BigInteger X = Y.modPow(x, p);

            out_X.print(X);

            System.out.println("A calculated X = " + X + "\n" +
                    "A -> B: X");

            in_pg.close();
            in_x.close();
            in_Y.close();
            out_X.close();
        } else if (met == 4) {
            BufferedReader in_pg = new BufferedReader(new FileReader("PrimeNumber.txt"));
            BufferedReader in_y = new BufferedReader(new FileReader("yy.txt"));
            BufferedReader in_k = new BufferedReader(new FileReader("k.txt"));
            BufferedReader in_X = new BufferedReader(new FileReader("X.txt"));
            PrintWriter out_z = new PrintWriter(new FileWriter("z.txt"));
            PrintWriter out_k1 = new PrintWriter(new FileWriter("k'.txt"));

            BigInteger p = new BigInteger(in_pg.readLine());
            BigInteger y = new BigInteger(in_y.readLine());
            BigInteger X = new BigInteger(in_X.readLine());
            BigInteger k = new BigInteger(in_k.readLine());
            BigInteger z;
            try {
                z = y.modInverse(p.subtract(BigInteger.ONE));
            } catch (ArithmeticException e) {
                System.out.println("There is no inverse element for " + y + " modulo " + p.subtract(BigInteger.ONE));
                return;
            }
            BigInteger k1 = X.modPow(z, p);

            out_z.print(z);
            out_k1.print(k1);

            System.out.println("B calculated z = " + z + "\n" +
                    "B calculated k' = " + k1);

            if (k.equals(k1))
                System.out.println("The protocol is correct");
            else
                System.out.println("Protocol is incorrect");

            in_pg.close();
            in_y.close();
            in_k.close();
            in_X.close();
            out_z.close();
            out_k1.close();
        }
    }
}
