public class HockeyGame
{
	private Team user, computer;
	private int userGoals, computerGoals, userShots, computerShots;
	private boolean inPlay,userPossession,puckLeftIce, puckRightIce, puckCenterIce;
	private Zone[] zones;
	private int time,delay;
	private String playByPlay;
	private Player puckCarrier;

	public HockeyGame(Team user)
	{
		time =delay=0;
		playByPlay="";
		this.user=user;
		this.computer=createRandomTeam();
		puckCarrier=null;

		zones = new Zone[17];
		for(int i=0; i<17; i++)
			zones[i]=new Zone(i);
		inPlay=userPossession=puckLeftIce=puckRightIce=puckCenterIce=false;
		userGoals = computerGoals = userShots = computerShots = 0;
	}

	public int getComputerScore()
	{
		return computerGoals;
	}

	public int getUserScore()
	{
		return userGoals;
	}

	public String getTime()
	{
		int period = (time/41)+1;
		String seconds ="00";
		int minutes = 20-((time/2)%20)-1;
		if(time%2==0&&time!=41)
			seconds="30";
		else
			seconds="00";

		return "Per "+ period + "      " + minutes+ ":" + seconds;
	}

	public void tick()
	{
		time++;

		if(delay<0)
		    delay++;
		else
		{
			//placement();

			if(userPossession&&inPlay)
			{
				if(puckCenterIce)
					userFoward();

				else if(puckRightIce)
					userFoward();

				else if(puckLeftIce)
					userOffense();
			}

			else if(!userPossession&&inPlay)
			{
				if(puckCenterIce)
					computerFoward();

				else if(puckRightIce)
					computerOffense();

				else if(puckLeftIce)
					computerFoward();
			}

			else if(!inPlay)
			{
				faceoff();
			}
		}

	}

	/*TO BE COMPLETED AT LATER DATE
	public void placement()
	{

	}*/
	public void faceoff()
	{
		puckCenterIce=true;

		//Choose Winner
		int compVal = (int) (Math.random()*10)+ (int)(((Forward)(computer.center())).getFaceoffPercent()-5);
		int userVal = (int) (Math.random()*10)+ (int)(((Forward)(user.center())).getFaceoffPercent()-5);

		if(compVal > userVal)
		{
			inPlay=true;
			userPossession=false;
			playByPlay+="\n"+computer.center() + "(Comp) wins posession of the faceoff!";
			puckCarrier=computer.getRandomPlayer();
			if(computer.center()==puckCarrier)
				playByPlay+="\n"+computer.center() + "(Comp) begings skating up the ice!";
			else
				playByPlay+="\n"+computer.center() + "(Comp) passes the puck to " + puckCarrier + "(Comp)!";
		}
		else if(compVal <= userVal)
		{
			inPlay=true;
			userPossession=true;
			playByPlay+="\n"+user.center() + "(User) wins posession of the faceoff!";
			puckCarrier=user.getRandomPlayer();
			if(user.center()==puckCarrier)
				playByPlay+="\n"+user.center() + "(User) begings skating up the ice!";
			else
				playByPlay+="\n"+user.center() + "(User) passes the puck to " + puckCarrier + "(User)!";
		}


		//Random person on the team gets puck
		puckCenterIce=true;
	}

