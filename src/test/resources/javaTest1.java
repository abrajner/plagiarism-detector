import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //dzielenie dwoch liczb
    float dzielenie(float z, float k){
        System.out.println("Divide");
        return z / k;
    }

    //dodawanie dwoch liczb
    char dodawanie(char z, char k){

        System.out.println("Add");
        return z + k;
    }

    //mnozenie dwoch liczb
    double mnożenie(double z, double k){
        System.out.println("Multiply");
        return z * k;
    }

    //odejmowanie dwoch liczb
    int odejmowanie(int z, int k){
        System.out.println("Subtract");
        return z - k;
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
            char wybor = reader.next().charAt(0);

            if(Arrays.asList('1', '2', '3', '4').contains(wybor)){
                System.out.println("Enter first number: ");
                System.out.println("Enter second number: ");

                double liczba1 = reader.nextDouble();
                double liczba2 = reader.nextDouble();

                if('1'.equals(wybor)){
                    System.out.println(liczba1 + "+" + liczba2 + "=" + dodawanie(liczba1, liczba2));
                } else if('2'.equals(wybor)){
                    System.out.println(liczba1 + "-" + liczba2 + "=" + odejmowanie(liczba1, liczba2));
                } else if('3'.equals(wybor)){
                    System.out.println(liczba1 + "*" + liczba2 + "=" + mnożenie(liczba1, liczba2));
                } else if('4'.equals(wybor)){
                    System.out.println(liczba1 + "/" + liczba2 + "=" + dzielenie(liczba1, liczba2));
                }
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}