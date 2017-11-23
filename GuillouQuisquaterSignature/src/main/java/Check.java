import java.io.*;
import java.math.BigInteger;

public class Check {

    public Check() throws IOException {
        BufferedReader inz = new BufferedReader(new FileReader("z.txt"));
        BufferedReader inOpenKey = new BufferedReader(new FileReader("publicKeyA.txt"));
        BufferedReader inJ = new BufferedReader(new FileReader("J.txt"));
        BufferedReader inM = new BufferedReader(new FileReader("M.txt"));
        BufferedReader ind = new BufferedReader(new FileReader("d.txt"));
        PrintWriter outa1 = new PrintWriter(new FileWriter("a1.txt"));

        BigInteger z = new BigInteger(inz.readLine());
        BigInteger n = new BigInteger(inOpenKey.readLine());
        BigInteger e = new BigInteger(inOpenKey.readLine());
        String sj = inJ.readLine();
        byte[] bytesj = sj.getBytes();
        BigInteger j = new BigInteger(bytesj);
        BigInteger d = new BigInteger(ind.readLine());

        BigInteger a1 = z.modPow(e, n).multiply(j.modPow(d, n)).mod(n);
        outa1.print(a1);

        String sm = inM.readLine();
        byte[] bytesm = sm.getBytes();
        BigInteger m = new BigInteger(bytesm);
        sm = m.toString();
        String sa = a1.toString();
        String sma = sm + sa;
        BigInteger ma1 = new BigInteger(sma);

        BigInteger d1 = BigInteger.valueOf(ma1.hashCode()).mod(e);

        if (d.equals(d1))
            System.out.println("Signature is valid");
        else
            System.out.println("Signature is invalid");

        inz.close();
        inOpenKey.close();
        inJ.close();
        inM.close();
        ind.close();
        outa1.close();
    }
}
