package ru.ifmo.web_lab_3.bd;

import ru.ifmo.web_lab_3.Point;

import java.sql.SQLException;
import java.util.Collection;

public interface CheckAreaDAO {
    void addNewResult(Point result) throws SQLException;
    void updateResult(Long bus_id, Point result) throws SQLException;
    Point getResultById(Long result_id) throws SQLException;
    Collection<Point> getAllResults() throws SQLException;
    void deleteResult(Point result) throws SQLException;
    void clearResults() throws SQLException;
    long countPoints() throws SQLException;
    long countMissedPoints() throws SQLException;
}
