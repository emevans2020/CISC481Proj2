import java.util.*;

class Main {
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
		System.out.println(clause1);

		/* Clause 2 */
		ArrayList<String> p4 = new ArrayList<>();
		Collections.addAll(p4, "aunt", "?x", "?y");

		ArrayList<String> p5 = new ArrayList<>();
		Collections.addAll(p5, "sister", "?x", "?z");

		ArrayList<String> p6 = new ArrayList<>();
		Collections.addAll(p6, "father", "?z", "?y");

		ArrayList<ArrayList<String>> clause2 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause2, p4, p5, p6);
		System.out.println(clause2);

		/* Clause 3 */
		ArrayList<String> p7 = new ArrayList<>();
		Collections.addAll(p7, "sister", "Mary", "Sue");
		ArrayList<ArrayList<String>> clause3 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause3, p7);
		System.out.println(clause3);

		/* Clause 4 */
		ArrayList<String> p8 = new ArrayList<>();
		Collections.addAll(p8, "sister", "Mary", "Doug");
		ArrayList<ArrayList<String>> clause4 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause4, p8);
		System.out.println(clause4);

		/* Clause 5 */
		ArrayList<String> p9 = new ArrayList<>();
		Collections.addAll(p9, "father", "Doug", "John");
		ArrayList<ArrayList<String>> clause5 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause5, p9);
		System.out.println(clause5);

		/* Clause 6 */
		ArrayList<String> p10 = new ArrayList<>();
		Collections.addAll(p10, "mother", "Sue", "Paul");
		ArrayList<ArrayList<String>> clause6 = new ArrayList<ArrayList<String>>();
		Collections.addAll(clause6, p10);
		System.out.println(clause6);

		/* knowledge base(kb) is an array list of clauses (arrayList) */
		ArrayList<ArrayList<ArrayList<String>>> knowledgeBase = new ArrayList<ArrayList<ArrayList<String>>>();
		Collections.addAll(knowledgeBase, clause1, clause2, clause3, clause4, clause5, clause6);

		ArrayList<ArrayList<String>> goals = new ArrayList<>();
		ArrayList<String> goal = new ArrayList<>();
		Collections.addAll(goal, "aunt", "?x", "?y");
		Collections.addAll(goals, goal);
		System.out.println(goals);

		HashSet<HashMap<String, String>> bindings = new HashSet<>();

	}
}

/*
 * open list changes every time you expand a node in general breadth first
 * search layer by layer - first in first out queue
 * 
 * https://www.geeksforgeeks.org/queue-interface-java/
 * 
 * depth first - go all the way down uses stack for list!
 * https://www.geeksforgeeks.org/stack-class-in-java/
 * 
 */
/*
 * pop or dequeue depending on which search start with the negated goal clause
 * in the open list 1. take this off open list 2. check if can unify (use helper
 * unify function) 3. you get the bindings out of unify (DONT MISMATCH BINDINGS)
 * 4. open list = list of lists each list has clause and list of bindings
 * clauses and bindings have to match so could two separates but the elements
 * need to match up! 5. if it does unify to empty clause--> success!! 6. no
 * unify = failure! I can assume
 * they"re all just going to be left negative I don"t need to go putting in
 * negatives in front of my clauses
 */

/*
 * Horn Clause is a list of ArrayLists where first ArrayList is assumed
 * non-negated and rest of the ArrayLists will be assumed negated
 */
