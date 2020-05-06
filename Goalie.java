public class Goalie extends Player
{
	private int saves, goalsAgainst, shutouts;
	private double savePercent, goalsAgainstAverage;

	public Goalie(String team, String position, String name, int rating, int gamesPlayed, int PIM,
				  int saves, int goalsAgainst, int shutouts, double savePercent, double goalsAgainstAverage)
	{
		super(team, position, name, rating ,gamesPlayed, PIM);
		this.saves = saves;
		this.goalsAgainst = goalsAgainst;
		this.shutouts = shutouts;
		this.savePercent = savePercent;
		this.goalsAgainstAverage = goalsAgainstAverage;
	}

	public int getSaves()
	{
		return saves;
	}

	public int getGoalsAgainst()
	{
		return goalsAgainst;
	}

	public int getShutouts()
	{
		return shutouts;
	}

	public double getSavePercent()
	{
		return savePercent;
	}

	public double getGoalsAgainstAverage()
	{
		return goalsAgainstAverage;
	}

	public String getStats()
	{
		String result = super.toString();

		result += "\nSaves: " + saves
		+"\nGoals Against: " + goalsAgainst
		+"\nShutouts: " + shutouts
		+"\nSave Percentage: " + savePercent
		+"\nGoals Against Average: " + goalsAgainstAverage;

		return result;
	}

	public String toString()
	{
		String result = super.getName();

		return result;
	}
}