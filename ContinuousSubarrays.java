import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public long continuousSubarrays(int[] nums) {
        long count = 0;
        int left = 0;
        Deque<Integer> minMDeque = new LinkedList<>();
        Deque<Integer> maxMDeque = new LinkedList<>();
        for(int right = 0; right < nums.length; right++){
            int num = nums[right];
            while(!minMDeque.isEmpty() && nums[minMDeque.peekLast()] > num)
                minMDeque.pollLast();
            minMDeque.offerLast(right);

            while(!maxMDeque.isEmpty() && nums[maxMDeque.peekLast()] < num)
                maxMDeque.pollLast();
            maxMDeque.addLast(right);

            while((nums[maxMDeque.peekFirst()] - nums[minMDeque.peekFirst()]) > 2){
                if(maxMDeque.peekFirst() < minMDeque.peekFirst()){
                    left = maxMDeque.pollFirst() + 1;
                }else
                    left = minMDeque.pollFirst() + 1;
            }

            count += right - left + 1;
        }

        return count;
    }
}