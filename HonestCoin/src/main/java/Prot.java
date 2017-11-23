import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Prot {

    public Prot(int met) throws Exception {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        if (met == 1) {
            PrintWriter out_p = new PrintWriter(new FileWriter("p.txt"));
            PrintWriter out_q = new PrintWriter(new FileWriter("q.txt"));
            PrintWriter out_n = new PrintWriter(new FileWriter("n.txt"));

            System.out.println("Enter the length of the prime number p and the length of the prime number q");
            int pp = sc.nextInt(), qq = sc.nextInt();
            BigInteger p = BigInteger.probablePrime(pp, random);
            BigInteger q = BigInteger.probablePrime(qq, random);
            BigInteger n = p.multiply(q);
            out_p.print(p);
            out_q.print(q);
            out_n.print(n);
            System.out.println("A chose two numbers:");
            System.out.println("p = " + p);
            System.out.println("q = " + q);
            System.out.println("A -> B: n = " + n);

            out_p.close();
            out_q.close();
            out_n.close();
        }
        if (met == 2) {
            BufferedReader in_n = new BufferedReader(new FileReader("n.txt"));
            PrintWriter out_r = new PrintWriter(new FileWriter("r.txt"));
            PrintWriter out_z = new PrintWriter(new FileWriter("z.txt"));

            BigInteger n = new BigInteger(in_n.readLine());
            int nn = (n.divide(BigInteger.valueOf(2)).subtract(BigInteger.ONE)).bitLength();

            BigInteger r = BigInteger.ZERO;
            while (r.equals(BigInteger.ZERO) || r.compareTo(n.divide(BigInteger.valueOf(2)).subtract(BigInteger.ONE)) == 1)
                r = new BigInteger(nn, random);
            BigInteger z = r.modPow(BigInteger.valueOf(2), n);
            out_r.print(r);
            out_z.print(z);
            System.out.println("B chose r = " + r);
            System.out.println("Calculated z = r^2(mod n)");
            System.out.println("B -> A: z = " + z);

            in_n.close();
            out_r.close();
            out_z.close();
        }
        if (met == 3) {
            BufferedReader in_z = new BufferedReader(new FileReader("z.txt"));
            BufferedReader in_p = new BufferedReader(new FileReader("p.txt"));
            BufferedReader in_q = new BufferedReader(new FileReader("q.txt"));
            PrintWriter out_x = new PrintWriter(new FileWriter("x.txt"));
            PrintWriter out_y = new PrintWriter(new FileWriter("y.txt"));

            BigInteger p = new BigInteger(in_p.readLine());
            BigInteger q = new BigInteger(in_q.readLine());
            BigInteger z = new BigInteger(in_z.readLine());
            BigInteger x, y;
            BigInteger x1 = Sol(z, p), y1 = p.subtract(x1).mod(p);
            BigInteger x2 = Sol(z, q), y2 = q.subtract(x2).mod(q);
            if (x1.compareTo(BigInteger.valueOf(-1)) == 0 || x2.compareTo(BigInteger.valueOf(-1)) == 0) {
                System.out.println("There is no comparison solution. Choose other numbers p, q or another number r");
                return;
            }
            BigInteger sol1 = ch(x1, p, x2, q);
            BigInteger sol2 = ch(x1, p, y2, q);
            BigInteger sol3 = ch(y1, p, x2, q);
            BigInteger sol4 = ch(y1, p, y2, q);
            List<BigInteger> sol = new ArrayList<>();
            sol.add(sol1);
            sol.add(sol2);
            sol.add(sol3);
            sol.add(sol4);
            Collections.sort(sol);
            x = sol.get(0);
            y = sol.get(1);

            out_x.print(x);
            out_y.print(y);
            System.out.println("A found the solutions of equation");
            System.out.println("x = " + x);
            System.out.println("y = " + y);

            in_p.close();
            in_q.close();
            in_z.close();
            out_x.close();
            out_y.close();
        }
        if (met == 4) {
            BufferedReader in_x = new BufferedReader(new FileReader("x.txt"));
            BufferedReader in_y = new BufferedReader(new FileReader("y.txt"));
            PrintWriter out_choice = new PrintWriter(new FileWriter("choice.txt"));

            System.out.println("Choose which value you want to send to B:");
            System.out.println("1. x");
            System.out.println("2. y");

            BigInteger x = new BigInteger(in_x.readLine());
            BigInteger y = new BigInteger(in_y.readLine());

            int met2 = sc.nextInt();
            if (met2 == 1) {
                out_choice.print(x);
                System.out.println("A -> B: x = " + x);
            } else if (met2 == 2) {
                out_choice.print(y);
                System.out.println("A -> B: y = " + y);
            }

            in_x.close();
            in_y.close();
            out_choice.close();
        }
        if (met == 5) {
            BufferedReader in_choice = new BufferedReader(new FileReader("choice.txt"));
            BufferedReader in_r = new BufferedReader(new FileReader("r.txt"));

            BigInteger choice = new BigInteger(in_choice.readLine());
            BigInteger r = new BigInteger(in_r.readLine());

            System.out.println("B compared r with the solution of A");
            System.out.print("Result: ");

            if (choice.equals(r))
                System.out.println("A guessed - eagle");
            else
                System.out.println("A did not guess - tails");

            in_choice.close();
            in_r.close();
        }
    }

    public static int Y(BigInteger a, BigInteger n) {

        int res = 1;
        int s = 0;

        while (true) {
            if (a.compareTo(BigInteger.ZERO) == 0)
                return 0;

            if (a.compareTo(BigInteger.ONE) == 0)
                return res;

            int k = 0;
            BigInteger a1 = a;
            while (a1.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                a1 = a1.divide(BigInteger.valueOf(2));
                k++;
            }

            if (k % 2 == 0) {
                s = 1;
            } else {
                if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.ONE) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(7)))
                    s = 1;
                else if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5)))
                    s = -1;
            }

            if (a1.equals(BigInteger.ONE))
                return res * s;

            if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && a1.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)))
                s = -s;

            a = n.mod(a1);
            n = a1;
            res = res * s;
        }
    }

    public static BigInteger Sol(BigInteger a, BigInteger p) throws Exception {
        if (Y(a, p) == -1)
            return BigInteger.valueOf(-1);
        BigInteger n = BigInteger.ZERO;
        if (Y(a, p) == 1) {
            for (BigInteger i = BigInteger.valueOf(2); i.compareTo(p.subtract(BigInteger.ONE)) == -1; i = i.add(BigInteger.ONE)) {
                if (Y(i, p) == -1) {
                    n = i;
                    break;
                }
            }
        }

        BigInteger h;
        h = p.subtract(BigInteger.ONE);
        long k = 0;
        while (h.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            h = h.divide(BigInteger.valueOf(2));
            k++;
        }

        BigInteger a1 = a.modPow(h.add(BigInteger.ONE).divide(BigInteger.valueOf(2)), p);
        BigInteger a2;
        try {
            a2 = a.modInverse(p);
        } catch (ArithmeticException e) {return BigInteger.valueOf(-1);}

            BigInteger n1 = n.modPow(h, p);
            BigInteger n2 = BigInteger.ONE;

            for (int i = 0; i < k - 1; i++) {
                BigInteger b = (a1.multiply(n2)).mod(p);
                BigInteger c = (a2.multiply(b).multiply(b)).mod(p);
                BigInteger d = c.modPow(BigInteger.valueOf(2).pow((int) (k - 2 - i)), p);
                int ji = 0;
                if (d.equals(BigInteger.ONE))
                    ji = 0;
                else if (d.equals(p.subtract(BigInteger.ONE)))
                    ji = 1;
                n2 = n2.multiply(n1.pow(((int) Math.pow(2, i) * ji))).mod(p);
            }
            return (a1.multiply(n2)).mod(p);
    }

    public static BigInteger ch(BigInteger a1, BigInteger p, BigInteger a2, BigInteger q){
        BigInteger dp = q, dq = p;
        BigInteger dpp = dp.modInverse(p), dqq = dq.modInverse(q);
        BigInteger x1 = dp.multiply(dpp).multiply(a1);
        BigInteger x2 = dq.multiply(dqq).multiply(a2);
        BigInteger x0 = x1.add(x2).mod(p.multiply(q));
        return x0;
    }
}
