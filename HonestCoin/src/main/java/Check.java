import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Check {

    public Check(int met1) throws Exception {
        if (met1 == 1){
            BufferedReader in_p = new BufferedReader(new FileReader("p.txt"));
            BufferedReader in_q = new BufferedReader(new FileReader("q.txt"));
            BigInteger p = new BigInteger(in_p.readLine());
            BigInteger q = new BigInteger(in_q.readLine());
            System.out.println("A -> B:");
            System.out.println("p = " + p);
            System.out.println("q = " + q);

            in_p.close();
            in_q.close();
        }
        if (met1 == 2){
            BufferedReader in_z = new BufferedReader(new FileReader("z.txt"));
            BufferedReader in_p = new BufferedReader(new FileReader("p.txt"));
            BufferedReader in_q = new BufferedReader(new FileReader("q.txt"));
            PrintWriter out_x1 = new PrintWriter(new FileWriter("x1.txt"));
            PrintWriter out_y1 = new PrintWriter(new FileWriter("y1.txt"));

            BigInteger p = new BigInteger(in_p.readLine());
            BigInteger q = new BigInteger(in_q.readLine());
            BigInteger z = new BigInteger(in_z.readLine());
            BigInteger x, y;
            BigInteger x1 = Prot.Sol(z, p), y1 = p.subtract(x1).mod(p);
            BigInteger x2 = Prot.Sol(z, q), y2 = q.subtract(x2).mod(q);
            if (x1.compareTo(BigInteger.valueOf(-1)) == 0 || x2.compareTo(BigInteger.valueOf(-1)) == 0) {
                System.out.println("There is no comparison solution. Choose other numbers p, q or another number r");
                return;
            }
            BigInteger sol1 = Prot.ch(x1, p, x2, q);
            BigInteger sol2 = Prot.ch(x1, p, y2, q);
            BigInteger sol3 = Prot.ch(y1, p, x2, q);
            BigInteger sol4 = Prot.ch(y1, p, y2, q);
            List<BigInteger> sol = new ArrayList<>();
            sol.add(sol1);
            sol.add(sol2);
            sol.add(sol3);
            sol.add(sol4);
            Collections.sort(sol);
            x = sol.get(0);
            y = sol.get(1);

            out_x1.print(x);
            out_y1.print(y);
            System.out.println("B obtained p, q and found solutions of equation");
            System.out.println("x1 = " + x);
            System.out.println("y1 = " + y);
            System.out.println("B -> A: x1, y1");

            in_p.close();
            in_q.close();
            in_z.close();
            out_x1.close();
            out_y1.close();
        }
        if (met1 == 3){
            BufferedReader in_x = new BufferedReader(new FileReader("x.txt"));
            BufferedReader in_y = new BufferedReader(new FileReader("y.txt"));
            BufferedReader in_x1 = new BufferedReader(new FileReader("x1.txt"));
            BufferedReader in_y1 = new BufferedReader(new FileReader("y1.txt"));

            BigInteger x = new BigInteger(in_x.readLine());
            BigInteger y = new BigInteger(in_y.readLine());
            BigInteger x1 = new BigInteger(in_x1.readLine());
            BigInteger y1 = new BigInteger(in_y1.readLine());

            System.out.println("A compared her solutions to B's solutions:");
            if (x.compareTo(x1) == 0 && y.compareTo(y1) == 0)

                System.out.println("B honestly held the protocol");
            else
                System.out.println("B did not hold the protocol");
        }
    }
}
