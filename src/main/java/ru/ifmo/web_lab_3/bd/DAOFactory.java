package ru.ifmo.web_lab_3.bd;

import ru.ifmo.web_lab_3.management.PointCounter;
import ru.ifmo.web_lab_3.management.PointStatistic;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class DAOFactory {

    private static final CheckAreaDAOImpl resultDAO = new CheckAreaDAOImpl();
    private static DAOFactory instance;

    private DAOFactory() {

        // регистрация бинов управления
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();

            // регистрация PointCounter
            ObjectName name1 = new ObjectName("ru.ifmo.web_lab_3.management:type=PointCounter");
            if (server.isRegistered(name1))
                server.unregisterMBean(name1);
            var pointCounter = new PointCounter();
            resultDAO.addPointStatusObserver(pointCounter);
            server.registerMBean(pointCounter, name1);

            // регистрация PointStatistic
            ObjectName name2 = new ObjectName("ru.ifmo.web_lab_3.management:type=PointStatistic");
            if (server.isRegistered(name2))
                server.unregisterMBean(name2);
            server.registerMBean(new PointStatistic(), name2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }

    public CheckAreaDAO getResultDAO() {
        return resultDAO;
    }
}
