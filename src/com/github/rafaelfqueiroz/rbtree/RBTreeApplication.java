package com.github.rafaelfqueiroz.rbtree;

import java.util.List;

import com.github.rafaelfqueiroz.rbtree.builder.RBTreeBuilder;
import com.github.rafaelfqueiroz.rbtree.datasource.FileDataSource;
import com.github.rafaelfqueiroz.rbtree.domain.Data;
import com.github.rafaelfqueiroz.rbtree.domain.RBElement;
import com.github.rafaelfqueiroz.rbtree.domain.RBTree;
import com.github.rafaelfqueiroz.rbtree.utils.PrinterUtils;
import com.github.rafaelfqueiroz.rbtree.utils.SeacherUtils;

public class RBTreeApplication {

	public static void main(String[] args) {
		try {
			System.out.println("--------------------------------------------------------");
			System.out.println("------ PROGRAMA PARA EXECUÇÃO DA ÁRVORE RUBRO NEGRA ----");
			System.out.println("--------------------------------------------------------");
			
			String path = args[0];
			
			if (path == null || path.isEmpty()) {
				throw new Exception("Não foi possível carregar o arquivo para o caminho especificado.");
			}
			
			RBTree tree = new RBTree();
			RBTreeBuilder treeBuilder = new RBTreeBuilder();
			//FileDataSource dataSource = new FileDataSource("resources/dicionario2.txt");
			FileDataSource dataSource = new FileDataSource(path);
			List<Data> database = dataSource.loadDataSource();
			
			System.out.println("\n\n------------------ INICIANDO EXECUÇÃO ------------------");
			for (Data line : database) {
				if (line.getWord().isEmpty()) {
					break;
				}
				RBElement element = new RBElement(line.getWord());
				if (line.isInsert()) {
					if (SeacherUtils.search(tree, element.getKey()) == null) {
						//System.out.println("Executando inserção da palavra: " + line.getWord());
						treeBuilder.rbInsert(tree, element);
					} else {
						System.out.println("ERROR: palavra " + element.getKey() + "já foi inserida na árvore.");
					}
						
				}
				if (line.isDelete()) {
					RBElement eSearching = SeacherUtils.search(tree, element.getKey());
					if (eSearching != null) {
						//System.out.println("Executando deleção da palavra: " + line.getWord());
						treeBuilder.rbDelete(tree, eSearching);
						PrinterUtils.rbPrint(tree);
						PrinterUtils.rbCheck(tree);
					} else {
						System.out.println("ERROR: palavra " + element.getKey() + " não está na árvore.");
					}
				}
			}
			System.out.println("\n\n------------------ RELATÓRIO FINAL ------------------");
			PrinterUtils.rbPrint(tree);
			PrinterUtils.rbCheck(tree);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
