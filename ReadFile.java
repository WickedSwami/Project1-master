import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

/**
 * Write a description of class ReadFile here.
 * 
 * @author Eric Stuppard 
 * @version September 5th, 2015
 */
public class ReadFile
{
    private HashMap<String,Integer> countWords = new HashMap<String,Integer>();
    
    /**
     * Constructor for objects of class ReadFile
     */
    public void readIt(Scanner infile)
    {
        
        
        while (infile.hasNext())
        {
            int freq = 1;
            String word = infile.next();
            countWords.put(word, freq);
            System.out.println(word);
        }
        
                
    }
    
    /**
    * BONUS: Writing to a file
    * @param outputFile is the file object for output
    * @param someWords is a list of words to print out
    **/
    public void writeIt(PrintWriter outputFile, ArrayList<String> someWords)
    {
        outputFile.println("<html>");
		outputFile.println("<body>");
		
        for (String word: someWords) {
            int freq = countWords.get(word);
            outputFile.println("WORD: " + "<p style='font-size:"+freq*10+"'>"+word+"</p>"););
            
            outputFile.println("FREQUENCY: " + freq);

        }
        
        outputFile.println("</html>");
		outputFile.println("</body>");
    }
 
    public HashMap<String,Integer> getCountWords()
    {
        return countWords;
    }
    
    

    public static void main(String [] args)
    {
        
        if (args.length < 1) {
            System.out.println("You are a bad person. Give a filename to read");
        } else {
            ReadFile mainObject = new ReadFile();
            File fileToRead = new File(args[0]);
            try {
                Scanner in = new Scanner(fileToRead);
                mainObject.readIt(in);
                /* Now demonstrate a PrintWriter for printing a file */
                PrintWriter outFile = new PrintWriter("WickedWords.html");
                HashMap<String,Integer> myWords = mainObject.getCountWords();
                ArrayList<String> wordCloud = new ArrayList<String>();
                
                for (String word : myWords.keySet()) {
                    int freq = myWords.get(word);
                    wordCloud.add(word);
                }
                
                mainObject.writeIt(outFile,wordCloud);
                outFile.close();
            } catch (IOException e) {
                System.out.println("There's a file error! " + e);
            }
        }
    
    }
}
