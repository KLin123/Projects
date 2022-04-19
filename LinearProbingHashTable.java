import java.util.Scanner;
@SuppressWarnings("unchecked")

public class LinearProbingHashTable<K,V>
{
	private static class Entry<K,V>
	{
		K key;
		V value;
		boolean isActive;

		Entry(K key, V value, boolean active)
		{
			this.key = key;
			this.value = value;
			isActive = active;
		}
	}

	int size; // Size of the hashtable
	int elements; // Number of elements in the hashtable
	Entry<K,V> table[];

	LinearProbingHashTable(int size)
	{
		this.size = size;
		elements = 0;
		table = new Entry[size];
	}

	public boolean insert(K key, V value)
	{
		/*
			inserts entry, rehashes if half full,
         		can re-use deleted entries, throws
         		exception if key is null, returns
         		true if inserted, false if duplicate.
		*/

		if(key == null)
		{
			throw new IllegalArgumentException("The key cannot be null\n");
		}

		int index = getHashLocation(key);

		if(isActive(index))
		{
			return false;
		}

		table[index] = new Entry<>(key, value, true);
		elements++; // Increase the number of elements in the table

		double howFull = (double)elements / (double)size; // Determines how full the table is to determine if I need to rehash

		if(howFull >= 0.5) // If the table is .5 full or greater
		{
			rehash();
		}

		return true;
	} 		

	public V find(K key)
	{
		// Returns value for key, or null if not found

		int index = getHashTarget(key);
		int count = 1;

		while(table[index] != null)
		{
			if(count > size)
			{
				return null;
			}

			if(table[index].key == key)
			{
				if(isActive(index)) // If it has not been deleted yet
				{
					return table[index].value;
				}
			}

			index = (index + 1) % size; // Goes to next space

			count++;
		}

		return null; // if not found
	}

	public boolean delete(K key)
	{
		int index = getHashLocation(key);

		if(isActive(index))
		{
			table[index].isActive = false;
			elements--; // Decrease the number of elements in the array
			return true;
		}
		else
		{
			return false;
		}
	}

	private void rehash()
	{
		size = size*2; // Doubles table size

		Entry<K,V> oldTable[] = table; // Saves the old table

		table = new Entry[size]; // Creates a new table	with the new size

		elements = 0;

		for(Entry<K,V> i : oldTable)
		{
			if((i != null) && (i.isActive))
			{
				insert(i.key, i.value);
			}
		}

		System.out.println("Rehashed the table");	
	}

	public int getHashTarget(K key)
	{
		// return original hash index

		int index = key.hashCode() % size;
		return index;
	}

	public int getHashLocation(K key)
	{
		// returns the next free space

		int index = getHashTarget(key);

		while((table[index] != null) && (isActive(index)) && (table[index].key != key))
		{
			index++;
			index %= size;
		}

		return index;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < size; i++)
		{
			if(table[i] != null)
			{
				if(isActive(i)) // If the element has not been deleted yet
				{
					sb.append(getHashLocation(table[i].key) + "  " + table[i].key + ", " + table[i].value + "\n");
				}
				else
				{
					sb.append(getHashLocation(table[i].key) + "  " + table[i].key + ", " + table[i].value + "   deleted\n");
				}
			}
		}

		return new String(sb);
	}

	private boolean isActive(int currentPosition) // Marks the deleted entries
    	{
        	return ((table[currentPosition] != null) && (table[currentPosition].isActive));
    	}		

	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);

		int size; // Holds the size of the hashtable
		int input = 0; // for the menu

		System.out.println("Enter the size of the hashtable: ");
		size = scan.nextInt();
		scan.nextLine();

		LinearProbingHashTable<Integer, Integer> table = new LinearProbingHashTable<Integer, Integer>(size);

		do
		{
        	        System.out.println("==================================================================================== \n");
        	        System.out.println("PLEASE NOTE THAT THE HASHTABLE IS OF TYPE INTEGER, BUT THE CLASS IS A GENERIC TYPE \n");
        	        System.out.println("==================================================================================== \n");

                	System.out.println("What would you like to do with the Integer hashtable? (Please enter a number)");
                	System.out.println("1. Insert Into Table");
                	System.out.println("2. Find a Value");
                        System.out.println("3. Delete an Entry");
                        System.out.println("4. Return the Original Index");
                        System.out.println("5. Return the Actual Index");
                        System.out.println("6. Print the Hashtable");
                        System.out.println("7. EXIT");

                 	input = scan.nextInt();
                    	scan.nextLine();

			if(input == 1) // Deletes entry
			{
				int key, value;

				System.out.println("Enter the key: ");
				key = scan.nextInt();
				scan.nextLine();

				System.out.println("Enter the value: ");
				value = scan.nextInt();
				scan.nextLine();

				if(table.insert(key, value))
				{
					System.out.println("Insert was successful \n");
				}
				else
				{
					System.out.println("Insert was NOT successful \n");
				}
			}

			if(input == 2) // Finds entry
                        {
				int key;

				System.out.println("Enter the key for the value you want to find: ");
                        	key = scan.nextInt();

				if(table.find(key) == null)
				{
					System.out.println("The key was not found \n");
				}
				else
				{
					System.out.println("The value for the key is: " + table.find(key) + "\n");
				}
			}

			if(input == 3) // Deletes entry
                        {
				int key;

				System.out.println("Enter the key for the value you want to delete: ");
				key = scan.nextInt();

				if(table.find(key) == null)
				{
					System.out.println("That key does not exist\n");
				}
				else
				{
					table.delete(key);
					System.out.println("Delete was successful\n");
                        	}
			}

			if(input == 4) // Returns the original index
                        {
				int key;

				System.out.println("Enter the key: ");
				key = scan.nextInt();

				if(table.find(key) == null)
				{
					System.out.println("That key does not exist\n");
				}
				else
				{
					System.out.println("The original index is: " + table.getHashTarget(key) + "\n");
                        	}
			}

			if(input == 5) // Returns the index of a key
                        {
				int key;
		
				System.out.println("Enter the key");
				key = scan.nextInt();
				
				if(table.find(key) == null)
				{
					System.out.println("That key does not exist\n");
				}
				else
				{
					System.out.println("The index for the key is: " + table.getHashLocation(key) + "\n");
                        	}
			}

			if(input == 6) // Print out the table
                        {
				System.out.println("\n" + table.toString());
                        }

			if(input == 7)
                        {
				return;
                        }



		}while(input != 7);
	}
}
