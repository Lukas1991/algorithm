package Interview.amzn.oa;

import Interview.amzn.oa.KClosestPoints.Point;

/**
 * https://yeqiuquan.blogspot.com/2017/03/lintcode-626-rectangle-overlap.html
 * 
 * @author dacai
 *
 */
public class RectangleOverlap {

	public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
		// Write your code here

		if (l1.x > r2.x || l2.x > r1.x) {
			return false;
		}

		if (r1.y > l2.y || r2.y > l1.y) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
