package coding_problems_set.ds_algorithm.classic_algorithms;
import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class Dec_19_2024_1_LRUCache {
    private int capacity;
    private HashMap<Integer,Node> map; //维护一个哈希表
    private DoubleList cache; //维护一个双链表

    public Dec_19_2024_1_LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        //key不存在，返回-1
        if(! map.containsKey(key)){
            return -1;
        }
        //key存在==>提升到队头位置
        makeRecently(key);
        return map.get(key).value;
    }

    //LinkedHashMap 哈希表支持快速查找，链表支持快速插入和删除
    public void put(int key, int value) {
        //容量满，键存在 ==> 提升到队尾位置 （合并）//容量未满,键存在 ==> 提升到队尾位置
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            makeRecently(key);
        }else{
            //容量满，键不存在 ==> 删除最久未使用的，插入到队尾
            if(cache.size()==capacity){
                removeLeastRecently();
            }
            //容量未满，键不存在 ==> 直接插入到队尾
            addRecently(key,value);
        }
    }

    //实现几个API常用操作
    //01 将某个存在的key提升为最近使用的，删除所在位置，放到队尾
    private void makeRecently(int key){
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    //02 删除最久未使用的
    private void removeLeastRecently(){
        Node deleteNode = cache.removeFirst();
        int deleteKey = deleteNode.key;
        map.remove(deleteKey);
    }

    //03 将新键值对加到队尾
    private void addRecently(int key, int val){
        Node node = new Node(key,val);
        map.put(key,node);
        cache.addLast(node);
    }

    //首先实现双链表的 节点类
    static class Node {
        public int key;
        public int value;
        public Node next;
        public Node prev;

        public Node(int k, int v){
            this.key = k;
            this.value = v;
        }
    }

    static class DoubleList{
        private Node head;
        private Node tail;
        private int size;

        public DoubleList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
            size=0;
        }

        //双链表的尾部插入
        public void addLast (Node node){
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
            size++;
        }

        //双链表删除一个节点
        public void remove(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        //双链表删除第一个节点
        public Node removeFirst(){
            if(head.next == tail) return null;
            Node res = head.next;
            // head.next = res.next;
            // res.next.prev = head;
            remove(res); //调用函数 更加清晰
            return res;
        }

        //返回链表长度
        public int size(){
            return size;
        }
    }

    public static void main(String[] args) {
        Dec_19_2024_1_LRUCache lru = new Dec_19_2024_1_LRUCache(10);
        for(int i=0; i<11; i++) {
            lru.put(i, i * i);
        }
        System.out.println(lru.get(3));
    }
}