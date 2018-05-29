
import java.util.ArrayList;
import java.lang.*;

// Feel free to add any other imports

public class GrayCode{ 


	public static ArrayList<String> getGrayCode(int n) {

		ArrayList<String> result = new ArrayList();	// stores final result to output
		ArrayList<String> temp = new ArrayList();	// stores subsets for n-1 items

		if (n > 1) {
			temp = getGrayCode(n-1);
			
			for (int i = 0; i < temp.size(); i++) {			// prepend "0" to list of subsets
				result.add("0" + temp.get(i));
			}
			for (int i = temp.size() - 1; i >= 0; i--) {	// prepend "1" to reversed list of subsets
				result.add("1" + temp.get(i));
			}
			
		}
		else {
			result.add("0");
			result.add("1");
		}

		return result;
	}
       
   public static void main(String[] args) {
      
		ArrayList<String> result = new ArrayList<String>();
     
		result = getGrayCode(4);

		// print the result
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}

		System.out.println();
   }
}
