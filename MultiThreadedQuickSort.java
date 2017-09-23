
/**
 * @author Emmanuel J. Eppinger (GitHub: eppingere)
 * 
 *         Runs MultiThreadedQuickSortHelper
 * 
 */
public class MultiThreadedQuickSort {

	/**
	 * @param input
	 *            Array to be sorted
	 * @throws InterruptedException
	 */
	public static void sort(int[] input) throws InterruptedException {

		Thread currentThread = Thread.currentThread(); // current thread

		// creates new MultiThreadedQuickSortHelper object
		MultiThreadedQuickSortHelper sorter = new MultiThreadedQuickSortHelper(input, 0, input.length, currentThread);

		sorter.start(); // runs sorter

		// waits for sorter to finish
		synchronized (currentThread) {
			currentThread.wait();
		}

	}

}
