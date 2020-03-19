//-----------------------------------------------------

// Title: GRAPH

// Author: Muhammed İbrahim ÜNAL

//

// Section: 1

// Assignment: 1

// Description: Creating path and searching for shortest path

//-----------------------------------------------------


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Graph {
    static int Knight,Gold,m,n;
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;
    int[] distTo;
    int[] edgeTo;
    boolean[] marked;
    // GRAPH API TAKEN FROM COURSE BOOK
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        distTo=new int[V];
        edgeTo=new int[V];
        marked =new boolean[V];
        adj = new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
        for(int i = 0; i< marked.length; i++){
            marked[i] = false;
        }

    }

    public int V() {
        return V;
    }


    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }
    static void printGraph(Graph graph)
    {
        for(int v = 0; v < graph.V; v++)
        {
            System.out.print("head");
            System.out.println("Adjacency list of vertex "+ v);

            for(Integer pCrawl: graph.adj[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }

    //this part takes graph and input 2d array and checks for 8 possible ways for each '.' position
    public void findPath(Graph graph, char[][] board, int m, int n) {
        this.m=m;
        this.n=n;
        for(int i=0; i<m; i++){
            for(int k=0; k<n; k++){
                if(board[i][k]=='.'){
                    // 1 condition: goes 1 down  2 left  to check
                    if((i+1)>0 && (i+1)<m && (k-2)>0 && (k-2)<n && board[i+1][k-2] != 'T'){
                        graph.addEdge((i*n+k),(i+1)*n+(k-2));
                    }
                    // 2 condition: goes 1 up 2 left  to check
                    if((i-1)>0 && (i-1)<m && (k-2)>0 && (k-2)<n && board[i-1][k-2] != 'T'){
                        graph.addEdge((i*n+k),(i-1)*n+(k-2));
                    }
                    // 3 condition: goes 2 up 1 left to check
                    if((i-2)>0 && (i-2)<m && (k-1)>0 && (k-1)<n && board[i-2][k-1] != 'T'){
                        graph.addEdge((i*n+k),(i-2)*n+(k-1));
                    }
                    // 4 condition: goes 2 up 1 right to check
                    if((i-2)>0 && (i-2)<m && (k+1)>0 && (k+1)<n && board[i-2][k+1] != 'T'){
                        graph.addEdge((i*n+k),(i-2)*n+(k+1));
                    }
                    // 5 condition: goes 1 up 2 right
                    if((i-1)>0 && (i-1)<m && (k+2)>0 && (k+2)<n && board[i-1][k+2] != 'T'){
                        graph.addEdge((i*n+k),(i-1)*n+(k+2));
                    }
                    // 6 condition:  goes 1 down 2 right to check
                    if((i+1)>0 && (i+1)<m && (k+2)>0 && (k+2)<n && board[i+1][k+2] != 'T'){
                        graph.addEdge((i*n+k),(i+1)*n+(k+2));
                    }
                    // 7 condition:   goes 2 down 1 right to check
                    if((i+2)>0 && (i+2)<m && (k+1)>0 && (k+1)<n && board[i+2][k+1] != 'T'){
                        graph.addEdge((i*n+k),(i+2)*n+(k+1));
                    }
                    // 8 condition: goes 2 down 1 left to check
                    if((i+2)>0 && (i+2)<m && (k-1)>0 && (k-1)<n && board[i+2][k-1] != 'T'){
                        graph.addEdge((i*n+k),(i+2)*n+(k-1));
                    }
                }
                else if(board[i][k]=='K'){
                    Knight=(i*n+k);
                }
                else if(board[i][k]=='G'){
                    Gold=(i*n)+k;
                }


            }
        }





    }
    //takes knights adj list index and add it to the queue for look for its adjacency's
    // BFS search algorithm
    public void shortestPath(Graph graph, int Knight){
        Queue<Integer>queue=new LinkedList<>();
        queue.add(Knight);
        distTo[Knight]=0;
        marked[Knight]=true;
        int start=Knight;
        while (!queue.isEmpty() && marked[Gold]==false) {
            start = queue.remove();

            for (int w : graph.adj(start)) {
                if (!marked[w]) {
                    queue.add(w);
                    edgeTo[w] = start;
                    distTo[w] = distTo[start] + 1;
                    marked[w] = true;
                }


            }
        }
        int dist=0;
        if(marked[Gold]){
            //Begin from gold's index from edgeTo arr backwards in order to which path it came from...
            // it means we find the gold..
            int temp=Gold;
            ArrayList<Integer> reverse= new ArrayList<>();

            while(temp != Knight){
                reverse.add(temp);
                dist++;
                temp =edgeTo[temp];
            }

            System.out.println(dist+" steps");
            System.out.print("c"+ ((Knight/n)+1) + ","+((Knight%n)+1)+" to ");
            System.out.print("c"+ ((Gold/n)+1) + ","+((Gold%n)+1)+" : ");
            System.out.print("c"+ ((Knight/n)+1) + ","+((Knight%n)+1)+"-> ");
            for(int k=reverse.size()-1; k>0; k--){
                System.out.print("c"+((reverse.get(k)/n)+1)+","+((reverse.get(k)%n)+1)+" -> ");

            }
            System.out.print("c"+((reverse.get(0)/n)+1)+","+((reverse.get(0)%n)+1));
        }
        else {
            System.out.println("No path to the target.");

        }
//



    }

}