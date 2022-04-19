#include "kl0460HW4.h"

int main()
{
	header(); // Calls the function to print out the header
	gameDescription(); // Calls the function to print out the game description

	int rowChoice; // Holds the row the user wants to attack
	char colNum = 'B'; // Keeps track of the column in letter form
	int column = 1; // Keeps track of the column in number form
	int numAnts = 0; // The number of ants the player has
	int axis = 0; // Labels the y-axis
	int capture = 0; // Stores the amount of ants that got captured

	int **matrix = new int *[SIZE]; // Declare the 2D array
	for (int i = 0; i < SIZE; i++)
	{
		matrix[i] = new int[SIZE];
	}

	initialize2D(matrix, numAnts); // Calls the function to initialize the 2D array

	display2D(matrix, column);

	cout << "The number of ants in Anthony's army is " << numAnts << endl << endl;

	do // This plays the game
	{
		if (colNum == 'K')// If Anthony gets through all the columns
                {
                        cout << "Congratulations!!! Anthony has taken over all the colonies with " << numAnts << " ants remaining" << endl << endl;
                        break;
                }

		cout << "Please enter the row position in column " << colNum << " you want to attack: ";
		cin >> rowChoice;
		cout << endl;

		while ((rowChoice < 0) || (rowChoice > 9)) // While the user input is out of range
                {
                	cout << "Invalid input. Please enter the row you want to attack: ";
			cin >> rowChoice;
			cout << endl;
                }

		if (checkColonyFound(rowChoice, column, numAnts, matrix)) // If the Anthony finds the ant colony
		{
			capture = 0;

			cout << "You found the ant colony" << endl << endl;

			for (int i = 0; i < SIZE; i++)
                        {
                                if (matrix[i][column] != 'X') // If the patrol has not been visited before
                                {
					capture += matrix[i][column]; // How many ants got captured
                                        numAnts += matrix[i][column]; // Adds the leftover ants to Anthony's army
                                }
                        }

			column++; // Increases the column number

			display2D(matrix, column);

	                cout << "Anthony's army found the ant colony, captured " << capture << " ants" << endl << endl;

			colNum++;

		}

		else // If anthony doesn't find the ant colony
		{
			display2D(matrix, column);
		}

	} while (((rowChoice >= 0) || (rowChoice <= 9)) && (numAnts > 0)); // While rowChoice is between 0-9 (inclusively) and Anthony has ants in his army

	if (numAnts <= 0)// The player lost the game
	{
		cout << "Uh-oh, Anthony's army has been defeated before taking over all of the ant colonies" << endl << endl;

		cout << "   " << "A" << "  " << "B" << "  " << "C" << "  " << "D" << "  " << "E" << "  " << "F" << "  " << "G" << "  " <<
	         "H" << "  " << "I" << "  " << "J" << "  " << endl; // Labeling the x-axis

        	cout << " +-------------------------------+" << endl;

		axis = 0;

		for (int i = 0; i < SIZE; i++)
		{
			cout << axis++ << "|" << " "; // Labeling the y-axis

			for (int j = 0; j < SIZE; j++)
			{
				if (matrix[i][j] == 'X')
				{
					cout << "XX" << " ";
				}

				else
				{
					if (matrix[i][j] < 10) // If the number occupying this row and column is less than 10
         	      			{
                    				cout << "0" << matrix[i][j] << " ";
                			}

		                	else
                			{
                    				cout << matrix[i][j] << " ";
                			}
				}
			}

			cout << "|";
			cout << endl;
		}

		cout << " +-------------------------------+" << endl;
	}

	for (int i = 0; i < SIZE; i++)// Deletes the 2D array
	{
		delete[] matrix[i];
	}
	delete matrix;

	return 0;
}
