package ru.ifmo.web_lab_3;

import org.junit.jupiter.api.Test;

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
    void getResult() {
        assertTrue(true);
    }

}
