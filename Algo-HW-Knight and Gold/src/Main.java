//-----------------------------------------------------

// Title: Runner

// Author: Muhammed İbrahim ÜNAL


// Section: 1

// Assignment: 1

// Description: Taking input text file from user and creating 2d array

//-----------------------------------------------------
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static int n,m;
    static char board[][];
    static File file;
    static Graph graph;

    public static void main(String args []){
        //User should enter name of input file..
        System.out.println("Please enter the name of the input file...");
        String input;
        Scanner sc = new Scanner(System.in);
        input=sc.next();
        file=new File(input);
        createGraphFromFile(file);
        graph.findPath(graph,board,m,n);
        graph.shortestPath(graph,graph.Knight);
    }
    //function takes file and creates a 2d array which holds . T G and K...
    public static Graph createGraphFromFile(File file){
        try {
            Scanner sc = new Scanner(file);
             m  = sc.nextInt();
             n = sc.nextInt();
            graph = new Graph(m*n);
            board = new char[m][n];
            for(int i = 0; i<m; i++) {
                String line = sc.next();
                for(int k = 0; k<n; k++) {
                    board[i][k] = line.charAt(k);
                }
            }
            return graph;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block​
            e.printStackTrace();
        }
        return null;
    }



}




