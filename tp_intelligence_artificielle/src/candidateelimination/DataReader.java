package candidateelimination;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

//Contrat de codeage pour le format de fichier en lecture de données d'expérience
public interface DataReader {
	public List<SortedMap<String, Object>> read(String fileName) throws IOException;
}
