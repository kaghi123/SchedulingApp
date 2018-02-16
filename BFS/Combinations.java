package BFS;

import java.util.ArrayList;

public class Combinations {
	
	int maxUnit = 0;
	int minUnit = 0;
	
	public ArrayList<ArrayList<ClassInfo>> findCombination(ArrayList<ClassInfo> availList, int minUnits, int maxUnits) {
		
		maxUnit = maxUnits;
		minUnit = minUnits;
		
		ArrayList<ArrayList<ClassInfo>> combClasses = new ArrayList<ArrayList<ClassInfo>>();
		
		//goes through the size of the available list to see what possible combinations there are
		//ex. if avail = a, b, c, d then it has to find comb with size = 1: a, b, c, d. size = 2: ab, ac, ad, bc, ... 
		//size = 3: abc, acd, bcd, ... size = 4: abcd
		for(int i = 0; i < availList.size(); i++){
			int sizeOfavailList = availList.size();
			int sizeOfNextCombo = i+1;
			
			printCombination(availList, sizeOfavailList, sizeOfNextCombo, combClasses);
		}
		
		return combClasses;
	}


	//this method creates a new temp ArrayList to store the new combinations in
	public void printCombination(ArrayList<ClassInfo> availList, int sizeOfavailList, int sizeOfNextCombo, ArrayList<ArrayList<ClassInfo>> combClasses){
	
		ArrayList<ClassInfo> tempCombo = new ArrayList<ClassInfo>(sizeOfNextCombo);
    	for(int i = 0; i < sizeOfNextCombo; i++) {
    		//with out this line we get an index out of bounce exception
    		tempCombo.add(null);
    	}
    	
		//check to see if combo should be added to list
		storeCombinations(availList, tempCombo, 0, sizeOfavailList - 1, 0, sizeOfNextCombo, combClasses);
		
	}
	
	public void storeCombinations(ArrayList<ClassInfo> availList, ArrayList<ClassInfo> tempCombo, int start, int end, int index, int sizeOfNextCombo,  ArrayList<ArrayList<ClassInfo>> combClasses){
		
		//will start to add combinations to combClasses
		if (index == sizeOfNextCombo){
			
        	ClassInfo temp = null;
        	ArrayList<ClassInfo> tempList = new ArrayList<ClassInfo>();
        	
            for (int j = 0; j < sizeOfNextCombo; j++){
               temp = tempCombo.get(j);
               tempList.add(temp);
            }
            
            //check to see if the combo fits in the desired unit preference
            int totalUnits = 0;
            for (int j = 0; j < tempList.size(); j++){
            	totalUnits += tempList.get(j).getUnits();
            }
            
            if(maxUnit < totalUnits || minUnit > totalUnits ){
	
			}
            else {
            	combClasses.add(tempList);
            }
            
            	
            return;
		}
		
		
		for (int i = start; i <= end; i++){
            tempCombo.set(index, availList.get(i));
            //index + 1 replaces index with all possible elements
            storeCombinations(availList, tempCombo, i+1, end, index+1, sizeOfNextCombo, combClasses);
        }
	}
}
