public class MaxChunks {
    public static int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] prefix = new int[len];
        int[] suffix = new int[len];
        int max = arr[0];
        int min = arr[len - 1];
        for(int i = 0; i < len; i++){
            if(i == 0){
                prefix[i] = max;
                suffix[len - 1- i] = min;
            }else{
                max = Math.max(prefix[i-1], arr[i]);
                min = Math.min(suffix[len - 1 - i + 1], arr[len - 1 - i + 1]);
                prefix[i] = max;
                suffix[len - 1 - i] = min;
            }
        }

        int chunks = 1;
        for(int i = 0; i < arr.length; i++){
            if(prefix[i] < suffix[i])
                chunks++;
        }
        return chunks;
    }

    public static void main(String[] args) {
        System.out.println(maxChunksToSorted(new int[] {1,0,2,3,4}));
    }
}
