package math;

import java.util.ArrayList;
import java.util.List;

public class UglyNumber {
	
	public boolean isUgly(int num) {
        if (num == 0) return false;
        while (num%2 == 0 || num%3 == 0 || num%5 == 0) {
            if (num%2 == 0) {
                num=num/2;
            }
            if (num%3 == 0) {
                num=num/3;
            }
            if (num%5 == 0) {
                num=num/5;
            }
            
        }
        return num==1;
    }

	/** 
	 * l2 = list *2 : 1×2, 2×2, 3×2, 4×2, 5×2, …
	 * l3 = list *3 : 1×3, 2×3, 3×3, 4×3, 5×3, …
	 * l5 = list *5 : 1×5, 2×5, 3×5, 4×5, 5×5, …
	 */
	public int nthUglyNumber(int n) {
		if (n<0) return 0;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		
		int i=0, j=0, k=0;
		
		while (list.size() < n) {
			int m2 = list.get(i) *2;
			int m3 = list.get(j) *3;
			int m5 = list.get(k) *5;
			
			int min = Math.min(m2, Math.min(m3, m5));
			list.add(min);
			
			if (min == m2) i++;
			if (min == m3) j++;
			if (min == m5) k++;
		}
		
		return list.get(n-1);
	}
	
	/**
	 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
	 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers 
	 * given primes = [2, 7, 13, 19] of size 4.
	 */
	public int nthSuperUglyNumber(int n, int[] primes) {
        if (n<0) return 0;
        int k = primes.length;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        
        int[] pointers = new int[k];
        
        while(list.size() < n) {
            int[] ms = new int[k];
            int min = Integer.MAX_VALUE;
            for(int i=0; i<k; i++) {
                ms[i] = list.get(pointers[i]) * primes[i];
                min = Math.min(min, ms[i]);
            }
            
            list.add(min);
            
            for(int i=0; i<k; i++) {
                if(ms[i] == min) {
                    pointers[i] = pointers[i] + 1;
                }
            }
        }
        
        
       return list.get(n-1); 
    }
	
	public static void main(String[] args) {
	}

}
