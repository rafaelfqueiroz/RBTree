package com.github.rafaelfqueiroz.rbtree.builder;

import com.github.rafaelfqueiroz.rbtree.domain.RBElement;
import com.github.rafaelfqueiroz.rbtree.domain.RBTree;
import com.github.rafaelfqueiroz.rbtree.utils.RBColor;
import com.github.rafaelfqueiroz.rbtree.utils.RotatorUtils;
import com.github.rafaelfqueiroz.rbtree.utils.SeacherUtils;

public class RBTreeBuilder {

	public void	rbInsert(RBTree tree, RBElement element) {
		RBElement aux = null;
		RBElement temp = tree.getRoot();
		
		while (temp != null) {
			aux = temp;
			if (element.getKey().compareTo(temp.getKey()) < 0) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		
		element.setParent(aux);
		
		if (aux == null) {
			tree.setRoot(element);
		} else if (element.getKey().compareTo(aux.getKey()) < 0) {
			aux.setLeft(element);
		} else {
			aux.setRight(element);
		}
		
		element.setLeft(null);
		element.setRight(null);
		element.setColor(RBColor.RED);
		
		rbInsertFixup(tree, element);
	}
	
	private void rbInsertFixup(RBTree tree, RBElement element) {
		RBElement aux = null;
		
		while (element.getParent() != null 
				&& element.getParent().getColor().equals(RBColor.RED)) {
			
			if (element.getParent().equals(
					element.getGrandpa().getLeft())) {
				
				aux = element.getGrandpa().getRight();
				
				if (aux.getColor().equals(RBColor.RED)) {
					element.getParent().setColor(RBColor.BLACK);
					aux.setColor(RBColor.BLACK);
					element.getGrandpa().setColor(RBColor.RED);
					element = element.getGrandpa(); 
				} else { 
					if (element.equals(element.getParent().getRight())) {
						element = element.getParent();
						RotatorUtils.leftRotate(tree, element);
					}
					
					element.getParent().setColor(RBColor.BLACK);
					element.getGrandpa().setColor(RBColor.RED);
					
					RotatorUtils.rightRotate(tree, element.getGrandpa());
				}
			} else {
				
				aux = element.getGrandpa().getLeft();
				
				if (aux != null && aux.getColor().equals(RBColor.RED)) {
					element.getParent().setColor(RBColor.BLACK);
					aux.setColor(RBColor.BLACK);
					element.getGrandpa().setColor(RBColor.RED);
					element = element.getGrandpa(); 
				} else { 
					if (element.equals(element.getParent().getLeft())) {
						element = element.getParent();
						RotatorUtils.rightRotate(tree, element);
					}
					
					element.getParent().setColor(RBColor.BLACK);
					element.getGrandpa().setColor(RBColor.RED);
					
					RotatorUtils.leftRotate(tree, element.getGrandpa());
				}
			}
		}
		
		tree.getRoot().setColor(RBColor.BLACK);
	}
	
	public void rbDelete(RBTree tree, RBElement element) {
		RBElement aux = element;
		RBElement temp = null; 
		
		RBColor originalColor = aux.getColor();
		
		if (element.getLeft() == null) {
			temp = element.getRight();
			RotatorUtils.transplant(tree, element, element.getRight());
		} else if (element.getRight() == null)  {
			temp = element.getLeft();
			RotatorUtils.transplant(tree, element, element.getLeft());
		} else {
			aux = SeacherUtils.treeMinimum(tree); //TODO VERIFICAR SE É MESMO ARVORE OU NO
			originalColor = aux.getColor();
			temp = aux.getRight();
			
			if (aux.equals(element)) {
				temp.setParent(aux);
			} else {
				RotatorUtils.transplant(tree, aux, aux.getRight());
				aux.setRight(element.getRight());
				aux.getRight().setParent(aux);
			}
			
			RotatorUtils.transplant(tree, element, aux);
			aux.setLeft(element.getLeft());
			aux.getLeft().setParent(aux);
			aux.setColor(element.getColor());
		}
		
		if (originalColor.equals(RBColor.BLACK)) {
			rbDeleteFixup(tree, element);
		}
	}

	private void rbDeleteFixup(RBTree tree, RBElement element) {
		RBElement temp = null; 
		
		while (!element.equals(tree.getRoot()) && element.getColor().equals(RBColor.BLACK)) {
			if (element.equals(element.getParent().getLeft())) {
				temp = element.getParent().getRight();
				
				if (temp.getColor().equals(RBColor.RED)) {
					temp.setColor(RBColor.BLACK);
					temp.getParent().setColor(RBColor.RED);
					RotatorUtils.leftRotate(tree, element.getParent());
					temp = element.getParent().getRight();
				}
				
				if (temp.getLeft().getColor().equals(RBColor.BLACK) 
						&& temp.getRight().getColor().equals(RBColor.BLACK)) {
					temp.setColor(RBColor.RED);
					element = element.getParent();
				} else {
					if (temp.getRight().getColor().equals(RBColor.BLACK)) {
						temp.getLeft().setColor(RBColor.BLACK);
						temp.setColor(RBColor.RED);
						RotatorUtils.rightRotate(tree, temp);
						temp = element.getParent().getRight();
					}
					
					temp.setColor(element.getParent().getColor());
					element.getParent().setColor(RBColor.BLACK);
					temp.getRight().setColor(RBColor.BLACK);
					RotatorUtils.leftRotate(tree, element.getParent());
					element = tree.getRoot();
				}
			} else {
				temp = element.getParent().getLeft();
				
				if (temp.getColor().equals(RBColor.RED)) {
					temp.setColor(RBColor.BLACK);
					temp.getParent().setColor(RBColor.RED);
					RotatorUtils.rightRotate(tree, element.getParent());
					temp = element.getParent().getLeft();
				}
				
				if (temp.getRight().getColor().equals(RBColor.BLACK) 
						&& temp.getLeft().getColor().equals(RBColor.BLACK)) {
					temp.setColor(RBColor.RED);
					element = element.getParent();
				} else {
					if (temp.getLeft().getColor().equals(RBColor.BLACK)) {
						temp.getRight().setColor(RBColor.BLACK);
						temp.setColor(RBColor.RED);
						RotatorUtils.leftRotate(tree, temp);
						temp = element.getParent().getLeft();
					}
					
					temp.setColor(element.getParent().getColor());
					element.getParent().setColor(RBColor.BLACK);
					temp.getLeft().setColor(RBColor.BLACK);
					RotatorUtils.rightRotate(tree, element.getParent());
					element = tree.getRoot();
				}
			}
		}
		
		element.setColor(RBColor.BLACK);
	}
}
