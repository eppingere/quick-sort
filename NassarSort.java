import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NassarSort {

	private int threshold;
	private int k;
	Set<Integer> Is;

	public NassarSort(int k, int threshold) {
		this.k = k;
		this.threshold = threshold;
		Is = new HashSet<Integer>();

		for (int i = 0; i < k; i++)
			Is.add(i);
	}

	public long sort(int[] input) {
		long startTime, endTime;
		startTime = System.nanoTime();
		sort(input, 0, input.length);
		endTime = System.nanoTime();

		return endTime - startTime;
	}

	private static int insertPivot(int[] input, int pivot_value, int a, int b) {

		int wall = a;
		for (int i = a; i < b; i++) {
			if (input[i] < pivot_value) {
				int temp = input[i];
				input[i] = input[wall];
				input[wall] = temp;

				wall++;
			}
		}

		return wall;

	}

	public void sort(int[] input, int a, int b) {

		if (b - a <= 1)
			return;

		if (b - a < threshold) {
			Arrays.sort(input, a, b);
			return;
		}

		int[] pivots = new int[k - 1];

		for (int p = 0; p < k - 1; p++)
			pivots[p] = input[a + p];

		Arrays.sort(pivots);

		int[] As = new int[k];
		int[] Bs = new int[k];

		As[0] = a;
		Bs[k - 1] = b;

		for (int p = 0; p < k - 1; p++) {
			As[p + 1] = insertPivot(input, pivots[p], As[p], b);
			Bs[p] = As[p + 1];
		}

		Is.parallelStream().forEach((i) -> {
			sort(input, As[i], Bs[i]);
		});

	}

}
