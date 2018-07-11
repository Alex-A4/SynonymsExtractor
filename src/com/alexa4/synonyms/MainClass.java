package com.alexa4.synonyms;

import com.alexa4.synonyms.Thesaurus.Thesaurus;
import java.io.IOException;

public class MainClass {
	public static void main(String[] args){
		try {
			Thesaurus thesaurus = new Thesaurus();
			thesaurus.readThesaurus();

			SynonymsExtractor.findSynonyms(thesaurus);

			thesaurus.writeThesaurus();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
