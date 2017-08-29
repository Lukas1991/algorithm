package Tree;

import java.util.Stack;

public class LongestAbsoluteFilePath {

	/**
	 * 388. Longest Absolute File Path.
	 * 
	 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext".
	 * 
	 * The directory dir contains an empty sub-directory subdir1 and a
	 * sub-directory subdir2 containing a file file.ext.
	 * 
	 * The string
	 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
	 * 
	 * We are interested in finding the longest (number of characters) absolute
	 * path to a file within our file system.
	 * 
	 * For example, in the second example above, the longest absolute path is
	 * "dir/subdir2/subsubdir2/file2.ext", and its length is 32
	 */
	public int lengthLongestPath(String input) {
		String[] arr = input.split("\n");
		// store dir length
		Stack<Integer> stack = new Stack<>();
		int max = 0;

		for (int i = 0; i < arr.length; i++) {
			String curr = arr[i];
			int level = level(curr);
			String text = text(curr);

			while (stack.size() > level) {
				stack.pop();
			}

			if (isDir(curr)) {
				if (stack.isEmpty()) {
					stack.push(text.length());
				} else {
					stack.push(text.length() + stack.peek() + 1);
				}
			} else {
				if (stack.isEmpty()) {
					max = Math.max(max, text.length());
				} else {
					max = Math.max(max, text.length() + stack.peek() + 1);
				}
			}
		}

		return max;
	}

	private int level(String curr) {
		String s = curr.replace("\t", "");
		return (curr.length() - s.length());
	}

	private String text(String curr) {
		return curr.replace("\t", "");
	}

	private boolean isDir(String curr) {
		return curr.indexOf(".") == -1;
	}

	public static void main(String[] args) {
		LongestAbsoluteFilePath obj = new LongestAbsoluteFilePath();
		String str = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
		// System.err.println(obj.lengthLongestPath(str));
		// System.err.println(obj.lengthLongestPath("a\n\tb.txt\na2\n\tb2.txt"));
		System.err.println(obj.lengthLongestPath("a\n\tb\n\t\tc.txt\n\taaaa.txt"));

	}

}
