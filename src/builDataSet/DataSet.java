package builDataSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

public class DataSet {
	
		String CSVFile = "G:\\6th Semester\\AI\\DataSetForProject_3\\MatchInCosecutiveOvers.csv";
		BufferedReader bro = null;
		String line = "", splitBy = ",";
		PrintWriter pw;
		StringBuilder sb = new StringBuilder();
		HashMap<String, String> team;
		String[] raw = new String[22];
		String teamValue;
		String oponentValue;
		
		public DataSet() {
				team = new HashMap<String, String>();
				buildHashMap();
		}
		
		public void readFileWriteFile() throws IOException {
				//int j=0;
				PrintWriter pw = new PrintWriter(new File("G:\\6th Semester\\AI\\DataSetForProject_3\\trainingData.csv"));
				sb.append("Team");sb.append(',');sb.append("Run/10");sb.append(',');sb.append("Wicket/10");sb.append(',');sb.append("Opponent");sb.append(',');sb.append("Total Run");sb.append('\n');
				bro = new BufferedReader(new FileReader(CSVFile));
				for(int i=1; i<3; i++) {line = bro.readLine();}
				int j=0;
				while((line = bro.readLine()) != null) {
						
						raw = line.split(splitBy);
						//for(int i=0; i<22; i++) System.out.print(raw[i]+" ");
						//System.out.println();
						//if(j==5) break;
						//j++;
						
						if(team.containsKey(raw[1]) && team.containsKey(raw[12])) {
								teamValue = team.get(raw[1]);
								oponentValue = team.get(raw[12]);
						
								sb.append(teamValue);sb.append(',');
								sb.append(raw[2]);sb.append(',');sb.append(raw[3]);sb.append(',');sb.append(oponentValue);sb.append(',');sb.append(raw[10]);sb.append('\n');
						}
						if(j == 1200) break;
						j++;
				}
				pw.write(sb.toString());
				pw.close();
				bro.close();
		}
		
		public void buildHashMap() {
				team.put("England", "13000");
				team.put("India", "12000");
				team.put("New Zealand", "11000");
				team.put("South Africa", "10000");
				team.put("Pakistan", "9000");
				team.put("Australia", "8000");
				team.put("Bangladesh", "7000");
				team.put("Sri Lanka", "6000");
				team.put("West Indies", "5000");
				team.put("Afghanistan", "4000");
				team.put("Zimbabwe", "3000");
				team.put("Ireland", "2000");
				team.put("Scotland", "1000");
		}
}

