import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        // TODO: Write your code here!
        return naiveHelperIterative(temperatures);
    }

    public static int naiveApproach(int[] temperatures) {
        return naiveHelper(0, temperatures, -9999, 0);
    }

    public static int naiveHelper(int index, int[] temperatures, int max, int streak) {
        if (index >= temperatures.length) {
            return streak;
        }
        int runMax = 0;
        if (temperatures[index] > max) {
            runMax = naiveHelper(index + 1, temperatures, temperatures[index], streak + 1);
            int temp = naiveHelper(index + 1, temperatures, max, streak);
            if (temp > runMax) {
                runMax = temp;
            }
        }
        else {
            runMax = naiveHelper(index + 1, temperatures, max, streak);
        }

        return runMax;
    }

    public static int naiveHelperIterative(int[] temperatures) {

        // Queue order goes index, maxTemp, streak
        Queue<int[]> data = new LinkedList<>();

        int maxStreak = 0;
        int[] initial = new int[4];
        initial[1] = -9999;
        initial[3] = 9999;
        data.add(initial);


        while (!data.isEmpty()) {
            int[] current = data.poll();
            int index = current[0];
            int streak = current[2];
            int max = current[1];
            int lowestSkipped = current[3];
            if (index >= temperatures.length) {
                if (streak > maxStreak) maxStreak = streak;
                continue;
            }
            if (temperatures[index] > max) {
                if (temperatures[index] < lowestSkipped) {
                    initial = new int[4];
                    initial[0] = index + 1;
                    initial[1] = temperatures[index];
                    initial[2] = streak + 1;
                    initial[3] = 9999;
                    data.add(initial);
                }

                initial = new int[4];
                initial[0] = index + 1;
                initial[1] = max;
                initial[2] = streak;
                if (lowestSkipped > temperatures[index]) initial[3] = temperatures[index];
                data.add(initial);
            }
            else {
                initial = new int[4];
                initial[0] = index + 1;
                initial[1] = max;
                initial[2] = streak;
                initial[3] = lowestSkipped;
                data.add(initial);
            }
        }
        return maxStreak;
    }
}
