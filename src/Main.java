import java.util.HashMap;
import java.util.Map;
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

    public static int convertRomanToInt(String s)
    {
        Map<Character, Integer> map=new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        s = s.replace("IV","IIII");
        s = s.replace("IX","VIIII");
        s = s.replace("XL","XXXX");
        s = s.replace("XC","LXXXX");
        s = s.replace("CD","CCCC");
        s = s.replace("CM","DCCCC");
        int number = 0;

        for (int i = 0; i < s.length(); i++)
        {
            number = number + (map.get(s.charAt(i)));
        }

        return number;
    }

    public static String RomanNumerals(int num)
    {
        Map<String, Integer> romanNumerals = new HashMap<String, Integer>();
        romanNumerals.put("X", 10);
        romanNumerals.put("IX", 9);
        romanNumerals.put("V", 5);
        romanNumerals.put("IV", 4);
        romanNumerals.put("I", 1);
        StringBuilder result = new StringBuilder();

        for(Map.Entry<String, Integer> entry : romanNumerals.entrySet())
        {
            int matches = num / entry.getValue();
            result.append(repeat(entry.getKey(), matches));
            num = num % entry.getValue();
        }
        return result.toString();
    }

    public static String repeat(String s, int n)
    {
        if(s == null)
        {
            return null;
        }
        return s.repeat(Math.max(0, n));
    }
}
