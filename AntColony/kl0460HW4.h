#include <iostream>
#include <cstdlib>
#include <stdio.h>
#include <ctime>

using namespace std;

void header(); // Prints out the header
void gameDescription(); // Prints out the game description
void initialize2D(int **matrix, int& numAnts); // Initializes the 2D array
void display2D (int **matrix, int& column); // Displays the matrix
bool checkColonyFound(int row, int col, int& numAnts, int **matrix); // Checks if the colony was a patrol or not

const int SIZE = 10;
