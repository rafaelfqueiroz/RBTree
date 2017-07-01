package com.github.rafaelfqueiroz.rbtree.utils;

public enum RBColor {

	RED {
		@Override
		public String getName() {
			return "RED";
		}
	},
	BLACK {
		@Override
		public String getName() {
			return "BLACK";
		}
	};
	
	public abstract String getName();
	
	private RBColor() {
		
	}
}
