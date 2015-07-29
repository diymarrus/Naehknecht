package de.umpanet.naehen.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;

public class ParseMeasuerments {

	private HashMap<String,Double> measuerments = new HashMap<>(); 

	public ParseMeasuerments() {
		// TODO Auto-generated constructor stub


	}
	public HashMap<String,Double> parse(){
		File csvfile = new File("/home/marrus/private/mareike_hoeger/html/mea.csv");
		String[] names= null;
		String[] values = null; 

		try{
			FileReader filereader = new FileReader(csvfile);

			BufferedReader reader = new BufferedReader( new InputStreamReader(
					new FileInputStream(csvfile), "UTF-8"));


			String line = reader.readLine();

			while(line != null){
				String[] parts = line.split(",");
				if(parts.length>0){
					//System.out.println(parts[0]);

					if(parts[0].contains("Measurement Names")){
						names = parts;
					}
					if(parts[0].contains("Daten")){
						values =parts;
					}

					
				}
				line = reader.readLine();
			}
			
			System.out.println("Finished reading file ");
			reader.close();
				}catch(Exception e){
					e.printStackTrace();
				}

			for(int i = 1; i < values.length; i++){
				if(names.length > i){
				String name = names[i].replace(" (cm)", "");
				name = name.replaceAll("\"", "");
				String val = values[i].replaceAll("\"", "");
				val = val.replaceAll(" ", "");
				if(!val.isEmpty()) {
				double value = Double.valueOf(val);
				//System.out.println(name + " " + value);
				this.measuerments.put(name,value);
				}
				}
			}







			return this.measuerments;

		}
	}
