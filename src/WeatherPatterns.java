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

        return 0;
    }

    public int naiveApproach(int[] temperatures) {
        return naiveHelper(0, temperatures, -9999, 0);
    }

    public int naiveHelper(int index, int[] temperatures, int max, int streak) {
        if (index >= temperatures.length) {
            return max;
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

}
