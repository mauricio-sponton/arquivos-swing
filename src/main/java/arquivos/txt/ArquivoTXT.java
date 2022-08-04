package arquivos.txt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivoTXT {
	
	private static final String LOCAL_ARQUIVO = "src/main/resources/arquivo.txt";

	public void escreverArquivo(List<Pessoa> pessoas) throws IOException {
		
		File arquivo = criarArquivo();
		var writer = new FileWriter(arquivo);
		
		for(Pessoa p : pessoas) {
			writer.write(p.getNome() + ";" + p.getEmail() + ";" + p.getIdade() + "\n");
		}
		
		writer.flush();
		writer.close();
		
	}
	
	public List<Pessoa> lerArquivo() throws FileNotFoundException {
		FileInputStream entradaArquivo = new FileInputStream(LOCAL_ARQUIVO);
		Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		while(lerArquivo.hasNext()) {
			String linha = lerArquivo.nextLine();
			
			if(linha != null && !linha.isEmpty()) {
				
				String[] dados = linha.split(";");
				
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(dados[0]);
				pessoa.setEmail(dados[1]);
				pessoa.setIdade(Integer.parseInt(dados[2]));
				
				pessoas.add(pessoa);
			}
		}
		
		lerArquivo.close();
		
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
