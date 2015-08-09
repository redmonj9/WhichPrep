package com.dcu.redmonj9.whichprep.prepositions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {

	private static ArrayList<String> dictionary;
	private static ArrayList<String> prepositions;
	
	public Dictionary(){
		dictionary = new ArrayList<String>();
		prepositions = new ArrayList<String>();
	}
	
	public String getDictionaryItem(int index){
		return dictionary.get(index);
	}
	
	public String getPrepItem(int index) {
		return prepositions.get(index);
	}
	
	public static ArrayList<String> getDictionary(InputStream inputStream){
		dictionary = new ArrayList<>();
		int fileNo = new Random().nextInt(9);
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			List<String> sentences = new ArrayList<String>();
			String line;
			while((line = reader.readLine()) != null){
				sentences.add(line);
			}
			for(int i = 0; i < 30; i++){
				dictionary.add(sentences.get(new Random().nextInt(sentences.size())));
			}
			inputStream.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		return dictionary;
	}
	
	public static ArrayList<String> getPrepositions(){
		prepositions = new ArrayList<String>();
		prepositions.add(Prepositions.ABOUT.prep);
		prepositions.add(Prepositions.AFTER.prep);
		prepositions.add(Prepositions.AGAINST.prep);
		prepositions.add(Prepositions.AMONG.prep);
		prepositions.add(Prepositions.AROUND.prep);
		prepositions.add(Prepositions.AS.prep);
		prepositions.add(Prepositions.AT.prep);
		prepositions.add(Prepositions.BEFORE.prep);
		prepositions.add(Prepositions.BETWEEN.prep);
		prepositions.add(Prepositions.BY.prep);
		prepositions.add(Prepositions.DURING.prep);
		prepositions.add(Prepositions.FOR.prep);
		prepositions.add(Prepositions.FROM.prep);
		prepositions.add(Prepositions.IN.prep);
		prepositions.add(Prepositions.INTO.prep);
		prepositions.add(Prepositions.LIKE.prep);
		prepositions.add(Prepositions.OF.prep);
		prepositions.add(Prepositions.ON.prep);
		prepositions.add(Prepositions.OUT.prep);
		prepositions.add(Prepositions.OVER.prep);
		prepositions.add(Prepositions.THROUGH.prep);
		prepositions.add(Prepositions.TO.prep);
		prepositions.add(Prepositions.UNDER.prep);
		prepositions.add(Prepositions.WITH.prep);
		prepositions.add(Prepositions.WITHOUT.prep);
		return prepositions;
	}
}
