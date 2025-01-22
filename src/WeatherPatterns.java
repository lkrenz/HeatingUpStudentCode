import java.util.*;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        ArrayList<Integer>[] adjList = buildList(temperatures);
        int[] paths = new int[temperatures.length];

        int longestStreak = 0;

        for (int i = 0; i < temperatures.length; i++) {
            findLongest(adjList, paths, i);
            if (paths[i] > longestStreak) longestStreak = paths[i];
        }
        return longestStreak;
    }

    public static void findLongest(ArrayList<Integer>[] adjList, int[] paths, int node) {
        if (adjList[node].isEmpty()) {
            paths[node] = 1;
        }
        int max = 0;
        for (int i : adjList[node]) {
            if (paths[i] == 0) {
                findLongest(adjList, paths, i);
            }
            if (paths[i] > max) {
                max = paths[i];
            }
        }
        paths[node] = max + 1;
    }

    public static ArrayList<Integer>[] buildList(int[] temperatures) {
        ArrayList<Integer>[] adjList = new ArrayList[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            int currentTemp = temperatures[i];
            adjList[i] = new ArrayList<>();
            for (int j = i; j > 0; j--) {
                if (temperatures[j] < currentTemp) {
                    adjList[i].add(j);
                }
            }
        }
        return adjList;
    }
}
