package com.senacrs.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exemple1 {
	
	static Scanner input = new Scanner(System.in);
	static String[][] mat = new String[20][20];
	static List<String> words = new ArrayList<String>();

	public static void main(String[] args) {
	    //System.out.println("Read from file or from keyboard?");
	    //String option = input.next();
//	    if (option.equals("file"))
//	    	readFromFile();
//	    else readFromKeyboard();
		
		words.add("cure");
		words.add("abacate");
		words.add("keyboard");
		words.add("batatinha");
		words.add("airplane");
		words.add("motorcycle");
		words.add("cat");
		words.add("dog");
		words.add("listerine");
		for (int i = 0; i < words.size();i++){
			String word = words.get(i);
			if (i < getHalfOfList()) {
				//insert horizontal word
				//for (int j = 0;j < word.length(); j++) mat[i][j] = word.charAt(j)+"";
				boolean inserted = false;
				for (int iPos = 0; iPos < 20; iPos++) {
					for (int jPos = getNextFreeI(iPos); jPos < 20; jPos++){
						if (doesWordFitOnIPosition(word, iPos, jPos)) {
							System.out.println(word + " fits");
							insertHorizontalWord(word, iPos, jPos);
							inserted = true;
						}
						if (inserted) break;
					}
					if (inserted) break;
				}
				if (!inserted) System.out.println("Word " + word + "does not fit");
			}
			else {
				//insert vertical word
				boolean inserted = false;
				for (int iPos = 0; iPos < 20; iPos++) {
					for (int jPos = getNextFreeJ(iPos); jPos < 20; jPos++){
						if (doesWordFitOnJPosition(word, iPos, jPos)) {
							System.out.println(word + " fits");
							insertVerticalWord(word, iPos, jPos);
							inserted = true;
						}
						if (inserted) break;
					}
					if (inserted) break;
				}
				if (!inserted) System.out.println("Word " + word + "does not fit");
			}
		}
		printMatrix();
	}
	
	private static void insertHorizontalWord(String word, int iPos, int jPosInitial) {
		int j = jPosInitial;
		
		for (int w = 0; w < word.length(); w++){
			mat[iPos][j] = word.charAt(w)+"";
			j++;
		}
		
	}
	
	private static void insertVerticalWord(String word, int iPosInitial, int jPos) {
		int i = iPosInitial;
		for (int l = 0; l < word.length(); l++){
			mat[i][jPos] = word.charAt(l)+"";
			i++;
		}
	}
	//vertical
	private static int getNextFreeJ(int i) {
		for (int pos = 0 ; pos < 20; pos ++) 
			if (mat[i][pos] == null) return pos + 1;
		return 0;
	}
	
	//vertical
	private static int getNextFreeI(int j) {
		for (int pos = 0 ; pos < 20; pos ++) 
			if (mat[j][pos] == null) return pos + 1;
		return 0;
	}

	private static void printMatrix() {
		for (int i = 0; i < mat[0].length; i++){
			for (int j = 0; j < mat[0].length; j++)
				System.out.print(mat[i][j] == null ? '.' : (mat[i][j]));
			System.out.println("\n");
		}
	}

	private static int getHalfOfList() {
		return words.size()/2;
	}

//	private static void readFromFile() {
//	}

	private static void readFromKeyboard() {
		String option = "";
		while (!option.equals("No")) {
			System.out.println("Type a word");
			words.add(input.next());
			System.out.println("Enter new word?");
			option = input.next();
		}
	}
	
	private static boolean isPositionFree(int i, int j){
		return mat[i][j] == null ? true : false;
	}
	
	private static boolean isThereWordLeftSide(int i, int j){
		if (i == 0) return false;
		return mat[i-1][j] != null;
	}
	private static boolean isThereWordRightSide(int i, int j){
		if (i == 19) return false;
		return mat[i+1][j] != null;
	}
	
	private static boolean isThereWordBelow(int i, int j){
		if (j == 19) return false;
		return mat[i][j+1] != null;
	}
	
	private static boolean isThereWordAbove(int i, int j){
		if (j == 0) return false;
		return mat[i][j-1] != null;
	}
	
	private static boolean isThereNearWord(int i, int j){
		return isThereWordLeftSide(i, j) || isThereWordRightSide(i, j) || isThereWordBelow(i, j) || isThereWordAbove(i, j); 
	}
	
	private static boolean doesWordFitOnJPosition(String word, int i, int j){
		if (i + word.length() > 20) return false;
		boolean fits = true;
		int iPos = i;
		int jPos = j;
		for (int pos = 0; pos < word.length(); pos ++) {
			if(!isPositionFree(iPos, jPos) || isThereNearWord(iPos, jPos)){
				fits = false;
			}
			iPos++;
		}
		return fits;
	}
	
	private static boolean doesWordFitOnIPosition(String word, int i, int j){
		if (i + word.length() > 20) return false;
		boolean fits = true;
		int iPos = i;
		int jPos = j;
		for (int pos = 0; pos < word.length(); pos ++) {
			if (jPos >= 19) {
				fits = false;
				break;
			}
			if(!isPositionFree(iPos, jPos) || isThereNearWord(iPos, jPos)){
				fits = false;
			}
			jPos++;
		}
		return fits;
	}
}