public class Printjob implements Comparable<Printjob>
{
	String name;
	int priority;
	int numPages;
	String type; // outside or inside job

	Printjob(String name, int priority, int numPages, String type)
	{
		this.name = name;
		this.priority = priority;
		this.numPages = numPages;
		this.type = type;
	}

	@Override

	public int compareTo(Printjob job)
	{
		int priority1 = job.priority*job.numPages;
		int priority2 = this.priority*this.numPages;

		if(priority1 < priority2)
		{
			return 1;
		}
		else if(priority1 > priority2)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
