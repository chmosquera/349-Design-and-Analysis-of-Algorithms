
import java.util.ArrayList;
import java.lang.*;

// Feel free to add any other imports

public class CombObjects{

	public static ArrayList<String> getLexPerm(String str) {

		// initiate and allocate enough room for all elements needed
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();

		if (str.length() > 1) {
			for (int i = 0; i < str.length(); i++) {

				char lastc = str.charAt(i);

				// create new string without character at index i
				StringBuilder sb = new StringBuilder(str);
				sb.deleteCharAt(i);
				String s = sb.toString();

				// get Lex Permutation of new string
				temp = getLexPerm(s);

				// add removed character to each string in temp list
				for (int j = 0; j < temp.size(); j++) {
					result.add(lastc + temp.get(j));
				}
			}

		}
		else {
			result.add(str);
		}
		return result;

	}

    public static ArrayList<String> getMinChgPerm(String str) {

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();   

        if (str.length() > 1) {
            char x = str.charAt(str.length()-1);    // get last char
            StringBuilder sb = new StringBuilder(str);
            sb.deleteCharAt(str.length()-1);
            String s = sb.toString();

            // get permutations of new string;
            temp = getMinChgPerm(s);

			boolean r2l = true;						// which direction to iterate thru string

			// when iterating through string, alternate direction 
            for (int j = 0; j < temp.size(); j++) {
				if (r2l) {
	                for (int l = temp.get(j).length(); l >= 0; l--) {
	                    StringBuilder sb2 = new StringBuilder(temp.get(j));
	                    sb2.insert(l, x);
	                    String s2 = sb2.toString();
	                    result.add(s2);
	                }
				} else {
					for (int k = 0; k <= temp.get(j).length(); k++) {
						StringBuilder sb2 = new StringBuilder(temp.get(j));
						sb2.insert(k, x);
						String s2 = sb2.toString();
						result.add(s2);
					}
				}
				r2l = !r2l;
            }
        }
        else {
            result.add(str);
        }
        return result;
    }
       
	public static void main(String[] args) {
      
		ArrayList<String> result = new ArrayList<String>();

		String str1 = "abc";
		String str2 = "abcd";

		// 1
		result = getLexPerm(str1);
  
		System.out.println("getLexPerm: " + str1);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println();    

		// 2
		result = getLexPerm(str2);
  
		System.out.println("getLexPerm: " + str2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 
                
        // 3
		result = getMinChgPerm(str1);
  
		System.out.println("getMinChgPerm: " + str1);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 

        // 4
		result = getMinChgPerm(str2);
  
		System.out.println("getMinChgPerm: " + str2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 
	}
}
