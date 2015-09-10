/**
* Example using the scanner to read in a file word-by-word
* @author Gary Lewandowski
* @version 1 September 2015
**/
import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReadFileExample
{
    private ArrayList<String> words = new ArrayList<String>(); 
    
    /**
     * echo the given file
     * kind of boring
     * @param the scanner for the file
     **/
	public void readIt(Scanner infile)
	{
		while (infile.hasNext())
		{
			String word = infile.next();
			words.add(word);
			System.out.println("WORD: " + word);
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
	    
	    for (String word: someWords) 
		{
		    outputFile.println(word);
		}
		
		outputFile.println("</html>");
		outputFile.println("</body>");
	}

	public static void main(String [] args)
	{
		if (args.length < 1) {
			System.out.println("You are a bad person. Give a filename to read");
		}
		else {
			ReadFileExample mainObject = new ReadFileExample();
			File fileToRead = new File(args[0]);
			try {
				Scanner in = new Scanner(fileToRead);
				mainObject.readIt(in);
				
				/* Now demonstrate a PrintWriter for printing a file */
				PrintWriter outFile = new PrintWriter("words.html");
				
				
				words.add("how");
				words.add("now");
				words.add("brown");
				words.add("cow");
				
				mainObject.writeIt(outFile,words);
				outFile.close();
			} catch(IOException e) {
				System.out.println("Uh oh, file error! "+ e);
			}

		}
	}
}