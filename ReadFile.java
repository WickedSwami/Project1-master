import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//import java.util.Set;
//import java.util.ArrayList;

/**
 * Write a description of class ReadFile here.
 * 
 * @author Eric Stuppard 
 * @version September 5th, 2015
 */
public class ReadFile
{
    private HashMap<String,Integer> countWords = new HashMap<String,Integer>();
    private ArrayList<String> myWords = new ArrayList<String>();
    
    /**
     * Constructor for objects of class ReadFile
     */
    public void readIt(Scanner infile)
    {
        
        while (infile.hasNext())
        {
            String word = infile.next();
            myWords.add(word);
            System.out.println(word);
        }
        
    }
    
    /**
    * BONUS: Writing to a file
    * @param outputFile is the file object for output
    * @param someWords is a list of words to print out
    **/
    public void writeIt(PrintWriter outputFile, HashMap<String,Integer> someWords)
    {
        outputFile.println("<html>");
        outputFile.println("<body>");
        
        
        for (String word: someWords.keySet()) {
            
            int freq = someWords.get(word);
            outputFile.println("<p style='font-size:"+freq*10+"'>"+word+"</p>");
        }
        
        
        outputFile.println("</html>");
        outputFile.println("</body>");
    }
    
    public int getOneWordCount(String word)
    {
        if (countWords.containsKey(word)) {
            return countWords.get(word);
        }
        
        return 0;
    }
    
    public HashMap<String, Integer> getCountWords()
    {
        return countWords;
    }
 
    public ArrayList<String> getMyWords()
    {
        return myWords;
    }
    
    public HashMap<String, Integer> countWordOccurences(ArrayList<String> words) {
        int i=1;
        
        for (String word : words) {
            if (!countWords.containsKey(word)) {
                countWords.put(word, 1);
            } else {
                countWords.put(word,countWords.get(word)+1);
            }
        }
        
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
                
                ArrayList<String> wordCloud = mainObject.getMyWords();
                mainObject.countWordOccurences(wordCloud);
                HashMap<String,Integer> wordMap = mainObject.getCountWords();
                
                ArrayList<String> sortCloud = new ArrayList<String>(wordMap.keySet());
                ArrayList<Integer> valueCloud = new ArrayList<Integer>(wordMap.values());
                mergeSort(sortCloud,valueCloud);
                
                PrintWriter outFile = new PrintWriter("WickedWords.html");
                mainObject.writeIt(outFile,wordMap);
                outFile.close();
            } catch (IOException e) {
                System.out.println("There's a file error! " + e);
            }
        }
    
    }
    
    public static void mergeSort(ArrayList<String> theList, ArrayList<Integer> theValues)
    {
        mergeSortWork(theList,theValues,0,theValues.size()-1);
    }
    
    public static void mergeSortWork(ArrayList<String> theList, ArrayList<Integer> theValues, int startIndex, int endIndex)
    {
        if (startIndex < endIndex) {
            int listLength = endIndex - startIndex+1;
            int middle = startIndex + listLength/2;
            mergeSortWork(theList, theValues, startIndex, middle);
            mergeSortWork(theList, theValues, middle+1, endIndex);
            merge(theList, theValues, startIndex, middle+1, endIndex);
        }
    }
    
    public static void merge(ArrayList<String> theList, ArrayList<Integer> theValues, int startA, int startB, int endB) 
    {
       
       ArrayList<String> tempArray = new ArrayList<String>();
       
       int listLength = endB-startA + 1;
       int index = 0;
       int indexA = startA;
       int indexB = startB;
       
       
       while (indexA < startB && indexB <= endB) {
           if (theValues.get(indexA) < theValues.get(indexB)) {
               
               tempArray.set(index, theList.get(indexA));
               indexA++;
           } else {
               tempArray.set(index, theList.get(indexB));
               indexB++;
           }
           index++;
       }
                
       for (String s : tempArray) {
           for (;indexA<startB;indexA++,index++) {
               tempArray.set(index, theList.get(indexA));
               
           }
           
           for (;indexB<startB;indexB++,index++) {
               tempArray.set(index, theList.get(indexB));
               
           }
           
           for (int i=0; i<index; i++) {
               theList.set(startA+1, tempArray.get(i));
               
           }
        }
        
    }
    
}
