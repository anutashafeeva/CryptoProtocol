import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class KeyGenerator {

    public KeyGenerator() throws IOException {

        PrintWriter outOpenKey = new PrintWriter(new FileWriter("publicKeyA.txt"));
        PrintWriter outSecretKey = new PrintWriter(new FileWriter("secretKeyA.txt"));
        BufferedReader readInf = new BufferedReader(new FileReader("inf.txt"));
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the length of the prime number p and the length of the prime number q");
        int pp = sc.nextInt(), qq = sc.nextInt();

        BigInteger p = BigInteger.probablePrime(pp, random);
        BigInteger q = BigInteger.probablePrime(qq, random);
        BigInteger n = p.multiply(q);
        BigInteger f = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.probablePrime(f.bitLength(), random).mod(f);
        while (!e.gcd(f).equals(BigInteger.ONE) || e.equals(BigInteger.ZERO) || e.equals(BigInteger.ONE)) {
            e = BigInteger.probablePrime(f.bitLength(), random).mod(f);
        }

        BigInteger ss = e.modInverse(f);
        String j = readInf.readLine();
        byte[] bytes = j.getBytes();
        BigInteger jj = new BigInteger(bytes);

        BigInteger x = jj.modPow(ss, n).modInverse(n);

        BigInteger y = x.modPow(e, n);

        outOpenKey.println(n + "\n" + e + "\n" + y);
        outSecretKey.print(x);

        outOpenKey.close();
        outSecretKey.close();
        readInf.close();
    }
}
