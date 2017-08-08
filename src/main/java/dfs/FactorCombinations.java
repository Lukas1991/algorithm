package dfs;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

	/**
	 * Write a function that takes an integer n and return all possible
	 * combinations of its factors. 
	 * input: 12 output: [ [2, 6], [2, 2, 3], [3,4]]
	 * 
	 * input: 32 output: [[2, 16],[2, 2, 8],[2, 2, 2, 4],[2, 2, 2, 2, 2],[2, 4, 4],[4, 8]]
	 */
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		getFactors(n, 2, new ArrayList<>(), res);
		return res;
	}

	private void getFactors(int n, int start, List<Integer> tmp, List<List<Integer>> res) {
		if (n <= 1) {
			if (tmp.size() > 1) {
				res.add(new ArrayList<>(tmp));
			}
			return;
		}

		for (int i = start; i <= n; i++) {
			if (n % i == 0) {
				tmp.add(i);
				getFactors(n / i, i, tmp, res);
				tmp.remove(tmp.size() - 1);
			}
		}

	}

	public static void main(String[] args) {
		FactorCombinations obj = new FactorCombinations();
		List<List<Integer>> res = obj.getFactors(24);

		System.err.println(res.toString());
	}

}
