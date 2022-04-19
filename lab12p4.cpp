#include <iostream>
#include <string.h>
#include <string>
#include <vector>

using namespace std;

class Employee
{
	private:
		string name;; // Employee name
		long salary; // Employee salary

	public:
		Employee(string newName, long newSal)
		{
			name = newName;
			salary = newSal;
		}

		virtual void show_info()
		{
			cout << "Employee Name: " << getName() << endl;
		}

		string getName() {return name;}

		long getSalary() {return salary;}
};

class Manager:public Employee
{
	public:
	Manager(string manager, long salary, string newDegree):Employee(manager, salary)
	{
		degree = newDegree;
	}

	void show_info()
	{
		cout << "Manager Name: " << getName() << endl; // Calling the parent class employee
		cout << "Manager Salary: " << getSalary() << endl;
		cout << "Manager Degree: " << degree << endl << endl;
	}

	protected:
		string degree; // The degree of the manager
};


class Worker:public Employee
{
        public:
        Worker(string worker, long salary, string newPosition):Employee(worker, salary)
        {
                position = newPosition;
        }

        void show_info()
        {
                cout << "Worker Name: " << getName() << endl; // Calling the parent class employee
		cout << "Worker Salary: " << getSalary() << endl;
		cout << "Worker Position " << position << endl << endl;
        }

        protected:
                string position; // The position of the worker and all it's child classes
};

class Officer:public Worker
{
	public:
	Officer(string officer, long salary, string newPosition):Worker(officer, salary, newPosition){}

	void show_info()
        {
                cout << "Officer Name: " << getName() << endl;
		cout << "Officer Salary: " << getSalary() << endl;
                cout << "Officer Position " << position << endl << endl;
        }
};

class Technician:public Worker
{
	public:
		Technician(string technician, long salary, string newPosition):Worker(technician, salary, newPosition){}

		void show_info()
        	{
                	cout << "Technician Name: " << getName() << endl;
			cout << "Technician Salary: " << getSalary() << endl;
                	cout << "Technician Position " << position << endl << endl;
        	}
};

int main()
{
	cout << "\nInheritance and Polymorphism:\n" << endl;

	int numEmp; // The number of employees
	int choice; // The user's menu choice
	int size = 0; // The size of the array

	cout << "How many employees do you want to create" << endl;
	cin >> numEmp;

	vector <Employee*> employeeList; // Holds all of the employees

	while(choice != 6)
	{
		cout << "MENU (Pick the number choice)" << endl;
		cout << "1) Create Manager" << endl;
		cout << "2) Create Officer" << endl;
		cout << "3) Create Technician" << endl;
		cout << "4) Print All Employees" << endl;
		cout << "5) Search For Employee By Name" << endl;
		cout << "6) Exit" << endl;
		cin >> choice;
		cin.ignore();

		if (choice == 1) // If the user wants to create a manager
		{
			long salary; // Holds the manager's salary
			string name; // Holds the manager's name
			string degree;; // Holds the manager's degree

			cout << "CREATE MANAGER" << endl << endl;
			cout << "What is the name of the manager?" << endl;
			getline(cin, name); // Holds the manager's name

			cout << "What is the salary of the manager?" << endl;
			cin >> salary;
			cin.ignore();

			cout << "What is the degree of the manager?" << endl;
			getline(cin, degree); // Holds the manager's degree

			employeeList.push_back(new Manager(name, salary, degree)); // Adding an object to the collection

			cout << endl;
			size++;
		}

		if (choice == 2) // If the user wants to create an officer
		{
			string name;
			long salary;
			string position;

			cout << "CREATE OFFICER" << endl << endl;
			cout << "What is the name of the officer?" << endl;
			getline(cin, name);

			cout << "What is the salary of the officer?" << endl;
			cin >> salary;
                        cin.ignore();

			cout << "What is the position of the officer?" << endl;
			getline(cin, position);

			employeeList.push_back(new Officer(name, salary, position)); // Adding an object to the collection

			cout << endl;
			size++;
		}

		if (choice == 3) // If the user wants to create a technician
		{
			string name;
			long salary;
			string position;

			cout << "CREATE TECHNICIAN" << endl << endl;
			cout << "What is the name of the technician?" << endl;
                        getline(cin, name);

                        cout << "What is the salary of the technician?" << endl;
                        cin >> salary;
                        cin.ignore();

                        cout << "What is the position of the technician?" << endl;
                        getline(cin, position);

                        employeeList.push_back(new Technician(name, salary, position)); // Adding$

			cout << endl;
                        size++;
		}

		if (choice == 4) // If the user wants to print all of the employees
		{
			cout << "PRINT ALL EMPLOYEES" << endl << endl;

			for (int i = 0; i < size; i++) // Prints all the employees
		        {
                		employeeList.at(i) -> show_info();
        		}
		}

		if (choice == 5) // If the user wants to search for a employee by the name
		{
			string name;

			cout << "SEARCH FOR EMPLOYEE" << endl << endl;

			cout << "Enter the name of the employee" << endl;
			getline(cin, name);

			for (int i = 0; i < size; i++)
			{
				if (employeeList.at(i) -> getName() == name)
				{
					employeeList.at(i) -> show_info();
				}
			}

			cout << endl;
		}

		if (choice == 6) // If the user wants to exit the program
		{
			for (auto i = 0; i < employeeList.size(); i++) // Cleaning up memory
			{
				delete employeeList[i];
			}
			employeeList.clear();

			return 0;
		}
	}

	return 0;
}

