package pl.pwr.swd.core;

import java.util.ArrayList;
import java.util.List;

import pl.pwr.swd.exceptions.NoValueAssignedException;
import pl.pwr.swd.model.Attribute;
import pl.pwr.swd.model.Fact;


public class Core {
	
	public static ArrayList<ArrayList<Attribute>> analyseData(List<Attribute> inputList, List<Fact> facts, List<Attribute> outputList) throws NoValueAssignedException {
		ArrayList<ArrayList<Attribute>> returnList = new ArrayList<ArrayList<Attribute>>();
		
		// For each possible permutation
		for (int i = 0; i < Math.pow(2, outputList.size()); i++) {
			
			System.out.println();
			
			// Set values of output for testing facts
			for (int j = 0; j < outputList.size(); j++) {
				int mask = (int)Math.pow(2, j);
				boolean value = (i & mask) == 0 ? false : true;
				outputList.get(j).setValue(value);
			}
			
			
			// Check if Facts are true or not
			boolean isPermutationValid = true;
			for (Fact f : facts) {
				try {
					if (f.getValue() == false) {
						isPermutationValid = false;
						break;
					}
				}
				catch (NoValueAssignedException e) {
					continue;
				}
			}
			
			if (isPermutationValid) {
				ArrayList<Attribute> addList = new ArrayList<Attribute>();
				
				for (Attribute a : outputList) {
					Attribute at = new Attribute(a.getDescription(), false);
					at.setValue(a.getValue());
					addList.add(at);
				}
				returnList.add(addList);
			}
		}
		
		return returnList;
	}
}
