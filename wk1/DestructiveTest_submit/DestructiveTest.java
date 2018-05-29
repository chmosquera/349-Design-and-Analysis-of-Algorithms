
import java.util.ArrayList;
import java.lang.*;

// Feel free to add any other imports

public class DestructiveTest{  //need to change the class name
   /**
    * Expects to be returned an ArrayList<Integer> that has these in the following order:
    * 0 : height of the ladder (input parameter)
    * 1 : actual highest safe run (input parameter)
    * 2 : Highest safe rung determined by this algorithm
    * 3 : Total number of drops required to find the highest safe rung
    * 4 : The first rung from which the device was dropped
    * 5 : The second rung from which the device was dropped
    * 6 : The third rung from which the device was dropped
    * 7 : Rung where the first test device broke
    * 8 : Rung where the second test device broke
*/

	public ArrayList<Integer> findHighestSafeRung(int height, int safest) {

		// initiate and allocate enough room for all elements needed
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i <9; i++) {
			result.add(0);
		}

		result.set(0, height);
		result.set(1, safest);

		int drops = 0;
		int devices = 2;
		double height_d = (double)height;
		int gapsize = (int)Math.sqrt(height_d);
		int last_rung = 0, cur_rung = 0;

		while (devices > 0) {
			if (devices == 2) {
				last_rung = cur_rung;
				cur_rung = cur_rung + gapsize;
				drops++;

				// 1st, 2nd, 3rd rung from which device was dropped
				if (drops == 1) result.set(4, cur_rung);
				else if (drops == 2) result.set(5, cur_rung);
				else if (drops == 3) result.set(6, cur_rung);
	
				if (cur_rung > safest) {
					devices--;
					result.set(7, cur_rung);	// where 1st device broke
					cur_rung = last_rung;		// go back to safe rung
				}
			}
			else if (devices == 1) {
				last_rung = cur_rung;
				cur_rung++;
				drops++;

				if (cur_rung > safest) {
					devices--;
					result.set(8, cur_rung);
				}
			}
		}

		result.set(2, last_rung);	// safest determined rung
		result.set(3, drops);

		return result;

	}
       
   public static void main(String[] args) {
      
      ArrayList<Integer> result = new ArrayList<Integer>();
      DestructiveTest destTest = new DestructiveTest();

      result = destTest.findHighestSafeRung(100, 97);
      
      System.out.print("Test 1");
      System.out.println(" called with arguments (100, 97)   " );
      System.out.println("program computes: ");
      
      System.out.println("    ladder     safe   my safe  totdrps    drop1    drop2    drop3   dev1brk  dev2brk");    
      for (int i = 0; i < result.size(); i++) {
         String numberAsString = String.format("% 9d", result.get(i));
                     //System.out.print(result.get(i));
         System.out.print(numberAsString);
      }
      System.out.println();    
   }
}
