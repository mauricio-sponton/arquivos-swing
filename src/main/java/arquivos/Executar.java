package arquivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import arquivos.excel.ArquivoExcel;
import arquivos.txt.ArquivoTXT;
import arquivos.txt.Pessoa;

public class Executar {

	public static void main(String[] args) throws IOException {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		String tipoArquivo = JOptionPane.showInputDialog("Qual o tipo do arquivo? 1 -> TXT | 2 -> Excel");
		String quantidadePessoa = JOptionPane.showInputDialog("Quantas pessoas deseja inserir no arquivo?");
		int quantidadePessoaConvertida = Integer.parseInt(quantidadePessoa);

		for (int i = 0; i < quantidadePessoaConvertida; i++) {
			String nomePessoa = JOptionPane.showInputDialog("Nome");
			String emailPessoa = JOptionPane.showInputDialog("Email");
			String idadePessoa = JOptionPane.showInputDialog("Idade");

			Pessoa pessoa = new Pessoa(nomePessoa, emailPessoa, Integer.parseInt(idadePessoa));
			pessoas.add(pessoa);

		}

		List<Pessoa> lista = null;

		if (tipoArquivo.equals("1")) {
			
			var arquivo = new ArquivoTXT();
			arquivo.escreverArquivo(pessoas);

			lista = arquivo.lerArquivo();

		} else if (tipoArquivo.equals("2")) {
			
			var arquivoExcel = new ArquivoExcel();
			
			arquivoExcel.escreverArquivo(pessoas);
			arquivoExcel.adicionarCelula();
			
			lista = arquivoExcel.lerArquivo();

		} else {

		}

		for (Pessoa p : lista) {
			System.out.println("Nome: " + p.getNome());
			System.out.println("Email: " + p.getEmail());
			System.out.println("Idade: " + p.getIdade());
			System.out.println("----------------------------------------------------------");
		}

	}
}
