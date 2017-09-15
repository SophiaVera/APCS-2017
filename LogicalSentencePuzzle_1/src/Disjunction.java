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
