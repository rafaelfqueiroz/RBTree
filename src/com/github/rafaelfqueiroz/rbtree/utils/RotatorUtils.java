package com.github.rafaelfqueiroz.rbtree.utils;

import com.github.rafaelfqueiroz.rbtree.domain.RBElement;
import com.github.rafaelfqueiroz.rbtree.domain.RBTree;

public class RotatorUtils {

	public static void leftRotate(RBTree tree, RBElement element) {
		RBElement aux = element.getRight();
		element.setRight(aux.getLeft());
		
		if (aux.getLeft() != null) {
			aux.getLeft().setParent(element);
		}
		
		aux.setParent(element.getParent());
		
		if (element.getParent() == null) {
			tree.setRoot(aux);
		} else if (element.equals(element.getParent().getLeft())) {
			element.getParent().setLeft(aux);
		} else {
			element.getParent().setRight(aux);
		}
		
		aux.setLeft(element);
		element.setParent(aux);
	}

	public static void rightRotate(RBTree tree, RBElement element) {
		RBElement aux = element.getLeft();
		element.setLeft(aux.getRight());
		
		if (aux.getRight() != null) {
			aux.getRight().setParent(element);
		}
		
		aux.setParent(element.getParent());
		
		if (element.getParent() == null) {
			tree.setRoot(aux);
		} else if (element.equals(element.getParent().getRight())) {
			element.getParent().setRight(aux);
		} else {
			element.getParent().setLeft(aux);
		}
		
		aux.setRight(element);
		element.setParent(aux);
	}
	
	public static void transplant(RBTree tree, RBElement elementA, RBElement elementB) {
		if (elementA.getParent() == null) {
			tree.setRoot(elementB);
		} else if (elementA.equals(elementA.getParent().getLeft())) {//Verifica se é filho da esquerda
			elementA.getParent().setLeft(elementB);
		} else {
			elementA.getParent().setRight(elementB);
		}
		
		if (elementB != null) {
			elementB.setParent(elementA.getParent());
		}
	}
}
