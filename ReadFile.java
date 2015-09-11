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
        
        
        for (String word: someWords.keySet()) {
            
            int freq = someWords.get(word);
            outputFile.println("<p style='font-size:"+freq*10+"'>"+word+"</p>");
        }
        //outputFile.println(someWords);
        
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
                
                /*Iterator it = wordMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                }
                
                */
                
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
        
        /*HashMap<String, Integer> items = new HashMap<String,Integer>();
        for (HashMap<String,Integer> hm : theList) {
            for (String s : hm.keySet()) {
                
                items.put(s,hm.get(s));
                List<Integer> valueList = new ArrayList<Integer>();
                valueList.add((items.get(s)));
                mergeSortWork(items,0,valueList.size()-1);
            }
        }
        
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            List<Integer> valueList = new ArrayList<Integer>(items.values());
            mergeSortWork(valueList.size(),0,valueList.size()-1);
        } */
        
        
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
       
       //HashMap<String,Integer> temporaryMap = new HashMap<String, Integer>();
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
               //tempArray.get(index) = theList.get(indexA);
               
           }
           
           for (;indexB<startB;indexB++,index++) {
               tempArray.set(index, theList.get(indexB));
               //tempArray.get(index) = theList.get(indexB);
               
           }
           
           for (int i=0; i<index; i++) {
               theList.set(startA+1, tempArray.get(i));
               //theList.get(startA+i) = tempArray.get(i);
           }
        }
            
       
       /*Iterator it = theList.entrySet().iterator();
       while (it.hasNext()) {
            
        Map.Entry nums = (Map.Entry) it.next();
        List<String> keyList = new ArrayList<String>(theList.keySet());
        List<Integer> valueList = new ArrayList<Integer>(theList.values());

           
           
           
           for (String w : keyList) {
               while (indexA < startB && indexB <= endB) {
                   if (valueList.get(indexA) < valueList.get(indexB)) {
                       temporaryList.put(w, indexA);
                       indexA++;
                   } else {
                       temporaryList.put(w, indexB);
                       indexB++;
                   }
                   index++;
                }
                
               for (;indexA<startB;indexA++,index++) {
                   temporaryList.put(w, indexA);
                   
               }
               for (;indexB<startB;indexB++,index++) {
                   temporaryList.put(w, indexB);
                   
               }
               for (int i=0; i<index; i++) {
                   theList.put(keyList.get(startA+i), i);
               }
            }
        }*/
    }
    
    
}
