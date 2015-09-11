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
                
                Iterator it = wordMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry) it.next();
                    //System.out.println(pairs.getKey() + " = " + pairs.getValue());
                }
                
                ArrayList<HashMap<String,Integer>> sortThis=new ArrayList<HashMap<String,Integer>>();
                //List<String> wordList = new ArrayList<String>(wordMap.keySet());
                //List<Integer> valueList = new ArrayList<Integer>(wordMap.values());
                
                for (String s : wordMap.keySet()) {
                    HashMap<String, Integer> entry = new HashMap<String, Integer>();
                    entry.put(s,wordMap.get(s));
                    sortThis.add(entry);
                }
                mergeSort(sortThis);
                
                PrintWriter outFile = new PrintWriter("WickedWords.html");
                mainObject.writeIt(outFile,wordMap);
                outFile.close();
            } catch (IOException e) {
                System.out.println("There's a file error! " + e);
            }
        }
    
    }
    
    public static void mergeSort(ArrayList<HashMap<String,Integer>> theList)
    {
        HashMap<String, Integer> items = new HashMap<String,Integer>();
        for (HashMap<String,Integer> hm : theList) {
            for (String s : hm.keySet()) {
                
                items.put(s,hm.get(s));
                List<Integer> valueList = new ArrayList<Integer>();
                valueList.add((items.get(s)));
                mergeSortWork(items,0,valueList.size()-1);
            }
        }
        
        
        /*Iterator it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            List<Integer> valueList = new ArrayList<Integer>(items.values());
            mergeSortWork(valueList.size(),0,valueList.size()-1);
        } */
        
        
    }
    
    public static void mergeSortWork(HashMap<String, Integer> theList, int startIndex, int endIndex)
    {
        if (startIndex < endIndex) {
            int listLength = endIndex - startIndex+1;
            int middle = startIndex + listLength/2;
            mergeSortWork(theList, startIndex, middle);
            mergeSortWork(theList, middle+1, endIndex);
            merge(theList, startIndex, middle+1, endIndex);
        }
    }
    
    public static void merge(HashMap<String,Integer> theList, int startA, int startB, int endB) 
    {
       //HashMap<Integer,String> reverseList = new HashMap<Integer,String>();
       HashMap<String,Integer> temporaryList = new HashMap<String,Integer>();
       
       int listLength = endB-startA + 1;
       int index = 0;
       int indexA = startA;
       int indexB = startB;
       
       Iterator it = theList.entrySet().iterator();
       while (it.hasNext()) {
            
        Map.Entry nums = (Map.Entry) it.next();
        List<String> keyList = new ArrayList<String>(theList.keySet());
        List<Integer> valueList = new ArrayList<Integer>(theList.values());

           
           /*for (int i=0; i<keyList.size(); i++) {
                reverseList.put(valueList.get(i),keyList.get(i));
           }*/
           
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
        }
    }
    
    
}
