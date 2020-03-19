public class Edge implements Comparable<Edge>
{
    private final int v, w;
    private final double weight;
    public Edge(City v, City w, double weight)
    {
        this.v=v.getId();
        this.w =w.getId();
        this.weight = weight;
    }
    public double getWeight(){
        return this.weight;
    }
    public int either()
    { return v; }
    public int other(int vertex)
    {
        if (vertex == v) return w;
        else return v;
    }

    public int compareTo(Edge that)
    {
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else return 0;
    }
}