import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class NassarSortTester {

	public static void main(String[] args) {
		int LENGTH_OF_TEST_ARRAY = (int) 3.575e8;

		int[] testArray = new int[LENGTH_OF_TEST_ARRAY];
		int MIN_VALUE = Integer.MIN_VALUE;
		int MAX_VALUE = Integer.MAX_VALUE;

		try {

			long before_time;
			long after_time;

			System.out.println("Generating Random Array...");

			before_time = System.nanoTime();

			for (int i = 0; i < testArray.length; i++)
				testArray[i] = genRandom(MIN_VALUE, MAX_VALUE);

			after_time = System.nanoTime();
			System.out.println("Random Array of Length: " + testArray.length + " Generated in "
					+ (after_time - before_time) / 1e9 + " seconds\n");

			Thread.sleep(1000);

			System.out.println("Testing on NassarSort...");

			NassarSort N = new NassarSort(12, 400);

			before_time = System.nanoTime();
			N.sort(testArray, 0, testArray.length);
			after_time = System.nanoTime();

			System.out.println("Sorted in " + (after_time - before_time) / 1e9 + " seconds\n");

			System.out.println("Testing Correctness of sort....");
			if (!isSorted(testArray))
				System.out.println("ERROR: This is awkward....NassarSort Failed to Correctly Sort Array");
			else
				System.out.println("Sorted Correctly!!!\n");

			Thread.sleep(1000);

			System.out.println("DONE!!!!!");

		} catch (Exception e) {
			System.out.println("Opps something went wrong:\n" + e.toString());
		}

	}

	static int genRandom(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	static boolean isSorted(int[] input) {
		if (input.length <= 1)
			return true;

		for (int i = 1; i < input.length; i++)
			if (input[i] < input[i - 1])
				return false;

		return true;
	}

}
