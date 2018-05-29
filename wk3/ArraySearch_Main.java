import java.util.Arrays;

// Lab week 3-2 Searching in Arrays

class ArraySearch_Main
{
    public static void main(String[] args) {
        ArraySearch AS = new ArraySearch();

	int[] result = new int[2];
        int[][] a1 = new int[][] {
								{1, 2, 8, 9, 11},
								{3, 6, 12, 13, 21},
								{7,10,13, 29, 32},
								{10, 11, 28, 30, 34}
								};

        result = AS.search(a1, 12);
        System.out.println("Expected: 1 2");
	System.out.println("Results: " + result[0] + " " + result[1]);
        System.out.println();

        result = AS.search(a1,30);
        System.out.println("Expected: 3 3");
	System.out.println("Results: " + result[0] + " " + result[1]);
        System.out.println();

        result = AS.search(a1,27);
        System.out.println("Expected: -1 -1");
	System.out.println("Results: " + result[0] + " " + result[1]);
        System.out.println();
    }
}
