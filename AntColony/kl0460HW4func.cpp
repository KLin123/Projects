#include "kl0460HW4.h"

void header()
{
        cout << " +----------------------------------------------+" << endl;
        cout << " |        Computer Science and Engineering      |" << endl;
        cout << " |         CSCE 1030 - Computer Science I       |" << endl;
        cout << " |       Karen Lin kl0460 kl0460@my.unt.edu     |" << endl;
        cout << " +----------------------------------------------+" << endl;
}

void gameDescription()
{
        cout << "               Welcome to Anthony's Battle!" << endl;
        cout << "----------------------------------------------------------" << endl;
        cout << "Anthony the ant has decided that he wants to take over all" << endl;
        cout << "the adjacent ant colonies with his army of ants from colo-" << endl;
        cout << "ny 'A'. To do this, Anthony's army will attempt to capture" << endl;
        cout << "ants from columns 'B' through 'J', with at least 1 ant re-" << endl;
        cout << "maining when the column 'J' ant colony is found. Each col-" << endl;
        cout << "umn's ant colony is identified with a 0 in one of the rows" << endl;
        cout << "for that column, while patrols are represented by integers" << endl;
        cout << "between 1 and 10 for the number of ants in that particular" << endl;
        cout << "patrol. On the way to column 'J' if Anthony's army encoun-" << endl;
        cout << "ters a patrol, the number in the patrol is subtracted from" << endl;
        cout << "Anthony's army total. Once his army finds the colony, how-" << endl;
        cout << "ever, any patrols not confronted will be added to his army" << endl;
        cout << "total. If Anthony makes really good decisions, then he can" << endl;
        cout << "take over all of the adjacent ant colonies; otherwise, his" << endl;
        cout << "army will be defeated!" << endl;
        cout << "----------------------------------------------------------" << endl << endl;
}

void initialize2D (int **matrix, int& numAnts)
{
        srand(time(NULL));

	int randNum = (rand() % 10) + 1;

        for (int row = 0; row < SIZE; row++)
        {
			for (int col = 0; col < SIZE; col++)
			{
				*(matrix[row] + col) = (rand() % 10) + 1; // Initialize the matrix with random numbers 1-10
			}
        }

	for (int col = 0; col < SIZE; col++)
	{
		matrix[(rand() % 10)][col] = 0; // Replaces the number in a random row with zero
	}

	for (int i = 0; i < SIZE; i++) // Adding up all the numbers in the 1st column for the starting amount
	{
		numAnts += matrix[i][0];
	}
}

void display2D (int **matrix, int& column)
{
	int axis = 0; // Labels the y-axis

	cout << "   " << "A" << "  " << "B" << "  " << "C" << "  " << "D" << "  " << "E" << "  " << "F" << "  " << "G" << "  " <<
         "H" << "  " << "I" << "  " << "J" << "  " << endl; // Labeling the x-axis

	cout << " +-------------------------------+" << endl;

	for (int i = 0; i < SIZE; i++)
	{
	    	cout << axis++ << "|" << " "; // Labeling the y-axis

		for (int j = 0; j < SIZE; j++)
		{
			if (j == 0)
			{
				if (matrix[i][0] < 10) // If the number occupying this row and column is less than 10
        			{
          				cout << "0" << matrix[i][0] << " ";
        			}

				else
        			{
            				cout << matrix[i][0] << " ";
        			}
			}

			else if (j < column) // If j is less than the current column
			{
				if (matrix[i][j] == 'X') // If the patrol has been visited
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

			else if (j == column) // If j is equal to the current column
			{
				if (matrix[i][j] == 'X') // If the patrol has been visited
				{
					cout << "XX" << " ";
				}
				else
				{
					cout << "**" << " ";
				}
			}

			else
			{
				cout << "**" << " ";
			}
		}

		cout << "|";
        	cout << endl;
	}

	cout << " +-------------------------------+" << endl << endl;
}

bool checkColonyFound(int row, int col, int& numAnts, int **matrix)
{
	if (matrix[row][col] == 0) // If the value is zero
	{
		return true;
	}

	else if (matrix[row][col] == 'X')
	{
		cout << "Anthony has already visited this patrol" << endl << endl;
		return false;
	}

	else // If Anthony visits a new patrol whose value isn't zero
	{
		numAnts = numAnts - matrix[row][col];
		matrix[row][col] = 'X';
		cout << "Anthony's army suffered casualties and now has only " << numAnts << " ants left" << endl << endl;

		return false;
	}
}
