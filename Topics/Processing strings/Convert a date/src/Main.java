import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String[] splittedInput = sc.nextLine().split("-");
        System.out.print(splittedInput[1] + "/" + splittedInput[2] + "/" + splittedInput[0]);
    }
}