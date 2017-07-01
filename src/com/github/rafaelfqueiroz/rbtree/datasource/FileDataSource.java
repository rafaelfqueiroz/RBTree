package com.github.rafaelfqueiroz.rbtree.datasource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.github.rafaelfqueiroz.rbtree.domain.Data;

public class FileDataSource {

	private String sourcePath;
	
	public FileDataSource() {	}
	
	public FileDataSource(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	@SuppressWarnings("resource")
	public List<Data> loadDataSource() throws Exception {
		try {
			System.out.println("------------------ CARREGANDO DADOS DO ARQUIVO ------------------");
			BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
			List<Data> dataBase = new ArrayList<Data>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				Data lineData = new Data();
				String[] lineContent = line.split(" ");
				lineData.setWord(lineContent[0].trim());
				lineData.setOperation(Integer.parseInt(lineContent[1]));
				System.out.println(lineContent[0] + "\t\t\t\t (" + lineContent[1] + ")");
				dataBase.add(lineData);
			}
			
			return dataBase;
		} catch (FileNotFoundException e) {
			throw new Exception("Não foi possível carregar o arquivo para o caminho especificado.");
		}
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	
}
