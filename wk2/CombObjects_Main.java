
import java.util.ArrayList;
import java.lang.*;

// Feel free to add any other imports

public class CombObjects_Main{

	public static void main(String[] args) {

                CombObjects co = new CombObjects();
      
		ArrayList<String> result = new ArrayList<String>();

		String str1 = "abc";
		String str2 = "abcd";

		// 1
		result = co.getLexPerm(str1);
  
		System.out.println("getLexPerm: " + str1);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println();    

		// 2
		result = co.getLexPerm(str2);
  
		System.out.println("getLexPerm: " + str2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 
                
                // 3
		result = co.getMinChgPerm(str1);
  
		System.out.println("getMinChgPerm: " + str1);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 

        // 4
		result = co.getMinChgPerm(str2);
  
		System.out.println("getMinChgPerm: " + str2);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		System.out.println(); 
	}
}
