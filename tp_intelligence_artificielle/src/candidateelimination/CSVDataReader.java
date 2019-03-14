package candidateelimination;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CSVDataReader implements DataReader {

	
	@Override
	public List<SortedMap<String, Object>> read(String fileName) throws IOException {
		Path inputPath = FileSystems.getDefault().getPath(fileName);
		
		if(Files.exists(inputPath)) {
			List<String> lines = Files.readAllLines(inputPath);
			List<SortedMap<String, Object>> results = new ArrayList<>();
			List<String> headers = Arrays.asList(lines.get(0).split(";"));
			
			for (int index = 1; index < lines.size(); index++) {
				
				SortedMap<String, Object> instance = new TreeMap<>(new Comparator<String>(){

					@Override
					public int compare(String h1, String h2) {
						Integer i2 = headers.indexOf(h2);
						Integer i1 = headers.indexOf(h1);
						return i1.compareTo(i2);

					}
					
				});
				String line = lines.get(index);
				String[] values = line.split(";");
				
				for (int h = 0; h < headers.size(); h++) {
					if(h<headers.size()-1) {
						instance.put(headers.get(h), values[h]);
					}
					else {
						if(h < values.length-1) {
							instance.put(headers.get(h), Arrays.copyOfRange(values,h,values.length));
						}
						else {
							instance.put(headers.get(h), values[h]);
						}
							
					}
				}
				results.add(instance);
			}
			return results;
		}else {
			throw new IOException("Invalid file name");
		}
		
	}
}
