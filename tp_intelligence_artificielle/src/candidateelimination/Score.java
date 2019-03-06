package candidateelimination;

public enum Score {
	NONE("none",-1),
	
	ZERO("value",0),
	ONE("value",1),
	TWO("value",2),
	THREE("value",3),
	FOUR("value",4),
	FIVE("value",5),
	SIX("value",6),
	SEVEN("value",7),
	EIGHT("value",8),
	ALL("all",10);
	
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	protected String label;
	protected Integer value;
	/**
	 * @param label
	 * @param value
	 */
	private Score(String label, Integer value) {
		this.label = label;
		this.value = value;
	}
	protected static Score getScore(String val) {
		Score score = Score.NONE;
		  for (Score:Score.values()) {
			  if(s.value.equals(val))
				  score=s; 
		  }
		  return score;
	}

}
