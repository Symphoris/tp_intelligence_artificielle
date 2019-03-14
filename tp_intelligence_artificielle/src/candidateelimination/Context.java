package candidateelimination;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

//Singleton
public enum Context {
	instance;
	
	protected List<SortedMap<String, Object>> trainingData;
	protected List<SortedMap<String, Object>> attributeDomains;
	protected List<SortedMap<String, Object>> classes;
	protected CSVDataReader reader;
	
	Context(){
		DataReader reader = new CSVDataReader();
		try {
			trainingData = reader.read("ressources/zoo.csv");
			attributeDomains= reader.read("ressources/schema.csv");
			classes = reader.read("ressources/class.csv");
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
			System.out.println("\nPour la classe "+i+" on classe les expériences");
			//trier les donées d'expérineces de telle sorte que les exemples positifs soient en premier et les contre exmples en dernier
			Collections.sort(trainingData, new ExperienceComparator(i));
			Concept concept =new Concept(i);
			//boucler sur toutes les données d'expérience
			boolean conceptCanBelearned =false;
			for(SortedMap<String, Object> trainingInstance : trainingData){
				//TODO concept.addTrainingInstance
				conceptCanBelearned = concept.addTrainingInstance(trainingInstance);
				if(!conceptCanBelearned) {
					System.out.println("Concept ne peut être appris");
					break;
				}
			}
			System.out.println("conceptCanBeLearned = "+conceptCanBelearned);
			if(conceptCanBelearned) {
				concept.printSpecificBoudary();
				concept.printGenericBoundary();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
			System.out.println(Context.instance.trainingData);
			System.out.println(Context.instance.attributeDomains);
			System.out.println(Context.instance.classes);
		
	}
}
