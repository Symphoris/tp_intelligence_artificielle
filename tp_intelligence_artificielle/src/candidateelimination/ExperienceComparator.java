package candidateelimination;

import java.util.Comparator;
import java.util.Map;

public class ExperienceComparator implements Comparator<Map<String, Object>> {
	protected int currentPositiveClassification;
	
	public ExperienceComparator(int currentPositiveClassification) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
	}

	@Override
	public int compare(Map<String, Object> m1, Map<String, Object> m2) {
		final int v1 = Integer.parseInt(m1.get("class_type").toString());
		final int v2 = Integer.parseInt(m2.get("class_type").toString());
		if(v1== currentPositiveClassification) {
			return Integer.compare(1,2);//positif
		}
		if(v2== currentPositiveClassification) {
			return Integer.compare(2,1);//negatif
		}
		return Integer.compare(1,1);//0
	}
}
