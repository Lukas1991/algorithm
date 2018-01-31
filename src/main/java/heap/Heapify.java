package heap;

/**
 * Heapify Demo https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/DemoHeapify.pdf
 * <p>
 * Heapify一个Array，也就是对array中的元素进行siftup或者siftdown的操作。根据min heap定义进行操作即可。
 * 这里值得注意的是，对于扫描整个array的情况下，siftup和siftdown有complexity上的区别。
 * 基本的原因在于：siftdown的complexity，实质上是node相对于bottom移动的次数，而根据binary heap本身的特性，决定了约靠近bottom的node越多；相对照的是siftup，是node相对于root节点的移动次数。
 * <p>
 * 如果Heapify可以用O(n)实现，那么HeapSort所需的时间复杂度为何是O(nlogn)？
 * 因为HeapSort其实包含了两个步骤，第一步，Heapify，build (min) heap，所需时间复杂度O(n)，
 * 第二步，依次删除最小值（min heap），对于Heap来说，删除操作的复杂度是O(logn)， 而这个删除需要执行O(n)，来得到最终sort的结果，于是总体时间复杂度是O(nlogn)。
 */
public class Heapify {

    /**
     * 从中点开始，往前扫，siftdown
     * O(n)
     */
    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        }
    }

    //i, 2i+1, 2i+2中取最小的，跟交换, 就是最小的挪到顶部。
    //交换完还有左右孩子，继续取最小交换
    void siftdown(int[] A, int i) {
        while (i < A.length) {

            int smallestIndex = i;

            if (i * 2 + 1 < A.length && A[i * 2 + 1] < A[smallestIndex]) {
                smallestIndex = i * 2 + 1;
            }

            if (i * 2 + 2 < A.length && A[i * 2 + 2] < A[smallestIndex]) {
                smallestIndex = i * 2 + 2;
            }

            if (smallestIndex == i) { //不用交换了，以为root的子树已经heapify过了
                break;
            }

            swap(A, i, smallestIndex);

            i = smallestIndex;
        }
    }

    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
