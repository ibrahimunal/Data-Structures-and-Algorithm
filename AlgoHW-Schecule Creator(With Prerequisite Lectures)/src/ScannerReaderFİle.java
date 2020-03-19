import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ScannerReaderFile {
    public static void main(String[] args) {
        // Create an instance of File for data.txt file.
        System.out.println("Enter file name");

        Scanner sc= new Scanner(System.in);
        String filename=sc.next();

        File file = new File(filename);
        try {
            // Create a new Scanner object which will read the data
            // from the file passed in. To check if there are more 
            // line to read from it we check by calling the 
            // scanner.hasNextLine() method. We then read line one 
            // by one till all lines is read.
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}