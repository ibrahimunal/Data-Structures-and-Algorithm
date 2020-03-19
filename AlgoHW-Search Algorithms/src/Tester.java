import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.time.*;
public class Tester {
    public static void main(String args[]){
    String path=("C:\\Users\\ibrah_000\\IdeaProjects\\HW3-Part1\\medium.txt");
    EdgeWeightedGraph graph=new EdgeWeightedGraph(path);
    timeCalculator(graph);


    }

    public static void timeCalculator(EdgeWeightedGraph graph){

        Instant before = Instant.now();
        KruskalMST kMST= new KruskalMST(graph);
        Instant after = Instant.now();
        long delta = Duration.between(before, after).toMillis();
        System.out.println("Time for Kruskal "+delta+" ms");
        Instant before2 = Instant.now();
        LazyPrimMST lMST= new LazyPrimMST(graph);
        Instant after2 = Instant.now();
        long delta2 = Duration.between(before2, after2).toMillis();
        System.out.println("Time for Lazy Prim "+delta2+" ms");
        Instant before3 = Instant.now();
        PrimMST pMST= new PrimMST(graph);
        Instant after3 = Instant.now();
        long delta3 = Duration.between(before3, after3).toMillis();
        System.out.println("Time for Eager Prim "+delta3+" ms");
    }
}
