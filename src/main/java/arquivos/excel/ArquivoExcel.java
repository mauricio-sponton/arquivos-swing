package arquivos.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import arquivos.txt.Pessoa;

public class ArquivoExcel {

	private static final String LOCAL_ARQUIVO = "src/main/resources/arquivo-excel.xls";

	public void escreverArquivo(List<Pessoa> pessoas) throws IOException {

		File arquivo = criarArquivo();
		var workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Planilha de pessoas");

		int numeroLinha = 0;

		for (Pessoa p : pessoas) {
			Row linha = sheet.createRow(numeroLinha++);
			int celula = 0;

			Cell cellNome = linha.createCell(celula++);
			cellNome.setCellValue(p.getNome());

			Cell cellEmail = linha.createCell(celula++);
			cellEmail.setCellValue(p.getEmail());

			Cell cellIdade = linha.createCell(celula++);
			cellIdade.setCellValue(p.getIdade());
		}

		FileOutputStream saida = new FileOutputStream(arquivo);
		workbook.write(saida);
		workbook.close();
		saida.flush();
		saida.close();

	}

	public List<Pessoa> lerArquivo() throws IOException {

		FileInputStream entrada = new FileInputStream(LOCAL_ARQUIVO);
		var workbook = new HSSFWorkbook(entrada);
		HSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> iterator = sheet.iterator();

		List<Pessoa> pessoas = new ArrayList<>();

		while (iterator.hasNext()) {
			Row linha = iterator.next();
			Iterator<Cell> cells = linha.iterator();

			Pessoa pessoa = new Pessoa();

			while (cells.hasNext()) {
				Cell cell = cells.next();

				switch (cell.getColumnIndex()) {
				case 0:
					pessoa.setNome(cell.getStringCellValue());
					break;

				case 1:
					pessoa.setEmail(cell.getStringCellValue());
					break;
				case 2:
					pessoa.setIdade(Double.valueOf(cell.getNumericCellValue()).intValue());
					break;
				}
			}
			pessoas.add(pessoa);
		}

		workbook.close();
		entrada.close();

		return pessoas;
	}
	
	public void adicionarCelula() throws IOException {
		FileInputStream entrada = new FileInputStream(LOCAL_ARQUIVO);
		var workbook = new HSSFWorkbook(entrada);
		HSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> iterator = sheet.iterator();
		
		while(iterator.hasNext()) {
			Row linha = iterator.next();
			int numeroCelulas = linha.getPhysicalNumberOfCells();
			
			linha.createCell(numeroCelulas);
			linha.getCell(numeroCelulas).setCellValue(300+100);
		}
		
		entrada.close();
		
		FileOutputStream saida = new FileOutputStream(LOCAL_ARQUIVO);
		workbook.write(saida);
		workbook.close();
		
		saida.flush();
		saida.close();
	}

	private File criarArquivo() throws IOException {
		File arquivo = new File(LOCAL_ARQUIVO);

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
		return arquivo;
	}
}
