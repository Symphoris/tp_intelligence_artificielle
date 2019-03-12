package candidateelimination;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//Contrat de codeage pour le format de fichier en lecture de données d'expérience
public interface DataReader {
	public List<Map<String, Object>> read(String fileName) throws IOException;
}
