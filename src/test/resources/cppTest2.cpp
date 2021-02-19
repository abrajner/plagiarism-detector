#include <iostream>
using namespace std;

//dzielenie dwoch liczb
float dzielenie(float z, float k){
	cout << "Divide";
	return z / k;
}

// dodawanie dwoch liczb
char dodawanie(char z, char k){
	cout << "Add";
	return z + k;
}

// mnozenie dwoch liczb
double mnozenie(double z, double k){
	cout << "Multiply";
	return z * k;
}

// odejmowanie dwoch liczb
int odejmowanie(int z, int k){
	cout << "Subtract";
	return z - k;
}

int main() {

	cout << "Select operation.";
	cout << "1.Add";
	cout << "2.Subtract";
	cout << "3.Multiply";
	cout << "4.Divide";

	while(true){
		char wybor;

		cout << "Enter choice(1/2/3/4): ";
		cin >> wybor;

		double liczba1, liczba2;

		cout << "Enter first number: ";
		cin >> liczba1;

		cout << "Enter second number: ";
   		cin >> liczba2;

		if(wybor == '1'){
			cout << "%.1lf / %.1lf = %.1lf" << liczba1 << liczba2 << dodawanie(liczba1, liczba2);
		}else if(wybor == '2'){
        	cout << "%.1lf / %.1lf = %.1lf" << liczba1 << liczba2 << odejmowanie(liczba1, liczba2);
        }else if(wybor == '3'){
        	cout << "%.1lf / %.1lf = %.1lf" << liczba1 << liczba2 << mnozenie(liczba1, liczba2);
        }else if(wybor == '4'){
        	cout << "%.1lf / %.1lf = %.1lf" << liczba1 << liczba2 << dzielenie(liczba1, liczba2);
        }
        break;
	}
}