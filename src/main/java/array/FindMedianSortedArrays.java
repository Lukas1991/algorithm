package array;

public class FindMedianSortedArrays {

	
	/**
	 * There are two sorted arrays A and B of size m and n respectively. 
	 * Find the median of the two sorted arrays. 
	 * The overall run time complexity should be O(log (m+n)).
	 * Solution:
	 * http://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/
	 * @param A
	 * @param B
	 * @return
	 */
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
	 
		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
				+ findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}
	
	/**
	 * findKth,总长度5+8=13，中数是第7个，找6个小的。k=6
	 * A: [2,3,7,8,10]， aMid=2; A[aMid]=7
	 * 			
	 * B: [1,4,5,6,9,11,12,13]  bMid=3; B[bMid]=6
	 * A[aMid] > B[bMid], 包括B[bMid]在内的B前半段的4个数[1,4,5,6]都是小的，k=6-4=2, 再找2个小的数.
	 * 因为前四个小的数已经包括了6，所以B=[9,11,12,13],取B[bMid]之后的
	 * A=[2,3,7]，再考虑一次A[aMid]，（以防A之前长度是偶数，那么A[aMid]在前半段）
	 * 
	 */
	public static int findKth(int A[], int B[], int k, 
			int aStart, int aEnd, int bStart, int bEnd) {
		 System.out.println("k: "+k+" aStart: "+aStart+" aEnd: "+aEnd+" bStart: "+bStart+" bEnd: "+bEnd);
			int aLen = aEnd - aStart + 1;
			int bLen = bEnd - bStart + 1;
		 
			// Handle special cases
			if (aLen == 0)
				return B[bStart + k];
			if (bLen == 0)
				return A[aStart + k];
			if (k == 0)
				return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
		 
			//find kth in a and kth in b	
			int aMid = aLen * k / (aLen + bLen); // a's middle count
			int bMid = k - aMid - 1; // b's middle count

			// make aMid and bMid to be array index
			aMid = aMid + aStart;
			bMid = bMid + bStart;
		 
			if (A[aMid] > B[bMid]) {
				System.out.println("A[aMid]: "+A[aMid]+ " > B[bMid]: "+B[bMid]);
				k = k - (bMid - bStart + 1);  //all B first part is smaller 
				aEnd = aMid;  //remove a second part
				bStart = bMid + 1; //remove b first part
				// a second part, b first part, find a new Kth 
				
			} else {
				System.out.println("A[aMid]: "+A[aMid]+ " <= B[bMid]: "+B[bMid]);
				k = k - (aMid - aStart + 1);
				bEnd = bMid; //remove b second part
				aStart = aMid + 1;//remove a first part
			}
		 
			return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int a[]={1,6,7,11,13};
		//int b[]={2,3,4,5,11,15,16};
		
		int a[]={1,6,7,11,13,14,20};
		int b[]={2,11,15,16,17,18,19,20};
		
		double result=findMedianSortedArrays(a,b);
		System.out.println(result);
		//{1,2,3,4,5,6,7,11,11,11,13,15}
		
		//k=6
		//7>5 a{1,6,7} b{11,11,15} k=2 ,remove b front-4,
		//k=2
		//6<11 remove a front-2;a[7] b[11] k=0
		
		//k=5
		//7>4 remove
		
		//int a[]={1,6,7,11,13};
		//int b[]={2,11,15,16,17,18};
		
		
		
		
	}

}
