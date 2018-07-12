# SynonymsExtractor
This app extract synonyms from dictionary and build connection between thesaurus record

Thesaurus package contains all classes to work with Thesaurus:
Thesaurus.java contains methods to call read/write, also field ArrayList with all records
ThesaurusReader.java include methods to read records from thesaurus and build an ArrayList
ThesaurusWriter.java include methods to write all records into new thesaurus file
Record.java include all fields of thesaurus and some methods to get it

DictionaryStemmer package contains static class Stemmer.java to stem words or lines of words
DictionaryStemmer.java uses to stem all lines from dictionary. Need just to make some tests

SynonymsExtractor.java contains methods to extract synonymic relationships from dictionary and make new
connections between records on the basis of dictionary

Package org.tartarus.snowball contains Porter's stemmer
