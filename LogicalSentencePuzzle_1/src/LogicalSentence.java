/**
 * LogicalSentence is the most important class in the LogicalSentencePuzzle project.  It contains main(), which creates
 * the many different logical operators, and is a class that is extended by the majority of the other classes.  It also creates
 * the truth tables and has the findmatch() method.
 * @author Sophia Vera
 */
public class LogicalSentence {
	PropositionConstant prop;
	
	public LogicalSentence(PropositionConstant propositionConstant){
		prop = propositionConstant;
	}
	
	public LogicalSentence(){};
	
	/**
	 * This method gets the value stored at the index of the string of the PropositionConstant prop.
	 * It is often overwritten by other classes.
	 * @param truthAssignment - the hashmap
	 * @return the stored boolean value corresponding with the string
	 */
	public boolean evaluate(TruthAssignment truthAssignment){
		return truthAssignment.truthHashMap.get(prop.string);
	}
	
	public static void main(String[] args) {
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");
		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);
		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);
		LogicalSentence l5 =  new Conjunction(l3, new Negation(l4));		

		TruthAssignment ta1 = new TruthAssignment();
		
		ta1.truthHashMap.put(b.string,true);
		ta1.truthHashMap.put(a.string, false); 
		System.out.println(l5.evaluate(ta1));
		System.out.println(legal("a&"));
		System.out.println(findMatch("a(b)", 0));

		String[] pc = {"p"};
		truthTable(pc);
	}
	
	/**
	 * This function is not really useful; it would be better practise to plug Legal.legalSentence() directly
	 * into main rather than through legal().  However, in the example, legal() showed up inside of the LogicalSentence
	 * class so I chose to create a middleman to preserve the syntax.
	 * @param string - the logical sentence
	 * @return the legality of the logic sentene
	 */
	public static boolean legal(String string){
		return Legal.legalSentence(string);
	}
	
	/**
	 * 
	 * @param string - the logical expression being searched in
	 * @param number - unused int for recursiveness
	 * @return  the index of the rightmost character i enclosed by matching parens and which contains no parens.
	 */
	public static int findMatch(String string, int number){
		boolean openParens = false; //whether index is currently in parentheses
		for (int i = 0; i < string.length(); i++) {
			char letter = string.charAt(i);
			if (letter == '(') {
				openParens = true;
			} 
			else if (letter == ')') {
                if (openParens) {
                    return i-1;
                }
                //There shouldn't be a case where theres a closing parentheses without an open one
                return -1; 
            }
        }
		// the string is wrong
        return -1; 
	}
	
	/**
	 * This function takes an array of Strings and prints out the truth table that corresponding truth table.  It is 
	 * important to note that the farthest right column of the table is missing.  This is because no operator was 
	 * specified.
	 * @param strings
	 */
	public static void truthTable(String[] strings){
		String bar = "";
		//This prints out the origal row of table
		for(int i = 0; i< strings.length; i++){
			System.out.print("  "+strings[i] + " ");
			bar += "----";
		}
		//This prints out the bar
		System.out.println();
		System.out.println(bar+"-");
		
		//The height of the table can be found by 2^column number
		int rows = (int) Math.pow(2,strings.length);
		
		for (int i=0; i<rows; i++) {
			System.out.print("| ");
			for (int j=strings.length-1; j>=0; j--) {
				//Each column has alternating amount of 0s and 1s, from 2^0 on the right, then increasing by 1 power
				int numberOfSames = (int)Math.pow(2, j);
				//This sees whether the index is a block of 0s or 1s
				int evenOrOddRow = (i/numberOfSames)%2;
				System.out.print(evenOrOddRow + " | ");
			}
		            System.out.println();
		}
		System.out.println(bar+"-");
	}
}