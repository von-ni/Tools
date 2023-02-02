package src.test.java;

import java.util.Scanner;
import static java.lang.Math.pow;

public class FiguresToWordsCNY {

    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a positive amount");
//        int moneyInt = sc.nextInt();
//        int moneyDec = sc.nextInt();
        double figures = sc.nextDouble();
        int moneyInt = (int)figures / 1;
        int moneyDec = (int)(figures *100) % 100;


        // Integer Part
        String moneyStr = "";
        String[] arr = {"元","万","亿"};

        int count = 0;
        while(true){
            int digits = moneyInt % 10000;
            moneyInt = moneyInt / 10000;
            String words = getDigitsWord(digits);
            if (words.length() == 1) {
                if (count == 0) {
                    if (moneyInt != 0) moneyStr = arr[count] + moneyStr;
                    else {
                        System.out.println("零圆");
                        break;
                    }
                }
                count++;
                continue;
            }
            moneyStr = words + arr[count++]+ moneyStr;
            if (moneyInt == 0) break;
        }

        String s = moneyStr.charAt(0) + "";
        moneyStr = s.equals(getWord(0)) ? moneyStr.substring(1) : moneyStr;

        System.out.print(moneyStr);

        // Decimal Part
        String[] arr1 = {"角","分"};
        if (moneyDec == 0){
            System.out.print("整");
        } else {
            int num1 = moneyDec / 10;
            int num2 = moneyDec % 10;
            if (num1 == 0) {
                System.out.printf("%s分", getWord(num2));
            } else if (num2 == 0) {
                System.out.printf("%s角",getWord(num1));
            } else{
                System.out.printf("%s角%s分",getWord(num1), getWord(num2));
            }
        }
    }
    public static String getDigitsWord(int num) {
        String[] arr = {"仟","佰","拾"};
        String res = "";
        boolean flag = false;
        for (int i = 3; i >= 0; i--) {
            int current = num / (int) pow(10, i);
            num = num % (int) pow(10, i);
            if (current == 0){
                if (!flag && i != 0) {
                    res = res + getWord(current);
                    flag = true;
                }
                continue;
            }
            if (i == 0){
                res = res + getWord(current);
                break;
            }
            res = res + getWord(current) + arr[3-i];
            flag = false;
        }
        String s = res.charAt(res.length()-1) + "";
        res = s.equals(getWord(0)) ? res.substring(0, res.length()-1) : res;
        return res;
    }
    public static String getWord(int num) {
        String[] arr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        return arr[num];
    }
}
