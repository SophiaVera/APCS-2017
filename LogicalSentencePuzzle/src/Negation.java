/**
 * This class extends LogicalSentence and takes in a logical sentence, sets it truth value to its opposite (negating it).
 * @author Sophia Vera
 */
public class Negation extends LogicalSentence{
	private LogicalSentence ls;

	public Negation(LogicalSentence l1) {
		super();
		this.ls = l1;
	}
	
	/**
	 * Evaluate takes in a truthAssignment to access the Hashmap, and then sets returns the value opposite of
	 * the logical used to create the class, which is !p.
	 */
	@Override
	public boolean evaluate(TruthAssignment truthAssignment){
		return !ls.evaluate(truthAssignment);
	}
	
}
