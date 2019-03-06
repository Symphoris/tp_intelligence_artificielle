package candidateelimination;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataReader {
	public List<Map<String, Object>> read(String filename) throws IOException;

}
