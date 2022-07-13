import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        List<Integer> list1 = new ArrayList<>();
        String[] input = sc.nextLine().split(" ");
        for (String s : input) {
            list1.add(Integer.parseInt(s));
        }
        int num = sc.nextInt();
        int minDiff = Integer.MAX_VALUE;
        for (Integer x : list1) {
            minDiff = Math.min(Math.abs(num - x), minDiff);
        }
        List<Integer> list2 = new ArrayList<>();
        for (Integer x : list1) {
            if (Math.abs(num - x) == minDiff) {
                list2.add(x);
            }
        }
        Collections.sort(list2);
        for (Integer x : list2) {
            System.out.print(x + " ");
        }
    }
}