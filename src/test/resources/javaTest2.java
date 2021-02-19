import java.util.Arrays;
import java.util.Scanner;

public class Main {

    char add(char x, char y){
        System.out.println("Add");
        return x + y;
    }

    int subtract(int x, int y){
        System.out.println("Subtract");
        return x - y;
    }

    double multiply(double x, double y){
        System.out.println("Multiply");
        return x * y;
    }

    float divide(float x, float y){
        System.out.println("Divide");
        return x / y;
    }

    public static void main(String[] args) {
        System.out.println("Select operation.");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");

        Scanner reader = new Scanner(System.in);

        while (true){
            System.out.println("Enter choice(1/2/3/4): ");
            Character choice = reader.next().charAt(0);

            if(Arrays.asList('1', '2', '3', '4').contains(choice)){
                System.out.println("Enter first number: ");
                System.out.println("Enter second number: ");

                double num1 = reader.nextDouble();
                double num2 = reader.nextDouble();

                if('1'.equals(choice)){
                    System.out.println(num1 + "+" + num2 + "=" + dodawanie(num1, num2));
                } else if('2'.equals(choice)){
                    System.out.println(num1 + "-" + num2 + "=" + odejmowanie(num1, num2));
                } else if('3'.equals(choice)){
                    System.out.println(num1 + "*" + num2 + "=" + mnożenie(num1, num2));
                } else if('4'.equals(choice)){
                    System.out.println(num1 + "/" + num2 + "=" + dzielenie(num1, num2));
                }
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}