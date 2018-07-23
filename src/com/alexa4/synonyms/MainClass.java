package com.alexa4.synonyms;

import com.alexa4.synonyms.Thesaurus.Thesaurus;
import java.io.IOException;

public class MainClass {
	public static void main(String[] args){
		try {
			Thesaurus thesaurus = new Thesaurus();
			thesaurus.readThesaurus("/home/alexa4/Java-Projects/Projects/Synonyms/res/newThesaruses/tweets_auto_thesaurus_lsa_word2vec_lev_RT_syn_hyp-parts_RT_hyp");

			SynonymsExtractor.findSynonymsFromWholeDictionary(thesaurus);

			thesaurus.writeThesaurus("/home/alexa4/Java-Projects/Projects/Synonyms/res/newThesaruses/tweets_auto_thesaurus_lsa_word2vec_lev_RT_syn_hyp-parts_RT_hyp_result");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
