package predict;

import java.util.LinkedHashMap;
import java.util.Set;

public class DataToHandle {
		private LinkedHashMap<String, Double> features = new LinkedHashMap<String, Double>();
		
		public void putFeature(String feature, double value) {
				features.put(feature, value);
		}
		public double getFeature(String feature) {
				return features.get(feature);
		}
		public int size() {
			return features.size();
		}
		public Set<String> getFeatures(){
			return features.keySet();
		}
}
