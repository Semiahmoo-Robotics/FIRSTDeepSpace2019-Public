package frc.robot.utils;

/**
 * A generic utility class with a collection of public static methods.
 */
public final class Utils {

    /**
     * No instantiation.
     */
    private Utils() {
    }

    /**
     * Determines if two doubles are equal to each other by the given tolerance.
     * @param a The first value
     * @param b The second value
     * @param tolerance The amount the two values have to be within to be considered equal
     * @return True if equal within the tolerance
     */
    public static boolean isEqual(double a, double b, double tolerance) {
        return Math.abs(a - b) <= tolerance;
    }

    /**
     * Linearly interpolate (lerp) between two values.
     * @param a The first value
     * @param b The second value
     * @param alpha The percentage to lerp by. 0.0 is fully a, 1.0 is fully b.
     * @return The interpolated value
     */
    public static double lerp(double a, double b, double alpha) {
        return a + (b - a) * alpha;
    }

    /**
     * Clamp a value between a minimum and maximum value.
     * @param value The value to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value between the minimum and maximum
     */
    public static double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

}
