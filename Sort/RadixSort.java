import java.util.Random;

/**
 * This program implements and demonstrates the radix sort algorithm
 * with counting sort as the helper sorting algorithm
 * Theta(d(n + k)) time if counting sort takes Theta(n+k) time
 */


public class RadixSort {


    /**
     * Counting sort according the digit indicated by d
     * @param A the array A to be sorted
     * @param n the length of array A
     * @param k the upper bound of the range of elements in A
     * @param d indicates the digit
     * @return the sorted array B
     */
    public static void countingSort(int[] A, int n, int k, int d)
    {
        int[] B = new int[n];
        int[] C = new int[k+1];
        int factor = d * 10;

        for (int i = 0; i < k; i++)
            C[i] = 0;  // initialize the array C
        for (int j = 0; j < n; j++)
            C[(A[j] / d) % 10]++;
        // C[i] now contains the number of elements equal to i

        for (int i = 1; i < C.length; i++)
            C[i] += C[i-1];
        // C [i] now contains the number of elements less than or equal to i

        // copy A to B, starting from the end of A
        for (int j = n-1; j >= 0; j--)
        {
            B[C[(A[j] / d) % 10]-1] = A[j];
            C[(A[j] / d) % 10]--;  // deal with duplicate values
        }

        // copy B to A so that A is sorted according to the digit
        for (int i = 0; i < n; i++)
            A[i] = B[i];
    }

    /**
     * Find the max value in array A
     * @param A an integer array A
     * @return the max value in array A
     */
    public static int max(int[] A)
    {
        int max = A[0];
        for (int i = 0; i < A.length; i++)
        {
            if (A[i] > max)
                max = A[i];
        }
        return max;
    }

    /**
     * Apply radix sort the array A using counting sort
     * as the helper sorting algorithm
     * @param A the array A to be sorted
     */
    public static void radixSort(int[] A)
    {
        int m = max(A);
        int n = A.length;
        for (int d = 1; m / d > 0; d *= 10)
            countingSort(A, n, 10, d);
    }

    public static void printArray(int arr[])
    {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; ++i)
        {
            System.out.print(arr[i] + " ");
            count++;
            if (count % 20 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = new int[500];
        Random rand = new Random();
        for (int i = 0; i < A.length; i++)
            A[i] = rand.nextInt(1000);
        radixSort(A);
        printArray(A);
    }
}
