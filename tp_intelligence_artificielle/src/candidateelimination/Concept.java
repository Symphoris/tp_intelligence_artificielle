package candidateelimination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Concept {
	protected int currentPositiveClassification;
	protected Hypothesis specificBoundary;
	protected List<Hypothesis> generiqucBoundaries;
	/**
	 * @param currentPositiveClassification
	 */
	public Concept(int currentPositiveClassification) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
		final int nbConstraints = Context.instance.attributeDomains.get(0).size();
		this.specificBoundary = new Hypothesis(nbConstraints);
		for(String key: specificBoundary.getAttributes().keySet()) {
				specificBoundary.getAttributes().put(key,Score.NONE);
		}

		
		this.generiqucBoundaries = new ArrayList<>();
		
		for(String key: specificBoundary.getAttributes().put(key,Score.NONE));
		
	}
	public boolean addTrainingInstance (Map<String,Object> trainingInstance) {
		final String classValue = trainingInstance.get("class_type").toString();
		if(currentPositiveClassification == Integer.parseInt(classValue));
		//exemple positif
		  for(String key: Context.instance.attributeDomains.get(0).keySet()) {
			  if(specificBoundary.getAttributes().get(key).equals(Score.ALL))
				  continue;
			  else if (!specificBoundary.getAttributes().get(key).equals(trainingInstance.get(key))){
				  if(specificBoundary.getAttributes().get(key).equals(Score.NONE)) {
					  String val= trainingInstance.get(key).toString();
					   Score score=  Score.getScore(val);
					   specificBoundary.getAttributes().put(key,score);
				  }
				  else {
					  specificBoundary.getAttributes().put(key, Score.ALL);
				  }
			  }
			  
		  }
		
	}
	 else {
		
	}

	
	/**
	 * @param currentPositiveClassification
	 * @param specificBoundary
	 * @param generiqucBoundaries
	 */
	public Concept(int currentPositiveClassification, Hypothesis specificBoundary,
			List<Hypothesis> generiqucBoundaries) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
		this.specificBoundary = specificBoundary;
		this.generiqucBoundaries = generiqucBoundaries;
	}
	

}
