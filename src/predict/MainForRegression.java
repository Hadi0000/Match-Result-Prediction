package predict;

public class MainForRegression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			DataToHandle[] inputVectors = DataSetReader.read("G:\\6th Semester\\AI\\DataSetForProject_3\\trainingData.csv");
			double alpha = 0.0000000001;
			//double alpha = 0.0001;
			PredictionModel pm = Evolution.gradientDescent(inputVectors, "TotalRun", alpha);
			//DataToHandle[] testVector = TestIt.read("G:\\6th Semester\\AI\\DataSetForProject_3\\test1.csv");
			//System.out.println(testVector[0].getFeature("TotalRun"));
			//System.out.println);
			double value = pm.predict(inputVectors[92]);
			//System.out.println("Team ----------Run after 10 over----------wicket---------Opponent------Total Run");
			
			System.out.println(value);
	}

}
