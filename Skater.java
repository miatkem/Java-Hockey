public class Skater extends Player
{
	private int goals, assists, points, powerPlayGoals,
				powerPlayPoints, shortHandedGoals, shortHandedPoints,
				overtimeGoals, shots;
	private double shotPercent, pointsPerGame;


	public Skater(String team, String position, String name, int rating, int gamesPlayed, int PIM, int goals, int assists,
				  int points, double pointsPerGame, int shots, int shortHandedGoals, int shortHandedPoints, int overtimeGoals, double shotPercent)
	{
		super(team, position, name, rating, gamesPlayed, PIM);
		this.goals = goals;
		this.assists = assists;
		this.points = points;
		this.pointsPerGame = pointsPerGame;
		this.shots = shots;
		this.powerPlayGoals = powerPlayGoals;
		this.powerPlayPoints = powerPlayPoints;
		this.overtimeGoals = overtimeGoals;
		this.shortHandedGoals = shortHandedGoals;
		this.shortHandedPoints = shortHandedPoints;
		this.shotPercent = shotPercent;
	}

	public int getGoals()
	{
		return goals;
	}

	public int getAssists()
	{
		return assists;
	}

	public int getPoints()
	{
		return points;
	}

	public double getPointsPerGame()
	{
		return pointsPerGame;
	}

	public int getShots()
	{
		return shots;
	}

	public int getPowerPlayGoals()
	{
		return powerPlayGoals;
	}

	public int getPowerPlayPoints()
	{
		return powerPlayPoints;
	}

	public int getOvertimeGoals()
	{
		return overtimeGoals;
	}

	public int getShortHandedGoals()
	{
		return shortHandedGoals;
	}

	public int getShortHandedPoints()
	{
		return shortHandedPoints;
	}

	public double getShotPercent()
	{
		return shotPercent;
	}

	public String getName()
	{
		return super.getName();
	}

	public String toString()
	{
		String result = super.toString();

		result += "\nGoals: " + goals
	    +"\nAssists: " + assists
	    +"\nPoints: " + points
	    +"\nPoints Per Game: " + pointsPerGame
	    +"\nShots Taken: " + shots
	    +"\nPowerplay Goals: " + powerPlayGoals
	    +"\nPowerplay Points: " + powerPlayPoints
	    +"\nOvertime Goals: " + overtimeGoals
	    +"\nShorthanded Goals: " + shortHandedGoals
	    +"\nShorthanded Points: " + shortHandedPoints
	    +"\nShot Percent: " + shotPercent;

		return result;
	}
}