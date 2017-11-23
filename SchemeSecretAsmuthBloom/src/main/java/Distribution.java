import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Distribution {

    public Distribution() throws IOException {

        Scanner sc = new Scanner(System.in);
        BufferedReader in_M = new BufferedReader(new FileReader("M.txt"));
        PrintWriter out_fl = new PrintWriter(new FileWriter("flag.txt"));

        int tmp = 1;
        while (true) {
            String fl = Integer.toString(tmp) + ".txt";
            File file = new File(fl);
            if (file.exists()) {
                file.delete();
                tmp++;
            } else
                break;
        }

        System.out.println("Total number of shares");
        int n = sc.nextInt();
        System.out.println("Number of shares required to recovery the secret");
        int m = sc.nextInt();

        String sm = in_M.readLine();
        byte[] bytes = sm.getBytes(Charset.defaultCharset());
        BigInteger M = new BigInteger(bytes);
        if (M.compareTo(BigInteger.ZERO) == -1) {
            out_fl.print(0);
            M = M.negate();
        } else
            out_fl.print(1);

        BigInteger p = M.add(BigInteger.ONE);
        while (p.isProbablePrime(100) == false)
            p = p.add(BigInteger.ONE);

        List<BigInteger> d = new ArrayList<>();
        BigInteger dd = p.add(BigInteger.ONE);
        int nm = n - m + 2 - 1;
        while (true) {
            while (d.size() != n) {
                boolean fl = true;
                if (dd.isProbablePrime(100)) {
                    for (int j = 0; j < d.size(); j++) {
                        if (!d.get(j).gcd(dd).equals(BigInteger.ONE)) {
                            fl = false;
                            break;
                        }
                    }
                    if (fl)
                        d.add(dd);
                }
                dd = dd.add(BigInteger.ONE);
            }

            BigInteger x1 = BigInteger.ONE, x2 = BigInteger.ONE;
            for (int i = 0; i < m; i++) {
                x1 = x1.multiply(d.get(i));
            }
            for (int i = nm; i < n; i++) {
                x2 = x2.multiply(d.get(i));
            }
            x2 = x2.multiply(p);
            if (x1.compareTo(x2) == 1)
                break;
            else
                d.remove(0);
        }

        BigInteger x1 = BigInteger.ONE;
        for (int i = 0; i < m; i++) {
            x1 = x1.multiply(d.get(i));
        }
        BigInteger x2 = BigInteger.ONE;
        for (int i = n - m + 1; i < n; i++) {
            x2 = x2.multiply(d.get(i));
        }

        BigInteger r = BigInteger.valueOf((long) (Math.random() * Long.MAX_VALUE));
        BigInteger m1 = M.add(r.multiply(p));
        while (m1.compareTo(x2) == -1 || m1.compareTo(x1) == 1) {
            if (m1.compareTo(x2) == -1)
                r = r.multiply(BigInteger.valueOf(2));
            if (m1.compareTo(x1) == 1)
                r = r.divide(BigInteger.valueOf(3));
            m1 = M.add(r.multiply(p));
        }

        List<BigInteger> k = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            BigInteger y = m1.mod(d.get(i));
            k.add(y);
        }

        for (int i = 0; i < n; i++) {
            String s = Integer.toString(i + 1) + ".txt";
            PrintWriter out_pdk = new PrintWriter(new FileWriter(s));
            out_pdk.print(p + "\n" + d.get(i) + "\n" + k.get(i));
            out_pdk.close();
        }

        in_M.close();
        out_fl.close();
    }
}