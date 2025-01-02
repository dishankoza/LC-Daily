import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

class ThreeSubArray {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        int[] prefixSums = new int[nums.length + 1];
        prefixSums[0] = 0;
        for(int i = 0; i < nums.length; i++){
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        int currentIdx = 0, count = 0;
        while(count < 3){
            int takenSum = prefixSums[currentIdx + k] - prefixSums[currentIdx] + backtracking(currentIdx + k, nums, count + 1, k, prefixSums);
            int skipSum = backtracking(currentIdx + 1, nums, count, k, prefixSums);
            if(takenSum >= skipSum){
                res[count] = currentIdx;
                currentIdx += k;
                count++;
            }else{
                currentIdx += 1;
            }
        }
        return res;        
    }

    private int backtracking(int currentIdx, int[] nums, int choosen, int partitionNo, int[] prefixSums){
        if(currentIdx > nums.length - partitionNo) return 0;

        if(choosen == 3) return 0;

        //choose the current index
        int includedSum = 0;
        if(currentIdx + partitionNo <= nums.length){
            int sum = prefixSums[currentIdx + partitionNo] - prefixSums[currentIdx];
            includedSum = backtracking(currentIdx + partitionNo, nums, choosen + 1, partitionNo, prefixSums) + sum;
        }
        //skipping the current index
        int skipSum = backtracking(currentIdx + 1, nums, choosen, partitionNo, prefixSums);

        return Math.max(skipSum, includedSum);
    }

    public static void main(String[] args) {
        ThreeSubArray sol = new ThreeSubArray();
        System.out.println(Arrays.stream(sol.maxSumOfThreeSubarrays(new int[]{7,13,20,19,19,2,10,1,1,19}, 3)).boxed().collect(Collectors.toList()));
    }
}