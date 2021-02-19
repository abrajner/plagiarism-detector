#include <stdio.h>

//dzielenie dwoch liczb
float dzielenie(float z, float k){
	printf("Divide");
	return z / k;
}

// dodawanie dwoch liczb
char dodawanie(char z, char k){
	printf("Add");
	return z + k;
}

// mnozenie dwoch liczb
double mnozenie(double z, double k){
	printf("Multiply");
	return z * k;
}

// odejmowanie dwoch liczb
int odejmowanie(int z, int k){
	printf("Subtract");
	return z - k;
}

int main() {

	printf("Select operation.");
	printf("1.Add");
	printf("2.Subtract");
	printf("3.Multiply");
	printf("4.Divide");

	while(true){
		char wybor;

		printf("Enter choice(1/2/3/4): ");
		scanf("%c", &wybor);

		double liczba1, liczba2;

		printf("Enter first number: ");
		scanf("%lf", &liczba1);

		printf("Enter second number: ");
   		scanf("%lf", &liczba2);

		if(wybor == '1'){
			printf("%.1lf / %.1lf = %.1lf", liczba1, liczba2, add(liczba1, liczba2));
		}else if(wybor == '2'){
        	printf("%.1lf / %.1lf = %.1lf", liczba1, liczba2, subtract(liczba1, liczba2));
        }else if(wybor == '3'){
        	printf("%.1lf / %.1lf = %.1lf", liczba1, liczba2, multiply(liczba1, liczba2));
        }else if(wybor == '4'){
        	printf("%.1lf / %.1lf = %.1lf", liczba1, liczba2, divide(liczba1, liczba2));
        }
        break;
	}




}