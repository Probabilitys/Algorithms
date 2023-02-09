/**
 * This program implements and demonstrated the randomized quick sort algorithm
 * Theta(N^2) time in the worst cast
 * Theta(N lgN) expected running time
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QuickSort {

    /**
     * Sorts the subarray A[p : r]
     * @param A the array A to be sorted
     * @param p the leftmost index
     * @param r the rightmost index
     */
    public void quickSort(int[] A, int p, int r) {
        if (p >= r)
            return;
        // Partition the subarray around the pivot, which ends up in A[q]
        int q = randomizedPartition(A, p, r);
        quickSort(A, p, q-1);
        quickSort(A, q+1, r);
    }

    /**
     * partition the subarray A[p : r] and return the index of the randomly selected pivot
     * @param A the array A to be sorted
     * @param p the leftmost index
     * @param r the rightmost index
     * @return the index of the randomly selected pivot
     */
    public int randomizedPartition(int[] A, int p, int r) {
        Random rand = new Random();
        int i = rand.nextInt(p, r+1);  // the pivot
        swap(A, r, i);
        return partition(A, p, r);
    }

    /**
     * select the rightmost element as the pivot, partition the subarray A[p : r]
     * and return the new index of the selected pivot
     * @param A the array A to be sorted
     * @param p the leftmost index
     * @param r the rightmost index
     * @return the new index of the selected pivot
     */
    public int partition(int[] A, int p, int r) {
        int x = A[r];                   // the pivot
        int i = p - 1;                    // highest index into the low side
        for (int j = p; j < r; j++) {   // process each element other than the pivot
            if (A[j] < x) {             // determine whether this element belong on the low side
                i++;                    // new slot in the low side
                swap(A, i, j);          // put this element to the low side
            }
        }
        swap(A, i + 1, r);              // pivot goes just to the right side of the low side
        return i + 1;                   // new index of the pivot
    }

    /**
     * swap the element at index a and b in array A
     * @param A the array A
     * @param a the first index to be exchanged
     * @param b the second index to be exchanged
     */
    public void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // test the QuickSort class
        int[] n = {5, 2, 3, -1, 8, 0, 9, 10, 9};
        QuickSort qs = new QuickSort();
        qs.quickSort(n, 0, n.length-1);
        printArray(n);

        // tests the QueueQuickSort class
        int[] a = {5, 2, 3, -1, 8, 0, 9, 10, 9};
        QueueQuickSort.sort(a);
        printArray(a);

        // tests the QuickSortInPlace class
        int[] b = {5, 2, 3, -1, 8, 0, 9, 10, 9};
        QuickSortInPlace.quickSortInPlace(b, 0, b.length-1);
        printArray(b);
    }
}

/**
 * The class QueueQuickSort applys quick sort to a queue
 */
class QueueQuickSort
{
    /**
     * Apply quick sort to a queue
     * @param S the Queue S to be sorted
     */
    public static void quickSort(Queue<Integer> S)
    {
        int n = S.size();
        if (n < 2) return;  // one element is trivially sorted
        // divide
        int pivot = S.peek();
        Queue<Integer> L = new LinkedList<>();  // contains elements less than pivot
        Queue<Integer> E = new LinkedList<>();  // contains elements equal to pivot
        Queue<Integer> G = new LinkedList<>();  // contains elements greater than pivot
        while (!S.isEmpty())
        {
            // divide the original into L, E, G
            int e = S.remove();
            if (e < pivot)
                L.add(e);
            else if (e == pivot)
                E.add(e);
            else
                G.add(e);
        }
        // conquer
        quickSort(L);  // sorts the elements less than pivot
        quickSort(G);  // sorts the elements greater than piviot
        // concatenate results
        while (!L.isEmpty())
            S.add(L.remove());
        while (!E.isEmpty())
            S.add(E.remove());
        while(!G.isEmpty())
            S.add(G.remove());
    }

    /**
     * Applys quick sort to an array
     * @param A the array A to be sorted
     */
    public static void sort(int[] A)
    {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < A.length; i++)
            q.add(A[i]);
        quickSort(q);
        for (int i = 0; i < A.length; i++)
            A[i] = q.remove();
    }
}

/**
 * Additional Optimizations for Quick-Sort:
 * perform the quick sort in-place
 */
class QuickSortInPlace
{
    /**
     * Sort the subarray S[a : b] inclusive.
     * @param A the subarray A to be sorted
     * @param a the leftmost index of the subarray (inclusive)
     * @param b the rightmost index of the subarray (inclusive)
     */
    public static void quickSortInPlace(int[] A, int a, int b)
    {
        if (a >= b) return;  // trivially sorted
        int l = a;
        int r = b - 1;
        int pivot = A[b];
        int temp;  // temp object for swapping
        while (l <= r)
        {
            // scan until reaching value equal or larger than pivot (or right marker)
            while (l <= r && A[l] < pivot)
                l++;
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (l <= r && A[r] > pivot)
                r--;
            if (l <= r) // indices did not strictly cross
            {
                // swap values and shrink
                temp = A[l];
                A[l] = A[r];
                A[r] = temp;
                l++;
                r--;
            }
        }
        // put pivot into its place (marked by the left index)
        temp = A[l];
        A[l] = A[b];
        A[b] = temp;
        // make recursive calls
        quickSortInPlace(A, a, l-1);
        quickSortInPlace(A, l+1, b);

    }
}