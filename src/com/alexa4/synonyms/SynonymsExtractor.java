package com.alexa4.synonyms;

import com.alexa4.synonyms.DictionaryStemmer.Stemmer;
import com.alexa4.synonyms.Thesaurus.Record;
import com.alexa4.synonyms.Thesaurus.Thesaurus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which find synonyms in dictionary and add it to thesaurus semantic
 */
public class SynonymsExtractor {


	/**
	 * Find a synonyms in dictionary with standard file way and add it to thesaurus
	 * @param thesaurus thesaurus with records
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void findSynonyms(Thesaurus thesaurus) throws FileNotFoundException, IOException{
		File dictionaryFile = createDictFile();
		readAndFindSynonyms(dictionaryFile, thesaurus);
	}

	/**
	 * Find a synonyms in dictionary with input file way and add it to thesaurus
	 * @param thesaurus thesaurus with records
	 * @param fullFilePath full file way
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void findSynonyms(Thesaurus thesaurus, String fullFilePath) throws FileNotFoundException, IOException{
		File dictionaryFile = createDictFile(fullFilePath);
		readAndFindSynonyms(dictionaryFile, thesaurus);
	}


	/**
	 * 1) Reading every line from dictionary
	 * 2) For each record from thesaurus trying to find synonyms in this line
	 * 3) If it have found, and it count more than 1, then create new connection
	 *      between all suitable records, if it have not created
	 * @param dictionaryFile to read
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void readAndFindSynonyms(File dictionaryFile, Thesaurus thesaurus)
			throws FileNotFoundException, IOException{

		ArrayList<Record> records = thesaurus.getRecords();

		//Need to control was work completed fully or not
		int numberOfLine = 0;

		Scanner scanner = new Scanner(dictionaryFile);
		while (scanner.hasNextLine()){
			numberOfLine++;

			String lineFromDict = scanner.nextLine();
			String[] words = Stemmer.stemNotParsedText(lineFromDict, "[|]+");

			findSynonymsToEachRecord(words, records);

			//Need to see progress of work
			if (numberOfLine % 100000 == 0)
				System.out.println(numberOfLine);
		}
		System.out.println(numberOfLine);
	}

	/**
	 * Finding synonyms from one line for each record
	 * If there were found more than 1 record then they connect with each other
	 * @param words array of words from one line of dictionary
	 * @param records list of thesaurus records
	 */
	private static void findSynonymsToEachRecord(String[] words, ArrayList<Record> records){
		//Each suitable record will add to that list
		ArrayList<Record> recordsToSynonyms = new ArrayList<Record>();

		for (int i = 0; i < records.size(); i++){
			if (isContainsInSynonyms(words, records.get(i).getName())){
				recordsToSynonyms.add(records.get(i));
			}
		}

		if (recordsToSynonyms.size() > 1){
			connectRecords(recordsToSynonyms);
		}

	}


	/**
	 * Check does record name contains in @param words
	 * @param words array of synonyms
	 * @param name of record
	 * @return true if contains, false otherwise
	 */
	private static boolean isContainsInSynonyms(String[] words, String name){
		for(String word: words){
			if (name.equals(word))
			return true;
		}
		return false;
	}


	/**
	 *  For each couple of records is checking are they contains names of each other
	 * 	If some record not contain name of another record, then this connect will be create
	 * @param records list of record which suit to line of synonyms
	 */
	private static void connectRecords(ArrayList<Record> records){
		for (int i = 0; i < records.size()-1; i++){
			for (int j = i+1; j < records.size(); j++){

				if (!isSynonymContainsInRecord(records.get(i).getName(), records.get(j))){
					records.get(j).addSynonym(records.get(i).getName());
				}

				if (!isSynonymContainsInRecord(records.get(j).getName(), records.get(i))){
					records.get(i).addSynonym(records.get(j).getName());
				}
			}
		}
	}


	/**
	 * Check does @param synonym word contains in record synonyms
	 * @param synonym which need compare to record synonyms
	 * @param record where need find @param synonym
	 * @return true if contains, false otherwise
	 */
	private static boolean isSynonymContainsInRecord(String synonym, Record record){
		ArrayList<String> recordSynonyms = record.getSynonymsList();

		if (recordSynonyms != null)
			for (String synon: recordSynonyms)
				if (synonym.equals(synon)) return true;

		return false;
	}

	/**
	 * Creating file with standard file path
	 * @return new file which point to dictionary
	 */
	private static File createDictFile()throws FileNotFoundException{
		String filePath = new File("").getAbsolutePath().concat("/res/synmaster.txt");
		return new File(filePath);
	}

	/**
	 * Creating file with input full file path
	 * @param fileWay file path
	 * @return new file which point to dictionary
	 */
	private static File createDictFile(String fileWay)throws FileNotFoundException{
		return new File(fileWay);
	}
}
