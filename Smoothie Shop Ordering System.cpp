#include <iostream>
#include <iomanip>
using namespace std;

// main function
int main()
{
	// input
		// variables
		int smoothieType;	// variable to hold the menu choice of the type of smoothie
		int size;			// variable to hold the menu choice of the size of smoothie
		string name;		// variable to hold the customer name
		double subtotal;	// variable to hold the calculated subtotal of the smoothie order
		double salesTax;	// variable to hold the calculated sales tax of the smoothie order
		double total;		// variable to hold the calculated total of the order
		
		// constants
		const double BANANA = 0.62;		// banana smoothies cost $0.62 per ounce
		const double STRAWBERRY = 0.60; // strawberry smoothies cost $0.60 per ounce
		const double MANGO = 0.48;		// mango smoothies cost $0.48 per ounce
		const double BLUEBERRY = 0.57;	// blueberry smoothies cost $0.57 per ounce
		const double SALES_TAX = 0.045; // 4.5% sales tax rate
		
		// display the smoothie type menu
		cout << "Welcome to the Smoothest Smoothie Shop \n\n";
		cout << "\t1 - Banana ($0.62 / oz) \n";
		cout << "\t2 - Strawberry ($0.60 / oz) \n";
		cout << "\t3 - Mango ($0.48 / oz) \n";
		cout << "\t4 - Blueberry ($0.57 / oz) \n\n";
		cout << "Enter choice of smoothie: ";
		cin >> smoothieType;
		
		// validate the input of the smoothie type
		if(smoothieType < 1 or smoothieType > 4)
		{
			// display an error and terminate the program
			cout << "\nERROR: INVALID MENU CHOICE. PROGRAM WILL TERMINATE. \n";
			return 0;
		}
		
		// display the size menu
		cout << "\nChoose your smoothie size: \n";
		cout << "\t1 - Small (20 oz) \n";
		cout << "\t2 - Medium (32 oz) \n";
		cout << "\t3 - Large (40 oz) \n\n";
		cout << "Enter choice of size: ";
		cin >> size;
		
		// validate the input of the smoothie size
		if(size < 1 or size > 3)
		{
			// display an error and terminate the program
			cout << "\nERROR: INVALID MENU CHOICE. PROGRAM WILL TERMINATE. \n";
			return 0;
		}
		
		// clear the buffer
		cin.ignore();
		
		// get the input of the customer's name
		cout << "\nEnter the name of the customer: ";
		getline(cin, name);
	
	// processing
		if(smoothieType == 1)
		{
			// banana smoothie
			if(size == 1)
				subtotal = BANANA * 20.0; // small (20 oz)
			else if(size == 2)
				subtotal = BANANA * 32.0; // medium (32 oz)
			else
				subtotal = BANANA * 40.0; // large (40 oz)
		}
		else if(smoothieType == 2)
		{
			// strawberry smoothie
			if(size == 1)
				subtotal = STRAWBERRY * 20.0; // small (20 oz)
			else if(size == 2)
				subtotal = STRAWBERRY * 32.0; // medium (32 oz)
			else
				subtotal = STRAWBERRY * 40.0; // large (40 oz)
		}
		else if(smoothieType == 3)
		{
			// mango smoothie
			if(size == 1)
				subtotal = MANGO * 20.0; // small (20 oz)
			else if(size == 2)
				subtotal = MANGO * 32.0; // medium (32 oz)
			else
				subtotal = MANGO * 40.0; // large (40 oz)
		}
		else 
		{
			// blueberry smoothie
			if(size == 1)
				subtotal = BLUEBERRY * 20.0; // small (20 oz)
			else if(size == 2)
				subtotal = BLUEBERRY * 32.0; // medium (32 oz)
			else
				subtotal = BLUEBERRY * 40.0; // large (40 oz)
		}
		
		// calculate the sales tax
		salesTax = subtotal * SALES_TAX;
		
		// calculate the total
		total = subtotal + salesTax;
	
	// output
	// format to display 2 decimals
		cout << setprecision(2) << showpoint << fixed;
		cout << endl << name << endl;
		cout << "Subtotal: $" << subtotal << endl;
		cout << "Sales Tax: $" << salesTax << endl;
		cout << "Total: $" << total << endl;
}
