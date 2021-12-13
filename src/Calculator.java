import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        CalculatorMath calk = new CalculatorMath();

        calk.showResult();

    }

}

class CalculatorMath {

    Scanner sc = new Scanner(System.in);
    String inputStr = sc.nextLine();
    char[] inputChar = new char[inputStr.length()];


    // Перевод строки в char возврат массива Char
    public char[] convertToChar(String inputString) {

        for (int i = 0; i < inputStr.length(); i++) {
            inputChar[i] = inputStr.charAt(i);
        }
        return inputChar;
    }


    // Поиск знака и возврат его положения и факт знака
    public int[] findSign(String inputString) {
        int[] sign = new int[2];
        char[] znak = {'+', '-', '/', '*'};
        for (int i = 0; i < znak.length; i++) {
            for (int j = 1; j < convertToChar(inputStr).length; j++) {
                if (znak[i] == convertToChar(inputStr)[j]) {
                    sign[0] = i; // z - сам знак +-/*
                    sign[1] = j; // l - порядок знака
                }
            }
        }
        return sign;
    }

    // Возврат подстрок
    public String[] substrings(String inputString) {
        String[] subString = new String[2];
        subString[0] = inputStr.substring(0, findSign(inputString)[1]);
        subString[1] = inputStr.substring((findSign(inputString)[1] + 1), inputString.length());
        return subString;
    }

    //Положение чисел до мат. знака и после
    public int[] rimskie(String[] substrings) {

        int [] rimsk = new int[2];

        String rimskie1 = "I,II,III,IV,V,VI,VII,VIII,IX,X";
        String[] rimskie1Split = rimskie1.split(",");

        String substringBegin = substrings(inputStr)[0];
        String substringEnd = substrings(inputStr)[1];

        for (int i = 0; i < rimskie1Split.length; i++) {
            for (int j = 0; j < rimskie1Split.length; j++) {
                if ((substringBegin.equals(rimskie1Split[i])) & (substringEnd.equals(rimskie1Split[j]))) {
                    rimsk[0] = i; // Число до знака
                    rimsk[1] = j; // Число после знака
                }
            }
        }
        return rimsk;
    }

    // Результат математических операций с римскими числами.
    public String[] rimskieResult (int[] rimskie) {

        String rimskie1 = "I,II,III,IV,V,VI,VII,VIII,IX,X";
        String[] rimskie1Split = rimskie1.split(",");

        String rimskie2 = "X,XX,XXX,XL,L,LX,LXX,LXXX,XC,C";
        String[] rimskie2Split = rimskie2.split(",");

        String[] result = new String[1];

        // Мат. операция и вывод римских цифр
        int multiplication = ((rimskie(substrings(inputStr))[0]+1) * (rimskie(substrings(inputStr))[1])+1);
        int intPartMult = multiplication / 10;
        int semPartMult = multiplication % 10;
        int intPart = (rimskie(substrings(inputStr))[0] + rimskie(substrings(inputStr))[1]) / 10;
        int semPart = (rimskie(substrings(inputStr))[0] + rimskie(substrings(inputStr))[1]) % 10;

        for (int i = 0; i < rimskie1Split.length; i++) {
            for (int j = 0; j < rimskie1Split.length; j++) {
                if ((substrings(inputStr)[0].equals(rimskie1Split[i])) && (substrings(inputStr)[1].equals(rimskie1Split[j]))) {

                    // "+"
                    if (findSign(inputStr)[0] == 0) {
                        if ((rimskie(substrings(inputStr))[0] + rimskie(substrings(inputStr))[1]) > 10) {
                            result[0] = (rimskie1Split[9] + rimskie1Split[semPart + 1]);
                            return result;
                        } else if ((rimskie(substrings(inputStr))[0] + rimskie(substrings(inputStr))[1]) <= 10) {
                            result[0] = (rimskie1Split[(rimskie(substrings(inputStr))[0] + rimskie(substrings(inputStr))[1] + 1)]);
                            return result;
                        }
                    }

                    // "-"
                    else if (findSign(inputStr)[0] == 1) {
                        result[0] = (rimskie1Split[(rimskie(substrings(inputStr))[0] - rimskie(substrings(inputStr))[1] - 1)]);
                        return result;
                    }

                    // "/"
                    else if (findSign(inputStr)[0] == 2) {
                        result[0] = (rimskie1Split[(rimskie(substrings(inputStr))[0] - 2) / (rimskie(substrings(inputStr))[1] + 1)]);
                        return result;
                    }

                    // Умножение не работает, надо узнать в чем вопрос

                    // "*"
                    else if (findSign(inputStr)[0] == 3) {
                        if (multiplication <= 10) {
                            result[0] = rimskie1Split[((rimskie(substrings(inputStr))[0]) * (rimskie(substrings(inputStr))[1])) - 1];
                            return result;
                        } else {
                            result[0] = rimskie2Split[(intPartMult)] + rimskie1Split[(semPartMult)];

                            return result;
                        }

                    }


                }
                else {
                    result [0] = "Недопустимое выражение";
                }
            }
        }
        return result;
    }

    // Результат математических операций с Арабскими числами
    public int arabskieResult(String[] substrings) {

        int result = 0;

                    if (findSign(inputStr)[0] == 0) {
                        result = (Integer.valueOf(substrings(inputStr)[0]) + Integer.valueOf(substrings(inputStr)[1]));
                        return result;
                    } else if (findSign(inputStr)[0] == 1) {
                        result = (Integer.valueOf(substrings(inputStr)[0]) - Integer.valueOf(substrings(inputStr)[1]));
                        return result;
                    } else if (findSign(inputStr)[0] == 2) {
                        result = (Integer.valueOf(substrings(inputStr)[0]) / Integer.valueOf(substrings(inputStr)[1]));
                        return result;
                    } else if (findSign(inputStr)[0] == 3) {
                        result = (Integer.valueOf(substrings(inputStr)[0]) * Integer.valueOf(substrings(inputStr)[1]));

                    }

        return result;
        }



       public void showResult ()  {

            String rimskie1 = "I,II,III,IV,V,VI,VII,VIII,IX,X";
           String[] rimskie1Split = rimskie1.split(",");

           String arabskie = "-10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10";
           String [] arabskieSplit = arabskie.split(",");

            if (findSign(inputStr)[1]==0) {
                System.out.print("Отсутствует знак");
            }
            else if (findSign(inputStr)[1]!=0) {
                for (int i = 0; i < rimskie1Split.length; i++) {
                    for (int j = 0; j < rimskie1Split.length; j++) {
                        if ((substrings(inputStr)[0].equals(rimskie1Split[i])) && (substrings(inputStr)[1].equals(rimskie1Split[j]))) {
                            System.out.println(rimskieResult(rimskie(substrings(inputStr)))[0]);
                        }
                        else if (findSign(inputStr)[1]!=0) {
                            for (int q = 0; q < rimskie1Split.length; q++) {
                                for (int z = 0; z < rimskie1Split.length; z++) {
                                    if ((substrings(inputStr)[0].equals(arabskieSplit[q])) && (substrings(inputStr)[1].equals(arabskieSplit[z]))) {
                                        System.out.println(arabskieResult(substrings(inputStr)));
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("Недопустимое выражение");
                        }
                    }
                }
            }

            //System.out.println(rimskieResult(rimskie(substrings(inputStr)))[0]);
            // System.out.println(arabskieResult(substrings(inputStr)));
    }

}




