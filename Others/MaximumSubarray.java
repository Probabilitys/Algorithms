/**
 * This program finds the subarray with the max sum within an array
 */


public class MaximumSubarray {

    /**
     * find the max cross subarray A[i:j] in A from low to high,
     * where low <= i < mid < j <= high
     * @param A an int array (or subarray)
     * @param low the start index
     * @param mid the mid index
     * @param high the end index
     * @return the sum of the max cross subarray in A
     */
    public static int findMaxCrossSubarray(int[] A, int low, int mid, int high)
    {
        if (low == high)
            return A[low];
        // find the max subarray on the low side and ends with index mid
        int low_sum = A[mid];
        int current_sum = low_sum;
        for (int i = mid-1; i >= low; i--)
        {
            current_sum = current_sum + A[i];
            if (current_sum > low_sum)
                low_sum = current_sum;
        }
        // find the max subarray on the high side and starts with index mid+1
        int high_sum = A[mid+1];
        current_sum = high_sum;
        for (int i = mid+2; i <= high; i++)
        {
            current_sum = current_sum + A[i];
            if (current_sum > high_sum)
                high_sum = current_sum;
        }
        return (low_sum + high_sum);
    }

    /**
     * find the max subarray in A from low to high,
     * @param A an in array (or subarray)
     * @param low the start index
     * @param high the end index
     * @return the sum of the max subarray in A
     */
    public static int findMaxSubarray(int[] A, int low, int high)
    {

        if (high == low)
            return A[low];
        else
        {
            int mid = (high + low) / 2;
            int left_sum = findMaxSubarray(A, low, mid);
            int right_sum = findMaxSubarray(A, mid+1, high);
            int cross_sum = findMaxCrossSubarray(A, low, mid, high);
            if (left_sum >= right_sum && left_sum >= cross_sum)
                return left_sum;
            else if (right_sum >= left_sum && right_sum >= cross_sum)
                return right_sum;
            else
                return cross_sum;
        }
    }

    public static void main(String[] args) {
        int[] A = {-2, -1, -3, -4, -5, -6, -7, -1};
        System.out.println(findMaxSubarray(A, 0, A.length-1));
    }
}
