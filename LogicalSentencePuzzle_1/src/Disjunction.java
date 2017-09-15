/**
 * This class extends LogicalSentence and takes in a 2 LogicalSentences.  It evaluates the disjunction, or the
 * 'or' of both the logical sentences.
 * @author Sophia Vera
 */
public class Disjunction extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;
	
	public Disjunction(LogicalSentence left, LogicalSentence right){
		super();
		this.left = left;
		this.right = right;
	}
	
	@Override
	public boolean evaluate(TruthAssignment truthAssignment){
		boolean retval = left.evaluate(truthAssignment) || right.evaluate(truthAssignment);
		return retval;
		
	}
}