	public void userFoward()
	{
		//Pass or skate foward
		int ran = (int) (Math.random() *2);
		if(ran==0)//pass
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for steal
			{
				Player onDefend = computer.getRandomPlayer();
				Player reciever = user.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = user.getRandomForward();

				int ranUser= (int) (Math.random() *(puckCarrier.getRating()-50));
				int ranComp = (int) (Math.random() *(onDefend.getRating()-50));

				if(ranUser>= ranComp)
				{
					playByPlay += "\n" + puckCarrier + "(User) makes a risky pass to " + reciever + "(User)";
					puckCarrier=reciever;
					if(puckCenterIce)//Move Forward
					{
						puckCenterIce=false;
						puckLeftIce=true;
						playByPlay += "\n" + puckCarrier + "(User) takes the puck across the blue line!";
					}
					else if(puckRightIce)//Move Forward
					{
						puckCenterIce=true;
						puckRightIce=false;
						playByPlay += "\n" + puckCarrier + "(User) takes the puck into center ice!";
					}
				}

				else
				{
					playByPlay += "\n" + onDefend + "(Comp) intercepts a pass to " + reciever + "(User)";
					userPossession=false;
					puckCarrier=onDefend;
				}

			}
			else//Complete Pass
			{
				Player reciever = user.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = user.getRandomForward();
				playByPlay += "\n" + puckCarrier + "(User) makes a clean pass to " + reciever + "(User)";
				puckCarrier=reciever;

				if(puckCenterIce)//Move Forward
				{
					puckCenterIce=false;
					puckLeftIce=true;
					playByPlay += "\n" + puckCarrier + "(User) takes the puck across the blue line!";
				}
				else if(puckRightIce)//Move Forward
				{
					puckCenterIce=true;
					puckRightIce=false;
					playByPlay += "\n" + puckCarrier + "(User) takes the puck into center ice!";
				}
			}

		}
			//pass
		else
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for hit
			{
				Player onDefend = computer.getRandomDefender();

				int ranUser= puckCarrier.getRating()-40;
				int ranComp = (int) (Math.random() *(((Defender)onDefend).getHits())) + (int) (Math.random() *(((Defender)onDefend).getTakeAways()));

				if(ranUser>= ranComp)
				{
					playByPlay += "\n" + puckCarrier + "(User) dekes around " + onDefend + "(Comp)";
					if(puckCenterIce)//Move Forward
					{
						puckCenterIce=false;
						puckLeftIce=true;
						playByPlay += "\n" + puckCarrier + "(User) skates the puck across the blue line!";
					}
					else if(puckRightIce)//Move Forward
					{
						puckCenterIce=true;
						puckRightIce=false;
						playByPlay += "\n" + puckCarrier + "(User) skates the puck into center ice!";
					}
				}

				else
				{
					ran = (int)  (Math.random()*2);
					if(ran==0)
						playByPlay += "\n" + onDefend + "(Comp) lands an amazing hit on " + puckCarrier + "(User)";
					else
						playByPlay += "\n" + onDefend + "(Comp) poke checks the puck away from " + puckCarrier + "(User)";
					userPossession=false;
					puckCarrier=onDefend;
				}
			}
		}
	}

	public void computerFoward()
	{
		//Pass or skate foward
		int ran = (int) (Math.random() *2);
		if(ran==0)//pass
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for steal
			{
				Player onDefend = user.getRandomPlayer();
				Player reciever = computer.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = computer.getRandomForward();

				int ranComp= (int) (Math.random() *(puckCarrier.getRating()-50));
				int ranUser = (int) (Math.random() *(onDefend.getRating()-50));

				if(ranComp>= ranUser)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) makes a risky pass to " + reciever + "(Comp)";
					puckCarrier=reciever;
					if(puckCenterIce)//Move Forward
					{
						puckCenterIce=false;
						puckRightIce=true;
						playByPlay += "\n" + puckCarrier + "(Comp) takes the puck across the blue line!";
					}
					else if(puckLeftIce)//Move Forward
					{
						puckCenterIce=true;
						puckLeftIce=false;
						playByPlay += "\n" + puckCarrier + "(Comp) takes the puck into center ice!";
					}
				}

				else
				{
					playByPlay += "\n" + onDefend + "(User) intercepts a pass to " + reciever + "(Comp)";
					userPossession=true;
					puckCarrier=onDefend;
				}

			}
			else//Complete Pass
			{
				Player reciever = computer.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = computer.getRandomForward();
				playByPlay += "\n" + puckCarrier + "(Comp) makes a clean pass to " + reciever + "(Comp)";
				puckCarrier=reciever;

				if(puckCenterIce)//Move Forward
				{
					puckCenterIce=false;
					puckRightIce=true;
					playByPlay += "\n" + puckCarrier + "(Comp) takes the puck across the blue line!";
				}
				else if(puckLeftIce)//Move Forward
				{
					puckCenterIce=true;
					puckLeftIce=false;
					playByPlay += "\n" + puckCarrier + "(Comp) takes the puck into center ice!";
				}
			}

		}
			//pass
		else
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for hit
			{
				Player onDefend = user.getRandomDefender();

				int ranComp= puckCarrier.getRating()-40;
				int ranUser = (int) (Math.random() *(((Defender)onDefend).getHits())) + (int) (Math.random() *(((Defender)onDefend).getTakeAways()));

				if(ranComp>= ranUser)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) dekes around " + onDefend + "(User)";
					if(puckCenterIce)//Move Forward
					{
						puckCenterIce=false;
						puckRightIce=true;
						playByPlay += "\n" + puckCarrier + "(Comp) skates the puck across the blue line!";
					}
					else if(puckLeftIce)//Move Forward
					{
						puckCenterIce=true;
						puckLeftIce=false;
						playByPlay += "\n" + puckCarrier + "(Comp) skates the puck into center ice!";
					}
				}

				else
				{
					ran = (int)  (Math.random()*2);
					if(ran==0)
						playByPlay += "\n" + onDefend + "(User) lands an amazing hit on " + puckCarrier + "(Comp)";
					else
						playByPlay += "\n" + onDefend + "(User) poke checks the puck away from " + puckCarrier + "(Comp)";
					userPossession=true;
					puckCarrier=onDefend;
				}
			}
		}
	}


	public void computerOffense()
	{
		//Pass or shoot
		int ran = (int) (Math.random() *3);

		if(ran==0) //Pass
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for steal
			{
				Player onDefend = user.getRandomPlayer();
				Player reciever = computer.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = computer.getRandomForward();

				int ranComp= (int) (Math.random() *(puckCarrier.getRating()-50));
				int raunUser = (int) (Math.random() *(onDefend.getRating()-50));

				if(ranComp>= raunUser)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) makes a risky pass to " + reciever + "(Comp)";
					puckCarrier=reciever;
				}

				else
				{
					playByPlay += "\n" + onDefend + "(User) intercepts a pass to " + reciever + "(Comp)";
					userPossession=true;
					puckCarrier=onDefend;
				}

			}
			else//Complete Pass
			{
				Player reciever = computer.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = user.getRandomForward();
				playByPlay += "\n" + puckCarrier + "(Comp) makes a fast pass to " + reciever + "(Comp)";
				puckCarrier=reciever;

				ran = (int) (Math.random()*3);
				if(ran==0)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) fires a one timer!";
					int ranComp=(int) (((Skater) puckCarrier).getShotPercent()*8);
					int ranUser =(int) (((Goalie)user.goalie()).getSavePercent()+user.goalie().getRating());

					computerShots++;

					if(ranComp>=ranUser)
					{
						playByPlay += "\n" + puckCarrier + "(Comp) scores!";
						userPossession=false;
						inPlay=false;
						computerGoals++;
						delay=4;
					}

					else
					{
						playByPlay += "\n" + user.goalie() + "(User) makes the save!";
						userPossession=true;
						ran = (int) (Math.random() *3);
						if(ran<3)
						{
							reciever = user.getRandomForward();
							while(reciever.equals(puckCarrier))
								reciever = computer.getRandomForward();
							//Save and pass
							playByPlay += "\n" + user.goalie() + "(User) stops the puck and passes to " + reciever +"!";
							userPossession=true;
							puckCarrier=reciever;

						}
						else
						{
							Player rebounder = computer.getRandomForward();
							puckCarrier=rebounder;
							//rebound
							playByPlay += "\n" + user.goalie() + "(User) barely saves the puck!";
							playByPlay += "\n" + rebounder + "(Comp) gains possession of the puck in front of the net!";
						}
					}
				}
			}
		}

		else if(ran == 1) //Shoot
		{
			//Shot on Net 2 of 3
			ran = (int) (Math.random() *3);
			playByPlay += "\n" + puckCarrier + "(Comp) takes the shot!";
			if(ran<3)
			{
				int ranComp=(int) (((Skater) puckCarrier).getShotPercent()*8);
				int ranUser =(int) (((Goalie)user.goalie()).getSavePercent()+user.goalie().getRating());

				computerShots++;

				if(ranComp>=ranUser)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) scores!";
					userPossession=true;
					inPlay=false;
					computerGoals++;
					delay=4;
				}

				else
				{
					playByPlay += "\n" + user.goalie() + "(User) makes the save!";
					userPossession=true;
					ran = (int) (Math.random() *3);
					if(ran<3)
					{
						Player reciever = user.getRandomForward();
						while(reciever.equals(puckCarrier))
							reciever = user.getRandomForward();
						//Save and pass
						playByPlay += "\n" + user.goalie() + "(User) stops the puck and passes to " + reciever +"(User)!";
						userPossession=true;
						puckCarrier=reciever;

					}
					else
					{
						Player rebounder = computer.getRandomForward();
						puckCarrier=rebounder;
						//rebound
						playByPlay += "\n" + user.goalie() + "(User) barely saves the puck!";
						playByPlay += "\n" + rebounder + "(Comp) gains possession of the puck in front of the net!";
					}
				}
			}

			//Blocked by Defender 1 of 3
			else
			{
				Player blocker = user.getRandomDefender();
				puckCarrier=blocker;
				//rebound
				playByPlay += "\n" + blocker + "(User) blocks the shot with his body!";
				playByPlay += "\n" + blocker + "(User) gains possession of the puck in front of the net!";
				userPossession=true;
			}

		}

		else if(ran == 2) //Skate
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for hit
			{
				Player onDefend = user.getRandomDefender();

				int ranComp= puckCarrier.getRating()-40;
				int ranUser = (int) (Math.random() *(((Defender)onDefend).getHits())) + (int) (Math.random() *(((Defender)onDefend).getTakeAways()));

				if(ranComp>= ranUser)
				{
					playByPlay += "\n" + puckCarrier + "(Comp) dekes around " + onDefend + "(User)";
					ran = (int) (Math.random() *2);
					if(ran==0)
						playByPlay += "\n" + puckCarrier + "(Comp) skates the puck into the crease!";
					else
						playByPlay += "\n" + puckCarrier + "(Comp) skates the puck back to the line!";
				}

				else
				{
					ran = (int)  (Math.random()*2);
					if(ran==0)
						playByPlay += "\n" + onDefend + "(User) lands an amazing hit on " + puckCarrier + "(Comp)";
					else
						playByPlay += "\n" + onDefend + "(User) poke checks the puck away from " + puckCarrier + "(Comp)";
					userPossession=true;
					puckCarrier=onDefend;
				}
			}
		}
	}

	public void userOffense()
	{
		//Pass or shoot
		int ran = (int) (Math.random() *3);

		if(ran==0) //Pass
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for steal
			{
				Player onDefend = computer.getRandomPlayer();
				Player reciever = user.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = user.getRandomForward();

				int ranUser= (int) (Math.random() *(puckCarrier.getRating()-50));
				int ranComp = (int) (Math.random() *(onDefend.getRating()-50));

				if(ranUser>= ranComp)
				{
					playByPlay += "\n" + puckCarrier + "(User) makes a risky pass to " + reciever + "(User)";
					puckCarrier=reciever;
				}

				else
				{
					playByPlay += "\n" + onDefend + "(Comp) intercepts a pass to " + reciever + "(User)";
					userPossession=false;
					puckCarrier=onDefend;
				}

			}
			else//Complete Pass
			{
				Player reciever = user.getRandomForward();
				while(reciever.equals(puckCarrier))
					reciever = user.getRandomForward();
				playByPlay += "\n" + puckCarrier + "(User) makes a fast pass to " + reciever + "(User)";
				puckCarrier=reciever;

				ran = (int) (Math.random()*3);
				if(ran==0)
				{
					playByPlay += "\n" + puckCarrier + "(User) fires a one timer!";
					int ranUser=(int) (((Skater) puckCarrier).getShotPercent()*8);
					int ranComp =(int) (((Goalie)computer.goalie()).getSavePercent()+computer.goalie().getRating());

					userShots++;

					if(ranUser>=ranComp)
					{
						playByPlay += "\n" + puckCarrier + "(User) scores!";
						userPossession=false;
						inPlay=false;
						userGoals++;
						delay=4;
					}

					else
					{
						playByPlay += "\n" + computer.goalie() + "(Comp) makes the save!";
						userPossession=false;
						ran = (int) (Math.random() *3);
						if(ran<3)
						{
							reciever = computer.getRandomForward();
							while(reciever.equals(puckCarrier))
								reciever = computer.getRandomForward();
							//Save and pass
							playByPlay += "\n" + computer.goalie() + "(Comp) stops the puck and passes to " + reciever +"(Comp)!";
							userPossession=false;
							puckCarrier=reciever;

						}
						else
						{
							Player rebounder = user.getRandomForward();
							puckCarrier=rebounder;
							//rebound
							playByPlay += "\n" + computer.goalie() + "(Comp) barely saves the puck!";
							playByPlay += "\n" + rebounder + "(User) gains possession of the puck in front of the net!";
						}
					}
				}
			}

			//Check One Timer
		}

		else if(ran == 1) //Shoot
		{
			//Shot on Net 2 of 3
			ran = (int) (Math.random() *3);
			playByPlay += "\n" + puckCarrier + "(User) takes the shot!";
			if(ran<3)
			{
				int ranUser=(int) (((Skater) puckCarrier).getShotPercent()*8);
				int ranComp =(int) (((Goalie)computer.goalie()).getSavePercent()+computer.goalie().getRating());

				System.out.println(ranUser + "shot at" + ranComp);
				userShots++;

				if(ranUser>=ranComp)
				{
					playByPlay += "\n" + puckCarrier + "(User) scores!";
					userPossession=false;
					inPlay=false;
					userGoals++;
					delay=4;
				}

				else
				{
					playByPlay += "\n" + computer.goalie() + "(Comp) makes the save!";
					userPossession=false;
					ran = (int) (Math.random() *3);
					if(ran<3)
					{
						Player reciever = computer.getRandomForward();
						while(reciever.equals(puckCarrier))
							reciever = computer.getRandomForward();
						//Save and pass
						playByPlay += "\n" + computer.goalie() + "(Comp) stops the puck and passes to " + reciever +"!";
						userPossession=false;
						puckCarrier=reciever;

					}
					else
					{
						Player rebounder = user.getRandomForward();
						puckCarrier=rebounder;
						//rebound
						playByPlay += "\n" + computer.goalie() + "(Comp) barely saves the puck!";
						playByPlay += "\n" + rebounder + "(User) gains possession of the puck in front of the net!";
					}
				}
			}

			//Blocked by Defender 1 of 3
			else
			{
				Player blocker = computer.getRandomDefender();
				puckCarrier=blocker;
				//rebound
				playByPlay += "\n" + blocker + "(Comp) blocks the shot with his body!";
				playByPlay += "\n" + blocker + "(Comp) gains possession of the puck in front of the net!";
				userPossession=false;
			}

		}

		else if(ran == 2) //Skate
		{
			ran = (int) (Math.random() *2);
			if(ran==0)//Chance for hit
			{
				Player onDefend = computer.getRandomDefender();

				int ranUser= puckCarrier.getRating()-40;
				int ranComp = (int) (Math.random() *(((Defender)onDefend).getHits())) + (int) (Math.random() *(((Defender)onDefend).getTakeAways()));

				if(ranUser>= ranComp)
				{
					playByPlay += "\n" + puckCarrier + "(User) dekes around " + onDefend + "(Comp)";
					ran = (int) (Math.random() *2);
					if(ran==0)
						playByPlay += "\n" + puckCarrier + "(User) skates the puck into the crease!";
					else
						playByPlay += "\n" + puckCarrier + "(User) skates the puck back to the line!";
				}

				else
				{
					ran = (int)  (Math.random()*2);
					if(ran==0)
						playByPlay += "\n" + onDefend + "(Comp) lands an amazing hit on " + puckCarrier + "(User)";
					else
						playByPlay += "\n" + onDefend + "(Comp) poke checks the puck away from " + puckCarrier + "(User)";
					userPossession=false;
					puckCarrier=onDefend;
				}
			}
		}
	}

	public String getPlayByPlay()
	{
		return playByPlay;
	}

	public Team createRandomTeam()
	{
		int gol = (int) (Math.random()*PlayerDirectory.goalieList.length);
		int cen = (int) (Math.random()*PlayerDirectory.fowardList.length);
		int rw = (int) (Math.random()*PlayerDirectory.fowardList.length);
		int lw = (int) (Math.random()*PlayerDirectory.fowardList.length);
		int rd = (int) (Math.random()*PlayerDirectory.defenderList.length);
		int ld = (int) (Math.random()*PlayerDirectory.defenderList.length);

		return new Team((Goalie) PlayerDirectory.goalieList[gol], (Forward) PlayerDirectory.fowardList[rw],
			(Forward) PlayerDirectory.fowardList[lw], (Forward) PlayerDirectory.fowardList[cen], (Defender) PlayerDirectory.defenderList[rd], (Defender) PlayerDirectory.defenderList[ld]);
	}

}