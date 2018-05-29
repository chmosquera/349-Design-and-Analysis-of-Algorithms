import java.util.Arrays;

// Lab week 3-2 Searching in Arrays

class ArraySearch
{
        // constructor
	public ArraySearch() {
	}

        public static int[] search (int[][] currArray, int target) {
            int[] result = {-1,-1};

            int rows = currArray.length;
            int cols = currArray[0].length;
            						        // start at rop right element of matrix
            int r = 0;
            int c = cols - 1;

            while (r < rows && c >= 0) {      			// r or c still within matrix
                
                if (target == currArray[r][c]) {		// target found
                    return new int[] {r,c};
                } else if (target > currArray[r][c]) {          // move down a row
                    r++;
                } else {                                        // move left a col
                    c--;
                }
            }
            
            return result;
        }

}
