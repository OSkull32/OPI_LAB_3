package ru.ifmo.web_lab_3.management;

import java.sql.SQLException;

public interface PointStatisticMBean {
    float successPercentage() throws SQLException;
}
