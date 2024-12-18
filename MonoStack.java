import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class  MonoStack {
    /*Brute force approach
    public int[] finalPrices(int[] prices) {
        for(int i = 0; i < prices.length - 1; i++){
            int price = prices[i];
            int right = i + 1;
            while(right < prices.length && prices[right] > price){
                right++;
            }
            if(right < prices.length)
               prices[i] -= prices[right];
        }
        return prices;
    }
     */

    //monoStack approach
    public static int[] finalPrices(int[] prices) {
        int[] monoStack = new int[prices.length];
        int top = - 1;
        for(int i = 0; i < prices.length; i++){
            int num = prices[i];
            while(top != -1 && prices[monoStack[top]] >= num){
                int idx = monoStack[top];
                prices[idx] -= num;
                top--;
            }
            top++;
            monoStack[top] = i;
        }
        return prices;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(
            Arrays.stream(finalPrices(new int[]{8,4,6,2,3}))
            .boxed().collect(Collectors.toList()));
        
            System.out.println(arr);
    }
}