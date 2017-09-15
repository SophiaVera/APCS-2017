/**
 * This class extends LogicalSentence and takes in a 2 LogicalSentence.  It evaluates the conjunction or 
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
	 * Evaluate takes in a truthAssignment to access the Hashmap, and then sets returns the value the conjunction of
	 * the objects two LogicalSentences which is the value of p&q.
	 */
	@Override
	public boolean evaluate(TruthAssignment truthAssignment){
		boolean retval = left.evaluate(truthAssignment) == right.evaluate(truthAssignment);
		return retval;
		
	}
}
