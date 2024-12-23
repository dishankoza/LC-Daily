import java.util.*;

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        List<Pair<Integer, Integer>> monoStack = new ArrayList<>();
        List<List<Pair<Integer, Integer>>> nq = new ArrayList<>(heights.length);
        for(int i = 0; i < heights.length; i++){
            nq.add(new ArrayList<>());
        }
        for(int i = 0; i < queries.length; i++){
            Arrays.sort(queries[i]);
            int a = queries[i][0];
            int b = queries[i][1];
            if(heights[b] > heights[a] || a == b) res[i] = b;
            nq.get(b).add(new Pair<>(heights[a], i));
        }

        for(int i = heights.length - 1; i >= 0; i--){
            
            for(Pair<Integer, Integer> pair: nq.get(i)){
                int idx = search(pair.getKey(), monoStack);
                if(idx < monoStack.size() && idx >= 0)
                    res[pair.getValue()] = monoStack.get(idx).getValue();
            }

            while(!monoStack.isEmpty() && heights[i] >= 
            monoStack.get(monoStack.size() - 1).getKey()){
                monoStack.remove(monoStack.size() - 1);
            }
            monoStack.add(new Pair<>(heights[i], i));
        }

        return res;
    }

    private int search(int height, List<Pair<Integer, Integer>> monoStack){
        int left = 0;
        int right = monoStack.size() - 1;
        int ans = -1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(monoStack.get(mid).getKey() > height){
                ans = Math.max(mid, ans);
                left = mid + 1;
            }else
                right = mid - 1;
        }
        return ans;
    }
}

class Pair<K, V> {
    private final K key;
    private final V value;

    // Constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getters
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    // Optional: Override toString for better readability
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    // Optional: Override equals and hashCode for comparison and hashing
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) && value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 31 + value.hashCode();
    }
}