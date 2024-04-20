package ru.ifmo.web_lab_3;

import org.junit.jupiter.api.Test;

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
    public void firstQuarterTest() {
        Object[][] firstQuarterTests = {
                {1.0,2.0,3.0, true},
                {1.0,2.0,3.0, true},
                {1.0,2.0,3.0, true},
                {1.0,2.0,3.0, true},
        };
        for(Object[] o : firstQuarterTests) {
            assertEquals(AreaChecker.isHit((double) o[0], (double) o[1], (double) o[2]), (boolean) o[3]);
        }
    }


    @Test
    void getResult() {
        assertTrue(true);
    }

}
