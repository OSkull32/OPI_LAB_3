package ru.ifmo.web_lab_3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AreaResultTest {

    @Test
    public void topHitRight() {
        double r = 5;
        for (double x = 0; x <= 4; x += 0.1) {
            for (double y = 0; y <= 2.5; y += 0.1) {
                    boolean hit = AreaChecker.isHit(x, y, r);
                    assertTrue(hit);
            }
        }
    }

    @Test
    public void topMissRight() {
        double r = 5;
        for (double x = 0; x <= 4; x += 0.1) {
            for (double y = 2.6; y <= 5; y += 0.1) {
                boolean hit = AreaChecker.isHit(x, y, r);
                assertFalse(hit);
            }
        }
    }

    @Test
    public void downMissRight() {
        double r = 5;
        for (double x = 0.1; x <= 4; x += 0.1) {
            for (double y = -5; y < 0; y += 0.1) {
                boolean hit = AreaChecker.isHit(x, y, r);
                assertFalse(hit);
            }
        }
    }

    @Test
    public void downHitLeft() {
        double r = 5;
        for (double x = -4; x <= 0; x += 0.1) {
            for (double y = -5; y <= 0; y += 0.1) {
                if (- x - y <= r) {
                    boolean hit = AreaChecker.isHit(x, y, r);
                    assertTrue(hit);
                }

            }
        }
    }

    @Test
    public void downMissLeft() {
        double r = 5;
        for (double x = -4; x <= 0; x += 0.1) {
            for (double y = -5; y <= 0; y += 0.1) {
                if (- x - y > r) {
                    boolean hit = AreaChecker.isHit(x, y, r);
                    assertFalse(hit);
                }

            }
        }
    }

    @Test
    public void topHitLeft() {
        double r = 5;
        for (double x = -4; x <= 0; x += 0.1) {
            for (double y = 0; y <= 5; y += 0.1) {
                if (x * x + y * y <= r / 2 * r / 2) {
                    boolean hit = AreaChecker.isHit(x, y, r);
                    assertTrue(hit);
                }

            }
        }
    }

    @Test
    public void topMissLeft() {
        double r = 5;
        for (double x = -4; x <= 0; x += 0.1) {
            for (double y = 0.1; y <= 5; y += 0.1) {
                if (x * x + y * y > r / 2 * r / 2) {
                    boolean hit = AreaChecker.isHit(x, y, r);
                    assertFalse(hit);
                }

            }
        }
    }

    @Test
    public void firstTest() {
        Object[][] firstTests = {
                {0.0,0.0,4.0, true},
                {0.0,2.0,4.0, true},
                {4.0,2.0,4.0, true},
                {4.0,0.0,4.0, true},
                {-4.0,0.0,4.0, true},
                {0.0,-4.0,4.0, true},
                {1.0,2.0,3.0, false},
                {2.0,1.0,3.0, true},
                {1.0,2.0,5.0, true},
        };
        for(Object[] o : firstTests) {
            assertEquals(AreaChecker.isHit((double) o[0], (double) o[1], (double) o[2]), (boolean) o[3]);
        }
    }

    @ParameterizedTest
    @CsvSource({"0.0,2.0,4.0, true",
            "4.0,2.0,4.0, true",
            "4.0,0.0,4.0, true",
            "2.0,2.0,4.0, true",
            "2.0,0.0,4.0, true"})
    public void testTopRightEdge(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"0.0,1.5,3.0, true",
            "0.0,0.5,1.0, true",
            "-1.0,0.0,2.0, true",
            "-1.4,1.4,4.0, true"})
    public void testTopLeftEdge(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"-2.0,0.0,2.0, true",
            "0.0,-3.0,3.0, true",
            "0.0,-1.0,3.0, true",
            "-1.0,-1.0,2.0, true"})
    public void testBottomLeftEdge(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"0.0,0.0,2.0, true",
            "0.0,0.0,3.0, true",
            "0.0,0.0,4.0, true",
            "0.0,0.0,5.0, true"})
    public void testZero(double x, double y, double r, boolean hit) {
            assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"1.0,1.0,3.0, true",
            "2.0,1.0,3.0, true",
            "0.5,1.0,2.0, true",
            "2.0,1.0,2.0, true"})
    public void testTopRightHit(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"-1.0,1.0,3.0, true",
            "-1.0,2.0,5.0, true",
            "-2.0,1.0,5.0, true"})
    public void testTopLeftHit(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"-1.0,-1.0,3.0, true",
            "-1.0,-2.0,5.0, true",
            "-2.0,-1.0,5.0, true"})
    public void testBottomLeftHit(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"2.0,3.0,3.0, false",
            "1.0,2.0,3.0, false",
            "1.0,2.0,2.0, false",
            "2.0,2.0,2.0, false"})
    public void testTopRightMiss(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"-2.0,3.0,3.0, false",
            "-1.0,2.0,3.0, false",
            "-1.0,2.0,2.0, false",
            "-2.0,2.0,2.0, false"})
    public void testTopLeftMiss(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"-2.0,-3.0,3.0, false",
            "-2.0,-2.0,3.0, false",
            "-1.0,-2.0,2.0, false",
            "-2.0,-2.0,2.0, false"})
    public void testBottomLeftMiss(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @ParameterizedTest
    @CsvSource({"2.0,-3.0,3.0, false",
            "2.0,-2.0,3.0, false",
            "1.0,-2.0,2.0, false",
            "2.0,-2.0,2.0, false"})
    public void testBottomRightMiss(double x, double y, double r, boolean hit) {
        assertEquals(AreaChecker.isHit(x, y, r), hit);
    }

    @Test
    public void testPositiveInfinity() {
        ArithmeticException expected = assertThrows(ArithmeticException.class, () -> AreaChecker.isHit(Double.POSITIVE_INFINITY, 2.0, 2.0) );
        assertEquals("Infinity not possible", expected.getMessage());
    }

    @Test
    public void testNegativeInfinity() {
        ArithmeticException expected = assertThrows(ArithmeticException.class, () -> AreaChecker.isHit(Double.NEGATIVE_INFINITY, 2.0, 2.0) );
        assertEquals("Infinity not possible", expected.getMessage());
    }

    @Test
    public void testNan() {
        ArithmeticException expected = assertThrows(ArithmeticException.class, () -> AreaChecker.isHit(Double.NaN, 2.0, 2.0) );
        assertEquals("Nan not possible", expected.getMessage());
    }


    @Test
    void getResult() {
        assertTrue(true);
    }

}
