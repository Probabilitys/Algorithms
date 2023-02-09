/**
 * This program implements and demonstrates the bucket sort algorithm
 * assumes that the input is drawn from a uniform distribution
 * O(n) in the average case
 */

import java.util.ArrayList;
import java.util.Random;

public class BucketSort {

    /**
     * Apply bucket sort on array A of size n, using k buckets
     * Use insertion sort to sort each bucket
     * @param A the array A to be sorted
     * @param n the size of array A
     * @param k the numbers of buckets to use in the algorithm
     */
    public static void bucketSort(int[] A, int n, int k)
    {
        // intialize the bucket
        ArrayList<Integer>[] bucket = new ArrayList[k];
        for (int i = 0; i < k; i++)
            bucket[i] = new ArrayList<>();
        int limit = max(A) + 1;

        // add elements to the corresponding bucket
        for (int i = 0; i < n; i++)
        {
            int index = (int) Math.floor(k * A[i] / limit);
            bucket[index].add(A[i]);
        }

        int count = 0;
        // sort each bucket using insertion sort
        for (int i = 0; i < k; i++)
        {
            if (!bucket[i].isEmpty())
            {
                insertionSort(bucket[i]);
                for (int j = 0; j < bucket[i].size(); j++)
                {
                    A[count] = bucket[i].get(j);
                    count++;
                }
            }

        }


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
     * Sorts the list A of size n
     * @param A the list A to be sorted
     */
    public static void insertionSort(ArrayList<Integer> A) {
        int n = A.size();
        for (int i = 1; i < n; i++)
        {
            int key = A.get(i);
            // Insert A[i] into the sorted subarray A[0 : i-1]
            int j = i - 1;
            while (j >= 0 && A.get(j) > key)
            {
                A.set(j+1, A.get(j));
                j--;
            }
            A.set(j+1, key);
        }
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
        bucketSort(A, 500, 100);
        printArray(A);
    }
}

