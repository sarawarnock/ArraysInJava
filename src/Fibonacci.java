public class Fibonacci {
    public long calculate(int n){
        // your code here
        if (n < 2) {
            return n;
        }
        long n0 = 0, n1 = 1;
        long sum = 0;
        for (int i = 1; i < n; i = i + 1) {
            sum = n0 + n1;
            n0 = n1;
            n1 = sum;
        }
        return sum;
    }
}