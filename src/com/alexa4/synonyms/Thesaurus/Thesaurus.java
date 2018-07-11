package com.alexa4.synonyms.Thesaurus;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains list of record and String filed in file
 */
public class Thesaurus {

	//List of records
	private ArrayList<Record> records;

	public Thesaurus(){
		records = new ArrayList<Record>();
	}

	/**
	 * Read thesaurus from standard file /res/thesaurus
	 * @throws IOException read error
	 */
	public void readThesaurus() throws IOException{
		ThesaurusReader reader = new ThesaurusReader(this);
		reader.readAllRecords();
		reader.closeStream();
	}

	/**
	 * Read thesaurus from input file
	 * @param fullFileWay full path of file
	 * @throws IOException read error
	 */
	public void readThesaurus(String fullFileWay) throws IOException{
		ThesaurusReader reader = new ThesaurusReader(this, fullFileWay);
		reader.readAllRecords();
		reader.closeStream();
	}

	/**
	 * Write thesaurus to standard file at /res/thesaurus
	 * @throws IOException write error
	 */
	public void writeThesaurus() throws IOException{
		ThesaurusWriter writer = new ThesaurusWriter(this);
		writer.writeAllRecords();
		writer.closeStream();
	}

	/**
	 * Write thesaurus to input file
	 * @param fullFileWay full path of file
	 * @throws IOException write error
	 */
	public void writeThesaurus(String fullFileWay) throws IOException{
		ThesaurusWriter writer = new ThesaurusWriter(this, fullFileWay);
		writer.writeAllRecords();
		writer.closeStream();
	}



	/**
	 * Add new record to thesaurus
	 * @param record is new record
	 */
	public void addRecord(Record record){
		records.add(record);
	}

	/**
	 * Getter of records
	 * @return list of records
	 */
	public  ArrayList<Record> getRecords(){
		return records;
	}
}
