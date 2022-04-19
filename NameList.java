import java.util.Scanner;

public class NameList
{
	Node head; // Head of list

	public class Node
	{
		String data;
		Node next;

		public Node(String d) // Constructor to create a new node
		{
			data = d;
			next = null;
		}
	}	

	public void add(String name) // Adds a new name. If letter not already there, add letter
	{
		Node curr = head;
		Node prev = null;
		Node newLetter = new Node(name.substring(0,1).toUpperCase());
		Node newName = new Node(name);

		if(name.length() < 2)
		{
			System.out.println("The length is too short for a name \n");
		}

		// Check if the list is empty. If it is empty, set the head to the letter and head.next to the name
		// If the list is not empty, then look for the letter the name starts with
		// If the letter is there (use find() method), put the name in alphabetical order
		// If the letter is not there (use find() method), add the letter and put in the name
		// BE CAREFUL WITH CASE SENSITIVITY

		if(head == null) // If the list is empty, add the name and the cooresponding letter
		{
			head = newLetter; // Adds the letter
			head.next = newName; // Adds the name

			System.out.println("Added " + newName.data + "\n");
		}
		else
		{
			if(find(name.substring(0,1))) // If letter exists
			{
				while((curr != null) && (name.compareToIgnoreCase(curr.data) > 0))
				{
					prev = curr;
					curr = curr.next;
				}

				prev.next = newName;
				newName.next = curr;
				System.out.println("Added " +  newName.data + "\n");
			}
			else // If letter does not exist
			{
				while((curr != null) && (name.compareToIgnoreCase(curr.data) > 0)) // if name > curr, then keep looking
				{
					prev = curr;
					curr = curr.next;
				}
	
				// The if-else checks if the added name needs to be in the beginning of the list or not
				if(prev != null)
				{
					prev.next = newLetter;
					newLetter.next = newName;
					newName.next = curr;
				}
				else // Modifies the head node
				{
					newName.next = head;
					newLetter.next = newName;
					head = newLetter;					
				}

				System.out.println("Added " +  newName.data + "\n");
			}
		}
	}

	public void remove(String name) // Removes a name. This means we do not have to worry about the head node until we deal with the letter
	{
		Node curr = head; // Holds node to be deleted
		Node prev = null; // Holds the node right before the one to be deleted

		if(head == null)
		{
			System.out.println("There is nothing to remove" + "\n");
		}
		else
		{	
			if(find(name)) // If the name exists
			{	
				// Search for name to be deleted
				while((curr != null) && (name.equalsIgnoreCase(curr.data) == false))
				{
					prev = curr; // The node right before curr.next
					curr = curr.next;
				}
		
				prev.next = curr.next; // Unlink name node from list

				//If prev.next holds the name and it is the only name with that letter, then prev must be the letter

				//if name is the last name for the letter, the letter should also be removed
				// check by seeing if the node right next to the letter is also a letter
				// if the node right next to the letter is also a letter, the letter has no names in it, so delete it

				Node temp = head;
				Node beforeTemp = null;

				while((temp != null) && (temp.data.equalsIgnoreCase(prev.data) == false)) // While we temp variable doesn't equal the letter
				{
					beforeTemp = temp;
					temp = temp.next;
				}

				if(prev.next != null) // Just in case the node that is deleted is at the end of the list
				{
					if((prev.next.data.length() == 1) && (prev.data.length() == 1))
                                	{
						if(beforeTemp != null) // Checks whether we are deleting from the head node or not
						{
                                        		beforeTemp.next = temp.next;
						}
						else
						{
							head = temp.next;
						}
                                	}
				}
				else
				{
                                	if((prev.next == null) && (prev.data.length() == 1)) // Takes into account the node at the very end of the list
                                	{
						beforeTemp.next = null;
                                	}
				}

				System.out.println("Successfully removed " + name + "\n");
			}
			else
			{
				System.out.println("That name is not in the list \n");
			}
		}
	}

	public void removeLetter(String letter) // Removes a letter and all names for that letter
	{
		if(head == null)
		{
			System.out.println("There is nothing to remove \n");
		}
		else
		{
			if(find(letter))
			{
				Node curr = head; // Holds node to be deleted
				Node prev = null; // Holds the node right before one to be deleted
				
				// Search for the letter to be deleted in the body of the list
				while((curr != null) && (letter.equalsIgnoreCase(curr.data) == false))
				{
					prev = curr; // Holds the node right before one to be deleted
                        		curr = curr.next;
				}
	
				Node temp = curr; // temp.next will eventally point to the next letter in the list

				while((curr.data.equalsIgnoreCase(temp.data.substring(0,1))) && (temp.next != null)) // If the letter to be deleted is equal to the first letter of the names 
				{
					temp = temp.next;
				}
	
				if((prev != null) && (temp.next != null)) // Takes into account the head and end node
				{
					prev.next = temp; // Delete the letter and all the names for that letter
				}
				else if(temp.next == null) // Gets rid of the last letter node
				{
					prev.next = null;
				}
				else
				{
					head = temp;
				}

				System.out.println("Successfully removed " + letter + " \n");
			}
			else
			{
				System.out.println("That letter is not in the list \n");
			}
		}
	}

	public boolean find(String name) // Finds a name
	{	
		Node curr = head;

		while(curr != null)
		{
			if(curr.data.equalsIgnoreCase(name)) // if the name is found (ignoring case)
			{
				return true;
			}

			curr = curr.next;
		}
			
		return false;
	}

	public String toString() // RETURN THE STRINGS
	{
		// if length of string is 1, then it is a letter
		// if not, it is a name

		StringBuilder sb = new StringBuilder();
		Node curr = head;

		if(curr == null)
		{
			return "The list is empty \n";
		}

		while(curr != null)
		{
			if(curr.data.length() == 1) // If element is a letter
			{
				sb.append(curr.data + "\n");
			}
			else
			{
				sb.append("  " + curr.data + "\n");
			} 

			curr = curr.next;
		}

		return new String(sb);
	}

	public static void main(String[] args)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);

		NameList list = new NameList();

		while(input != 6)
		{
			System.out.println("How would you like to modify the linked list? (Please enter a number)");
                	System.out.println("1. Add a New Name");
                	System.out.println("2. Remove a Name");
                	System.out.println("3. Remove a Letter");
                	System.out.println("4. Find a Name");
                	System.out.println("5. Return a String of the List");
                	System.out.println("6. EXIT");

                	input = in.nextInt();
                	in.nextLine();

			if(input == 1) // Add new name
			{
				String name;
	
				System.out.println("Please enter the name you want to add");
				name = in.nextLine();	

				list.add(name);
			}

			if(input == 2) // Remove a name
			{
				System.out.println("Please enter the name you want to remove");
				String name = in.nextLine();
	
				list.remove(name);
			}
	
			if(input == 3) // Remove a letter
                	{
				System.out.println("Please enter the letter you want to remove");
                        	String letter = in.nextLine();

                        	list.removeLetter(letter);
                	}

			if(input == 4) // Find a name
                	{
				System.out.println("Please enter the name you want to find");
                        	String name = in.nextLine();

                        	if(list.find(name))
				{
					System.out.println("The name was found \n");
				}
				else
				{
					System.out.println("The name was NOT found \n");
				}
                	}

			if(input == 5) // Return the list
           		{
                        	System.out.println(list.toString());
                	}

			if(input == 6) // Exit program
			{
				return;
			}
		}
	}
}
