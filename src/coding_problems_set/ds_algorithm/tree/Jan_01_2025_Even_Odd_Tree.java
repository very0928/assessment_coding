package coding_problems_set.ds_algorithm.tree;

import coding_problems_set.common_bean.TreeNode;

import java.util.*;

public class Jan_01_2025_Even_Odd_Tree {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        dfs(root, 0, l);
        return l;
    }

    private void dfs(TreeNode node, int row, List<Integer> l) {
        if(node == null) {
            return;
        }
        if(row == l.size()) {
            l.add(node.val);
        } else {
            l.set(row, Math.max(node.val, l.get(row)));
        }
        dfs(node.left, row + 1, l);
        dfs(node.right, row + 1, l);
    }

    public List<Integer> largestValues_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            long max = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            res.add((int) max);
        }
        return res;
    }

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            int pre = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                if (level % 2 == node.val % 2) {
                    return false;
                }
                if ((level % 2 == 1 && node.val > pre) || (level % 2 == 0 && node.val < pre)) {
                    return false;
                }

                pre = node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            level++;
        }
        return true;
    }

    public boolean isEvenOddTree_1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val % 2 == 0) return false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean isOddLevel = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (!isMeetCond(node.left, isOddLevel, temp)) return false;
                    queue.add(node.left);
                    temp.add(node.left.val);
                }
                if (node.right != null) {
                    if (!isMeetCond(node.right, isOddLevel, temp)) return false;
                    queue.add(node.right);
                    temp.add(node.right.val);
                }
            }
            isOddLevel = !isOddLevel;
        }
        return true;
    }

    private boolean isMeetCond(TreeNode node, boolean isOddLevel, List<Integer> list) {
        boolean evenOddCond = isOddLevel == (node.val % 2 != 0);
        if (!evenOddCond) {
            return false;
        }
        if (list.isEmpty()) {
            return true;
        }
        if (isOddLevel) {
            return node.val > list.get(list.size() - 1);
        } else {
            return node.val < list.get(list.size() - 1);
        }
    }
}
