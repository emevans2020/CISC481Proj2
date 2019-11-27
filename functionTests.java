import java.util.*;

public class functionTests extends TheoremProver {
	public static void main(String args[]) {
		/* These test that my helper functions work based off of the packet */
		HashMap<String, String> bindings = new HashMap<>();
		bindings.put("?x", "?y");
		bindings.put("?y", "?z");
		bindings.put("?w", "?q");
		bindings.put("?z", "?w");
		System.out.println(bindVar(bindings, "?x"));

		/* Tests whether my unify function works */
		ArrayList<String> pred1 = new ArrayList<String>();
		ArrayList<String> pred2 = new ArrayList<String>();
		Collections.addAll(pred1, "p", "?x", "y");
		Collections.addAll(pred2, "p", "?z", "?w");
		HashMap<String, String> bindings2 = new HashMap<String, String>();
		bindings.put("?x", "?w");
		System.out.println(Unify(pred1, pred2, bindings2));

		/* Tests if my uniquify function works based off of the provided example */
		ArrayList<ArrayList<String>> clause = new ArrayList<>();
		ArrayList<String> pred7 = new ArrayList<String>();
		Collections.addAll(pred7, "p", "?x", "?y");
		ArrayList<String> pred8 = new ArrayList<String>();
		Collections.addAll(pred8, "a", "?x", "?z");
		ArrayList<String> pred9 = new ArrayList<String>();
		Collections.addAll(pred9, "a", "?z", "?y");
		Collections.addAll(clause, pred7, pred8, pred9);

		counter.put("?x", 3);
		counter.put("?y", 6);
		counter.put("?z", 2);
		System.out.println(uniquify(clause));
	}
}