package com.github.rafaelfqueiroz.rbtree.domain;

public class Data {

	/*
	 * Palavra de cada linha do arquivo de entrada
	 */
	private String word;
	
	/**
	 * Operacao a ser realizada na palavra.
	 * 0 - Deletar
	 * 1 - Inserir
	 */
	private int operation;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}
	
	public boolean isInsert() {
		return operation == 1;
	}
	
	public boolean isDelete() {
		return operation == 0;
	}
	
	@Override
	public String toString() {
		return getWord() + "\t\t\t(" + getOperation() + ")";
	}
	
}
