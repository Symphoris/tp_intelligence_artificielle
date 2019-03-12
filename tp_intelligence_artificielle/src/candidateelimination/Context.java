package candidateelimination;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

//Singleton
public enum Context {
	instance;
	
	protected List<Map<String, Object>> trainingData;
	protected List<Map<String, Object>> attributeDomains;
	protected List<Map<String, Object>> classes;
	
	Context(){
		DataReader dr = new CSVDataReader();
		try {
			trainingData = dr.read("ressources/zoo.csv");
			attributeDomains= dr.read("ressources/schema.csv");
			classes = dr.read("ressources/class.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//On cherche ranger tous les animaux dans  des catégories déjà établies
	public void findHypothesis() {
		final int start = Integer.parseInt(classes.get(0).get("Class_Number").toString());
		final int end = Integer.parseInt(classes.get(classes.size() -1).get("Class_Number").toString());
		for (int i = start; i < end; i++) {
			System.out.println("Pour la classe "+i+" on classe les expériences");
			//trier les donées d'expérineces de telle sorte que les exemples positifs soient en premier et les contre exmples en dernier
			Collections.sort(trainingData, new ExperienceComparator(i));
			Concept concept =new Concept(i);
			//TODO
			for (Map<String, Object> instance : trainingData) {
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
			System.out.println(Context.instance.trainingData);
			System.out.println(Context.instance.attributeDomains);
			System.out.println(Context.instance.classes);
		
	}
}
