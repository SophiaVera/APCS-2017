/**
 * This class extends LogicalSentence and takes in a 2 LogicalSentences.  It evaluates the conjunction or 
 * 'and' of both the logical sentences.
 * @author Sophia Vera
 */
public class Conjunction extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;
	
	public Conjunction(LogicalSentence left, LogicalSentence right){
		super();
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Evaluate takes in a truthAssignment to access the Hashmap, and then  returns the value of the conjunction of
	 * the two LogicalSentences, which is the value of p&q.
	 */
	@Override
	public boolean evaluate(TruthAssignment truthAssignment){
		if(left.evaluate(truthAssignment) == right.evaluate(truthAssignment) && left.evaluate(truthAssignment)){
			return true;
		}
		else{
			return false;
		}
		
	}
}
