package candidateelimination;

import java.util.Map;
import java.util.TreeMap;

public class Hypothesis {
	protected Map<String, Score> attributes;
	
	public Hypothesis(Score s) {
		this.attributes = new TreeMap<>(Context.instance.attributeDomains.get(0).comparator());
		for(String key : Context.instance.trainingData.get(0).keySet()) { attributes.put(key, s); }
	}

	public Map<String, Score> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Score> attributes) {
		this.attributes = attributes;
	}

	public boolean isConsistent(Map<String, Object> trainingInstance) {
		boolean predict = true;
		for (String key : attributes.keySet()) {
			final Score s = Score.getScore(trainingInstance.get(key).toString());
			if(attributes.get(key).equals(Score.NONE)){
				predict = false;
			}			
			else if(attributes.get(key).equals(Score.ALL)){
				continue;
			}
			if(!attributes.equals(Score.getScore(trainingInstance.get(key).toString()))){
				predict = false;
				break;
				
			}
			
			return !predict;
		}
				
		return predict;
	}
	public boolean isLessGeneralTham(Hypothesis h) {
		int less =0;
		for (String key : attributes.keySet()) {
			int s1 = this.attributes.get(key).getValue();
			int s2 = h.attributes.get(key).getValue();
			if(s1>s2)
				return false;
			else if (s1==s2) {
				if(s1==1)
					if(!this.attributes.get(key).equals(h.attributes.get(key)))
							return false;
			}
			else {less++;}
		}
		return less>0;
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("Hypothesis [attributes =" );
		for (String key: attributes.keySet()) {
			sb.append("("+ key+"="+attributes.get(key)+")");
			sb.append("]");
		}
		return toString();
	}
}
