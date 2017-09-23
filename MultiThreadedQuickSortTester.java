import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Emmanuel J. Eppinger (GitHub: eppingere)
 *
 *         Class to test the speed of Multithreaded Quicksort vs JDK Quicksort
 *
 */
public class MultiThreadedQuickSortTester {

	/**
	 * @param args
	 *            Command line input
	 * 
	 *            Main method for testing Multithreaded Quick Sort
	 * 
	 */
	public static void main(String[] args) {

		int LENGTH_OF_TEST_ARRAY = (int) 1e8; // Just about the largest array most OSs will let you make without having
												// to change settings

		int[] testArray = new int[LENGTH_OF_TEST_ARRAY]; // Array on which we will test our sort

		int MIN_VALUE = Integer.MIN_VALUE; // Minimum value for test array
		int MAX_VALUE = Integer.MAX_VALUE; // Maximum value for test array

		try {

			// before and after times for timing functions
			long before_time;
			long after_time;

			int[] testArrayCopy = new int[testArray.length]; // copy of test array

			System.out.println("Generating Random Array ... ");

			before_time = System.nanoTime();

			for (int i = 0; i < testArray.length; i++) {
				testArray[i] = genRandom(MIN_VALUE, MAX_VALUE);
				testArrayCopy[i] = testArray[i];
			}

			after_time = System.nanoTime();
			System.out.println("Random Array Generated in " + (after_time - before_time) / 1e9 + " seconds\n");

			Thread.sleep(1000);

			System.out.println("Testing on JDK Sort...");

			before_time = System.nanoTime();
			Arrays.sort(testArrayCopy);
			after_time = System.nanoTime();

			System.out.println("Sorted in " + (after_time - before_time) / 1e9 + " seconds\n");

			Thread.sleep(1000);

			System.out.println("Testing on Multi Threaded Quick Sort...");

			before_time = System.nanoTime();
			MultiThreadedQuickSort.sort(testArray);
			after_time = System.nanoTime();

			System.out.println("Sorted in " + (after_time - before_time) / 1e9 + " seconds\n");

			System.out.println("Testing the validity of results...");

			if (!isSorted(testArrayCopy))
				System.out.println("JDK Failed to Correctly Sort Array");

			else if (!isSorted(testArray))
				System.out.println("Multi Threaded Quick Sort Failed to Correctly Sort Array");

			else
				System.out.println("Both sorts were sucessfull!!");

		} catch (Exception e) {
			System.out.println("Opps something went wrong:\n" + e.toString());
		}

	}

	/**
	 * @param min
	 *            Minimum value for output
	 * @param max
	 *            Maximum value for output
	 * @return random integer between min and max
	 */
	static int genRandom(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	/**
	 * @param input
	 *            array to test if sorted
	 * @return whether or not input is sorted from low to hi
	 */
	static boolean isSorted(int[] input) {
		for (int i = 1; i < input.length; i++)
			if (input[i] < input[i - 1])
				return false;

		return true;

	}

}
