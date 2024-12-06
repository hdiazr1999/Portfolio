#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

int getNumLoanApplications();
int generateCreditScore();
string generateScoreRating(int score);
string generateStatus(int score);
void analysis(int creditScores[], string scoreRatings[], string loanStatuses[], int numLoans);

int main() {
    srand(time(0));
    int numLoans = getNumLoanApplications();
    int creditScores[numLoans];
    string scoreRatings[numLoans];
    string loanStatuses[numLoans];

    for (int i = 0; i < numLoans; i++) {
        creditScores[i] = generateCreditScore();
        scoreRatings[i] = generateScoreRating(creditScores[i]);
        loanStatuses[i] = generateStatus(creditScores[i]);
    }

    analysis(creditScores, scoreRatings, loanStatuses, numLoans);

    return 0;
}

int getNumLoanApplications() {
    int numLoans;
    do {
        cout << "Enter the number of loan applications (between 50 and 2000): ";
        cin >> numLoans;
    } while (numLoans < 50 || numLoans > 2000);
    return numLoans;
}

int generateCreditScore() {
    return rand() % 601 + 300;
}

string generateScoreRating(int score) {
    if (score >= 300 && score <= 579) {
        return "poor";
    }
    else if (score >= 580 && score <= 669) {
        return "fair";
    }
    else if (score >= 670 && score <= 739) {
        return "good";
    }
    else if (score >= 740 && score <= 799) {
        return "very good";
    }
    else {
        return "exceptional";
    }
}

string generateStatus(int score) {
    if (score >= 630) {
        return "Approved";
    }
    else {
        return "Declined";
    }
}

void analysis(int creditScores[], string scoreRatings[], string loanStatuses[], int numLoans) {
    int total = 0, approved = 0, declined = 0, poor = 0, fair = 0, good = 0, veryGood = 0, exceptional = 0;
    cout << "Loan Application\tCredit Score\tScore Rating\tLoan Status\n";
    for (int i = 0; i < numLoans; i++) {
        cout << i + 1 << "\t\t\t" << creditScores[i] << "\t\t" << scoreRatings[i] << "\t\t" << loanStatuses[i] << endl;
        if (loanStatuses[i] == "Approved") {
            approved++;
        }
        else {
            declined++;
        }
        if (scoreRatings[i] == "poor") {
            poor++;
        }
        else if (scoreRatings[i] == "fair") {
            fair++;
        }
        else if (scoreRatings[i] == "good") {
            good++;
        }
        else if (scoreRatings[i] == "very good") {
            veryGood++;
        }
        else {
            exceptional++;
        }
        total++;
    }
    cout << "Number of loan applications: " << total << endl;
    cout << "Number of approved: "


