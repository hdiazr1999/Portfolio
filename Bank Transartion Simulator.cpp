#include <iostream>
#include <string>
#include <cstdlib> 
#include <ctime>

using namespace std;

int getNumAccounts();
string generateTransactionType();
long generateTransactionAmount();
void displayStats(string[], long[], int);

int main()
{
    // Seed random number generator
    srand(time(0));
    
    // Get number of bank accounts from user
    int numAccounts = getNumAccounts();
    
    // Declare and populate arrays based on user input
    string transactionTypes[numAccounts];
    long transactionAmounts[numAccounts];
    
    for (int i = 0; i < numAccounts; i++)
    {
        transactionTypes[i] = generateTransactionType();
        transactionAmounts[i] = generateTransactionAmount();
    }
    
    // Display Stats
    displayStats(transactionTypes, transactionAmounts, numAccounts);
    
    return 0;
}

// Allows user to specify number of bank accounts in simulation
int getNumAccounts()
{
    int numAccounts;
    cout << "How many bank accounts will be in the simulation (5-100): ";
    cin >> numAccounts;
    
    while (numAccounts < 5 || numAccounts > 100)
    {
        cout << "Please enter a valid number of accounts (5 - 100): ";
        cin >> numAccounts;
    }
    
    return numAccounts;
}

// Generates a random transaction type for the account
string generateTransactionType()
{
    int type = rand() % 2 + 1;
    
    if (type == 1)
    {
        return "deposit";
    }
    else
    {
        return "withdrawal";
    }
}

// Generates a random transaction amount between $5 and $10000
long generateTransactionAmount()
{
    long amount = rand() % 9996 + 5;
    return amount;
}

// Displays the transaction types and amounts along with counts, averages, and totals
void displayStats(string types[], long amounts[], int numAccounts)
{
    int totalTransactions = 0;
    int totalDeposits = 0;
    int totalWithdrawals = 0;
    double avgDeposits = 0;
    double avgWithdrawals = 0;
    double avgTransactions = 0;
    long totalDepositAmount = 0;
    long totalWithdrawalAmount = 0;
    long totalTransactionAmount = 0;
    
    cout << endl;
    cout << "Simulation Results:" << endl;
    cout << "------------------" << endl;
    cout << "Transaction # | Transaction Type | Transaction Amount" << endl;
    
    // Displays transaction number, type, and amount
    for (int i = 0; i < numAccounts; i++)
    {
        cout << (i + 1) << "              " << types[i] << "            $" << amounts[i] << endl;
    }
    
    // Calculates total transactions, deposits, and withdrawals
    for (int i = 0; i < numAccounts; i++)
    {
        totalTransactions++;
        
        if (types[i] == "deposit")
        {
            totalDeposits++;
            totalDepositAmount += amounts[i];
        }
        else
        {
            totalWithdrawals++;
            totalWithdrawalAmount += amounts[i];
        }
    }
    
    // Calculates average deposits, withdrawals, and transactions
    avgDeposits = totalDepositAmount / totalDeposits;
    avgWithdrawals = totalWithdrawalAmount / totalWithdrawals;
    avgTransactions = (totalDepositAmount + totalWithdrawalAmount) / totalTransactions;
    
    // Calculates total amounts
    totalTransactionAmount = totalDepositAmount + totalWithdrawalAmount;
    
    // Displays stats
    cout << endl;
    cout << "Total Transactions: " << totalTransactions << endl;
    cout << "Total Deposits: " << totalDeposits << endl;
    cout << "Total Withdrawals: " << totalWithdrawals << endl;
    cout << "Average Amount Per Deposit: $" << avgDeposits << endl;
    cout << "Average Amount Per Withdrawal: $" << avgWithdrawals << endl;
    cout << "Average Amount Per Transaction: $" << avgTransactions << endl;
    cout << "Total Amount Deposited: $" << totalDepositAmount << endl;
    cout << "Total Amount Withdrawn: $" << totalWithdrawalAmount << endl;
    cout << "Total Amount Transacted: $" << totalTransactionAmount << endl;
}
