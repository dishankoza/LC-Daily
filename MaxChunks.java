import java.util.Arrays;

public class MaxChunks {

    public static int maxChunksToSorted2(int[] arr){
        int len = arr.length;
        int chunks = 0;
        int sortedSum = 0;
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += arr[i];
            sortedSum += i;
            if(sum == sortedSum) chunks++;
        }
        return chunks;
    }

    public static int maxChunksToSorted3(int[] arr){
        int len = arr.length, max = Integer.MIN_VALUE, chunks = 0;
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        for(int i = 0; i < len; i++){
            max = Math.max(max, arr[i]);
            if(sortedArr[i] == max)
                chunks++;
        }
        return chunks;
    }

/*
    1, 2, 40, 4, 5, 0
    method 1 which is simple go prefix and suffix and that will dertermine the chunks
    max of prefix < min suffix then true else false

    method 2 sorting
    0, 1, 2, 4, 5, 40
    1 .. n
    sortedSum 0, 1, 3, 7, 12, 52
    sum 1, 3, 43, 47, 52, 52

    method 3 max
    1 ... n
    1, 2, 40, 4, 5, 0
    0, 1, 2, 4, 5, 40
    max = 1, 2, 40 so the chunks size should be 3 but actually it should be one

    methos 4 monotonic stack

*/

    public static int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] prefix = new int[len];
        int[] suffix = new int[len];
        int max = arr[0];
        int min = arr[len - 1];
        prefix[0] = max;
        suffix[len - 1] = min;

        for (int i = 1; i < len; i++) {
            // Update prefix (max from left) and suffix (min from right)
            prefix[i] = Math.max(prefix[i - 1], arr[i]);
            suffix[len - 1 - i] = Math.min(suffix[len - i], arr[len - i]);
        }

        int chunks = 1;
        for(int i = 0; i < arr.length; i++){
            if(prefix[i] < suffix[i])
                chunks++;
        }
        return chunks;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 40, 4, 5, 0};
        System.out.println(maxChunksToSorted(arr));
        System.out.println(maxChunksToSorted2(arr));
        System.out.println(maxChunksToSorted3(arr));
    }
}
