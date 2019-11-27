/* Emily Evans CISC481 */
import java.util.*;

class Main extends TheoremProver{
	public static void main(String[] args) {

		/* Clause 1 */
		ArrayList<String> p1 = new ArrayList<>();
		Collections.addAll(p1, "aunt", "?x", "?y");

		ArrayList<String> p2 = new ArrayList<>();
		Collections.addAll(p2, "sister", "?x", "?z");

		ArrayList<String> p3 = new ArrayList<>();
		Collections.addAll(p3, "mother", "?z", "?y");

		/* clauses are ArrayLists of predicates */
		ArrayList<ArrayList<String>> clause1 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause1, p1, p2, p3);

		/* Clause 2 */
		ArrayList<String> p4 = new ArrayList<>();
		Collections.addAll(p4, "aunt", "?x", "?y");

		ArrayList<String> p5 = new ArrayList<>();
		Collections.addAll(p5, "sister", "?x", "?z");

		ArrayList<String> p6 = new ArrayList<>();
		Collections.addAll(p6, "father", "?z", "?y");

		ArrayList<ArrayList<String>> clause2 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause2, p4, p5, p6);

		/* Clause 3 */
		ArrayList<String> p7 = new ArrayList<>();
		Collections.addAll(p7, "sister", "Mary", "Sue");
		ArrayList<ArrayList<String>> clause3 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause3, p7);

		/* Clause 4 */
		ArrayList<String> p8 = new ArrayList<>();
		Collections.addAll(p8, "sister", "Mary", "Doug");
		ArrayList<ArrayList<String>> clause4 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause4, p8);

		/* Clause 5 */
		ArrayList<String> p9 = new ArrayList<>();
		Collections.addAll(p9, "father", "Doug", "John");
		ArrayList<ArrayList<String>> clause5 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause5, p9);

		/* Clause 6 */
		ArrayList<String> p10 = new ArrayList<>();
		Collections.addAll(p10, "mother", "Sue", "Paul");
		ArrayList<ArrayList<String>> clause6 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause6, p10);

		/* knowledge base(kb) is an array list of clauses (arrayList) */
		ArrayList<ArrayList<ArrayList<String>>> knowledgeBase = new ArrayList<ArrayList<ArrayList<String>>>();
		Collections.addAll(knowledgeBase, clause1, clause2, clause3, clause4, clause5, clause6);

		ArrayList<ArrayList<String>> goals = new ArrayList<>();
		ArrayList<String> goal = new ArrayList<>();
		Collections.addAll(goal, "aunt", "?x", "?y");
		Collections.addAll(goals, goal);

		HashSet<HashMap<String, String>> bindings = new HashSet<>();

		System.out.println ("Knowledge Base: ");
		printClauses(knowledgeBase);
		System.out.println ("-------------------------------------------");
		System.out.println("Goals:\n" + goals);

        /* Comment & uncomment this section to do either BFS or DFS */
        bindings = DFS(knowledgeBase, goals);
        // bindings = BFS(knowledgeBase, goals);
     
        System.out.println("-------------------------------------------\nResult: ");
        for (HashMap<String, String> binding : bindings) {
            System.out.print("{");
            for (String variable : goals.get(0)) {
                if (variable.startsWith("?")) {
                    System.out.print(variable + "/" + bindVar(binding, variable) + ", ");
                }
            }
            System.out.println("}");
        }
	}
}

