import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode() {}
             TreeNode(int val) { this.val = val; }
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
         }
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, 0, res);
        return res;

    }

    private static void DFS(TreeNode node, Integer lvl, List<Integer> res){
        if(node == null) return;
        if(lvl == res.size()) res.add(node.val);
        else res.set(lvl, Math.max(node.val, res.get(lvl)));
        if(node.left != null) DFS(node.left, lvl+1, res);
        if(node.right != null) DFS(node.right, lvl+1, res);
    }
}