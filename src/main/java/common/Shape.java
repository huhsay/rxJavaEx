package common;

import com.sun.org.apache.bcel.internal.generic.RET;
import javafx.scene.shape.TriangleMesh;

import java.util.Observable;

public class Shape {

    public static final String HEXAGON = "HEXAGON";
    public static final String OCTAGON = "OCTAGON";
    public static final String RECTANGLE = "RECTANGLE";
    public static final String TRIANGLE = "TRIANGLE";
    public static final String DIAMOND = "DIAMOND";
    public static final String PENTAGON = "PENTAGON";
    public static final String BALL = "BALL";
    public static final String STAR = "STAR";
    public static final String NO_SHAPE = "NO_SHAPE";
    public static final String FLIPPED = "(flipped)";

    //Colors for shape
    public static final String RED = "1";
    public static final String YELLOW = "2";
    public static final String GREEN = "3";
    public static final String SKY = "4";
    public static final String BLUE = "5";
    public static final String PUPPLE = "6";
    public static final String ORANGE = "7";

    public static String getSuffix(String shape) {
        if (HEXAGON.equals((shape))) return "-H";
        if (OCTAGON.equals(shape)) return "-O";
        if (RECTANGLE.equals(shape)) return "-R";
        if (TRIANGLE.equals(shape)) return "-T";
        if (DIAMOND.equals(shape)) return "<>";
        if (PENTAGON.equals(shape)) return "-P";
        if (STAR.equals(shape)) return "-S";
        return "";
    }

    public static String getColor(String shape) {
        if (shape.endsWith("<>"))
            return shape.replace("<>", "").trim();

        int hypen = shape.indexOf("-");
        if (hypen > 0) {
            return shape.substring(0, hypen);
        }
        return shape;
    }
}