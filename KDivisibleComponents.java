import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // Build adjacency list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Variable to track the number of valid components
        int[] count = new int[1]; // Using an array to allow mutation inside dfs

        // Define DFS function
        // DFS function to traverse the tree and count valid components
        dfs(0, -1, adj, values, k, count);

        // Return the total number of valid components
        return count[0];
    }

    // DFS Helper Function
    private int dfs(int curr, int parent, Map<Integer, List<Integer>> adj, int[] values, int k, int[] count) {
        int total = 0; // Start with the current node's value
        for (int neighbor : adj.get(curr)) {
            if (neighbor != parent) { // Avoid revisiting the parent node
                total += dfs(neighbor, curr, adj, values, k, count);
            }
        }
        if (total % k == 0) {
            count[0]++; // Increment count if divisible by k
        }
        return total;
    }
}