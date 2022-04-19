import java.util.Scanner;  // Import the Scanner class
import java.io.File;
import java.io.FileNotFoundException;

public class Printer
{
	public static void main(String[] args) throws FileNotFoundException
	{
		BinaryHeap<OutsidePrintjob> heap = new BinaryHeap<>();

		Scanner in = new Scanner(System.in); // Reads user input

		String fileName; // Holds the input file name
		String name;
		int priority;
		int pages;	
		String type;
		double cost;
		int count = 0; // How many printjobs

		System.out.println("Please enter the input file name (Example: input.txt)");
		fileName = in.nextLine();

		File file = new File(fileName);
		try
		{
			Scanner scan = new Scanner(file); // used to scan information in the file
	
		while(scan.hasNext())
		{
			name = scan.next(); // Scans in the name from the file
			priority = scan.nextInt();
			pages = scan.nextInt();
			type = scan.next();		
			cost = 0.10 * (double)pages;

			// Creating an object for each print job and add it to the priority queue
			OutsidePrintjob job = new OutsidePrintjob(name, priority, pages, type, cost);
			heap.insert(job);

			count++;
		}

			scan.close(); // Close the input file
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}

		for(int i = 0; i < count; i++) // Printing out the jobs and then deleting them
		{
			OutsidePrintjob min = heap.findMin();
	
			if(min.type.equals("O") == true)
			{
				System.out.println(min.name + ": User Priority = " + min.priority + ", Pages = " + min.numPages + ", Cost = " + min.cost);
			}
			else
			{
				System.out.println(min.name + ": User Priority = " + min.priority + ", Pages = " + min.numPages);
			}

			heap.deleteMin();
		}
	}
}
