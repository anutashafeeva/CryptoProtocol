import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class Prot {

    public Prot(int met1) throws IOException {

        Random random = new Random();

        if (met1 == 1) {
            BufferedReader inOpenKey = new BufferedReader(new FileReader("publicKeyA.txt"));
            PrintWriter outa = new PrintWriter(new FileWriter("a.txt"));
            PrintWriter outr = new PrintWriter(new FileWriter("r.txt"));

            BigInteger n = new BigInteger(inOpenKey.readLine());
            BigInteger e = new BigInteger(inOpenKey.readLine());
            int szn = (int) (Math.random() * (n.bitLength() - 3) + 2);
            BigInteger r = BigInteger.probablePrime(szn, random);
            BigInteger a = r.modPow(e, n);

            System.out.println("A chose random r = " + r + "\n" + "A calculated a = r^e(mod n) = " + a + "\n");

            outa.print(a);
            outr.print(r);
            outa.close();
            outr.close();

        }
        if (met1 == 2) {
            BufferedReader inM = new BufferedReader(new FileReader("M.txt"));
            BufferedReader ina = new BufferedReader(new FileReader("a.txt"));
            BufferedReader inOpenKey = new BufferedReader(new FileReader("publicKeyA.txt"));
            PrintWriter outd = new PrintWriter(new FileWriter("d.txt"));

            BigInteger n = new BigInteger(inOpenKey.readLine());
            BigInteger e = new BigInteger(inOpenKey.readLine());
            BigInteger a = new BigInteger(ina.readLine());

            String sm = inM.readLine();
            byte[] bytes = sm.getBytes();
            BigInteger m = new BigInteger(bytes);
            sm = m.toString();
            String sa = a.toString();
            String sma = sm + sa;
            BigInteger ma = new BigInteger(sma);

            BigInteger d = BigInteger.valueOf(ma.hashCode()).mod(e);
            outd.print(d);

            System.out.println("A calculated d = H(M, a) = " + d);

            inM.close();
            ina.close();
            inOpenKey.close();
            outd.close();
        }
        if (met1 == 3) {
            BufferedReader inOpenKey = new BufferedReader(new FileReader("publicKeyA.txt"));
            BufferedReader inSecretKey = new BufferedReader(new FileReader("secretKeyA.txt"));
            BufferedReader ind = new BufferedReader(new FileReader("d.txt"));
            BufferedReader inr = new BufferedReader(new FileReader("r.txt"));
            BufferedReader inM = new BufferedReader(new FileReader("M.txt"));
            BufferedReader inJ = new BufferedReader(new FileReader("J.txt"));
            PrintWriter outz = new PrintWriter(new FileWriter("z.txt"));
            PrintWriter outsig = new PrintWriter(new FileWriter("sign.txt"));

            BigInteger n = new BigInteger(inOpenKey.readLine());
            BigInteger x = new BigInteger(inSecretKey.readLine());
            BigInteger r = new BigInteger(inr.readLine());
            BigInteger d = new BigInteger(ind.readLine());

            BigInteger z = r.multiply(x.modPow(d, n)).mod(n);
            outz.print(z);

            System.out.println("A formed a signature. A -> B: M, signature(d, z), J");

            inOpenKey.close();
            inSecretKey.close();
            ind.close();
            inr.close();
            inM.close();
            inJ.close();
            outz.close();
            outsig.close();
        }
    }
}
