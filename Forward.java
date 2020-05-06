public class Forward extends Skater
{
	private double faceoffPercent;

	public Forward(String team, String position, String name, int rating, int gamesPlayed, int PIM, int goals, int assists,
				   int points, double pointsPerGame, int shots, int overtimeGoals, int shortHandedGoals, int shortHandedPoints,
				   double shotPercent, double faceoffPercent)
	{
		super(team, position, name, rating, gamesPlayed, PIM, goals, assists, points, pointsPerGame, shots, shortHandedGoals, shortHandedPoints, overtimeGoals, shotPercent);
		this.faceoffPercent = faceoffPercent;
	}

	public double getFaceoffPercent()
	{
		return faceoffPercent;
	}

	public String getStats()
	{
		String result = super.toString();

		result += "\nFaceoff Percent: " + faceoffPercent;

		return result;
	}

	public String toString()
	{
		String result = super.getName();

		return result;
	}
}