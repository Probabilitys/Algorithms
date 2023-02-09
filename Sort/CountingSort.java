/**
 * This program implements and demonstrates the counting sort algorithm
 * assumes that each of the n input elements is an integer in the range
 * 0 to k, for some integer k.
 * O(n + k) time for n elements in the range 0 to k
 */

import java.util.Random;

public class CountingSort {

    /**
     * Sort the array A with n elements, where each elements is an integer
     * in the range 0 to k
     * @param A the array A to be sorted
     * @param n the length of array A
     * @param k the upper bound of the range of elements in A
     * @return the sorted array B
     */
    public static int[] countingSort(int[] A, int n, int k)
    {
        int[] B = new int[n];
        int[] C = new int[k+1];

        for (int i = 0; i < k; i++)
            C[i] = 0;  // initialize the array C
        for (int j = 0; j < n; j++)
            C[A[j]]++;
        // C[i] now contains the number of elements equal to i

        for (int i = 1; i < C.length; i++)
            C[i] += C[i-1];
        // C [i] now contains the number of elements less than or equal to i

        // copy A to B, starting from the end of A
        for (int j = n-1; j >= 0; j--)
        {
            B[C[A[j]]-1] = A[j];
            C[A[j]]--;  // deal with duplicate values
        }
        return B;
    }

    static void printArray(int arr[])
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
        int n = 500;
        int k = 1000;
        int[] N = new int[500];
        Random rand = new Random();
        for (int i = 0; i < N.length; i++)
            N[i] = rand.nextInt(k);
        int[] result = countingSort(N, n, k-1);
        printArray(result);
    }
}
