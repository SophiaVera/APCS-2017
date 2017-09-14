
public class Negation extends LogicalSentence{

	public Negation(LogicalSentence l1) {
		super();
		string ="~"+ l1.string;
	}

	public boolean evaluate(TruthAssignment truthAssignment){
		return truthAssignment.get();;
	}
	

}
