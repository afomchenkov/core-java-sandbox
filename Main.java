
import java.util.*;

record Tuple<A, B>(A first, B second) {
}

public class Main {

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
                if (x1 == x2 || y1 == y2) continue;

                // Check if the other two corners exist
                if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);

                    if (area > maxArea) {
                        maxArea = area;
                        bestRectangle = List.of(
                            new int[]{x1, y1}, new int[]{x1, y2},
                            new int[]{x2, y1}, new int[]{x2, y2}
                        );
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
                new Tuple(5, -4)
        ));
        printSquare(points);

        System.out.println("---- END ----");
    }
}
