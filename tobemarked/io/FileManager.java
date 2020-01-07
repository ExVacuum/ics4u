package tobemarked.io;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileManager {

    //Buffered Reader for User Input
    BufferedReader in = new BufferedReader( new InputStreamReader(System.in));

    public static void main(String[] args) {
        new FileManager();
    }

    public FileManager(){
        while (true) {

            //Catch any IOExceptions Thrown by These Methods
            try {

                //List Modes
                System.out.println("SELECT FUNCTION:\n-Writer\n-Reader\n-URLScan\n-exit");

                //Select Mode
                switch (in.readLine().toLowerCase()) {
                    case "writer":
                        writerProg();
                        break;
                    case "reader":
                        readerProg();
                        break;
                    case "urlscan":
                        urlProg();
                        break;
                    case "exit":
                        return;
                    default:

                        //Mode not found
                        System.out.println("Function not available.");
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //File Writer
    void writerProg() throws IOException {

        //Get File Name
        System.out.println("<FILE WRITER> ENTER FILENAME (WILL BE LOCATED UNDER res/):");
        String fname = in.readLine();

        //Get File
        File f = new File("res/"+fname);
        try {

            //Create File Writer
            PrintWriter prout = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));

            //Get Content From User
            System.out.println("<FILE WRITER> TYPE LINES TO BE ADDED TO THE FILE (~exit TO CLOSE)");
            while (true) {
                String s = in.readLine();
                if (!s.equalsIgnoreCase("~exit")) {
                    prout.print(s);

                    //Add Newline After Each Input Line
                    prout.printf("%n");
                    continue;
                }
                break;
            }

            //Close Writer
            prout.close();

        }catch (FileNotFoundException e){

            //Get Player to Retry if Filename is Invalid
            System.out.println("<FILE WRITER> INVALID FILENAME");
            writerProg();
        }
    }

    //File Reader
    void readerProg() throws IOException {

        //Get Filename
        System.out.println("<FILE READER> ENTER FILENAME");
        String fname = in.readLine();

        //Get File
        File f = new File(fname);
        try {

            //File Reader
            BufferedReader brFile = new BufferedReader(new FileReader(f));

            //Get Line
            String s = brFile.readLine();
            System.out.println("<FILE READER> "+fname);

            //Print all Lines Until End of File
            while (s!=null){
                System.out.println(s);

                //Next Line
                s = brFile.readLine();
            }

            //Close Reader
            brFile.close();

        }catch (FileNotFoundException e){

            //Get User to Retry if File is not Found
            System.out.println("<FILE READER> Unable to locate file: \""+fname+"\"");
            readerProg();
        }
    }

    //URL Scanner
    void urlProg() throws IOException {

        //Get URL
        System.out.println("Enter a webpage URL to scan:");
        URL url;
        try {
            url = new URL(in.readLine());
        }catch (MalformedURLException e){

            //Get User to Retry if URL is Invalid
            System.out.println("Invalid URL.");
            urlProg();
            return;
        }

        //Get Target String
        System.out.println("Enter a string to search for.");
        String wanted = in.readLine();

        //Open Connection and Reader
        URLConnection connection = url.openConnection();
        BufferedReader brWeb = new BufferedReader( new InputStreamReader(connection.getInputStream()));

        //Read Line
        String s = brWeb.readLine();

        //Count Matches
        int count = 0;

        //Keep Reading Lines Until End of Page
        while(s!=null){

            //If a Match is Found, Count it.
            if(s.contains(wanted)){
                count++;
            }

            //Next Line
            s = brWeb.readLine();
        }

        //Print # of Matches
        System.out.printf("%d matches found.\n", count);
    }
}
