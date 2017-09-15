public class LogicalSentence {
	PropositionConstant prop;
	
	public LogicalSentence(PropositionConstant propositionConstant){
		prop = propositionConstant;
	}
	
	public LogicalSentence(){};
	
	public boolean evaluate(TruthAssignment truth){
		return truth.truthHashMap.get(prop.string);
	}
	
	

	public static void main(String[] args) {
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");
		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);
		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);
		LogicalSentence l5 =  new Conjunction(l3, new Negation(l4));		

		TruthAssignment ta1 = new TruthAssignment();
		
		ta1.truthHashMap.put(b.string,true);
		ta1.truthHashMap.put(a.string, false); 
		System.out.println(l5.evaluate(ta1));
		System.out.println(legal("a&"));
		System.out.println(findMatch("a(b)", 0));

		String[] pc = {"p","q","r"};
		truthTable(pc);
	}
	
	public static boolean legal(String string){
		return Legal.legalSentence(string);
	}
	
	public static boolean findMatch(String string, int number){
		return true;
	}
	
	public static void truthTable(String[] strings){
		String bar = "";
		for(int i = 0; i< strings.length; i++){
			System.out.print("  "+strings[i] + " ");
			bar += "----";
		}
		System.out.println();
		System.out.println(bar+"-");
		
		int rows = (int) Math.pow(2,strings.length);
		
		for (int i=0; i<rows; i++) {
			System.out.print("| ");
			for (int j=strings.length-1; j>=0; j--) {
				int numberOfSames = (int)Math.pow(2, j);
				int thing = (i/numberOfSames)%2;
				System.out.print(thing + " | ");
			}
		            System.out.println();
		}
		System.out.println(bar+"-");
	}
}