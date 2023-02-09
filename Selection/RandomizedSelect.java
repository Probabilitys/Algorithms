import java.util.Random;

/**
 * The RANDOMIZED-SELECT procedure returns the i-th smallest element
 * of the array A[p : r], where 1 <= i <= r - p + 1
 */

public class RandomizedSelect {

    /**
     * partition the subarray A[p : r] and return the index of the randomly selected pivot
     * @param A the array A to be sorted
     * @param p the leftmost index
     * @param r the rightmost index
     * @return the index of the randomly selected pivot
     */
    public static int randomizedPartition(int[] A, int p, int r) {
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
    public static int partition(int[] A, int p, int r) {
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
    public static void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * Returns the i-th smallest element of array A using randomized-select
     * @param A an int array or subarray A
     * @param p the leftmost index for selection
     * @param r the rightmost index for selection
     * @param i the i-th smallest element to be selected
     * @return the i-th smallest element of array A
     */
    public static int randomizedSelect(int[] A, int p, int r, int i)
    {
        if (p == r)
            return A[p];  // i = 1 when p == r
        int q = randomizedPartition(A, p, r);
        int k = q - p + 1;
        if (i == k)
            return A[q];  // the pivot value is the answer
        else if (i < k)
            return randomizedSelect(A, p, q-1, i);  // select from the low side
        else
            return randomizedSelect(A, q+1, r, i-k);  // select from the high side
    }

    public static void main(String[] args) {
        int[] a = {5, 8, 2, 6, 7, 1, 3, 4, 9};
        int i = randomizedSelect(a, 0, a.length-1, 9);
        System.out.println(i);
    }
}
