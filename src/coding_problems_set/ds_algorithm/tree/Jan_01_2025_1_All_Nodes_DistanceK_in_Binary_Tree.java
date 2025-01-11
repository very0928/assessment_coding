package coding_problems_set.ds_algorithm.tree;

import coding_problems_set.common_bean.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jan_01_2025_1_All_Nodes_DistanceK_in_Binary_Tree {
    public static void main(String[] args) {

    }

    Map<TreeNode, TreeNode> parent = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    /**
     * DFS Method
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, null);
        find(target, null, k);
        return res;
    }

    public void dfs(TreeNode node, TreeNode pre) {
        if (node != null) {
            parent.put(node, pre);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public void find(TreeNode root, TreeNode pre, int k) {
        if (root == null) {
            return;
        }
        if (k == 0) {
            res.add(root.val);
        }
        if (root.left != pre) {
            find(root.left, root, k - 1);
        }
        if (root.right != pre) {
            find(root.right, root, k - 1);
        }
        if (parent.get(root) != pre) {
            find(parent.get(root), root, k - 1);
        }
    }


    /**
     * DFS Method 2
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK_1(TreeNode root, TreeNode target, int k) {
        findParent(root);
        findRes(target, k, 0, target);
        return res;
    }

    public void findRes(TreeNode node, int k, int depth, TreeNode from) {
        if (depth == k) {
            res.add(node.val);
            return;
        }
        if (node.left != null && node.left != from) {
            findRes(node.left, k, depth + 1, node);
        }
        if (node.right != null && node.right != from) {
            findRes(node.right, k, depth + 1, node);
        }
        TreeNode parentNode = parent.get(node);
        if (parentNode != null && parentNode != from) {
            findRes(parentNode, k, depth + 1, node);
        }
    }

    public void findParent(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parent.put(root.left, root);
            findParent(root.left);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            findParent(root.right);
        }
    }
}
