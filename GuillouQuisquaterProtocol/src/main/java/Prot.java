import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Prot {

    public Prot() throws IOException {

        BufferedReader inOpenKey = new BufferedReader(new FileReader("publicKeyA.txt"));
        BufferedReader inSecretKey = new BufferedReader(new FileReader("secretKeyA.txt"));
        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        BigInteger n = new BigInteger(inOpenKey.readLine());
        BigInteger e = new BigInteger(inOpenKey.readLine());
        BigInteger y = new BigInteger(inOpenKey.readLine());

        BigInteger x = new BigInteger(inSecretKey.readLine());

        System.out.println("Select the protocol step:" + "\n" +
                "1. 1st step of the protocol" + "\n" +
                "2. 2nd step of the protocol" + "\n" +
                "3. 3rd step of the protocol" + "\n" +
                "4. Checking");
        int step = sc.nextInt();

        if (step == 1) {
            PrintWriter out1 = new PrintWriter(new FileWriter("a.txt"));
            PrintWriter out12 = new PrintWriter(new FileWriter("r.txt"));

            int szn = (int) (Math.random() * (n.bitLength() - 3) + 2);
            BigInteger r = BigInteger.probablePrime(szn, random);
            out12.print(r);
            BigInteger a = r.modPow(e, n);
            out1.print(a);
            System.out.println("1st step of the protocol");
            System.out.println("A chose a random r = " + r + "\n" + "A -> B: a = r^e(mod n) = " + a + "\n");
            out1.close();
            out12.close();
        }

        if (step == 2) {
            PrintWriter out2 = new PrintWriter(new FileWriter("c.txt"));

            int sze = (int) (Math.random() * (e.bitLength()) + 1);
            BigInteger c = BigInteger.probablePrime(sze, random);
            out2.print(c);
            System.out.println("2nd step of the protocol");
            System.out.println("B chose a random c = " + c + "\n" + "B -> A: c" + "\n");
            out2.close();
        }

        if (step == 3) {
            BufferedReader in12 = new BufferedReader(new FileReader("r.txt"));
            BufferedReader in2 = new BufferedReader(new FileReader("c.txt"));
            PrintWriter out3 = new PrintWriter(new FileWriter("z.txt"));

            BigInteger r1 = new BigInteger(in12.readLine());
            BigInteger c1 = new BigInteger(in2.readLine());
            BigInteger z = x.modPow(c1, n).multiply(r1).mod(n);
            out3.print(z);
            System.out.println("3rd step of the protocol");
            System.out.println("A calculates z = r*(x^c)(mod n) = " + z + "\n" + "A -> B: z" + "\n");
            in12.close();
            in2.close();
            out3.close();
        }

        if (step == 4) {
            BufferedReader in1 = new BufferedReader(new FileReader("a.txt"));
            BufferedReader in2 = new BufferedReader(new FileReader("c.txt"));
            BufferedReader in3 = new BufferedReader(new FileReader("z.txt"));

            System.out.println("B checks: ");
            BigInteger z1 = new BigInteger(in3.readLine());
            BigInteger a1 = new BigInteger(in1.readLine());
            BigInteger c1 = new BigInteger(in2.readLine());
            BigInteger left = z1.modPow(e, n);
            BigInteger right = a1.multiply(y.modPow(c1, n)).mod(n);

            if (left.equals(right)) {
                System.out.println("Authenticity of A is proved");
            } else {
                System.out.println("Authenticity of A is not proved");
            }
            in1.close();
            in2.close();
            in3.close();
        }

        inOpenKey.close();
        inSecretKey.close();
    }
}
