package Utilities;

import java.util.ArrayList;
import java.util.List;

public class ExtractPageNumbersFromString {
	
	public int[] getPageArray(String pageString) {
		List<Integer> intList = new ArrayList<Integer>();
			if(pageString.contains("-")) {
				String[] rangeArray = pageString.split("-");
				try {
					int start = Integer.parseInt(rangeArray[0]);
					int end = Integer.parseInt(rangeArray[1]);
					for(int i = start; i <= end; i++) {
						if(!intList.contains(i)) {
							intList.add(i);
						}
					}
					if(!intList.contains(start)) {
						intList.add(start);
					}
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}else {
				try {
					int page = Integer.parseInt(pageString);
					if(!intList.contains(page)) {
						intList.add(page);
					}
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}
		if(intList.size() > 0) {
			int[] intArray = new int[intList.size()];
			int j = 0;
			for(int i : intList) {
				intArray[j] = i;
				j++;
			}
			return intArray;
		}
		return null;
	}

}
