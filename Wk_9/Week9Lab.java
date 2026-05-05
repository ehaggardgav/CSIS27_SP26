import java.util.*;


public class Week9Lab {
    public static void main(String[] args) {
        Week9Lab obj = new Week9Lab();

        int size = 1 + new Random().nextInt(10);
        int[] arr = generateRandomArray(size);

        System.out.println("Random values:");
        for (int n : arr) System.out.print(n + " ");
        System.out.println("\n");

        TreeNode root = buildTree(arr);

        System.out.println("Tree (level order):");
        printTree(root);

        int result = obj.minDepth(root);
        System.out.println("\nMinimum Depth: " + result);
    }

    public static int[] randomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

     public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;

        while (i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length) {
                current.left = new TreeNode(arr[i++]);
                queue.add(current.left);
            }

            if (i < arr.length) {
                current.right = new TreeNode(arr[i++]);
                queue.add(current.right);
            }
        }

        return root;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (current.left == null && current.right == null) {
                    return depth;
                }

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            depth++;
        }

        return depth;
    }

    static class TreeNode {
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

    
}
