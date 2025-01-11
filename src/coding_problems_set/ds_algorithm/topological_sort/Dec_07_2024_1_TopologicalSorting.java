package coding_problems_set.ds_algorithm.topological_sort;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Dec_07_2024_1_TopologicalSorting {

    class Graph {
        private final int vertices;
        private final LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++)
                adjList[i] = new LinkedList<>();
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        List<Integer> topologicalSortBFS() {
            int[] inDegree = new int[vertices];

            Arrays.stream(adjList).forEach(neighbors -> neighbors.forEach(n -> inDegree[n]++));

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < this.vertices; i++) {
                if (inDegree[i] == 0)
                    q.add(i);
            }
            List<Integer> topOrder = new ArrayList<>();
            int count = 0;
            while (!q.isEmpty()) {
                int currNode = q.poll();
                topOrder.add(currNode);
                count++;
                for (int neighbor : adjList[currNode]) {
                    if (--inDegree[neighbor] == 0) q.add(neighbor);
                }
            }

            if (count != this.vertices) {
                System.out.println("There exists a cycle in the graph");
            }

            return topOrder;
        }


        List<Integer> topologicalSortDFS() {
            Stack<Integer> stack = new Stack<>();
            boolean visited[] = new boolean[this.vertices];

            for (int i = 0; i < this.vertices; i++) {
                if (!visited[i]) {
                    dfs(visited, i, stack);
                }
            }

            List<Integer> topOrder = new ArrayList<>();
            while (!stack.isEmpty()) {
                topOrder.add(stack.pop());
            }
            return topOrder;
        }

        public void dfs(boolean[] visited, int node, Stack<Integer> stack) {
            visited[node] = true;
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    dfs(visited, neighbor, stack);
                }
            }
            stack.push(node);
        }
    }

    Graph g = new Graph(6);
    public Dec_07_2024_1_TopologicalSorting() {
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
    }

    public static void main(String[] args) {
        Dec_07_2024_1_TopologicalSorting sol = new Dec_07_2024_1_TopologicalSorting();
        List<Integer> list;
        list = sol.g.topologicalSortDFS();
        System.out.println("DFS: Topological sort of the given graph:" + list);
        list = sol.g.topologicalSortBFS();
        System.out.println("BFS: Topological sort of the given graph:" + list);
    }
}
