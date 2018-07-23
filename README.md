# SynonymsExtractor
This app extract synonyms from dictionary and build connection between thesaurus records

<h1>Thesaurus package</h1>
Contains all classes to work with Thesaurus:
<h2>Thesaurus.java</h2> 
Contains methods to call read/write, also field ArrayList with all records
<h2>ThesaurusReader.java</h2> 
Include methods to read records from thesaurus and build an ArrayList
<h2>ThesaurusWriter.java</h2> 
iclude methods to write all records into new thesaurus file
<h2>Record.java</h2>
 include all fields of thesaurus and some methods to get it

<h1>DictionaryStemmer package</h1>
Contains static class Stemmer.java to stem words or lines of words
DictionaryStemmer.java uses to stem all lines from dictionary. Need just to make some tests

<h2>SynonymsExtractor.java</h2>
Contains methods to extract synonymic relationships from dictionary and make new
connections between records on the basis of dictionary

<h1>Package org.tartarus.snowball</h1>
Contains Porter's stemmer

To exclude ability of inclusion synonyms from dictionary, comment conditional operator in
 SynonymsExtractor.java in method findSynonymsToEachRecord(..)
