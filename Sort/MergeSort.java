import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This program implements and demonstrated the merge sort algorithm
 * O(N logN) time
 */

/**
 * The class MergeSort applys merge sort to arrays
 */
public class MergeSort {

    /**
     * Perform merge sort on the array A from index l to r
     * @param A the array A to be sorted
     * @param l the leftmost index (inclusive)
     * @param r the rightmost index (inclusive)
     */
    public void mergeSort(int A[], int l, int r) {
        if (l >= r)
            return;
        int m = l + (r - l) / 2;
        mergeSort(A, l, m);  // recursively sort the subarray A[l : m]
        mergeSort(A, m+1, r);  // recursively sort the subarray A[m+1 : r]
        merge(A, l, m, r);  // merge the subarray A[l : m] and A[m+1 : r]
    }

    /**
     * merge the subarray A[l : mid] and
     * @param A the array A to be sorted
     * @param l the leftmost index (inclusive)
     * @param m the last index of the left part (inclusive)
     * @param r the rightmost of A (inclusive)
     */
    public void merge(int A[], int l, int m, int r)
    {
        int nL = m - l + 1;  // length of A[l : m]
        int nR = r - m;  // length of A[m+1 : r]
        int[] L = new int[nL];
        int[] R = new int[nR];

        for (int i = 0; i < nL; i++)  // copy A[l : m] into L
            L[i] = A[l + i];
        for (int j = 0; j < nR; j++)  // copy A[m+1 : r] into R
            R[j] = A[m + j + 1];

        // i indexes the smallest remaining element in L
        // j indexes the smallest remaining element in R
        // k indexes the location in A to fill
        int i = 0, j = 0, k = l;

        // As long as each of the arrays L and R contains an unmerged element,
        // copy the smallest unmerged element back into A
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            }
            else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        // Having gone through one of L and R entirely, copy the
        // remainder of the other to the end of A
        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }
        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // tests the MergeSort class
        int[] n = {2, 6, 2, 77, 4, -7, 0, 99};
        MergeSort ms = new MergeSort();
        ms.mergeSort(n, 0, n.length-1);
        printArray(n);

        // tests the QueueMergeSort class
        int[] a = {2, 6, 2, 77, 4, -7, 0, 99};
        QueueMergeSort.sort(a);
        printArray(a);

        // tests the MergeSortNonrecursive class
        int[] b = {2, 6, 2, 77, 4, -7, 0, 99};
        MergeSortNonrecursive.mergeSortBottomUp(b);
        printArray(b);
    }

}

/**
 * The class QueueMergeSort implements merge sort with linked lists
 */
class QueueMergeSort
{
    public static <E> void merge(Queue<E> S1, Queue<E> S2,
                                 Queue<E> S, Comparator<E> comp)
    {
        // merge two queues until one becomes empty
        while (!S1.isEmpty() && !S2.isEmpty())
            if (comp.compare(S1.peek(), S2.peek()) < 0)
                S.add(S1.remove());
            else
                S.add(S2.remove());

        // add the remaining elements to S
        while (!S1.isEmpty())
            S.add(S1.remove());
        while (!S2.isEmpty())
            S.add(S2.remove());
    }

    public static <E> void mergeSort(Queue<E> S, Comparator<E> comp)
    {
        int n = S.size();
        if (n < 2) return;  // only one element, queue is trivially sorted
        // divide
        Queue<E> S1 = new LinkedList<>();
        Queue<E> S2 = new LinkedList<>();
        while (S1.size() < n/2)  // move the first half to S1
            S1.add(S.remove());
        while (!S.isEmpty())  // move the remaining elements to S2
            S2.add(S.remove());
        // conquer (recursive)
        mergeSort(S1, comp);
        mergeSort(S2, comp);
        // merge
        merge(S1, S2, S, comp);
    }

    /**
     * sorts the array A using merge sort
     * @param A the array A to be sorted
     */
    public static void sort(int[] A)
    {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < A.length; i++)
            q.add(A[i]);
        mergeSort(q, new IntegerComparator());
        for (int i = 0; i < A.length; i++)
            A[i] = q.remove();
    }
}

/**
 * Comparator class for Integer
 */
class IntegerComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer i1, Integer i2) {
        return i1 - i2;
    }
}

/**
 * The class MergeSortNonrecursive implements a nonrecursive merge sort
 */
class MergeSortNonrecursive {
    public static void merge(int[] in, int[] out, int start, int inc)
    {
        int end1 = Math.min(start+inc, in.length);  // boundary for run 1
        int end2 = Math.min(start+2*inc, in.length);  // boundary for run 2
        int p = start;  // index into run 1
        int q = start + inc;  // index into run 2
        int r = start;  // index into output
        while (p < end1 && q < end2)
            if (in[p] < in[q])
                out[r++] = in[p++];  // take the next element from run 1
            else
                out[r++] = in[q++];

        // copy the remaining elements to output
        if (p < end1)
            System.arraycopy(in, p, out, r, end1-p);
        else if (q < end2)
            System.arraycopy(in, q, out, r, end2-q);
    }

    public static void mergeSortBottomUp(int[] A)
    {
        int n = A.length;
        int[] src = A;  // alias for the original
        int[] dest = new int[n];  // a new temporary array
        int[] temp;  // a temporary array only for swapping purpose
        for (int i = 1; i < n; i *= 2)
        {
            for (int j = 0; j < n; j += 2*i)
                merge(src, dest, j, i);

            // reverse roles of the arrays
            temp = src;
            src = dest;
            dest = temp;
        }

        // additional copy to get result to original
        if (A != src)
            System.arraycopy(src, 0, A, 0, n);
    }
}