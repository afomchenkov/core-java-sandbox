package Tasks.Arrays;

import java.util.*;

public class FindRectangle {
    static List<int[]> find(List<int[]> points) {
        if (points.size() == 0) {
            return List.of();
        }

        HashSet<String> check = new HashSet<>();
        for (var point : points) {
            check.add(point[0] + "|" + point[1]);
        }

        int maxArea = 0;
        List<int[]> bestRectangle = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int x1 = points.get(i)[0], y1 = points.get(i)[1];
                int x2 = points.get(j)[0], y2 = points.get(j)[1];

                if (x1 == x2 || y1 == y2) {
                    continue;
                }

                if (check.contains(x1 + "|" + y2) && check.contains(x2 + "|" + y1)) {
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);

                    if (maxArea < area) {
                        bestRectangle = List.of(
                            new int[]{x1, y1}, new int[]{x2, y2},
                            new int[]{x1, y2}, new int[]{x2, y1}
                        );
                    }
                }
            }
        }

        return bestRectangle;
    }

    public static void main(String[] args) {

    }
}
