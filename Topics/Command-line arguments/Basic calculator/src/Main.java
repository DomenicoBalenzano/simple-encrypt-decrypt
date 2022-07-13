class Problem {
    public static void main(String[] args) {
        String[] operators = {"+", "-", "*"};
        boolean valid = false;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i].equals(args[0])) {
                valid = true;
            }
        }
        if (valid) {
            if (args[0].equals(operators[0])) {
                System.out.println(Integer.parseInt(args[1]) + Integer.parseInt(args[2]));
            } else if (args[0].equals(operators[1])) {
                System.out.println(Integer.parseInt(args[1]) - Integer.parseInt(args[2]));
            } else {
                System.out.println(Integer.parseInt(args[1]) * Integer.parseInt(args[2]));
            }
        } else {
            System.out.println("Unknown operator");
        }
    }
}