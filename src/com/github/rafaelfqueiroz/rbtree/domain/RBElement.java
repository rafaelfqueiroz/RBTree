package com.github.rafaelfqueiroz.rbtree.domain;

import com.github.rafaelfqueiroz.rbtree.utils.RBColor;

public class RBElement {

	private String key;
	
	private RBElement parentElement;
	private RBElement leftElement;
	private RBElement rightElement;
	
	private RBColor color;
	
	public RBElement() {}
	
	public RBElement(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public RBElement getParent() {
		return parentElement;
	}

	public void setParent(RBElement parentElement) {
		this.parentElement = parentElement;
	}

	public RBElement getLeft() {
		return leftElement;
	}

	public void setLeft(RBElement leftElement) {
		this.leftElement = leftElement;
	}

	public RBElement getRight() {
		return rightElement;
	}

	public void setRight(RBElement rightElement) {
		this.rightElement = rightElement;
	}

	public RBColor getColor() {
		return color;
	}

	public void setColor(RBColor color) {
		this.color = color;
	}
	
	public RBElement getGrandpa() {
		if (parentElement == null) {
			return null;
		}
		return parentElement.getParent();
			
	}
	
	public void setGrandpa(RBElement element) {
		if (parentElement != null) {
			parentElement.setParent(element);
		}
	}
	
}
