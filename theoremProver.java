/* CISC481 Emily Evans */
import java.util.*;
import java.util.ArrayList;

class TheoremProver {
	public void bind (HashMap<String,String> bindings, String s1, String s2) {
		while (bindings.containsKey(s1)){
			s1 = bindings.get(s1);
		}
		while (bindings.containsKey(s2)){
			s2 = bindings.get(s2);
		}
		if (s1 != s2){
			bindings.put(s1,s2);
		}
	}

	public HashMap Unify (ArrayList<String> p1, ArrayList<String> p2, HashMap<String,String> bindings){
		if (p1.equals(p2)){
			return bindings;
		}
		if (p1.get(0)==p2.get(0) && (p1.size()==p2.size())){
			for (int i = 1; i < p2.size(); i++){
				if (p1.get(i)==p2.get(i)){
					continue;
				}
				if (p1.get(i)!=p2.get(i)){ /* check if first character of string in pred is variable or not */
					if ((p1.get(i)).charAt(0) == '?'){
						bind(bindings,p1.get(i), p2.get(i));
					} else if (p2.get(i).charAt(0) == '?') {
						bind(bindings,p2.get(i), p1.get(i));
					} else {
						return null;
					} 
				}
			}
			return bindings;
		}
		else {
			return null;
		}
	}
 }
	

/* Horn Clause is a list of ArrayLists where first ArrayList is assumed non-negated
and rest of the ArrayLists will be assumed negated */


class Main {
	public static void main (String args[]){
		/* Predicates */
		ArrayList <String> gp = new ArrayList <String>();
		Collections.addAll(gp, "grandparent","?x","?y");
		ArrayList <String> parent1 = new ArrayList <String> ();
		Collections.addAll(parent1, "parent","?x","?z");
		ArrayList <String> parent2 = new ArrayList <String> ();
		Collections.addAll (parent2, "parent", "?z", "?y");
		
		ArrayList <String> p1 = new ArrayList <String>();
		Collections.addAll(p1, "parent","Brian","Doug");
		ArrayList <String> p2 = new ArrayList <String>();
		Collections.addAll(p2, "parent","Doug","Mary");
		ArrayList <String> p3 = new ArrayList <String>();
		Collections.addAll(p3, "parent","Mary","Sue");

		/* Clauses arraylist of predicates */
		ArrayList <ArrayList> hc1 = new ArrayList<ArrayList>();
		Collections.addAll(hc1, gp,parent1, parent2);

		ArrayList <ArrayList> hc2 = new ArrayList <ArrayList>();
		hc2.add(p1);

		ArrayList <ArrayList> hc3 = new ArrayList <ArrayList>();
		hc3.add(p2);

		ArrayList <ArrayList> hc4 = new ArrayList <ArrayList>();
		hc4.add(p3);

		/* knowledge base(kb) is an array list of clauses (arrayList) */
		ArrayList <ArrayList> kb = new ArrayList <ArrayList>();
		Collections.addAll(kb,hc1,hc2,hc3,hc4);
	}
}

/* open list changes every time you expand a node in general
breadth first search layer by layer - first in first out queue

https://www.geeksforgeeks.org/queue-interface-java/

depth first - go all the way down uses stack for list! 
https://www.geeksforgeeks.org/stack-class-in-java/

*/
/* pop or dequeue depending on which search 
start with the negated goal clause in the open list
1. take this off open list
2. check if can unify (use helper unify function)
3. you get the bindings out of unify (DONT MISMATCH BINDINGS)
4. open list = list of lists each list has clause and list of bindings clauses and bindings have 
to match so could two separates but the elements need to match up!
5. if it does unify to empty clause--> success!!
6. no unify = failure! 
I can assume they"re all just going to be left negative I don"t need to go putting in negatives in front 
of my clauses */
