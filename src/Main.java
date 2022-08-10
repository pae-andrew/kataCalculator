import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        // Приветствуем пользователя и рассказываем правила
        System.out.println("""
                Добро пожаловать в калькулятор!
                Калькулятор принимает на ввод числа от 1 до 10 включительно,
                а также римские символы "I", "V" и "X", суммарно не превышающие 10.
                Чтобы завершить работу калькулятора введите "exit".
                
                Введите Ваш пример:
                """);

        // Калькулятор работает в цикле пока не выбросит ошибку или пользователь не введет "exit"
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break ;
            }
            System.out.println(calc(input));
        }

    }

    public static String calc(String input) throws Exception {

        String res = "";
        String[] expression = input.split(" ");

        if (checkInt(expression[0]) & checkInt(expression[2])) { // проверяем на арабские числа

            int a = Integer.parseInt(expression[0]);
            int b = Integer.parseInt(expression[2]);
            return calcInt(a, b, expression[1]);

        } else if (checkRomans(expression[0]) & checkRomans(expression[2])) { // Проверяем на римские числа

            return calcRomans(expression);

        } else { // Кидаем исключение, если символы не подходят под арабские или римские числа

            throw new Exception("Неизвестный символ");
        }
    }

    public static String calcInt(int a, int b, String sign) throws Exception {
        String res = "";

        if (a < 1 || a > 10 || b < 1 || b > 10) { // Проверяем на допустимую область значений
            throw new Exception("Числа должны быть от 1 до 10 включительно");
        }

        res = switch (sign) { // Вычисляем значение в зависимости от знака или кидаем ошибку
            case "+" -> String.valueOf(a + b);
            case "/" -> String.valueOf(a / b);
            case "*" -> String.valueOf(a * b);
            case "-" -> String.valueOf(a - b);
            default -> throw new Exception("Неизвестный математический знак");
        };

        return res;
    }

    public static String calcRomans(String[] expression) throws Exception {

        // Конвертируем римские числа в арабские
        int a = convertRomanToInt(expression[0]);
        int b = convertRomanToInt(expression[2]);

        // Отправляем числа в калькулятор и получаем результат
        int res = Integer.parseInt(calcInt(a, b, expression[1]));

        // Конвертируем арабское число в арабское и возвращаем результат
        return romanNumerals(res);
    }

    public static int convertRomanToInt(String s)
    {
        // Создаем мапу с римскими числами и их арабскими аналогами
        Map<Character, Integer> map=new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        // Заменяем числа с вычитанием на понятные комбинации
        s = s.replace("IV","IIII");
        s = s.replace("IX","VIIII");
        s = s.replace("XL","XXXX");
        s = s.replace("XC","LXXXX");
        s = s.replace("CD","CCCC");
        s = s.replace("CM","DCCCC");

        int number = 0;

        // Конвертируем римское число в арабское
        for (int i = 0; i < s.length(); i++)
        {
            number += (map.get(s.charAt(i)));
        }

        return number;
    }

    public static String romanNumerals(int num) throws Exception {
        if (num < 1) { // Проверяем число, чтобы не было меньше 1
            throw new Exception("Результат вычислений с римскими числами не может быть меньше 1");
        }

        // Создаем линкМапу с римскими числами и их арабскими аналогами, чтобы значения шли по порядку
        LinkedHashMap<String, Integer> romanNumerals = new LinkedHashMap<>();
        romanNumerals.put("C", 100);
        romanNumerals.put("XC", 90);
        romanNumerals.put("L", 50);
        romanNumerals.put("XL", 40);
        romanNumerals.put("X", 10);
        romanNumerals.put("IX", 9);
        romanNumerals.put("V", 5);
        romanNumerals.put("IV", 4);
        romanNumerals.put("I", 1);
        StringBuilder result = new StringBuilder();

        for(Map.Entry<String, Integer> entry : romanNumerals.entrySet())
        {
            int matches = num / entry.getValue(); // Считаем количество повторений символа
            result.append(repeat(entry.getKey(), matches)); // Записываем нужное количество символов
            num = num % entry.getValue(); // Оставляем остаток
        }
        return result.toString();
    }

    public static String repeat(String s, int n)
    {
        if(s == null) { return null; }
        return s.repeat(Math.max(0, n));
    }

    public static Boolean checkRomans(String str) throws Exception {
        if (str.charAt(0) == 45) { // Проверка на отрицательное число
            throw new Exception("Числа не могут быть отрицательные");
        }

        char[] chars = str.toCharArray();
        for (char ch : chars) { // Проверка на допустимые символы
            if ((int) ch != 73 & (int) ch != 88 & (int) ch != 86) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkInt(String str) throws Exception {
        if (str.charAt(0) == 45) { // Проверка на отрицательное число
            throw new Exception("Числа не могут быть отрицательные");
        }

        char[] chars = str.toCharArray();
        for (char ch : chars) { // Проверка на допустимые символы
            if ((int) ch < 48 || (int) ch > 57) {
                return false;
            }
        }
        return true;
    }
}
