public class Team
{
	private Player goalie, rightWing, leftWing, center, rightDefender, leftDefender;

	public Team(Goalie goalie, Forward rightWing, Forward leftWing, Forward center, Defender rightDefender, Defender leftDefender)
	{
		this.goalie=goalie;
		this.rightWing=rightWing;
		this.leftWing=leftWing;
		this.center=center;
		this.rightDefender=rightDefender;
		this.leftDefender=leftDefender;
	}

	public double getTeamRating()
	{
		double rating=0;

		rating+=goalie.getRating();
		rating+=rightWing.getRating();
		rating+=leftWing.getRating();
		rating+=center.getRating();
		rating+=rightDefender.getRating();
		rating+=leftDefender.getRating();
		rating=rating/6.0;

		return rating;
	}

	public Player getRandomPlayer()
	{
		int ran = (int) (Math.random()*5);
		switch(ran)
		{
			case 0: return center;
			case 1: return rightWing;
			case 2: return leftWing;
			case 3: return rightDefender;
			case 4: return leftDefender;
			default: return center;
		}
	}

	public Forward getRandomForward()
	{
		int ran = (int) (Math.random()*3);
		switch(ran)
		{
			case 0: return (Forward) center;
			case 1: return (Forward) rightWing;
			case 2: return (Forward) leftWing;
			default: return (Forward) center;
		}
	}

	public Defender getRandomDefender()
	{
		int ran = (int) (Math.random()*2);
		switch(ran)
		{
			case 0: return (Defender) rightDefender;
			case 1: return (Defender) leftDefender;
			default: return (Defender) rightDefender;
		}
	}

	public Player goalie()
	{
		return goalie;
	}

	public Player rightWing()
	{
		return rightWing;
	}

	public Player leftWing()
	{
		return leftWing;
	}

	public Player center()
	{
		return center;
	}

	public Player rightDefender()
	{
		return rightDefender;
	}

	public Player leftDefender()
	{
		return leftDefender;
	}
}