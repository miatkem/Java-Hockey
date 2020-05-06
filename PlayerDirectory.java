import java.io.*;

public class PlayerDirectory
{
	public static Player[] goalieList,defenderList,fowardList;

	public static void init()
	{
		goalieList = new Player[4];
		loadGoalies(4);
		loadDefenders(5);
		loadFowards(14);
	}

	public static void loadDefenders(int amt)
	{
		String line = "";
		defenderList = new Player[amt];

		try {
			FileReader fileReader = new FileReader("Defender.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int count =0;
			while((line = bufferedReader.readLine()) != null)
			{

				String[] lineSplit = line.split(", ");
				defenderList[count] = new Defender(lineSplit[0], lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]),
										Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[6]), Integer.parseInt(lineSplit[7]),  Integer.parseInt(lineSplit[8]),
										Double.parseDouble(lineSplit[9]), Integer.parseInt(lineSplit[10]), Integer.parseInt(lineSplit[11]),  Integer.parseInt(lineSplit[12]),
										Integer.parseInt(lineSplit[13]), Double.parseDouble(lineSplit[14]), Integer.parseInt(lineSplit[15]),  Integer.parseInt(lineSplit[16]),
										Integer.parseInt(lineSplit[17]));
				count++;
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(
				"Unable to open file");
		}
		catch(IOException ex) {
			System.out.println(
				"Error reading file");
		}
	}

	public static void loadFowards(int amt)
	{
		String line = "";
		fowardList = new Player[amt];

		try {
			FileReader fileReader = new FileReader("Forward.txt");

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int count =0;
			while((line = bufferedReader.readLine()) != null) {

				String[] lineSplit = line.split(", ");
				System.out.println(lineSplit.length);
				fowardList[count] = new Forward(lineSplit[0], lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]),
										Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[6]), Integer.parseInt(lineSplit[7]),  Integer.parseInt(lineSplit[8]),
										Double.parseDouble(lineSplit[9]), Integer.parseInt(lineSplit[10]), Integer.parseInt(lineSplit[11]),  Integer.parseInt(lineSplit[12]),
										Integer.parseInt(lineSplit[13]), Double.parseDouble(lineSplit[14]), Double.parseDouble(lineSplit[15]));
				count++;
			}

			// Always close files.
			bufferedReader.close();


		}
		catch(FileNotFoundException ex) {
			System.out.println(
				"Unable to open file '" +
				"Forward.txt" + "'");
		}
		catch(IOException ex) {
			System.out.println(
				"Error reading file '"
				+ "Forward.txt" + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}


	public static void loadGoalies(int amt)
	{
		String line = "";
		goalieList = new Player[amt];

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader =
				new FileReader("Goalies.txt");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader =
				new BufferedReader(fileReader);
			int count =0;
			while((line = bufferedReader.readLine()) != null) {

				String[] lineSplit = line.split(", ");
				goalieList[count] = new Goalie(lineSplit[0], lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]),
										Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[6]), Integer.parseInt(lineSplit[7]),  Integer.parseInt(lineSplit[8]),
										Double.parseDouble(lineSplit[9]), Double.parseDouble(lineSplit[10]));
				count++;
			}

			// Always close files.
			bufferedReader.close();

			System.out.println(goalieList[3].toString());
		}
		catch(FileNotFoundException ex) {
			System.out.println(
				"Unable to open file '" +
				"Goalies.txt" + "'");
		}
		catch(IOException ex) {
			System.out.println(
				"Error reading file '"
				+ "Goalies.txt" + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}
}