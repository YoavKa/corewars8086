package il.co.codeguru.corewars8086;

import il.co.codeguru.corewars8086.war.Competition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AutoCoreWars
{
	public static void main(String[] args) throws NumberFormatException, Exception
	{
		System.out.println("Availble options:");
		System.out.println("Syntax\t\tDescription\t\tDefault");
		System.out.println("-wars [num]\tWars\t\t\t1");
		System.out.println("-warriors [num]\tWarriors per Group\t4");
		System.out.println("-endless\tRun Endlessly\t\tfalse");
		System.out.println("-group [name]\tOnly with Group");
		System.out.println("-add [directory]Add Warriors Directory");
		System.out.println("-random\t\tRun Random Groups\tfalse");
		System.out.println("");
		System.out.println("Enter \"exit\" to exit engine");
		
		Scanner input = new Scanner(System.in);
		Competition c;
		boolean con = true;
		while (con) //while mode is OK, don't turn off and wait for next command
		{
			int warsPerCombination = 1;
			int warriorsPerGroup = 4;
			boolean runEndlessly = false, runBinomical = true;
			String groupName = "";
			ArrayList<String> extraWarriorDirectories = new ArrayList<String>();
			
			ArrayList<String> cmdArgs = new ArrayList<String>(Arrays.asList(input.nextLine().split(" ")));
			
			if (cmdArgs.get(0).equals("exit"))
				break; //time to close
			
			while (!cmdArgs.isEmpty())
			{
				switch (cmdArgs.remove(0))
				{
					case "-wars":
						warsPerCombination = Integer.parseInt(cmdArgs.remove(0));
						break;
					case "-warriors":
						warriorsPerGroup = Integer.parseInt(cmdArgs.remove(0));
						break;
					case "-endless":
						runEndlessly = true;
						break;
					case "-group":
						groupName = cmdArgs.remove(0);
						break;
					case "-add":
						extraWarriorDirectories.add(cmdArgs.remove(0));
						break;
					case "-random":
						runBinomical = false;
						break;
				}
			}
			
			c = new Competition(runEndlessly);
			for (String directory : extraWarriorDirectories)
				c.getWarriorRepository().readWarriorFiles(directory);
			
			if (c.getWarriorRepository().getNumberOfGroups() < warriorsPerGroup)
				System.out.println("Not enough survivors to run " + warriorsPerGroup + " survivors!");
			else
				System.out.println(c.runCompetition(warsPerCombination, warriorsPerGroup, groupName, runBinomical));
		}
		input.close();
	}
}