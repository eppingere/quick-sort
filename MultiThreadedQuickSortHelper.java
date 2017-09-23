
/**
 * @author Emmanuel J. Eppinger (GitHub: eppingere)
 * 
 *         Multithreaded QuickSortHelper
 * 
 *         Helper class for Multithreaded Quicksort
 *
 */
public class MultiThreadedQuickSortHelper extends Thread {

	// input array
	private int[] input;
	// lower bound
	private int min;
	// upper bound
	private int max;
	// copy of main Thread
	private Thread mainthread;

	// count of threads running
	private static Integer count = 0;

	/**
	 * @param input_in
	 *            array to be sorted
	 * @param a_in
	 *            lower index of section being sorted
	 * @param b_in
	 *            upper index of section being sorted
	 * @param mainthread_in
	 *            thread to run in
	 */
	public MultiThreadedQuickSortHelper(int[] input_in, int a_in, int b_in, Thread mainthread_in) {

		// initialize instance variables
		input = input_in;
		min = a_in;
		max = b_in;

		// make copy of mainthread
		mainthread = mainthread_in;

		// increase count
		synchronized (count) {
			count++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 * 
	 * Method to launch sort
	 * 
	 */
	@Override
	// Run method for thread
	public void run() {

		// sorts from this methods min to max
		sort(min, max);

		// decreases count
		synchronized (count) {
			count--;
			// if count isn't zero return
			if (count > 0) {
				return;
			}
		}

		// else notify
		synchronized (mainthread) {
			mainthread.notify();
		}

	}

	// sorting method, sorts on input from a to b
	/**
	 * @param a
	 *            lower bound for subsection being sorted
	 * @param b
	 *            upper bound for subsection being sorted
	 */
	public void sort(int a, int b) {

		// length, the amount that needs to be sorted
		int length = b - a;

		// if length is 0 or 1 return
		if (length < 2) {
			return;
		}

		// if length is 2
		if (length == 2) {

			// if the two elements are out of order
			if (input[a] > input[b - 1]) {

				// flip the elements
				int temp = input[a];
				input[a] = input[b - 1];
				input[b - 1] = temp;
			}

			// return after elements are in right order
			return;
		}

		// intiailize pivot value, wall, temp
		int pivot_value = input[b - 1];
		int wall = a;
		int temp;

		// loop through from a to b
		for (int i = a; i < b; i++) {

			// if the ith element is less than the pivot value
			if (input[i] < pivot_value) {

				// swap with the wall
				temp = input[i];
				input[i] = input[wall];
				input[wall] = temp;

				// increase wall
				wall++;
			}
		}

		// swap pivot with wall
		temp = input[wall];
		input[wall] = pivot_value;
		input[b - 1] = temp;

		// if new thread is not needed
		if (input.length < 5000000 || wall - a < input.length / 200) {

			// sort recursively
			sort(a, wall);

		} else {

			// make new thread and run on first half-sub array
			MultiThreadedQuickSortHelper thread1 = new MultiThreadedQuickSortHelper(input, a, wall, mainthread);
			thread1.start();

		}

		// recursively run on the second half-sub array
		sort(wall + 1, b);
	}

}
