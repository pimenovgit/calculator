package calculator;

import java.util.List;
import java.util.Arrays;
import java.io.IOException;

public class Main {
    public static String calc(String input) {
        String[] str = input.split(" ");
        LengthStr lr = new LengthStr();
        String resultCalc = null;
        lr.lengthStr(str);
        String symbol = str[1];
        String num_1 = str[0].trim();
        String num_2 = str[2].trim();
        NumFormat nf = new NumFormat();
        return nf.numFormatCheck(num_1, num_2, symbol);
    }
}
class RomanNumeral {
    public static int romantoArabic(String num) {
        String[] roman_num = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int sum = 1;
        if ((Arrays.asList(roman_num).indexOf(num) + 1) >= 0) {
            sum += Arrays.asList(roman_num).indexOf(num);
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. неверынй формат ввода, допустимый значения находятся в интервале от [1-10] или [I-X]");
                System.exit(0);
            }
        }
        int i = sum;
        return i;
    }

    public static String arabictoRoman(int num) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return (roman[num]);

    }
}

class ArabNumeral {
    public static int arabic(String num_1) {
        String[] arab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // Массив возможных значений
        List arabList= Arrays.asList(arab);
        int arabNum = 0;
        if (arabList.indexOf(num_1)>=0) {
            arabNum = Integer.parseInt(num_1);
        } else if (arabList.indexOf(num_1) < 0) {
            arabNum = 404;
        }

        return arabNum;
    }
}


class Result {
    public static int res(int a, int b, String symbol) {
        int finishRes = 0;
        switch (symbol) {    // вывод результата при корректных значения
            case "+":
                finishRes = (a + b);
                break;
            case "-":
                finishRes = (a - b);
                break;
            case "*":
                finishRes = (a * b);
                break;
            case "/":
                finishRes = (a / b);
                break;
            default:
                System.out.println("throws Exception //т.к. некоректный ввод арифметического выражения");
                System.exit(0);
        }
        return finishRes;
    }
}

class LengthStr {
    public static void lengthStr(String[] mass) {
        if (mass.length < 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System.exit(0);
            }
        } else if (mass.length > 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        }
    }
}

class NumFormat {
    public static String numFormatCheck(String firstNum, String secondNum, String symbol) {
        String resultCalc = null;
        ArabNumeral ar = new ArabNumeral();
        int firstTest = ar.arabic(firstNum);
        int secondTest = ar.arabic(secondNum);
        if ((firstTest == 404) && (secondTest != 404)) {
            RomanNumeral rn = new RomanNumeral();
            int firstRom = rn.romantoArabic(firstNum);
            if (firstRom > 0 && firstRom <= 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    System.exit(0);
                }
            }
        } else if ((firstTest != 404) && (secondTest == 404)) {
            RomanNumeral rn = new RomanNumeral();
            int secondRom = rn.romantoArabic(secondNum);
            if (secondRom > 0 && secondRom <= 10) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    System.exit(0);
                }
            }

        } else if ((firstTest != 404) && (secondTest != 404)) {
            Result rt = new Result();
            int arabResult = rt.res(firstTest, secondTest, symbol);
            resultCalc = Integer.toString(arabResult);
        } else {
            RomanNumeral rl = new RomanNumeral();
            int firstRom = rl.romantoArabic(firstNum);
            int SecondRom = rl.romantoArabic(secondNum);
            CheckRomanNum crn = new CheckRomanNum();
            crn.checkRomanNum(firstRom, SecondRom);
            Result rt = new Result();
            int romResult = rt.res(firstRom, SecondRom, symbol);
            resultCalc = rl.arabictoRoman(romResult);
        }
        return resultCalc;
    }
}

class CheckRomanNum {
    public static void checkRomanNum(int numRom1, int numRom2){
        if (numRom2 > numRom1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                System.exit(0);
            }
        }
    }
}

class StartCalc {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            Main mn = new Main();
            String resultCalc = mn.calc(input);
            System.out.println(resultCalc);
        }
    }
}
