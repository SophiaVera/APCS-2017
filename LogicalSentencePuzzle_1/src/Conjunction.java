// this is a random comment
//future me,
//remember to put documentation!
public class Conjunction extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;
	
	public Conjunction(LogicalSentence left, LogicalSentence right){
		this.left = left;
		this.right = right;
	}
	
	public boolean evaluate(TruthAssignment truthAssignment){
		boolean retval = left.evaluate(truthAssignment) == right.evaluate(truthAssignment);
		return retval;
		
	}
}
