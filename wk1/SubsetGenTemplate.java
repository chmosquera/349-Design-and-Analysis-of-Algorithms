import java.util.ArrayList;
import java.lang.String;

//	This class generates subsets of a set represented as a string in Java

public class SubsetGen   {
          
	public SubsetGen() {
    }

    public ArrayList<String> getSubsets(String word)    {
        ArrayList<String> subsets = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();

        if (size(word) > 0) {
			temp = getSubsets(new String(length(word)-1));
			System.out.print(word);
		} 
		else {
			//subsets.Add("");
		}
       
        return subsets;
    }
}
