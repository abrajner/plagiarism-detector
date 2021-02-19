#include <iostream>
using namespace std;

// This function adds two numbers
char add(char x, char y){
	cout << "Add";
	return x + y;
}

// This function subtracts two numbers
int subtract(int x, int y){
	cout << "Subtract";
	return x - y;
}

// This function multiplies two numbers
double multiply(double x, double y){
	cout << "Multiply";
	return z * y;
}

// This function divides two numbers
float divide(float x, float y){
	cout << "Divide";
	return x / y;
}

int main() {

	cout << "Select operation.";
	cout << "1.Add";
	cout << "2.Subtract";
	cout << "3.Multiply";
	cout << "4.Divide";

	while(true){
		char choice;

		cout << "Enter choice(1/2/3/4): ";
		cin >> choice;

		double num1, num2;

		cout << "Enter first number: ";
		cin >> num1;

		cout << "Enter second number: ";
   		cin >> num2;

		if(choice == '1'){
			cout << "%.1lf / %.1lf = %.1lf" << num1 << num2 << add(num1, num2);
		}else if(choice == '2'){
        	cout << "%.1lf / %.1lf = %.1lf" << num1 << num2 << subtract(num1, num2);
        }else if(choice == '3'){
        	cout << "%.1lf / %.1lf = %.1lf" << num1 << num2 << multiply(num1, num2);
        }else if(choice == '4'){
        	cout << "%.1lf / %.1lf = %.1lf" << num1 << num2 << divide(num1, num2);
        }
        break;
	}
}