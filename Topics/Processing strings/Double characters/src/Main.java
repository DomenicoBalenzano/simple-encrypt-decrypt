import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        for (String s : input) {
            System.out.print(s + s);
        }
    }
}