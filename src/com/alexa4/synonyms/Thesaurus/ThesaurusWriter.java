package com.alexa4.synonyms.Thesaurus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class to write new thesaurus file
 */
public class ThesaurusWriter implements ThesaurusLines{
	private static FileWriter writer;
	private Thesaurus thesaurus;

	/**
	 * Constructor of ThesaurusWriter
	 * Creating a FileWriter to write data to file
	 * @param thesaurus thesaurus in memory with all records
	 * @throws IOException
	 */
	public ThesaurusWriter(Thesaurus thesaurus) throws IOException{
		this.thesaurus = thesaurus;
		String filePath = new File("").getAbsolutePath().concat("/res/auto_thesaurus_test");
		File file = new File(filePath);
		writer = new FileWriter(file);
	}

	/**
	 * Constructor of ThesaurusWriter
	 * Creating a FileWriter to write data to file
	 * @param thesaurus thesaurus in memory with all records
	 * @param fileWay full path of file
	 * @throws IOException
	 */
	public ThesaurusWriter(Thesaurus thesaurus, String fileWay) throws IOException{
		this.thesaurus = thesaurus;
		File file = new File(fileWay);
		writer = new FileWriter(file);
	}

	/**
	 * Write all records to file
	 */
	public void writeAllRecords (){
		int countOfRecords = 0;
		ArrayList<Record> records = thesaurus.getRecords();
		for (Record record:records){
			try {
				writer.write("*NEWRECORD\n");
				writer.write(ID +record.getId()+"\n");
				writer.write(NAME + record.getName()+"\n");
				writer.write(record.getAssociates()+"\n");
				writer.write(record.getHypos()+"\n");
				writer.write(record.getHypers()+"\n");
				writer.write(SYNONYMS_NAMES + record.getSynonyms()+"\n");
				writer.write("\n");
				countOfRecords++;
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("There had been printed " + countOfRecords + " records");
	}

	public void closeStream() throws IOException{
		writer.close();
	}


}
