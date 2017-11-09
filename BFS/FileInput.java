package BFS;

import java.io.*;
import java.util.ArrayList;

public class FileInput {
	private ArrayList<ClassInfo> classes;
	private File csvFile;
	
	public FileInput(){
		
	} 
	
	public FileInput(String file) throws IOException {
		this.csvFile = new File(file);
		readCSV();
	} 
	//reads csv file and prints it out
	private void readCSV() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        this.classes = new ArrayList<ClassInfo>();

        try {
            br = new BufferedReader(new FileReader(this.csvFile));
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
            	// use comma as separator
                String[] classInfo = line.split(cvsSplitBy);
                addToClasses(classInfo);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	
	public ArrayList<ClassInfo> getListOfClassInfo(){
		if(this.classes != null) {
			return this.classes;
		}
		return null;
	}
	
	//puts the class info into an arraylist
	private void addToClasses(String[] classInfo) {
		ClassInfo ci = null;
		String delimeter = "/";
        if(classInfo[2].equals("") && classInfo[3].equals("")) {
        	ci = new ClassInfo(classInfo[0], Integer.parseInt(classInfo[1]), 
        			Integer.parseInt(classInfo[4]), classInfo[5], false, checkIsElective(classInfo[7]));
        	this.classes.add(ci);
        	
        }
        else if(!classInfo[3].equals("") && classInfo[2].equals("")){
        	String[] prerequisites;
        	if(classInfo[3].length() <= 1) {
        		prerequisites = classInfo[3].split(",");
        	}
        	else {
        		prerequisites = classInfo[3].split(delimeter);
        	}

        	insertPrereqisites(classInfo, prerequisites);
        	
        }
        else if(classInfo[3].equals("") && !classInfo[2].equals("")){
        	String[] corequisites;
        	if(classInfo[2].length() <= 1) {
        		corequisites = classInfo[2].split(",");
        	}
        	else {
        		corequisites = classInfo[2].split(delimeter);
        	}
        	insertCorequisites(classInfo, corequisites);
        	
        }
        else if(!classInfo[2].equals("") && !classInfo[3].equals("")){
        	String[] prerequisites;
        	if(classInfo[3].length() <= 1) {
        		prerequisites = classInfo[3].split(",");
        	}
        	else {
        		prerequisites = classInfo[3].split(delimeter);
        	}
        	String[] corequisites;
        	if(classInfo[3].length() <= 1) {
        		corequisites = classInfo[2].split(",");
        	}
        	else {
        		corequisites = classInfo[2].split(delimeter);
        	}
        	insertCorequisitesPrerequisites(classInfo, prerequisites, corequisites);
        }
        
        
        
	}
	
	//inserts just prerequisite 
	private void insertPrereqisites(String[] classInfo, String[] prerequisites) {
		ClassInfo ci;
		ArrayList<ClassInfo> prereqs = new ArrayList<ClassInfo>();
    	for(int i = 0; i < prerequisites.length; i++) {
    		for(int j = 0; j < classes.size(); j++) {
    			if(prerequisites[i].toLowerCase().equals(classes.get(j).getName().toLowerCase())){
    				prereqs.add(this.classes.get(j));
    			}
    		}
    	}
    	
    		ci = new ClassInfo(classInfo[0], Integer.parseInt(classInfo[1]), 
    				prereqs, Integer.parseInt(classInfo[4]), classInfo[5], false, checkIsElective(classInfo[7]));
    		this.classes.add(ci);
    	
	}
	
	//inserts just corequisites
	private void insertCorequisites(String[] classInfo, String[] corequisites) {
		ClassInfo ci;
		ArrayList<ClassInfo> coreqs = new ArrayList<ClassInfo>();
    	for(int i = 0; i < corequisites.length; i++) {
    		for(int j = 0; j < this.classes.size(); j++) {
    			//need fix
    			//cant find class when its not in database yet
    			if(corequisites[i].toLowerCase().equals(this.classes.get(j).getName().toLowerCase())){
    				coreqs.add(this.classes.get(j));
    			}
    		}
    	}
    	
    		ci = new ClassInfo(classInfo[0], Integer.parseInt(classInfo[1]), coreqs, 
    				Integer.parseInt(classInfo[4]),  classInfo[5], false, checkIsElective(classInfo[7]));
    		this.classes.add(ci);
    	
	}
	
	//inserts both corequisites and prerequisites
	private void insertCorequisitesPrerequisites(String[] classInfo, String[] prerequisites, 
			 String[] corequisites) {
		ClassInfo ci;
		ArrayList<ClassInfo> prereqs = new ArrayList<ClassInfo>();
		ArrayList<ClassInfo> coreqs = new ArrayList<ClassInfo>();
    	for(int i = 0; i < prerequisites.length; i++) {
    		for(int j = 0; j < this.classes.size(); j++) {
    			if(prerequisites[i].toLowerCase().equals(this.classes.get(j).getName().toLowerCase())){
    				prereqs.add(this.classes.get(j));
    			}
    		}
    	}
    	
    	for(int i = 0; i < corequisites.length; i++) {
    		for(int j = 0; j < this.classes.size(); j++) {
    			if(corequisites[i].toLowerCase().equals(this.classes.get(j).getName().toLowerCase())){
    				coreqs.add(this.classes.get(j));
    			}
    		}
    	}
    	
    	
    		ci = new ClassInfo(classInfo[0], Integer.parseInt(classInfo[1]), 
    				coreqs, prereqs, Integer.parseInt(classInfo[4]), classInfo[5], false, checkIsElective(classInfo[7]));
    		this.classes.add(ci);
    	
	}
	
	private Boolean checkIsElective(String isElective) {
		if(isElective.equals("N")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
}



