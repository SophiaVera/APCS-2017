/**
 * This entire class is from a previous APCS project.  Its purpose is to check to see if a logical Sentence is considered 
 * legal.  Since this old project was virtually the same as the legal() in LogicalSentencePuzzle, I decided to copy it.
 * @author Sophia Vera
 */
public class Legal {
	/**
	 * legalSentence() takes in a string, and returns whether the string is considered
	 * a legal logical sentence.  A legal sentence is defined as being a:
	 * 	-Simple sentence, input p output q
	 * 	-Begins with a ~ Followed by a logical sentence
	 * 	-Begins with a logical sentence, followed by a logical operator,followed by a logical sentence
	 * 
	 * @param sentence
	 * @return isLegal
	 */
	public static boolean legalSentence(String sentence){
		//Removes all spaces from the string
		sentence = sentence.replaceAll(" ","");
		
		//If nothing is being tested, the legality is false
		if(sentence.length() == 0){
			return false;
		}
		
		//If sentence has uneven parentheses
		if(parenthesesCheck(sentence)==false){
			return false;
		}
		else{
			sentence = sentence.replaceAll( "\\(" , "");
			sentence = sentence.replaceAll( "\\)", "");
		}
		
		//If it begins with a ~, run legalSentence as if the ~ wasn't there
		if(startsWithNot(sentence)){
			return legalSentence(cutNot(sentence));
		}
		
		//If the sentence being tested is just p or q, it is legal
		if(simpleSentence(sentence)){
			return true;
		}
		
		//Create ints for the location of the start and stop of the operator.
		int opLeft = findFirstOperator(sentence);
		int opRight = findEndOfOperator(sentence);
		
		//If the operator has a length of one, set the end of the op to the start of the op
		if(opRight<0){
			opRight = opLeft;
		}
		
		//If the operator has a longer length,check that it equals the proper operator syntax
		//One can't just check the last index, or else the = in the biconditional won't ever be checked.
		else if(sentence.substring(opLeft, opRight+1).equals("=>")==false && sentence.substring(opLeft, opRight+1).equals("<=>")==false){
			return false;
		}
		
		//Checks that what's in front of and behind the operator are legal
		if(legalSentence(sentence.substring(0, opLeft)) && legalSentence(sentence.substring(opRight + 1))){
			return true;
		}
		//If not, there not legal
		else{
			return false;
		}
	}
	
	/**Checks if the sentence is either p or q
	 */
	public static boolean simpleSentence(String sentence){
		if(sentence.equals("p")||sentence.equals("q")){
			return true;
		}
		else{ 
			return false;
		}
	}
	
	/**Sees if the sentence starts with a ~
	 */
	public static boolean startsWithNot(String sentence){
		if(sentence.indexOf("~")==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**Removes the first index of a string(to be used when the first index is ~)
	 */
	public static String cutNot(String sentence){
		return sentence.substring(1);
	}
	
	/**Finds index of the first sign in the operator
	 */
	public static int findFirstOperator(String sentence){
		if(sentence.indexOf("|")>0){
			return sentence.indexOf("|");
		}
		else if(sentence.indexOf("&")>0){
			return sentence.indexOf("&");			
		}
		else if(sentence.indexOf("<")>0){
			return sentence.indexOf("<");	
		}
		else if(sentence.indexOf("=")>0){
			return sentence.indexOf("=");	
		}
		else{
			return 0;
		}
	}
	
	/**Finds last index of the operator
	 * Only checks for the > because all multi-index operators end with >
	 */
	public static int findEndOfOperator(String sentence){
		return sentence.indexOf(">");	
	}
	
	public static boolean parenthesesCheck(String sentence){
		int leftParens = 0;
		int rightParens = 0;
		for(int i = 0; i < sentence.length(); i++){
			if(sentence.substring(i, i+1) == "("){
				leftParens += 1;
			}
			else if (sentence.substring(i, i+1) == ")"){
				leftParens += 1;
			}
		}
		if (leftParens != rightParens){
			return false;
		}
		return true;
	}
}
