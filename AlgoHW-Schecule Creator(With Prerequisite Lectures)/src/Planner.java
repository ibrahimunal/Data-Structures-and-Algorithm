import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Planner
{
    static Scanner input;
    static File file;
    static Digraph graph;
    public static String fileName;
    public static  ST symbolTable;
    public static  ST symbolTable2;
    public static LinkedList<String> preRequisiteList;
    public static void main(String[] args) {

        try {
            System.out.print("Enter the file name with extension : ");

            input = new Scanner(System.in);
            fileName=input.nextLine();
            File file = new File(fileName);

            input = new Scanner(file);
            symbolTable=createSymbolTable(file);
            graph=createGraphFromFile(file,symbolTable);
            Scanner sc=new Scanner(System.in);
            int choice;
            do{
                String courseId;
                int courseVertex;
                System.out.print("Enter choice (0: Exit, 1: List courses, 2: Check order, 3: Change load):");
                choice=sc.nextInt();
                switch (choice){
                    case 1:
                        graph.printSchedule(graph,symbolTable2);
                        file = new File(fileName);
                        input = new Scanner(file);
                        symbolTable=createSymbolTable(file);
                        graph=createGraphFromFile(file,symbolTable);
                        break;
                    case 2:
                        System.out.print("Enter course:");
                        courseId=sc.next();
                        courseVertex= (int) symbolTable.get(courseId);
                        graph.findPreRequisite(graph,courseVertex,symbolTable2);
                        for(int i=0; i<graph.backTrackList.size(); i++){

                            System.out.print(symbolTable2.get(graph.backTrackList.get(i)));
                            System.out.print("->");
                        }


                      /*  for(int i=0; i<preRequisiteList.size(); i++){
                            int vertex= (int) symbolTable.get(preRequisiteList.get(i));
                            graph.backTrack(graph,vertex);
                        }
                     /*   for(int i=0; i<graph.reversePostorder.size(); i++){
                            System.out.print(symbolTable2
                                    .get(graph.reversePostorder.get(i)));
                            System.out.print("'");

                        }*/



                        break;
                    case 3:
                            System.out.print("Enter load: ");
                        int load=sc.nextInt();
                        graph.setLoad(load);
                        System.out.println("New load is "+ load);
                        break;



                }
            graph.backTrackList= new ArrayList<Integer>();
            }while(choice!=0);

            input.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static ST createSymbolTable(File file) throws FileNotFoundException {
        Scanner scan= new Scanner(file);
        ST symbolTable= new ST();
        symbolTable2=new ST();
        int vertex=Integer.parseInt(scan.nextLine());
        for(int i=0; i<vertex; i++){
            String line[]= scan.nextLine().split(" ");
            symbolTable.put(line[0],i);
            symbolTable2.put(i,line[0]);
        }
        return symbolTable;
    }

    private static Digraph createGraphFromFile(File file,ST symbol) throws FileNotFoundException {

        //reads file and adds it to SymbolTable

        int v=Integer.parseInt(input.nextLine());
        Digraph G=new Digraph(v);
        for(int i = 0; i<v; i++) {
            String line[] = input.nextLine().split(" ");
            if(line.length>1){
                for(int k=0; k<line.length-1; k++){
                    G.addEdge((Integer) symbol.get(line[k+1]),(Integer) symbol.get(line[0]));
                }
            }

         /*       for(int j=k+1; j<line.length; j++){
                    G.addEdge((Integer) symbol.get(line[j]),(Integer) symbol.get(line[k]));
                }*/
        }
        return G;

    }
    public static void printGraph(Digraph graph)
    {
        for(int v = 0; v <graph.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer pCrawl: graph.adj(v)){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }

}

