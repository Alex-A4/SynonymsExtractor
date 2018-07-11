package com.alexa4.synonyms.Thesaurus;

import java.util.ArrayList;

/**
 * Record description class
 * This class describes one record from thesaurus
 */
public class Record{
	private String id;
	private String name;
	private String associates;
	private String hypos;
	private String hypers;
	private ArrayList<String> synonyms_names;

	/**
	 * Constructor with all parameters
	 * @param id
	 * @param name
	 * @param associates
	 * @param hypos
	 * @param hypers
	 * @param synonyms_names
	 */
	Record(String id, String name, String associates, String hypos, String hypers, ArrayList<String> synonyms_names){
		this.id = id;
		this.name = name;
		this.associates = associates;
		this.hypos = hypos;
		this.hypers = hypers;
		this.synonyms_names = synonyms_names;
	}

	/**
	 * Add synonym to synonym_names
	 * @param synonym new synonym
	 */
	public void addSynonym(String synonym){
		if (synonyms_names == null)
			synonyms_names = new ArrayList<String>();
		synonyms_names.add(synonym);
	}

	/**
	 * Returns the id of this record
	 * @return
	 */
	public String getId(){
		return id;
	}

	/**
	 * Returns the name of this record
	 * @return
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the associates of this record
	 * @return
	 */
	public String getAssociates(){
		return associates;
	}

	/**
	 * Returns the hyponyms of this record
	 * @return
	 */
	public String getHypos(){
		return hypos;
	}

	/**
	 * Returns the hyperonyms of this record
	 * @return
	 */
	public String getHypers(){
		return hypers;
	}

	/**
	 * Returns the synonyms of this record
	 * @return
	 */
	public String getSynonyms(){
		return makeSynonyms();
	}

	/**
	 * Helper method which need to build line like "[synonym1, synonym2, ...]"
	 * @return
	 */
	private  String makeSynonyms(){
		String line = "";

		if (synonyms_names == null)
			line = "[]";
		else{
			line = line.concat("[");
			for (int i = 0; i < synonyms_names.size(); i++){
				if (i == synonyms_names.size() - 1) line = line.concat(synonyms_names.get(i));
				else line = line.concat(synonyms_names.get(i) + ", ");
			}
			line = line.concat("]");
		}
		return line;
	}

	/**
	 * Returns the list of synonyms
	 * @return
	 */
	public ArrayList<String> getSynonymsList(){
		return synonyms_names;
	}
}
