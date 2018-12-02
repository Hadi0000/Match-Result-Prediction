package predict;

import java.util.LinkedHashMap;

public class Evolution {

	public static PredictionModel gradientDescent(DataToHandle[] inputVectors, String TotalRun, double alpha) {
		// TODO Auto-generated method stub
		//ScaleFeature scaledData = standardise(inputVectors);
		
		double[][] train = new double[inputVectors[0].size()+1][inputVectors.length];
		for(int i=0; i<inputVectors.length; i++) {
				train[0][i] = 1;
				train[train.length -1][i] = inputVectors[i].getFeature(TotalRun);
				int j=1;
				for(String feature : inputVectors[i].getFeatures()) {
						if(!feature.equals(TotalRun)) {
								train[j][i] = inputVectors[i].getFeature(feature);
								j++;
						}
				}
		}
		
		//System.out.println(inputVectors[0].size());
		double[] coefficient = new double[inputVectors[0].size()];
		double[] temps = new double[coefficient.length];
		double thresHold = 0;
		
		do {	
			for(int i=0; i<coefficient.length; i++) {
					temps[i] = coefficient[i] - (alpha * ((double)1/train.length)*evaluateCost(coefficient, train, i));
					
					thresHold += Math.abs(coefficient[i]-temps[i]);
			}
			
			for(int i=0; i<coefficient.length; i++) {
					coefficient[i] = temps[i];
					
			}
			//System.out.println();
			
			
			//a++;
		}while(thresHold> 1E-1);
		
		/*for(int i=0; i<coefficient.length; i++) {
			System.out.printf("%f\t",coefficient[i]);
		}*/
			
		//deStandardise(scaledData, inputVectors, coefficient); 
		
		LinkedHashMap<String, Double> parameters = new LinkedHashMap<String, Double>();
		parameters.put("Intercepts", coefficient[0]);
		int j=1;
		
		for(String feature : inputVectors[1].getFeatures()) {
				if(!feature.equals(TotalRun)) {
						parameters.put(feature, coefficient[j]);
						//System.out.println(feature + "  ");
						//System.out.printf("%f\n", coefficient[j]);
						//System.out.println();
						j++;
				}
		}
		
		PredictionModel output = new PredictionModel(parameters, TotalRun, 0);
		//output.rSquared = calculateRSquared(inputVectors, output);
		return output;
	}
	
	/*private static class ScaleFeature {
		public DataToHandle[] observations;
		public double[] xbars;
		public double[] sigmas;
		
		public ScaleFeature(DataToHandle[] observations, double[] xbars, double[] sigmas) {
			// TODO Auto-generated constructor stub
			this.observations = observations;
			this.xbars = xbars;
			this.sigmas = sigmas;
		}
			
		
	}*/

	/*private static ScaleFeature standardise(DataToHandle[] inputVectors) {
		// TODO Auto-generated method stub
		double[] xbars = new double[inputVectors[0].size()];
		double[] sigmas = new double[inputVectors[0].size()];
		
		for(int i=0; i<inputVectors.length; i++) {
			int j=0;
			for(String feature : inputVectors[i].getFeatures()) {
				xbars[j] += inputVectors[i].getFeature(feature);
				j++;
			}
		}
		
		for(int i=0; i<xbars.length; i++) {
			xbars[i] = xbars[i]/inputVectors.length;
		}
		
		for(int i=0; i<inputVectors.length; i++) {
			int j=0;
			for(String feature : inputVectors[i].getFeatures()) {
				sigmas[j] += Math.pow(inputVectors[i].getFeature(feature)-xbars[j], 2);
			}
		}
		
		for(int i=0; i<sigmas.length; i++) {
			sigmas[i] = Math.sqrt(sigmas[i]/inputVectors.length);
		}
		
		for(int i=0; i<inputVectors.length; i++) {
			int j=0;
			for(String feature : inputVectors[i].getFeatures()) {
				inputVectors[i].putFeature(feature, (inputVectors[i].getFeature(feature)-xbars[j])/sigmas[j]);
				j++;
			}
		}
		
		ScaleFeature output = new ScaleFeature(inputVectors, xbars, sigmas);
		return output;
		
		
	}*/
	
	/*private static void deStandardise(ScaleFeature scaledData, DataToHandle[] inputVectors, double[] coefficient) {
		for(int i=1; i<coefficient.length; i++) {
			coefficient[0] -= coefficient[i]*(scaledData.xbars[i-1]/scaledData.sigmas[i-1]);
			coefficient[i] = (coefficient[i]*scaledData.sigmas[scaledData.sigmas.length-1])/scaledData.sigmas[i-1];
		}
		
		coefficient[0] *= scaledData.sigmas[scaledData.sigmas.length-1];
		coefficient[0] += scaledData.xbars[scaledData.xbars.length-1];
		
		for(int i=0; i<inputVectors.length; i++) {
			int j=0;
			for(String feature : inputVectors[i].getFeatures()) {
				inputVectors[i].putFeature(feature, ((inputVectors[i].getFeature(feature)*scaledData.sigmas[j])+scaledData.xbars[j]));
				j++;
			}
		}
	}*/

	/*private static double calculateRSquared(DataToHandle[] inputVectors, PredictionModel output) {
		// TODO Auto-generated method stub
			double avgY = 0;
			for(int i=0; i<inputVectors.length; i++) {
					avgY += inputVectors[i].getFeature(output.dVariable);
			}
			avgY /= inputVectors.length;
			
			double rss = 0;
			double tss = 0;
			for(int i=0; i<inputVectors.length; i++) {
					rss += Math.pow((inputVectors[i].getFeature(output.dVariable) - output.predict(inputVectors[i])), 2);
					tss += Math.pow((inputVectors[i].getFeature(output.dVariable)-avgY), 2);
			}
		return (1 - rss/tss);
	}*/

	private static double evaluateCost(double[] coefficient, double[][] train, int featureIndex) {
		// TODO Auto-generated method stub
		double result = 0;
		for(int i=0; i<train[0].length; i++) {
				double error = 0;
				for(int j=0; j<train.length-1; j++) {
						error += train[j][i]*coefficient[j]; 
				}
				
				error -= train[train.length - 1][i];
				error *= train[featureIndex][i];
				
				result += error;
		}
		
		return result;
	}

}
