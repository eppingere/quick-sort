import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuickSortEppinger {

	public static void main(String[] args)
			throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
		// TODO Auto-generated method stub

		// Reads file in

		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);

		// array of strings that holds the input values
		ArrayList<String> str_array = new ArrayList<String>();

		// adds values to str_array
		while (in.hasNextLine()) {
			str_array.add(in.nextLine());
		}

		// close in
		in.close();

		// array copies
		int[] input = new int[str_array.size()];
		// int[] copy = new int[str_array.size()];

		// loops through arrays and adds and converts values
		for (int i = 0; i < str_array.size(); i++) {
			input[i] = Integer.parseInt(str_array.get(i));
			// copy[i] = input[i];
		}

		// tries to run sort
		try {

			// time values
			long time_after;
			long time_before;

			// makes print writer
			PrintWriter out = new PrintWriter("EJ_output.txt");

			time_before = System.nanoTime();
			ej_quick_sort3(input, 0, input.length);
			time_after = System.nanoTime();

			// prints result
			System.out.println("Eppinger's Sort Took: " + ((double) (time_after - time_before) / 1000000000.0) + "s\n");

			// System.out.print(sorted(input));

			// sorting_algorithms.file_output(input, out);

			// writes to out
			for (int i = 0; i < input.length; i++) {
				out.println(input[i]);
			}

			// closes out
			out.close();

		}

		catch (Error e) {
			System.out.println(
					"Hey Mr. Nassar, \nUnfortunately, something went wrong, probably with the writing of the output file.\n Thanks,\nEJ");
		}

	}

	// actual sorting function
	@SuppressWarnings("null")
	public static void ej_quick_sort3(int[] input, int a, int b) throws InterruptedException {

		// creates copy of the current thread
		Thread mainthread = Thread.currentThread();

		// creates thread_quick_sort_ej thread: thread1
		thread_quick_sort_ej thread1 = new thread_quick_sort_ej(input, a, b, mainthread);

		// starts thread 1
		thread1.start();

		// waits to be notified
		synchronized (mainthread) {
			mainthread.wait();
		}
	}

	// sorted method checks if the output is sorted.
	public static boolean sorted(int[] input) {

		// loops through input and if any element is unsorted returns false
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] > input[i + 1])
				return false;
		}

		// returns true
		return true;
	}

}
