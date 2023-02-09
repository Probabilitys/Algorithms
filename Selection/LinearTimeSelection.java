/**
 * This program demonstrates a linear-time selection algorithm
 */

public class LinearTimeSelection {

    /**
     * swap the element at index a and b in array A
     * @param A the array A
     * @param a the first index to be exchanged
     * @param b the second index to be exchanged
     */
    public static void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * Sorts the array A of size n
     * @param A he array A to be sortedt
     * @param p the size of array A
     * @param g
     */
    public static void insertionSort(int[] A, int p, int g) {
        for (int i = (p+g); i <= (p + 4 * g); i += g)
        {
            int key = A[i];
            // Insert A[i] into the sorted subarray A[0 : i-1]
            int j = i - g;
            while (j >= p && A[j] > key)
            {
                A[j+g] = A[j];
                j -=g ;
            }
            A[j+g] = key;
        }
    }

    /**
     * select the rightmost element as the pivot, partition the subarray A[p : r]
     * and return the new index of the selected pivot
     * @param A the array A to be sorted
     * @param p the leftmost index
     * @param r the rightmost index
     * @param x the selected pivot
     * @return the new index of the selected pivot
     */
    public static int partitionAround(int[] A, int p, int r, int x) {
        swap(A, r, x);
        int y = A[r];                   // the pivot
        int i = p - 1;                    // highest index into the low side
        for (int j = p; j < r; j++) {   // process each element other than the pivot
            if (A[j] < y) {             // determine whether this element belong on the low side
                i++;                    // new slot in the low side
                swap(A, i, j);          // put this element to the low side
            }
        }
        swap(A, i + 1, r);              // pivot goes just to the right side of the low side
        return i + 1;                   // new index of the pivot
    }

    /**
     * returns the i-th smallest element in array A from index p to r
     * in linear time
     * @param A an integer array (subarray) A
     * @param p the leftmost index
     * @param r the rightmost index
     * @param i the i-th smallest element
     * @return the i-th smallest element
     */
    public static int select(int[] A, int p, int r, int i)
    {
        while ((r - p + 1) % 5 != 0)
        {
            for (int j = p + 1; j <= r; j++)  // put the minimum into A[p]
                if (A[p] > A[j])
                    swap(A, p, j);

            if (i == 1)  // if we want the minimum, we are done
                return A[p];

            // otherwise, we want the (i-1)st element of A[p+1 : r]
            p++;
            i--;
        }

        int g = (r - p + 1) / 5;  // number of 5-element groups
        for (int j = p; j <= p+g-1; j++)  // sort each group using a helper sorting algorithm
            insertionSort(A, j, g);

        // All group medians now lie in the middle fifth of A[p : r]
        // Find the pivot x recursively as the median of the group medians
        int mid = (int) Math.ceil((double) g/2);
        int x = select(A, p + 2*g, p + 3*g -1, mid);
        int q = partitionAround(A, p, r, x);  // partition around the pivot
        int k = q - p + 1;
        if (i == k)
            return A[k];
        else if (i < k)
            return select(A, p, q-1, i);
        else
            return select(A, q+1, r, i-k);
    }

    public static void main(String[] args) {
        int[] a = {5, 15, 8, 2, 12, 6, 14, 7, 10, 1, 3, 4, 9, 11, 13};
        int i = select(a, 0, a.length-1, 13);
        System.out.println(i);
    }
    
}
