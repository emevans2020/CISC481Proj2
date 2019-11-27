import java.util.*;

class Test2 extends TheoremProver{
	public static void main(String[] args) {

		/* Clause 1 */
		ArrayList<String> p1 = new ArrayList<>();
		Collections.addAll(p1, "simple_sentence", "?x", "?y", "?z", "?u", "?v");

		ArrayList<String> p2 = new ArrayList<>();
		Collections.addAll(p2, "noun_phrase", "?x", "?y");

		ArrayList<String> p3 = new ArrayList<>();
		Collections.addAll(p3, "verb_phrase", "?z", "?u", "?v");

		/* clauses are ArrayLists of predicates */
		ArrayList<ArrayList<String>> clause1 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause1, p1, p2, p3);

		/* Clause 2 */
		ArrayList<String> p4 = new ArrayList<>();
		Collections.addAll(p4, "noun_phrase", "?x", "?y");

		ArrayList<String> p5 = new ArrayList<>();
		Collections.addAll(p5, "article", "?x");

		ArrayList<String> p6 = new ArrayList<>();
		Collections.addAll(p6, "noun", "?y");

		ArrayList<ArrayList<String>> clause2 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause2, p4, p5, p6);

		/* Clause 3 */
		ArrayList<String> p7 = new ArrayList<>();
		Collections.addAll(p7, "verb_phrase", "?x", "?y", "?z");

		ArrayList<String> p8 = new ArrayList<>();
		Collections.addAll(p8, "verb", "?x");

		ArrayList<String> p9 = new ArrayList<>();
		Collections.addAll(p9, "noun_phrase", "?y", "?z");

		ArrayList<ArrayList<String>> clause3 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause3, p7, p8, p9);

		/* Clause 4 */
		ArrayList<String> p11 = new ArrayList<>();
		Collections.addAll(p11, "article", "a");
		ArrayList<ArrayList<String>> clause4 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause4, p11);

		/* Clause 5 */
		ArrayList<String> p12 = new ArrayList<>();
		Collections.addAll(p12, "article", "the");
		ArrayList<ArrayList<String>> clause5 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause5, p12);

		/* Clause 6 */
		ArrayList<String> p13 = new ArrayList<>();
		Collections.addAll(p13, "noun", "man");
		ArrayList<ArrayList<String>> clause6 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause6, p13);

		/* Clause 7 */
		ArrayList<String> p14 = new ArrayList<>();
		Collections.addAll(p14, "noun", "dog");
		ArrayList<ArrayList<String>> clause7 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause7, p14);

		/* Clause 8 */
		ArrayList<String> p15 = new ArrayList<>();
		Collections.addAll(p15, "verb" , "likes");
		ArrayList<ArrayList<String>> clause8 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause8, p15);

		/* Clause 9 */
		ArrayList<String> p16 = new ArrayList<>();
		Collections.addAll(p16, "verb", "bites");
		ArrayList<ArrayList<String>> clause9 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause9, p16);

		/* knowledge base(kb) is an array list of clauses (arrayList) */
		ArrayList<ArrayList<ArrayList<String>>> knowledgeBase = new ArrayList<ArrayList<ArrayList<String>>>();
		Collections.addAll(knowledgeBase, clause1, clause2, clause3, clause4, clause5, clause6, clause7, clause8, clause9);

		ArrayList<ArrayList<String>> goals = new ArrayList<>();
		ArrayList<String> goal = new ArrayList<>();
		Collections.addAll(goal, "simple_sentence", "?x" ,"?y", "?z", "?u", "?v");
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