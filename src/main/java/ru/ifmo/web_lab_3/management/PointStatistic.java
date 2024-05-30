package ru.ifmo.web_lab_3.management;

import ru.ifmo.web_lab_3.bd.DAOFactory;

import java.sql.SQLException;

public class PointStatistic implements PointStatisticMBean {
    // бин регистрируется в DAOFactory, если че
    @Override
    public float successPercentage() throws SQLException {
        var missed  = (float) DAOFactory.getInstance().getResultDAO().countMissedPoints();
        var total = DAOFactory.getInstance().getResultDAO().countPoints();
        return 1 - missed / total;
    }
}
