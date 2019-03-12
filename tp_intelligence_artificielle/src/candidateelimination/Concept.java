package candidateelimination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//représente le résulatt de la phase d'apprentissage automatique
public class Concept {
	protected int currentPositiveClassification;
	protected Hypothesis specificBoundary;
	protected List<Hypothesis> genericBoundaries;
	
	public Concept(int currentPositiveClassification) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
		//nombre d'attributs par hypothèses
		final int nbConstraints = Context.instance.attributeDomains.get(0).size();
		this.specificBoundary = new Hypothesis(nbConstraints);

		for (String key : specificBoundary.getAttributes().keySet()) {
			specificBoundary.getAttributes().put(key, Score.NONE);
		}
		this.genericBoundaries = new ArrayList<>();
		Hypothesis motGeneral = new  Hypothesis(nbConstraints);
		
		for (String key : motGeneral.getAttributes().keySet()) {
			motGeneral.getAttributes().put(key, Score.ALL);
		}
		genericBoundaries.add(motGeneral);
	}
	
	/*Le coeur de l'algo d'apprentissage
	 * @param trainingInstance represente une ligne du fichier zoo.csv  ou une experience e*/
	public boolean addTrainingInstance(Map<String, Object> trainingInstance) {
		final String classValue = trainingInstance.get("class_type").toString();
		if(currentPositiveClassification == Integer.parseInt(classValue)) {
			//exemple positif
			for(String key : Context.instance.attributeDomains.get(0).keySet()) {//S
				//L'attribut Scpécific à déjà pour valeur ALL, il ne sert plus
				if(specificBoundary.getAttributes().get(key).equals(Score.ALL)) {
					continue;	
				}
				//L'attribut a une valeur différente de celle de l'expérience courante
				if(!specificBoundary.getAttributes().get(key).equals(trainingInstance.get(key))) {
					if(specificBoundary.getAttributes().get(key).equals(Score.NONE)) {
						//calcule du score associé à la valeur de l'expérience
						String val = trainingInstance.get(key).toString();
						Score score = Score.getScore(val);
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
			for(int index=0; index < genericBoundaries.size(); index++ ) {//G
				Hypothesis g = genericBoundaries.get(index);
				if(!g.isConsistent(trainingInstance, classValue)) {
					List<Hypothesis> minSpec = min(g, trainingInstance);
					genericBoundaries.remove(g);
					index--;
					//ajouter minSpec
					for (Hypothesis ms : minSpec) {
						genericBoundaries.add(ms);
					}
				}
			}
		}
		return true;
	}
//TODO construire la methode min qui retourne la plus petite hypothese à partir d'un ensemble
//et d'une experience
}
