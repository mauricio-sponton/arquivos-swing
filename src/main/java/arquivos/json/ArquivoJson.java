package arquivos.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import arquivos.txt.Pessoa;

public class ArquivoJson {

	
	private static final String LOCAL_ARQUIVO = "src/main/resources/arquivo.json";

	public void escreverArquivo(String json) throws IOException {
		
		File arquivo = criarArquivo();
		var writer = new FileWriter(arquivo);
		
		writer.write(json);
		
		writer.flush();
		writer.close();
		
	}
	
	public List<Pessoa> lerArquivo() throws FileNotFoundException {
		FileReader fileReader = new FileReader(LOCAL_ARQUIVO);
		JsonArray jsonArray = (JsonArray) JsonParser.parseReader(fileReader);
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		for(JsonElement jsonElement : jsonArray) {
			
			Pessoa pessoa = new Gson().fromJson(jsonElement, Pessoa.class);
			pessoas.add(pessoa);
		}
		
		return pessoas;
	}
	
	private File criarArquivo() throws IOException {
		File arquivo = new File(LOCAL_ARQUIVO);
		
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		return arquivo;
	}
}
