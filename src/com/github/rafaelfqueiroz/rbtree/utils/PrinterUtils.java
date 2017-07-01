package com.github.rafaelfqueiroz.rbtree.utils;

import com.github.rafaelfqueiroz.rbtree.domain.RBElement;
import com.github.rafaelfqueiroz.rbtree.domain.RBTree;

public class PrinterUtils {

	public static void rbPrint(RBTree tree) {
		RBElement element = tree.getRoot();
		rbPrint(element);
	}
	
	
	private static void rbPrint(RBElement element) {
		if (element != null) {
			rbPrint(element.getLeft());
			if (element.getLeft() != null) {
				System.out.print(", ");
			}
			
			System.out.print(element.getKey());
			
			if (element.getRight() != null) {
				System.out.print(", ");
			}
			
			rbPrint(element.getRight());
			
		}
	}
	
	public static void rbCheck(RBTree tree) {
		RBElement element = tree.getRoot();
		
		System.out.println();
		
		rbCheck(element);
	}
	
	private static void rbCheck(RBElement element) {
		if (element != null) {
			printCheck(element);
			rbCheck(element.getLeft());
			rbCheck(element.getRight());
		}
	}
	
	private static void printCheck(RBElement element) {
		System.out.println("(" + (element.getParent() != null ? element.getParent().getKey() : null) 
				+ ", " + element.getKey() + ", " + element.getColor().getName() + ", " + blackHeight(element) + ", "  +
				(element.getLeft() != null ? element.getLeft().getKey() : null) + ", " + 
				(element.getRight() != null ? element.getRight().getKey() : null) + ")");
	}
	
	private static int blackHeight(RBElement element){
		if(element == null){
			return 1;
		}
		
		int leftHeight = blackHeight(element.getLeft());
		if(element.getLeft() != null && (element.getLeft().getColor().equals(RBColor.BLACK))){
			leftHeight++;
		}
		int rightHeight = blackHeight(element.getRight());
		if(element.getRight() != null && (element.getRight().getColor().equals(RBColor.BLACK))){
			rightHeight++;
		}
		
		if (leftHeight >= rightHeight) {
			return leftHeight;
		} else {
			return rightHeight;
		}
	}
}
