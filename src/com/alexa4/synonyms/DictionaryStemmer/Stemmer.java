package com.alexa4.synonyms.DictionaryStemmer;

import org.tartarus.snowball.ext.russianStemmer;

/**
 * Stemmer which could be use to stem text
 */
public final class Stemmer {
	/**
	 * Stem not parsed text with input regular expression
	 * @param text input text
	 * @param regex regular expression to split text
	 * @return array of stemmed words
	 */
	public static String[] stemNotParsedText(String text, String regex){
		russianStemmer rs = new russianStemmer();
		String[] words = text.split(regex);
		for(int i = 0; i < words.length; i++){
			rs.setCurrent(words[i]);
			rs.stem();
			words[i] = rs.getCurrent();
		}
		return words;
	}

	/**
	 * Stem parsed text
	 * @param words array of words
	 * @return array of stemmed words
	 */
	public static String[] stemArrayOfWords(String[] words){
		russianStemmer rs = new russianStemmer();
		for (String word:words){
			rs.setCurrent(word);
			rs.stem();
			word = rs.getCurrent();
		}
		return words;
	}
}
