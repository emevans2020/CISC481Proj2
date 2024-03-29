/* CISC481 Emily Evans */
import java.util.*;

public class TheoremProver {
	static final int MAXRESOLUTIONS = 300;

	public static HashMap<String, Integer> counter = new HashMap<>();

	public static void bind(HashMap<String, String> bindings, String s1, String s2) {
		while (bindings.containsKey(s1)) {
			s1 = bindings.get(s1);
		}
		while (bindings.containsKey(s2)) {
			s2 = bindings.get(s2);
		}
		if (s1 != s2) {
			bindings.put(s1, s2);
		}
	}

	public static HashMap<String, String> Unify(ArrayList<String> p1, ArrayList<String> p2,
			HashMap<String, String> bindings) {

		@SuppressWarnings("unchecked")
		HashMap<String, String> result = (HashMap<String, String>) bindings.clone();

		if (p1.equals(p2)) {
			return result;
		}
		if (p1.get(0) == p2.get(0) && (p1.size() == p2.size())) {
			for (int i = 1; i < p2.size(); i++) {
				if (p1.get(i) == p2.get(i)) {
					continue;
				}
				if (p1.get(i) != p2.get(i)) { /* check if first character of string in pred is variable or not */
					if ((p1.get(i)).charAt(0) == '?') {
						bind(result, p1.get(i), p2.get(i));
					} else if (p2.get(i).charAt(0) == '?') {
						bind(result, p2.get(i), p1.get(i));
					} else {
						return null;
					}
				}
			}
			return result;
		} else {
			return null;
		}
	}

	public static String bindVar(HashMap<String, String> bindings, String s) {
		while (bindings.containsKey(s)) {
			s = bindings.get(s);
		}
		return s;
	}

	public static ArrayList<ArrayList<String>> uniquify(ArrayList<ArrayList<String>> clause) {
		HashSet<String> changed = new HashSet<>();
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		for (ArrayList<String> p : clause) {
			ArrayList<String> tmp_p = new ArrayList<>();
			for (String s : p) {
				if (s.charAt(0) == '?') {
					if (changed.contains(s)) {
						s += counter.get(s);
					} else if (!counter.containsKey(s)) {
						counter.put(s, 1);
						changed.add(s);
						s += "1";
					} else {
						counter.put(s, counter.get(s) + 1);
						changed.add(s);
						s += counter.get(s);
					}
				}
				tmp_p.add(s);
			}
			result.add(tmp_p);
		}
		return result;
	}

	public static void printClauses(ArrayList<ArrayList<ArrayList<String>>> clauses){
        for (ArrayList<ArrayList<String>> clause : clauses) {
            System.out.println(clause);
        }
    }

    public static HashSet<HashMap<String, String>> DFS(ArrayList<ArrayList<ArrayList<String>>> clauses,
        ArrayList<ArrayList<String>> goal){
        int counter = 0;
        HashMap<String, String> bindings = new HashMap<>();

        HashSet<HashMap<String, String>> result = new HashSet<>();

        Stack<goalWithBindings> open_list = new Stack<>();
        open_list.push(new goalWithBindings(goal, bindings));

        while (!open_list.isEmpty() && bindings!=null) {

            goalWithBindings cur_goalWithBindings = open_list.pop();

            ArrayList<ArrayList<String>> currentGoal = cur_goalWithBindings.currentGoal;
            HashMap<String, String> currentBinding = cur_goalWithBindings.currentBinding;

            for(ArrayList<ArrayList<String>> cur_clause : clauses) {

                cur_clause = uniquify(cur_clause);
                counter++;
                HashMap<String, String> new_bindings = Unify(currentGoal.get(0),
                        cur_clause.get(0), currentBinding);

                if (new_bindings == null) {
                    continue;
                }
                if (new_bindings != null) {
                    ArrayList<ArrayList<String>> successor = new ArrayList<>();

                    successor.addAll(cur_clause);
					successor.remove(0);

					@SuppressWarnings("unchecked")
                    ArrayList<ArrayList<String>> tmp_cur_goal = (ArrayList<ArrayList<String>>) currentGoal.clone();
                    tmp_cur_goal.remove(0);
                    successor.addAll(tmp_cur_goal);

                    if (successor.isEmpty()) {
                        result.add(new_bindings);
                    }
                    if (!successor.isEmpty() && counter<=MAXRESOLUTIONS) {
                        open_list.push(new goalWithBindings(successor, new_bindings));
                    }
                }
            }
        }
        return result;
    }

    public static HashSet<HashMap<String, String>> BFS(ArrayList<ArrayList<ArrayList<String>>> clauses,
                                                       ArrayList<ArrayList<String>> goal){
        int counter = 0;
        HashMap<String, String> bindings = new HashMap<>();

        HashSet<HashMap<String, String>> result = new HashSet<>();

        Queue<goalWithBindings> open_list = new LinkedList<>();
        open_list.add(new goalWithBindings(goal, bindings));


        while (!open_list.isEmpty() && bindings!=null) {

            goalWithBindings cur_goalWithBindings = open_list.remove();

            ArrayList<ArrayList<String>> currentGoal = cur_goalWithBindings.currentGoal;
            HashMap<String, String> currentBinding = cur_goalWithBindings.currentBinding;

            for(ArrayList<ArrayList<String>> cur_clause : clauses) {

                cur_clause = uniquify(cur_clause);
                counter++;
                HashMap<String, String> new_bindings = Unify(currentGoal.get(0),
                    cur_clause.get(0), currentBinding);

                if (new_bindings == null) {
                    continue;
                }
                if (new_bindings != null) {
                    ArrayList<ArrayList<String>> successor = new ArrayList<>();

                    successor.addAll(cur_clause);
					successor.remove(0);
					
					@SuppressWarnings("unchecked")
                    ArrayList<ArrayList<String>> tmp_cur_goal = (ArrayList<ArrayList<String>>) currentGoal.clone();
                    tmp_cur_goal.remove(0);
                    successor.addAll(tmp_cur_goal);

                    if (successor.isEmpty()) {
                        result.add(new_bindings);
                    }
                    if (!successor.isEmpty() && counter<=MAXRESOLUTIONS) {
                        open_list.add(new goalWithBindings(successor, new_bindings));
                    }
                }
            }
        }
        return result;
    }
}