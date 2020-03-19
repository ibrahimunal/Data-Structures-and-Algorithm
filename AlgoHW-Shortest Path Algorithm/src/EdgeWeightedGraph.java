import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EdgeWeightedGraph
{
    private  City[] cities;
    public int numOfCities;
    private int numOfEdges;
    private  int V;
    private double[] distTo;
    private Edge[] edgeTo;
    private static EdgeWeightedGraph graph;
    private IndexMinPQ<Double> pq;
    private HashMap<String, Integer> map;
    Scanner sc = new Scanner(System.in);

    private File file;

    private  LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(String fileName)
    {
        File newFile = new File(fileName);

        try{
            Scanner fileReader = new Scanner(newFile);
            this.V = fileReader.nextInt();
            map = new HashMap<>();
            this.numOfEdges = Integer.parseInt(fileReader.nextLine().trim());
            cities = new City[this.V];
            adj = (LinkedList<Edge>[]) new LinkedList[this.V];
            for (int v = 0; v < V; v++)
                adj[v] = new LinkedList<Edge>();
            while (fileReader.hasNext()){
                String command = fileReader.nextLine();
                String parts[] = command.split(" ");
                if(parts.length==4){
                    int index = Integer.parseInt(parts[0]);
                    int xCord = Integer.parseInt(parts[1]);
                    int yCord = Integer.parseInt(parts[2]);
                    String cityName = parts[3];
                    map.putIfAbsent(cityName, index);
                    City city = new City(index,xCord,yCord,cityName);
                    cities[index] = city;
                }
                else if(parts.length==2){
                    int cityOne = Integer.parseInt(parts[0]);
                    int cityTwo = Integer.parseInt(parts[1]);
                    double distance = calculateDistance(cities[cityOne],cities[cityTwo]);
                    Edge myEdge = new Edge(cities[cityOne],cities[cityTwo],distance);
                    this.addEdge(myEdge);
                    Edge myEdge2 = new Edge(cities[cityTwo], cities[cityOne], distance);
                    this.addEdge(myEdge2);

                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void addEdge(Edge e)
    {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }
    public Iterable<Edge> adj(int v)
    { return adj[v]; }

    public static City findCityById(int id, LinkedList<City> cities){
        City returnCity = null;
        for(int i=0; i<cities.size(); i++){
            if(id==cities.get(i).getId()){
                returnCity=cities.get(i);
            }
            break;
        }
        return returnCity;
    }
    public static double calculateDistance(City source, City destination){
        double distance;
        double x = source.getxCoord() - destination.getxCoord();
        double y = source.getyCoord() - destination.getyCoord();
        distance=Math.sqrt((x*x)+(y*y));
        return distance;
    }

   /* public static EdgeWeightedGraph fileToGraph(Scanner sc){
        cities= new LinkedList<City>();
        City city;
        String cityName;
        int x,y,id;
        numOfCities  = sc.nextInt();
        numOfEdges = sc.nextInt();
        graph= new EdgeWeightedGraph(numOfCities);
        for(int i = 0; i<numOfCities; i++) {
            String line[] = sc.nextLine().split(" ");
            id=Integer.parseInt(line[0]);
            x=Integer.parseInt(line[1]);
            y=Integer.parseInt(line[2]);
            cityName=(line[3]);
            city=new City(id,x,y,cityName);
            cities.add(city);
        }
        for(int i=0; i<numOfEdges; i++){
            String[] line = sc.next().split(" ");
            City sourceCity=findCityById(Integer.parseInt(line[0]),cities);

            City destCity=findCityById(Integer.parseInt(line[1]),cities);
            Edge edge= new Edge(sourceCity,destCity,calculateDistance(sourceCity,destCity));
            graph.addEdge(edge);

        }

        return  graph;
    }*/

 public int DijkstraSP(String source, String Destination){
     edgeTo = new Edge[this.V];
     distTo = new double[this.V];
     pq = new IndexMinPQ<Double>(this.V);
     for (int v = 0; v < this.V; v++)
         distTo[v] = Double.POSITIVE_INFINITY;
     distTo[map.get(source)] = 0.0;

     pq.insert(map.get(source), 0.0);
     while (!pq.isEmpty())
     {
         int v = pq.delMin();
         for (Edge e : this.adj(v))
             relax(e);
     }
     printPath(map.get(source),map.get(Destination),0);

     return (int)distTo[map.get(Destination)];
 }
    private void relax(Edge e)
    {
        int v = e.either();
       int  w = e.other(v);
        if (distTo[w] > distTo[v] + e.getWeight())
        {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert (w, distTo[w]);
        }
    }
    public double distTo(int v)
    { return distTo[v]; }
    public Iterable<Edge> pathTo(int v)
    {
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.either()])
            path.push(e);
        return path;
    }
    public void printPath(int source, int dest,int visited){
        if(cities[dest].getCityName().equalsIgnoreCase(cities[source].getCityName())){
            System.out.println(visited+" cities to be visited: ");
            System.out.println(cities[source].getCityName());

            return;

        }
        else{
            visited++;
            printPath(source,edgeTo[dest].either(),visited);
            System.out.println(cities[dest].getCityName());
        }

    }
}