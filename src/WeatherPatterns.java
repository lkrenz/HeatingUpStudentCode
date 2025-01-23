import java.util.*;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Liam Krenz
 */

public class WeatherPatterns {

    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {

        // Build the adjacency list
        ArrayList<Integer>[] adjList = buildList(temperatures);

        // Array to store longest path to each node
        int[] paths = new int[temperatures.length];

        int longestStreak = 0;

        // Iterate through each temperature node and find the longest path
        for (int i = 0; i < temperatures.length; i++) {
            findLongest(adjList, paths, i);
            if (paths[i] > longestStreak) longestStreak = paths[i];
        }
        return longestStreak;
    }

    // Recursive method for determining longest path to each node
    public static void findLongest(ArrayList<Integer>[] adjList, int[] paths, int node) {

        // Base case: node has no paths to it
        if (adjList[node].isEmpty()) {
            paths[node] = 1;
        }

        // Iterate through all adjacent nodes and find longest path
        int max = 0;
        for (int i : adjList[node]) {
            if (paths[i] == 0) {
                // Recursive call finds longest path to previous node
                findLongest(adjList, paths, i);
            }
            if (paths[i] > max) {
                max = paths[i];
            }
        }
        // Sets this node's value in paths
        paths[node] = max + 1;
    }

    // Builds adjacency list given integer array
    // I used a forward adjacency list
    public static ArrayList<Integer>[] buildList(int[] temperatures) {

        // Initiates array of array lists
        ArrayList<Integer>[] adjList = new ArrayList[temperatures.length];

        // Iterates through temperatures adding all values higher than the current value to the list
        for (int i = 0; i < temperatures.length; i++) {
            int currentTemp = temperatures[i];
            adjList[i] = new ArrayList<>();
            for (int j = i; j < temperatures.length; j++) {
                if (temperatures[j] > currentTemp) {
                    adjList[i].add(j);
                }
            }
        }
        return adjList;
    }
}
