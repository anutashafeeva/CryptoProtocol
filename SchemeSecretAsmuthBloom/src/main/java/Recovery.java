import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recovery {

    public Recovery() throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out_M = new PrintWriter(new FileWriter("M1.txt"));
        BufferedReader in_fl = new BufferedReader(new FileReader("flag.txt"));

        System.out.println("List the number of shares in a space");
        String[] s = sc.nextLine().split(" ");

        BigInteger p = BigInteger.ONE;
        List<BigInteger> d = new ArrayList<>();
        List<BigInteger> k = new ArrayList<>();
        BigInteger dd = BigInteger.ONE;
        for (int i = 0; i < s.length; i++) {
            String t = s[i] + ".txt";
            BufferedReader in_pdk = new BufferedReader(new FileReader(t));
            BigInteger xp = new BigInteger(in_pdk.readLine());
            BigInteger xd = new BigInteger(in_pdk.readLine());
            BigInteger xk = new BigInteger(in_pdk.readLine());
            p = xp;
            d.add(xd);
            k.add(xk);
            dd = dd.multiply(xd);
            in_pdk.close();
        }

        List<BigInteger> D = new ArrayList<>();
        List<BigInteger> D1 = new ArrayList<>();
        BigInteger m1 = BigInteger.ZERO;
        for (int i = 0; i < s.length; i++) {
            D.add(dd.divide(d.get(i)));
            D1.add(D.get(i).modInverse(d.get(i)));
            m1 = m1.add(D.get(i).multiply(D1.get(i)).multiply(k.get(i))).mod(dd);
        }

        BigInteger M = m1.mod(p);
        if (in_fl.readLine().equals("0"))
            M = M.negate();
        byte[] bytes = M.toByteArray();
        String sm = new String(bytes);
        out_M.print(sm);
        System.out.println("Recovered secret M: " + sm);

        out_M.close();
        in_fl.close();
    }
}