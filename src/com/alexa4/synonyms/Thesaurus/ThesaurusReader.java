package com.alexa4.synonyms.Thesaurus;

import com.alexa4.synonyms.DictionaryStemmer.Stemmer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ThesaurusReader implements ThesaurusLines{
	private static Scanner sc;
	private Thesaurus thesaurus;

	/**
	 * Constructor of ThesaurusReader
	 * Creating a scanner to read data from file
	 * @param thesaurus thesaurus in memory with all records
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ThesaurusReader(Thesaurus thesaurus) throws IOException, FileNotFoundException{
		this.thesaurus = thesaurus;
		String filePath = new File("").getAbsolutePath().concat("/res/auto_thesaurus_lsa_lev_RT_syn_hyp-parts_RT_hyp");
		File file = new File(filePath);
		sc = new Scanner(file);
	}

	/**
	 * Constructor of ThesaurusReader
	 * Creating a scanner to read data from file
	 * @param thesaurus thesaurus in memory with all records
	 * @param fileWay full file path
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public ThesaurusReader(Thesaurus thesaurus, String fileWay) throws IOException, FileNotFoundException{
		this.thesaurus = thesaurus;
		File file = new File(fileWay);
		sc = new Scanner(file);
	}

	/**
	 * Method to read one record into thesaurus
	 * @return boolean value which describe was new record read or not
	 */
	public boolean readNewRecord(){
		String line;
		while(sc.hasNextLine()){
			line = sc.nextLine();
			if (line.contains("*NEWRECORD")){
				String id = sc.nextLine();
				String name = sc.nextLine();
				String associates = sc.nextLine();
				String hypos = sc.nextLine();
				String hypers = sc.nextLine();
				String synonyms = sc.nextLine();
				thesaurus.addRecord(makeNewRecord(id, name, associates, hypos, hypers, synonyms));
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to read all record into thesaurus
	 */
	public void readAllRecords(){
		String line;

		while(sc.hasNextLine()){
			line = sc.nextLine();
			if (line.contains("*NEWRECORD")){
				String id = sc.nextLine();
				String name = sc.nextLine();
				String associates = sc.nextLine();
				String hypos = sc.nextLine();
				String hypers = sc.nextLine();
				String synonyms = sc.nextLine();
				thesaurus.addRecord(makeNewRecord(id, name, associates, hypos, hypers, synonyms));
			}
		}
		System.out.println("Reading had been finished");
	}

	/**
	 * Creating a new record which was read
	 * @param id of word
	 * @param name which describes word
	 * @param associates String line, not change
	 * @param hypos String line, not change
	 * @param hypers String line, not change
	 * @param synonyms String line which contains or not synonym words
	 * @return new Record
	 */
	private Record makeNewRecord(String id, String name, String associates,
	                             String  hypos, String hypers, String synonyms){

		id = getId(id);
		name = getName(name);
		String nameAfterStem = Stemmer.stemOneWord(name);
		ArrayList<String> synons = getSynonyms(synonyms);
		return new Record(id, name, nameAfterStem, associates, hypos, hypers, synons);
	}

	/**
	 * Getting ID of word
	 * @param id line like "ID = .."
	 * @return ID of word
	 */
	private String getId(String id){
		return id.replace(ID,"");
	}

	/**
	 * Getting name of word
	 * @param name line like "NAME = .."
	 * @return name of word
	 */
	private String getName(String name){
		return name.replace(NAME, "");
	}

	/**
	 * Getting synonyms of word
	 * @param synonyms line like "SYNONYMS_NAMES = [..]"
	 * @return array of synonyms
	 */
	private  ArrayList<String> getSynonyms(String synonyms){
		synonyms = synonyms.replace(SYNONYMS_NAMES, "");
		if (synonyms.equals("[]"))
			return null;

		synonyms = synonyms.substring(synonyms.indexOf('[')+1, synonyms.indexOf(']'));

		String[] words = synonyms.split("[,]+");
		ArrayList<String> synonms = new ArrayList<String>();

		for (int i = 0; i < words.length; i++){
			words[ i ] = words[ i ].trim();

			words[ i ] = words[ i ].replace("u\'", "");

			synonms.add(words[ i ]);
		}
		return synonms;
	}

	public void closeStream(){
		sc.close();
	}
}
