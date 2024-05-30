package ru.ifmo.web_lab_3.management;

import java.sql.SQLException;

public interface PointCounterMBean {
    long countPoints() throws SQLException;
    long countMissedPoints() throws SQLException;
}
