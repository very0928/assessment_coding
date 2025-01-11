package coding_problems_set.ds_algorithm.tree;

import java.util.*;

public class Dec_19_2024_1_Basic_Tree_methods {
    public static void main(String[] args) {
        int[] random = new int[] {67,7,30,73,10,0,78,81,10,74};
        Node roots = new Node();
        for (int number: random){
            roots.add(number);
        }

        int[] arr1 = new int[] {1,3,2,4,7,9};
        List<Node> list = new ArrayList<>();
        Node newNode = new Node();
        Node root = newNode.createTree(arr1,list);
        root.printTree(root);
    }


    public static class Node{
        public Node leftNode;
        public Node rightNode;
        public Object value;
        public int no;

        public Node(){
            super();
        }

        public Node(Object node){
            super();
            this.value = node;
        }

        public Node (Object node, int no){
            super();
            this.no = no;
            this.value = node;
        }

        public Object getValue(){
            return value;
        }

        public String toString(){
            return Objects.toString(value);
        }


        public void add(Object v){
            if(null == value){
                value = v;
            }
            else {
                if(((Integer)v) - ((Integer)value) <= 0){
                    if(null==leftNode){
                        leftNode = new Node();
                    }
                    leftNode.add(v);
                }
                else {
                    if(null == rightNode){
                        rightNode = new Node();
                    }
                    rightNode.add(v);
                }
            }
        }

        public List<Object> values(){
            List<Object> values = new ArrayList<>();

            if(null != leftNode){
                values.addAll(leftNode.values());
            }

            values.add(value);

            if(null != rightNode){
                values.addAll(rightNode.values());
            }

            return values;
        }

        public void preOrder(Node root){
            if(null == root){
                return;
            }else {
                System.out.print(root.toString()+"\t");
                preOrder(root.leftNode);
                preOrder(root.rightNode);
            }
        }

        /**
         * 使用非递归的方法实现 遍历
         * @param root
         */
        //非递归实现前序遍历
        public void preOrderNonRecursive(Node root){
            //访问根节点，接着判断当前节点又子树不为空则入栈，然后访问左子树
            Stack<Node> nodeStack = new Stack<>();
            nodeStack.push(null);
            while (root != null){
                System.out.println("preOrderNonRecursive: "+root.getValue());
                if(root.rightNode != null){
                    nodeStack.push(root.rightNode);
                }
                if(root.leftNode != null){
                    root = root.leftNode;
                }else {
                    root = nodeStack.pop(); //如果左节点为空的话，就出栈右节点
                }
            }

        }

        //非递归实现 中序遍历
        public void midOrderNonRecursive(Node root){
            //将遇到的节点压到栈中，当左子树为空时候弹出栈，遍历右子树
            Stack<Node> nodeStack = new Stack<>();
            do{
                while(root != null){
                    nodeStack.push(root);
                    root = root.leftNode;
                }
                if(!nodeStack.empty()){
                    root = nodeStack.pop();
                    System.out.println("midOrderNonRecursive: "+root.getValue());
                    root = root.rightNode;
                }
            }while (root != null || !nodeStack.empty());
        }

        //非递归 后序遍历
        public void postOrderNonRecursive(Node root){
            Stack<Node> nodeStack = new Stack<>();
            Node prev = root;
            do{
                while (root != null){
                    nodeStack.push(root);
                    root = root.leftNode;
                }
                //访问当前节点的右节点
                if(!nodeStack.isEmpty()){
                    //获取右子树但先不弹出
                    Node temp = nodeStack.peek().rightNode;
                    //不存在右子树或者右子树已经访问过 访问父节点
                    if(temp == null || temp==prev){
                        root = nodeStack.pop();
                        System.out.println("postOrderNonRecursive: "+root.getValue());
                        //记录访问过的节点
                        prev =root;
                        //当前节点置空
                        root =null;
                    }else {
                        //存在右子树，需要优先访问右子树
                        root = temp;
                    }
                }
            }while (root != null || !nodeStack.empty());
        }
        
        public int treeHeight(Node root){
            if(null == root){
                return 0;
            }else {
                int i = treeHeight(root.leftNode);
                int j = treeHeight(root.rightNode);
                return (i>=j)? i+1:j+1;
            }
        }

        public int treeSize(Node root){
            if(null == root){
                return 0;
            }else {
                return 1+treeSize(root.leftNode)+treeSize(root.rightNode);
            }
        }

        //前序创建二叉树
        public Node createBinaryTree(List<String> data){
            if(data == null || data.isEmpty()){
                return null;
            }
            return createBinaryTreeHelper(data.size(),data);
        }
        public Node createBinaryTreeHelper(int size, List<String> data){
            Node node;
            String d = data.get(0);
            int index = size-data.size();

            if(d.equals("#")){
                node =null;
                data.remove(0);
                return node;
            }

            node = new Node(d); //index, d
//            node.value = d;

            if(index ==0){
                Node rootNew = node;
            }
            data.remove(0);

            node.leftNode = createBinaryTreeHelper(size,data);
            node.rightNode = createBinaryTreeHelper(size,data);
            return node;

        }

        public void setLeft(Node leftNode){
            this.leftNode = leftNode;
        }

        public void setRight(Node rightNode){
            this.rightNode = rightNode;
        }

        public Node createTree(int[] arr, List<Node> list){
            //把数组中的元素变成节点的形式
            for(int i=0; i<arr.length;i++){
                Node node = new Node(arr[i]);
                list.add(node);
            }
            //关联节点
            for(int j=0; j<list.size()/2-1;j++){
                list.get(j).setLeft(list.get(j*2+1));
                list.get(j).setRight(list.get(j*2+2));
            }
            //处理父节点
            int index = list.size()/2-1;
            list.get(index).setLeft(list.get(index*2+1));
            if(list.size()%2==1){
                list.get(index).setRight(list.get(index*2+2));
            }
            Node rootNode = list.get(0);
            return rootNode;
        }

        //打印树，层次遍历
        public void printTree(Node root){
            if(root == null){
                return;
            }
            Queue<Node> queue = new LinkedList<Node>();
            queue.offer(root);

            while (!queue.isEmpty()){
                Node node = queue.peek();
                queue.poll();

                if(node != null){
                    System.out.println(node.toString()+" ");
                }

                if(node.leftNode != null){
                    System.out.println(node.getLeft());
                }

                if(node.rightNode != null){
                    System.out.println(node.getRight());
                }
            }
        }

        public Node getLeft() {
            return leftNode;
        }

        public Node getRight(){
            return rightNode;
        }

    }
}
