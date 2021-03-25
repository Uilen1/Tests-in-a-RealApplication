package model.utilities.json;

import model.utilities.excel.DataDictionary;
import model.utilities.excel.NameRunTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonData {

	private List<Object> testCase = new ArrayList<Object>();
	private JSONObject jsonObject;

	/********* Obter Arquivo JSON *********/

	// criando método que recebe o nome do arquivo json e o nome do grupo de dados desejado
	public JSONObject getJsonDataObject(String jsonFileName, String dataGroup) {

		// criando um parses para utilizamos no momento da leitura do arquivo json
		JSONParser parser = new JSONParser();

		// criando um objeto json que vai receber os dados do arquivo json
		JSONObject jsonDataObject = null;
		try {
			// inserindo o arquivo json em um objeto
			Object jsonFileObject = parser.parse(new FileReader(System.getProperty("user.dir") + File.separator + "data" +
					File.separator + "json" + File.separator + jsonFileName+".json"));

			// convertendo o objeto com o arquivo json para o formato de objeto json
			jsonDataObject = (JSONObject) jsonFileObject;
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		// garantindo que o objeto json não está nulo
		assert jsonDataObject != null;

		// retornando o objeto json, filtrando pelo grupo de dados desejado
		return (JSONObject) jsonDataObject.get(dataGroup);
	}

	public List<Object> getJsonData(String jsonFileName, String dataGroup){
		try {
				jsonObject = getJsonDataObject(jsonFileName, dataGroup);
				Object[] obj = new Object[2];
				obj[0] = jsonObject.get("nameTest");
				obj[1] = jsonObject;
				testCase.add(obj);
		} catch (Exception e) {
			System.out.println("Não possível obter os dados do arquivo JSON! " + e.getMessage());
		}
		return testCase;
	}
	
}
