package candidateelimination;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum Context {
	instance;
	protected List<Map<String,Object>> trainingData;
	protected List<Map<String,Object>> classes;
	protected List<Map<String,Object>> attributeDomains;
	Context(){
		DataReader dr = new CSVDataReader();
		try {
			trainingData = dr.read("resources/zoo.csv");
			attributeDomains = dr.read("resources/class.csv");
			classes = dr.read("resources/class.csv");
			
		}
		catch(IOException e) {}
		
	}
	public void findHypothesis() {
		final int start= Integer.parseInt(classes.get(0).get("Class_Number").toString());
		final int end =  Integer.parseInt(classes.get(classes.size()-1).get("Class_Number").toString());
		for(int i= start; i<= end;i++) {
			System.out.println("pour la classe "+i+"on classe les experiences");
			//tirer les données d'exxperiences
			Collections.sort(trainingData,new ExperienceComparator());
		}
	}
	

}
