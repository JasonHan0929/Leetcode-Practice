public class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return 0 ;
        int mid = 0;
        int low = 1;
        int high = x;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (mid > x / mid)
                high = mid - 1;
            else {
                if (mid + 1 > x / (mid + 1))// when x = 2
                    return mid;
                low = mid + 1;
            }
        }
        return -1;
    }
}
/*
The sequence 1, 2, ... , n has no duplication.
Near the very end, closest step, before while loop, left = mid = right.
In while, If mid < sqrt(x), left = mid + 1 executed, right pointer is not moving, and right is the answer.
If while, If mid > sqrt(x), right = mid - 1 executed, right pointer shifts left 1, closest to sqrt(x), right is also the answer.
*/
public class Solution {
    public static int mySqrt(int x) {
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
