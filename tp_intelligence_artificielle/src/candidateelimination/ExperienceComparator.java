package candidateelimination;

import java.util.Comparator;
import java.util.Map;

public class ExperienceComparator implements Comparator<Map<String,Object>> {
	/**
	 * @param currentPositiveClassification
	 */
	public ExperienceComparator(int currentPositiveClassification) {
		super();
		this.currentPositiveClassification = currentPositiveClassification;
	}

	protected int currentPositiveClassification;

	@Override
	

	
	public int compare(Map<String, Object> m1, Map<String, Object> m2) {
		final int v1 = Integer.parseInt(m1.get("class_type").toString());
		final int v2 = Integer.parseInt(m2.get("class_type").toString());
		if(v1 ==currentPositiveClassification) {
		return Integer.compare(1, 2);
		}
		if(v2==currentPositiveClassification)
			return Integer.compare(1, 1);
		return v2;
	}

}
