public class Player
{
	private String team, position, name;
	private int gamesPlayed, PIM, rating;

	public Player(String team, String position, String name, int rating, int gamesPlayed, int PIM)
	{
		this.team = team;
		this.position = position;
		this.name = name;
		this.gamesPlayed = gamesPlayed;
		this.PIM = PIM;
		this.rating = rating;

	}

	public String getTeam()
	{
		return team;
	}

	public String getPosition()
	{
		return position;
	}

	public String getName()
	{
		return name;
	}

	public int getGamesPlayed()
	{
		return gamesPlayed;
	}

	public int getPIM()
	{
		return PIM;
	}

	public int getRating()
	{
		return rating;
	}

	public String toString()
	{
		String result = "Player: " + name
					   +"\nTeam: " + team
					   +"\nPosition: " + position
					   +"\nRating: " + rating
					   +"\nGames Played: " + gamesPlayed
					   +"\nPenalties in Minutes: " + PIM;
		return result;
	}
}