package simple.pow;

public class Pow {

    public static double pow(double x, int n) {
        if (n == 0) return 1.0;

        double y = pow(x, n / 2);

        return n % 2 == 0 ? y * y : y * y * x;

    }

    public static void main(String[] args) {
        double x = 4.0;
        int n = -4;

        System.out.println(n >= 0 ? pow(x, n) : 1.0 / pow(x, n));

    }
}
