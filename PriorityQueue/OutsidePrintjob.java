public class OutsidePrintjob extends Printjob
{
	double cost = 0.0;

	OutsidePrintjob(String name, int priority, int numPages, String type, double cost)
	{
		super(name, priority, numPages, type);

		this.cost = cost;
	}
}
