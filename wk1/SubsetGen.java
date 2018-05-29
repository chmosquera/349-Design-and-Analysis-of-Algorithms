import java.util.ArrayList;
import java.lang.String;

//	This class generates subsets of a set represented as a string in Java

public class SubsetGen {
          
	public SubsetGen() {    }

    public ArrayList<String> getSubsets(String word)    {
        ArrayList<String> subsets = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();

        if (word.length() > 0) {
			String s = new String(word.toCharArray(), 0, word.length()-1);
			temp = getSubsets(s);
			
			for (int i = 0; i < temp.size(); i++) {
				subsets.add(temp.get(i));
			}
			for (int i = 0; i < temp.size(); i++) {
				subsets.add(temp.get(i) + word.charAt(word.length()-1));
			}
		} 
		else {
			subsets.add("");
		}
       
        return subsets;
    }



}

