import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.*;

    public class Digraph{
        public boolean marked[];
        public int V;
        public LinkedList<Integer>[] adj;
        public Stack<Integer> reversePostorder;
        public LinkedList<Integer> topSort=new LinkedList<Integer>();
        public int[] edgeTo;
        public int[] distTo;
        public int[] inDegree;
        public boolean[] isTaken;
        private int load=6;
        LinkedList preRequisiteList;
        ArrayList<Integer> backTrackList=new ArrayList<Integer>();

        public int getLoad() {
            return load;
        }

        public void setLoad(int load) {
            this.load = load;
        }

        public Digraph(int V)
        {
            this.V = V;
            isTaken=new boolean[V];
            marked = new boolean[V];
            edgeTo=new int[V];
            adj = new LinkedList[V];
            inDegree=new int[V];
            for (int v = 0; v < V; v++){
                adj[v] = new LinkedList<Integer>();
                isTaken[v]=false;
            }

        }
        public void addEdge(int v, int w)
        {

            adj[v].add(w);
            inDegree[w]++;

        }

        public Iterable<Integer> adj(int v)
        { return adj[v]; }

        public void reversePostOrder(Digraph G){
            marked= new boolean[V];
            distTo= new int[V];

            reversePostorder=new Stack<Integer>();
            distTo[0]=0;
            for(int v=0; v<V; v++){
                if(!marked[v]){
                    dfs(G,v);
                }
            }
            while(!reversePostorder.isEmpty()){
                topSort.add(reversePostorder.pop());
            }
        }

        private void dfs(Digraph g, int v) {
            marked[v] = true;
            for (int w : g.adj(v)){
                if (!marked[w]) {
                    edgeTo[w]=v;
                    distTo[w] = distTo[v] + 1;

                    dfs(g, w);
                }
            }

            reversePostorder.push(v);
        checkForSingularity(g,edgeTo);

        }
       public void checkForSingularity(Digraph G,int edgeTo[]){
            for(int i=0; i<G.V; i++)
                  if(distTo[i] == 0){
                      edgeTo[i]=i;
                  }

        }

        public void findPreRequisite(Digraph G,int courseId,ST symbolTable){

            //System.out.print("You should take "+symbolTable.get(courseId)+" after ");
            for(int v=0; v<G.V; v++)
                for(int w: G.adj(v)){
                    if(w==courseId){

                        findPreRequisite(G,v,symbolTable);
                        backTrackList.add(v);
                        //backTrack(G,v,symbolTable);

                        //preRequisiteList.add(symbolTable.get(v));

                    }
                }
           /* for(int i=0; i<preRequisiteList.size(); i++){
                System.out.print(preRequisiteList.get(i));
                System.out.print("->");
            }*/

        }
        
        public void backTrack(Digraph G, int courseId, ST symbolTable){

            int temp = courseId;
            backTrackList.add(courseId);

           /* while(temp != 0){
                temp=edgeTo[temp];
                backTrackList.add(temp);

            }

            */
        }
        Digraph getTranspose(){

            Digraph g = new Digraph(V);
            for (int v = 0; v < V; v++) {
                //Recur for all the certices adjacent to this vertex
                Iterator<Integer> i = adj[v].listIterator();
                while (i.hasNext()){
                    int n = i.next();
                    g.adj[n].add(v);
                }
            }
            return g;
        }
        public void printSchedule(Digraph g, ST symbolTable){
            int semesterCount=0;
            load=this.getLoad();
            while(!isGraduated(inDegree)){
                LinkedList<Integer>semester=new LinkedList<>();
                LinkedList<Integer>indexes=new LinkedList<>();

                for(int i=0; i<inDegree.length; i++){
                    if(inDegree[i]==0 && semester.size()<load){
                        semester.add(i);
                        indexes.add(i);
                    }
                }
                for(int i=0; i<indexes.size(); i++){
                    for(int j : g.adj(indexes.get(i))){
                        inDegree[j]--;
                    }
                }
                for(int i=0;i<indexes.size();i++){
                    inDegree[indexes.get(i)]--;
                }

                semesterCount++;

                System.out.print("Semester :"+semesterCount);
                for(int i=0; i<semester.size(); i++){
                    System.out.print("->");
                    System.out.print(symbolTable.get(semester.get(i)));
                }
                System.out.println();

            }


        }

        public boolean isGraduated(int []inDegree){
            boolean isGraduated=true;
            for(int i=0; i<inDegree.length; i++){
                if(inDegree[i] != -1){
                    isGraduated=false;
                }
            }
            return isGraduated;
        }
       /* public void printBackTrack(ArrayList<Integer> backTrack){
            for(int i=0; i<backTrack.size(); i++){
                System.out.print(backTrack.get(i));
                System.out.print(" ->");
            }
        }*/

    }


