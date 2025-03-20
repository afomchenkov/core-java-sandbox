
import java.util.*;

record Tuple<A, B>(A first, B second) {
}

public class Main {
    class MergeIntervalsGraph {
        private Map<int[], List<int[]>> graph;
        private Map<Integer, List<int[]>> nodesInComp;
        private Set<int[]> visited;

        // return whether two intervals overlap (inclusive)
        private boolean overlap(int[] a, int[] b) {
            return a[0] <= b[1] && b[0] <= a[1];
        }

        // build a graph where an undirected edge between intervals u and v exists
        // iff u and v overlap.
        private void buildGraph(int[][] intervals) {
            graph = new HashMap<>();
            for (int[] interval : intervals) {
                graph.put(interval, new LinkedList<>());
            }

            for (int[] interval1 : intervals) {
                for (int[] interval2 : intervals) {
                    if (overlap(interval1, interval2)) {
                        graph.get(interval1).add(interval2);
                        graph.get(interval2).add(interval1);
                    }
                }
            }
        }

        // merges all of the nodes in this connected component into one interval.
        private int[] mergeNodes(List<int[]> nodes) {
            int minStart = nodes.get(0)[0];
            for (int[] node : nodes) {
                minStart = Math.min(minStart, node[0]);
            }

            int maxEnd = nodes.get(0)[1];
            for (int[] node : nodes) {
                maxEnd = Math.max(maxEnd, node[1]);
            }

            return new int[] { minStart, maxEnd };
        }

        // use depth-first search to mark all nodes in the same connected component
        // with the same integer.
        private void markComponentDFS(int[] start, int compNumber) {
            Stack<int[]> stack = new Stack<>();
            stack.add(start);

            while (!stack.isEmpty()) {
                int[] node = stack.pop();
                if (!visited.contains(node)) {
                    visited.add(node);

                    if (nodesInComp.get(compNumber) == null) {
                        nodesInComp.put(compNumber, new LinkedList<>());
                    }
                    nodesInComp.get(compNumber).add(node);

                    for (int[] child : graph.get(node)) {
                        stack.add(child);
                    }
                }
            }
        }

        // gets the connected components of the interval overlap graph.
        private void buildComponents(int[][] intervals) {
            nodesInComp = new HashMap<>();
            visited = new HashSet<>();
            int compNumber = 0;

            for (int[] interval : intervals) {
                if (!visited.contains(interval)) {
                    markComponentDFS(interval, compNumber);
                    compNumber++;
                }
            }
        }

        public int[][] merge(int[][] intervals) {
            buildGraph(intervals);
            buildComponents(intervals);

            // for each component, merge all intervals into one interval.
            List<int[]> merged = new LinkedList<>();
            for (int comp = 0; comp < nodesInComp.size(); comp++) {
                merged.add(mergeNodes(nodesInComp.get(comp)));
            }

            return merged.toArray(new int[merged.size()][]);
        }
    }

    class MajorityElement {
        private Map<Integer, Integer> countNums(int[] nums) {
            Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
            for (int num : nums) {
                if (!counts.containsKey(num)) {
                    counts.put(num, 1);
                } else {
                    counts.put(num, counts.get(num) + 1);
                }
            }
            return counts;
        }

        public int majorityElement(int[] nums) {
            Map<Integer, Integer> counts = countNums(nums);

            Map.Entry<Integer, Integer> majorityEntry = null;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() > nums.length / 2)
                    return entry.getKey();
            }

            return majorityEntry.getKey();
        }
    }

    class LongestSubstring {
        public int run(String s) {
            int begin = 0;
            int end = 0;

            HashMap<Character, Boolean> window = new HashMap<>();
            for (int l = 0, r = 0; r < s.length(); r++) {
                char ch = s.charAt(r);

                if (window.getOrDefault(ch, false)) {
                    while (s.charAt(l) != s.charAt(r)) {
                        window.put(s.charAt(l++), false);
                    }
                    l++;
                } else {
                    window.put(ch, true);
                    if (r - l > end - begin) {
                        begin = l;
                        end = r;
                    }
                }
            }

            return end - begin + 1;
        }
    }

    class ReverseWords {
        public String run(String s) {
            int left = 0, right = s.length() - 1;

            while (left <= right && s.charAt(left) == ' ')
                ++left;
            while (left <= right && s.charAt(right) == ' ')
                --right;

            Deque<String> d = new ArrayDeque();
            StringBuilder word = new StringBuilder();

            while (left <= right) {
                char c = s.charAt(left);

                if ((word.length() != 0) && (c == ' ')) {
                    d.offerFirst(word.toString());
                    word.setLength(0);
                } else if (c != ' ') {
                    word.append(c);
                }
                ++left;
            }
            d.offerFirst(word.toString());

            return String.join(" ", d);
        }
    }

    public static void printSquare(List<Tuple<Integer, Integer>> points) {
        Set<String> pointSet = new HashSet<>();
        for (var p : points) {
            pointSet.add(p.first() + "," + p.second()); // Store points as "x,y"
        }

        int maxArea = 0;
        List<int[]> bestRectangle = new ArrayList<>();

        // Iterate through all pairs of points
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int x1 = points.get(i).first(), y1 = points.get(i).second();
                int x2 = points.get(j).first(), y2 = points.get(j).second();

                // Skip if they form a horizontal or vertical line (they must be diagonal)
                if (x1 == x2 || y1 == y2)
                    continue;

                // Check if the other two corners exist
                if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);

                    if (area > maxArea) {
                        maxArea = area;
                        bestRectangle = List.of(
                                new int[] { x1, y1 }, new int[] { x1, y2 },
                                new int[] { x2, y1 }, new int[] { x2, y2 });
                    }
                }
            }
        }

        for (int[] p : bestRectangle) {
            System.out.println(Arrays.toString(p));
        }
    }

    public static void main(String[] args) {
        System.out.println("---- START ----");

        List<Tuple<Integer, Integer>> points = new ArrayList<>(Arrays.asList(
                new Tuple(-3, 5),
                new Tuple(5, 1),
                new Tuple(-3, 1),
                new Tuple(-3, -1),
                new Tuple(-3, -4),
                new Tuple(5, -3),
                new Tuple(5, -4)));
        printSquare(points);

        System.out.println("---- END ----");
    }
}
