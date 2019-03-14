package candidateelimination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

//représente le résulatt de la phase d'apprentissage automatique
public class Concept {
	protected int currentPositiveClassification;
	protected Hypothesis specificBoundary;
	protected List<Hypothesis> genericBoundaries;
	private int nbConstraints;
	
	public Concept(int currentPositiveClassification) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
		//nombre d'attributs par hypothèses
		nbConstraints = Context.instance.attributeDomains.get(0).size();
		this.specificBoundary = new Hypothesis(Score.NONE);
		//System.out.println("Concept specific Boundary = "+specificBoundary);
		this.genericBoundaries = new ArrayList<>();
		Hypothesis motGeneral = new  Hypothesis(Score.ALL);
		//System.out.println("Concept motGeneral = "+motGeneral);
		genericBoundaries.add(motGeneral);
	}
	
	/*Le coeur de l'algo d'apprentissage
	 * @param trainingInstance represente une ligne du fichier zoo.csv  ou une experience e*/
	public boolean addTrainingInstance(SortedMap<String, Object> trainingInstance) {
		final String classValue = trainingInstance.get("class_type").toString();
		if(currentPositiveClassification == Integer.parseInt(classValue)) {
			//exemple positif
			for(String key : Context.instance.attributeDomains.get(0).keySet()) {//S
				if(key.equals("animal_name")) {
					continue;
				}
				//L'attribut Scpécific à déjà pour valeur ALL, il ne sert plus
				if(specificBoundary.getAttributes().get(key).equals(Score.ALL)) {
					continue;	
				}
				String val = trainingInstance.get(key).toString();
				Score score = Score.getScore(val); 
				//L'attribut a une valeur différente de celle de l'expérience courante
				if(!specificBoundary.getAttributes().get(key).equals(score)) {
					if(specificBoundary.getAttributes().get(key).equals(Score.NONE)) {
						specificBoundary.getAttributes().put(key, score);
					}
					else {
						specificBoundary.getAttributes().put(key, Score.ALL);
					}
				}
			}
		}
		else {
			//contre exemple ou exemple negatif
			System.out.println("exemple négatif");
			for(int index=0; index < genericBoundaries.size(); index++ ) {//G
				Hypothesis g = genericBoundaries.get(index);
				System.out.println("training Insatnce = "+trainingInstance);
				System.out.println("g.attributes="+g.attributes);
				if(!g.isConsistent(trainingInstance)) {
					List<Hypothesis> minSpec = minimalSpecialization(g, trainingInstance);
					System.out.println("minSpec= "+minSpec);
					genericBoundaries.remove(g);
					index--;
					//ajouter minSpec
					for (Hypothesis ms : minSpec) {
						genericBoundaries.add(ms);
					}
					final int n = genericBoundaries.size();
					boolean[] flag = new boolean[n];
					for(int j = 0; j<n; j++) {
						for(int k=j+1;k<n;k++) {
							Hypothesis h1 = genericBoundaries.get(j);
							Hypothesis h2 = genericBoundaries.get(k);
							if(h1.isLessGeneralTham(h2)) {
								flag[j]= true;
								break;
							}else if(h2.isLessGeneralTham(h1)) {
								flag[k]= true;
							}
						}
					}
					for(int j = n-1; j>0; j--) {
						if(flag[j]) {
							genericBoundaries.remove(j);
						}
					}
				}
				
			}
			if(!specificBoundary.isConsistent(trainingInstance)) {
				System.out.println("Current Specific Boudary: ");
				System.out.println("specicifBoudary.attributes");
				System.out.println("INCONSISITENT DATA");
				System.out.println(trainingInstance);
				return false; //Concept cannot be learned
			}
		}
		return true;
	}
private List<Hypothesis> minimalSpecialization(Hypothesis h, SortedMap<String, Object> trainingInstance) {
		List<Hypothesis> minSpec = new ArrayList<>();
		for (String key1 : Context.instance.attributeDomains.get(0).keySet()) {
			if(key1.equals("animal_name")) {
				continue;
			}
			if(h.getAttributes().get(key1).equals(Score.ALL)){
				String values = Context.instance.attributeDomains.get(0).get(key1).toString();
				List<String> valueList = Arrays.asList(values.substring(1, values.length()-1).split(";"));
				for(String key2: valueList) {
					Hypothesis ms = new Hypothesis(Score.ALL);
					for(String key3 : Context.instance.attributeDomains.get(0).keySet()) {
						if(key3.equals("animal_names")) {
							continue;
						}
						ms.getAttributes().put(key3, h.getAttributes().get(key3));
					}
					Score score = Score.getScore(key2);
					ms.getAttributes().put(key1, score);
					if(ms.isConsistent(trainingInstance)) {
						if(specificBoundary.isLessGeneralTham(ms)) {
							minSpec.add(ms);
						}
					}
				}
			}
		}
		return minSpec;
	}

	//TODO construire la methode min qui retourne la plus petite hypothese à partir d'un ensemble
//et d'une experience
	public void printSpecificBoudary(){
		System.out.println("Specific Bundary for Class "+ currentPositiveClassification);
		System.out.println(specificBoundary.attributes);
	}

	public void printGenericBoundary(){
		System.out.println("Generic Boudary for Class " +currentPositiveClassification);
		for(Hypothesis h: genericBoundaries){
			System.out.println(h.attributes);
		}
	}
}