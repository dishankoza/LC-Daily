import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

 
class Solution {

    //Definition for a binary tree node.
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


    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        int level = 0;
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            if(level % 2 != 0){
                int l = 0, right = size - 1;
                Object[] tempNodeArr = que.toArray();
                while(l < right){
                     TreeNode lNode = (TreeNode) tempNodeArr[l];
                     TreeNode rNode = (TreeNode) tempNodeArr[right];
                     int temp = lNode.val;
                     lNode.val = rNode.val;
                     rNode.val = temp;
                     l++;
                     right--;
                }
            }
            for(int i = 0; i < size; i++){
                TreeNode node = que.poll();
                if(node.left != null) que.add(node.left);
                if(node.right != null) que.add(node.right);
            }
            level++;
                
        }
        return root;
    }
}

/* 
dfs
determine current level, keep an level variable to track tree level based on the counting the poping
*/