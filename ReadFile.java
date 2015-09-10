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
            
            
            /*ArrayList<String> wordBank = new ArrayList<String>();
            wordBank.add(word);
            countWords.put(word,1);

            for (String w : wordBank) {
                if (countWords.containsKey(w)) {
                    int freq = 1;
                    countWords.put(w,new Integer(freq));
                } else {
                    int consecutiveOcc = countWords.get(w);
                    consecutiveOcc++;
                    countWords.put(w,new Integer(consecutiveOcc));
                }
            }  */
            
            //int[] valueList = new int[countWords.size()];
            //mergeSort(valueList);
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
        
        
        /*for (String word: someWords.keySet()) {
            
            int freq = someWords.get(word);
            outputFile.println("<p style='font-size:"+freq*10+"'>"+word+"</p>");
        }*/
        outputFile.println(someWords);
        
        outputFile.println("</html>");
        outputFile.println("</body>");
    }
    
    
    public HashMap<String, Integer> getCountWords()
    {
        return countWords;
    }
 
    public ArrayList<String> getMyWords()
    {
        /*ArrayList<String> allWords = new ArrayList<String>();
        
        for (String word : countWords.keySet()) {
            allWords.add(word);
        }
        
        return allWords;
        */
       
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
        
        
        
        /*for (String word : words) {
            if (myWords.contains(word)) {
                //Integer value = 1;
                //countWords.put(word, new Integer(value));
                
                Integer consecutiveOcc = 1;
                consecutiveOcc ++;
                countWords.put(word, new Integer(consecutiveOcc));
            } else {
                Integer value = 1;
                countWords.put(word, new Integer(value));
                
                //Integer consecutiveOcc = countWords.get(word);
                //consecutiveOcc ++;
                //countWords.put(word, new Integer(consecutiveOcc));
            }
            
        } */ 
        
        
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
                HashMap<String,Integer> wordMap = mainObject.getCountWords();
                mainObject.countWordOccurences(wordCloud);

                PrintWriter outFile = new PrintWriter("WickedWords.html");
                mainObject.writeIt(outFile,wordMap);
                outFile.close();
            } catch (IOException e) {
                System.out.println("There's a file error! " + e);
            }
        }
    
    }
    
    /*public static void mergeSort(int[] theList)
    {
        mergeSortWork(theList,0,theList.length-1);
    }
    
    public static void mergeSortWork(int [] theList, int startIndex, int endIndex)
    {
    	if (startIndex < endIndex) {
    		int listLength = endIndex - startIndex+1;
    		int middle = startIndex + listLength/2;
    		mergeSortWork(theList, startIndex, middle);
    		mergeSortWork(theList, middle+1, endIndex);
    		merge(theList, startIndex, middle+1, endIndex);
    	}
    }
    
    public static void merge(int [] theList, int startA, int startB, int endB) 
    {
    	int listLength = endB-startA + 1;
    	int[] temporaryList = new int[listLength];
    	int index = 0;
    	int indexA = startA;
    	int indexB = startB;
    	while (indexA < startB && indexB <= endB) {
    		if (theList[indexA] < theList[indexB]) {
    			temporaryList[index] = theList[indexA];
    			indexA++;
    		} else {
    			temporaryList[index] = theList[indexB];
    			indexB++;
    		}
    		
    		index++;
    	}
    	
    	for (;indexA<startB;indexA++,index++) {
    		temporaryList[index] = theList[indexA];
    	}
    	
    	for (;indexB<=startB;indexB++,index++) {
    		temporaryList[index] = theList[indexB];
    	}
    	
    	for (int i = 0; i < index; i++) {
    		theList[startA+i]=temporaryList[i];
    	}
    }*/
    
    
}
