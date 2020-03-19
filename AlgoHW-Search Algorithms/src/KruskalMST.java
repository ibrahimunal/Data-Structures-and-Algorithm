import MinPQ.MinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KruskalMST
{
    private Queue<Edge> mst = new LinkedList<>();
    public KruskalMST(EdgeWeightedGraph G)
    {
        MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V()-1)
        {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w))
            {
                uf.union(v, w);
                mst.add(e);
            }
        }
    }
    public Iterable<Edge> edges()
    { return mst; }
}
