package predict;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PredictionModel {
		public LinkedHashMap<String, Double> parameters;
		public String dVariable;
		public double rSquared;
		HashMap<String, String> team = new HashMap<String, String>();
		
		
		public PredictionModel(LinkedHashMap<String, Double> parameters, String dVariable, double rSquared ) {
				this.parameters = parameters;
				this.dVariable = dVariable;
				this.rSquared = rSquared;
		}
		
		public double predict(DataToHandle input) {
				
				double predictedY = parameters.get("Intercepts");
				for(String feature: parameters.keySet()) {
						//System.out.println(feature);
						if(!feature.equals("Intercepts")) {
								predictedY += parameters.get(feature)*input.getFeature(feature);
								//System.out.println(input.getFeature(feature));
						}
				}
				return predictedY;
		}
		
		/*public void buildHashMap() {
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
	}*/
		public String toString() {
				String output="";
				
				for(String feature : parameters.keySet()) {
						output = output + feature + "\t\t" + parameters.get(feature)+"\n";
						//System.out.println(parameters.get(feature));
				}
				return output;
		}
		
}
