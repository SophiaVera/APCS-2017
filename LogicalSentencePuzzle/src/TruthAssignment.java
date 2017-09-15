import java.util.HashMap;

/**
 * It should be fairly obvious that this class doesn't really need to exist: it litteraly contains only a single
 * hashmap.  However, it exists in order to fit into the requirements of the project.
 * @author Sophia Vera
 */
public class TruthAssignment{
	HashMap<String, Boolean> truthHashMap;
	
	public TruthAssignment(){
		 truthHashMap = new HashMap<String,Boolean>();
	}
}
