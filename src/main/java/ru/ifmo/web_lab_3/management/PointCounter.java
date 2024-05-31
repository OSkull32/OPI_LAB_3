package ru.ifmo.web_lab_3.management;

import ru.ifmo.web_lab_3.bd.DAOFactory;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.sql.SQLException;


public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean, PointStatusObserver {
    // бин регистрируется в DAOFactory, если че

    private boolean lastPointStatus;

    @Override
    public void updatePointStatus(boolean status) {
        if (!status && !lastPointStatus) {
            sendNotification(new Notification("ru.ifmo.web_lab_3.management.PointCounter",
                    this, 1, "Miss twice in a row!"));
        }
        lastPointStatus = status;
    }

    @Override
    public long countPoints() throws SQLException {

        return DAOFactory.getInstance().getResultDAO().countPoints();
    }

    @Override
    public long countMissedPoints() throws SQLException {
        return DAOFactory.getInstance().getResultDAO().countMissedPoints();
    }
}
