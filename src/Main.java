import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));

    }

    public static String calc(String input) {
        String res = null;
        String[] expression = input.split(" ");
        int a = Integer.parseInt(expression[0]);
        int b = Integer.parseInt(expression[2]);
        System.out.printf("%d, %d\n", a, b);
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            return "Числа должны быть от 1 до 10 включительно";
        }
        res = switch (expression[1]) {
            case "+" -> String.valueOf(a + b);
            case "/" -> String.valueOf(a / b);
            case "*" -> String.valueOf(a * b);
            case "-" -> String.valueOf(a - b);
            default -> "Операция не найдена";
        };
        return res;
    }
}
