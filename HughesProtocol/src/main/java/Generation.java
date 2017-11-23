import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Generation {
    public Generation() throws IOException {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        PrintWriter out_pg = new PrintWriter(new FileWriter("PrimeNumber.txt"));

        System.out.println("Enter the length of the prime number q, with which to build the p = q*2^k + 1");
        int sq = sc.nextInt();

        BigInteger q = BigInteger.probablePrime(sq, random);
        int k = 1;
        BigInteger p = q.multiply(BigInteger.valueOf(2).pow(k)).add(BigInteger.ONE);

        while (p.isProbablePrime(1) == false) {
            k++;
            p = q.multiply(BigInteger.valueOf(2).pow(k)).add(BigInteger.ONE);
        }

        BigInteger g = BigInteger.ONE;
        while (g.modPow(p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)), p).equals(BigInteger.ONE) ||
                g.modPow(p.subtract(BigInteger.ONE).divide(q), p).equals(BigInteger.ONE))
            g = g.add(BigInteger.ONE);

        System.out.println("A and B generated a large prime number p and its primitive root g" + "\n" +
                "p = " + p + "\n" +
                "g = " + g);

        out_pg.print(p + "\n" + g);

        out_pg.close();
    }
}
