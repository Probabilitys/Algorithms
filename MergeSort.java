public class MergeSort {

    
    /** Implements merge sort by sequence */
    public static void mergeSort(
        NodeSequence<Integer> numbers, 
        NodeSequence<Integer> temp,
        int left, int right
    ) throws BoundaryViolationException, InvalidPositionException {
        
        int mid;
        if (right > left) {
            mid = (right + left) / 2;
            mergeSort(numbers, temp, left, mid);
            mergeSort(numbers, temp, mid+1, right);
            merge(numbers, temp, left, mid+1, right);
        }
    }
        
    public static void merge(
        NodeSequence<Integer> numbers, 
        NodeSequence<Integer> temp,
        int left, int mid, int right
    ) throws BoundaryViolationException, InvalidPositionException {

        Position<Integer> leftPos = numbers.atIndex(left);
        Position<Integer> midPos = numbers.atIndex(mid);
        Position<Integer> rightPos = numbers.atIndex(right);
        Position<Integer> tmpPos = temp.atIndex(left);

        
        int left_end, num_elements, tmp_pos;

        left_end = mid - 1;
        tmp_pos = left;
        num_elements = right - left + 1;

        while ((left <= left_end) && (mid <= right)) {

            if (leftPos.getElement() <= midPos.getElement()) {
                temp.set(tmpPos, leftPos.getElement());
                tmpPos = temp.after(tmpPos);
                leftPos = numbers.after(leftPos);
                tmp_pos = tmp_pos + 1;
                left = left +1;
            } 
            else {
                temp.set(tmpPos, midPos.getElement());
                tmpPos = temp.after(tmpPos);
                midPos = numbers.after(midPos);
                tmp_pos = tmp_pos + 1;
                mid = mid + 1;
            }
        }

        while (left <= left_end) {
            temp.set(tmpPos, leftPos.getElement());
            tmpPos = temp.after(tmpPos);
            leftPos = numbers.after(leftPos);
            left = left + 1;   
            tmp_pos = tmp_pos + 1;
        }

        while (mid <= right) {
            temp.set(tmpPos, midPos.getElement());
            tmpPos = temp.after(tmpPos);
            midPos = numbers.after(midPos);
            mid = mid + 1;
            tmp_pos = tmp_pos + 1;
        }

        Position<Integer> tmpRight = temp.atIndex(right);
        for (int i=0; i < num_elements; i++) {
            numbers.set(rightPos, tmpRight.getElement());
            rightPos = numbers.before(rightPos);
            tmpRight = temp.before(tmpRight);
        }
    }  // end merge method
    /** end sequence merge sort */


    /** Implements merge sort by array */
    void mergeSort(int numbers[], int temp[], int array_size) {
        m_sort(numbers, temp, 0, array_size - 1);
    }        
    void m_sort(int numbers[], int temp[], int left, int right) {
        int mid;
        if (right > left) {
            mid = (right + left) / 2;
            m_sort(numbers, temp, left, mid);
            m_sort(numbers, temp, mid+1, right); 
            merge(numbers, temp, left, mid+1, right);
        }
    }        
    void merge(int numbers[], int temp[], int left, int mid, int right) {
        int i, left_end, num_elements, tmp_pos;
        left_end = mid - 1;
        tmp_pos = left;
        num_elements = right - left + 1;
        while ((left <= left_end) && (mid <= right)) {    
            if (numbers[left] <= numbers[mid]) {  
                temp[tmp_pos] = numbers[left]; 
                tmp_pos = tmp_pos + 1; 
                left = left +1;
            } else {        
                temp[tmp_pos] = numbers[mid];                
                tmp_pos = tmp_pos + 1;                
                mid = mid + 1;    
            }        
        }
        while (left <= left_end) {        
            temp[tmp_pos] = numbers[left];        
            left = left + 1;        
            tmp_pos = tmp_pos + 1;        
        }
        while (mid <= right) {        
            temp[tmp_pos] = numbers[mid];        
            mid = mid + 1;        
            tmp_pos = tmp_pos + 1;        
        }
        for (i=0; i <= num_elements; i++) {        
            numbers[right] = temp[right];        
            right = right - 1;        
        }
    }  // end merge
    /** end array merge sort */
}
