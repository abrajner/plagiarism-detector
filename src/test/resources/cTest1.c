#include <stdio.h>

// This function adds two numbers
char add(char x, char y){
	printf("Add");
	return x + y;
}

// This function subtracts two numbers
int subtract(int x, int y){
	printf("Subtract");
	return x - y;
}

// This function multiplies two numbers
double multiply(double x, double y){
	printf("Multiply");
	return x * y;
}

// This function divides two numbers
float divide(float x, float y){
	printf("Divide");
	return x / y;
}

int main() {

	printf("Select operation.");
	printf("1.Add");
	printf("2.Subtract");
	printf("3.Multiply");
	printf("4.Divide");

	while(true){
		char choice;

		printf("Enter choice(1/2/3/4): ");
		scanf("%c", &choice);

		double num1, num2;

		printf("Enter first number: ");
		scanf("%lf", &num1);

		printf("Enter second number: ");
   		scanf("%lf", &num2);

		if(choice == '1'){
			printf("%.1lf / %.1lf = %.1lf", num1, num2, add(num1, num2));
		}else if(choice == '2'){
        	printf("%.1lf / %.1lf = %.1lf", num1, num2, subtract(num1, num2));
        }else if(choice == '3'){
        	printf("%.1lf / %.1lf = %.1lf", num1, num2, multiply(num1, num2));
        }else if(choice == '4'){
        	printf("%.1lf / %.1lf = %.1lf", num1, num2, divide(num1, num2));
        }
        break;
	}




}