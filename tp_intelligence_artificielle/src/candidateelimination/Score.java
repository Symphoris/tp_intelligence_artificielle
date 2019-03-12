package candidateelimination;

public enum Score {
	NONE("none", -1),
	ZERO("value",0),
	ONE("value", 1),
	TWO("value", 2),
	THREE("value", 3),
	FOUR("value", 4),
	FIVE("value", 5),
	SIX("value", 6),
	SEVEN("value", 7),
	EIGHT("value", 8),
	NINE("value", 9),
	ALL("all", 10);
	
	protected String label;
	protected Integer value;
	
	private Score(String label, Integer value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	protected static Score getScore(String val) {
		Score score = Score.NONE;
		for (Score s : Score.values()) {
			if(s.value.equals(val)) {
				score = s;
			}
		}
		return score;
	}
}