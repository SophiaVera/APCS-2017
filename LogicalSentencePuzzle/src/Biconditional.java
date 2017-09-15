/**
 * This class extends LogicalSentence and takes in a 2 LogicalSentences.  It evaluates the biconditional or 
 * 'if and only if' of both the logical sentences.
 * @author Sophia Vera 
 */
public class Biconditional extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;
	
	public Biconditional(LogicalSentence left, LogicalSentence right){
		super();
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Evaluate takes in a truthAssignment to access the Hashmap, and then  returns the value of the biconditional of
	 * the two LogicalSentences.
	 */
	@Override
	public boolean evaluate(TruthAssignment truthAssignment){
		boolean retval = left.evaluate(truthAssignment) == right.evaluate(truthAssignment);
		return retval;
		
	}
}


