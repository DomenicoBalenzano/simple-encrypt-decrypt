import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        StringBuilder output = new StringBuilder();
        try {
            int count = 0;
            String s = input[0];
            for (String value : input) {
                if (s.equals(value)) {
                    count++;
                } else {
                    System.out.print(s + count);
                    s = value;
                    count = 1;
                }
            }
            System.out.print(s + count);
        } catch (Exception e) {
            System.out.println(input[0]);
        }
    }
}