import java.time.LocalTime;

public class ClockIntersection {
    public static void main(String... args) {

        clockIntersections(11, 10);
    }

    /**
     * takes a number of hours and minutes (up to several days), and returns the
     * number of times the minute and hour hands of a standard analog clock
     * would cross in that period of time, assuming that the clock always starts
     * at midnight, with one intersection. The function should also print every
     * unique location of the intersections, whether in degrees with precision
     * of 2 decimal points, or in a hour:minute:second format. 
     * 
     * You don’t have to
     * worry about error handling caused by improper inputs.
     * 
     * @param hours
     * @param minutes
     * @return
     */
    public static int clockIntersections(int hours, int minutes) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        int intersections = 1;
        long time = (long) ((12 / 11.0) * 60 * 60);
        long timeInSeconds = (hours * 60 + minutes) * 60;
        intersections += timeInSeconds / time;
//        System.out.println(intersections);
        for (int i = 0; i < intersections; i++) {
            System.out.println(i + 1 + ". intersection at: " + midnight.plusSeconds(i * time));
        }
        return intersections;
    }
}