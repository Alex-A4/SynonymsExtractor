package com.alexa4.synonyms.DictionaryStemmer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class uses to stem dictionary's lines if it will be changed or add another dictionaries
 */
public class DictionaryStemmer {
	public static void main(String[] args){
		int countOfLines = 0;
		String filePath = new File("").getAbsolutePath();
		File fileToRead = new File( filePath.concat("/res/synmaster.txt"));
		File fileToWrite = new File(filePath.concat("/res/synmaster_stem.txt"));
		try {
			Scanner scanner = new Scanner(fileToRead);
			FileWriter fileWriter = new FileWriter(fileToWrite);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				countOfLines++;
				String words[] = Stemmer.stemNotParsedText(line, "[|]+");

				if (countOfLines % 10000 == 0)
					System.out.println(countOfLines);

				fileWriter.write(createLineToWrite(words)+"\n");
			}


			scanner.close();
			fileWriter.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(countOfLines);
	}

	private static String createLineToWrite(String[] words){
		String line = "";
		for (int i = 0; i < words.length; i++){
			if (i == words.length - 1)
				line = line.concat(words[i]);
			else line = line.concat(words[i] + "|");
		}
		return line;
	}
}
