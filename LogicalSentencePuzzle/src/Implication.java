/**
 * This class extends LogicalSentence and takes in a 2 LogicalSentences.  It evaluates the implication or 
 * 'p implies q' of both the logical sentences.
 * @author Sophia Vera 
 */
public class Implication extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;
	
	public Implication(LogicalSentence left, LogicalSentence right){
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
		if (left.evaluate(truthAssignment) && !right.evaluate(truthAssignment)){
			return false;
		}
		else{
			return true;
		}
	}
}
