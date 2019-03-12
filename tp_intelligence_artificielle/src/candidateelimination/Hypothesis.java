package candidateelimination;

import java.util.HashMap;
import java.util.Map;

public class Hypothesis {
	protected Map<String, Score> attributes;
	
	public Hypothesis(final int nbConstraints) {
		this.attributes = new HashMap<>(nbConstraints);
	}

	public Map<String, Score> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Score> attributes) {
		this.attributes = attributes;
	}

	public boolean isConsistent(Map<String, Object> trainingInstance, String classValue) {
		int predict = -1;
		for (String key : attributes.keySet()) {
			final Score s = Score.getScore(trainingInstance.get(key).toString());
			if(attributes.get(key).equals(Score.NONE)){
				predict = 0;
			}			
			else if(attributes.get(key).equals(Score.ALL)){
				continue;
			}
			if(!attributes.equals(Score.getScore(trainingInstance.get(key).toString()))){
				predict = 0;
			}
			return predict == Integer.parseInt(classValue);
		}
				
		return false;
	}
}
