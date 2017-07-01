package com.github.rafaelfqueiroz.rbtree.utils;

import com.github.rafaelfqueiroz.rbtree.domain.RBElement;
import com.github.rafaelfqueiroz.rbtree.domain.RBTree;

public class SeacherUtils {

	/**
	 * Retorna o menor elemento da árvore a partir da raiz dela.
	 * Busca o elemento mais à esquerda da árvore.
	 * @param tree
	 * @return
	 */
	public static RBElement treeMinimum(RBTree tree) {
		RBElement element = tree.getRoot();
		return treeMinimum(element);
	}
	
	public static RBElement treeMinimum(RBElement element) {
		while (element.getLeft() != null) {
			element = element.getLeft();
		}
		
		return element;
	}
	
	/**
	 * Busca pelo elemento que possue o valor da chave igual a @param{word}
	 * @param tree
	 * @param word
	 * @return
	 */
	public static RBElement search(RBTree tree, String word) {
		RBElement elementSearched = tree.getRoot();
		while (elementSearched != null && !elementSearched.getKey().equals(word)) {
			if (elementSearched.getKey().compareTo(word) < 0) {
				elementSearched = elementSearched.getRight();
			} else {
				elementSearched = elementSearched.getLeft();
			}
		}
		return elementSearched;
	}
}
