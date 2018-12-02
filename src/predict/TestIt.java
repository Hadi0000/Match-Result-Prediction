package predict;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestIt {
	public static DataToHandle[] read(String filePath) {
		
		BufferedReader reader = null;
		DataToHandle[] dataArray = null;
		
		String text = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			int size =-1;
			for(int i=0; i<1200; i++) {
				text = reader.readLine();
			}
			while((text = reader.readLine())!=null) {
					size++;
			}
			
			dataArray = new DataToHandle[size];
			reader  = new BufferedReader(new FileReader(filePath));
			String[] features = reader.readLine().split(",");
			int index = 0;
			while((text = reader.readLine())!=null) {
					String[] values = text.split(",");
					dataArray[index] = new DataToHandle();
					for(int i=0; i<features.length; i++) {
							dataArray[index].putFeature(features[i], Double.parseDouble(values[i]));
					}
					index++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataArray;
}
}
