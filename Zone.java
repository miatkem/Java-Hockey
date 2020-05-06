public class Zone
{
	private double shootingChance, passingChance, shotBlockingChance, passBlockingChance, hitChance;
	private Player offender,defender;
	private boolean passingLaneBlocked,shootingLaneBlocked,puckPresent;

	public Zone(int zoneNumber)
	{
		setStats(zoneNumber);
		offender=null;
		defender=null;
		passingLaneBlocked = shootingLaneBlocked = puckPresent = false;
	}

	public void setStats(int zoneNum)
	{
		zoneNum=zoneNum%10;
		switch(zoneNum)
		{
			//Ends
			case 1: shootingChance=0; passingChance=40; passBlockingChance=50; hitChance=80; break;
			case 2: shootingChance=30; passingChance=70; passBlockingChance=40; hitChance=80; break;
			case 3: shootingChance=80; passingChance=40; passBlockingChance=60; hitChance=20; break;
			case 4: shootingChance=30; passingChance=70; passBlockingChance=40; hitChance=80; break;
			case 5: shootingChance=50; passingChance=70; passBlockingChance=40; hitChance=50; break;
			case 6: shootingChance=65; passingChance=90; passBlockingChance=70; hitChance=30; break;
			case 7: shootingChance=50; passingChance=70; passBlockingChance=40; hitChance=50; break;
			//Center Ice
			case 8: shootingChance=10; passingChance=80; passBlockingChance=50; hitChance=60; break;
			case 9: shootingChance=20; passingChance=90; passBlockingChance=70; hitChance=30; break;
			case 0: shootingChance=10; passingChance=80; passBlockingChance=50; hitChance=60; break;
		}
	}

	public Player getDefender()
	{
		return defender;
	}
	public Player getOffender()
	{
		return offender;
	}

	public void enterPuck()
	{
		puckPresent=true;
	}

	public void exitPuck()
	{
		puckPresent=false;
	}
	public void enterDefense(Player defender,String passingOrShooting)
	{
		this.defender=defender;
		if(passingOrShooting.equals("shooting"))
			shootingLaneBlocked=true;
		else
			passingLaneBlocked=true;
	}

	public void enterOffense(Player offender)
	{
		this.offender=offender;
	}

	public void clearZone()
	{
		offender=null;
		defender=null;
	}

	public boolean passingLaneBlocked()
	{
		return passingLaneBlocked;
	}
	public boolean shootingLaneBlocked()
	{
		return shootingLaneBlocked;
	}
}