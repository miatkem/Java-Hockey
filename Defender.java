public class Defender extends Skater
{
	private int takeAways, blockedShots, hits;

	public Defender(String team, String position, String name, int rating, int gamesPlayed, int PIM, int goals, int assists,
				  int points, double pointsPerGame, int shots, int overtimeGoals, int shortHandedGoals, int shortHandedPoints,
				  double shotPercent, int takeAways, int blockedShots, int hits)
	{
		super(team, position, name, rating ,gamesPlayed, PIM, goals, assists, points, pointsPerGame, shots, shortHandedGoals, shortHandedPoints, overtimeGoals, shotPercent);
		this.takeAways = takeAways;
		this.blockedShots = blockedShots;
		this.hits = hits;
	}

	public int getTakeAways()
	{
		return takeAways;
	}

	public int getBlockedShots()
	{
		return blockedShots;
	}

	public int getHits()
	{
		return hits;
	}

	public String getStats()
	{
		String result = super.toString();

		result += "\nTakeaways: " + takeAways
		+"\nBlocked Shots: " + blockedShots;

		return result;
	}

	public String toString()
	{
		return super.getName();
	}
}