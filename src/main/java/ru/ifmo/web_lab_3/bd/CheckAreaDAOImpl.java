package ru.ifmo.web_lab_3.bd;

import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.ifmo.web_lab_3.Point;
import ru.ifmo.web_lab_3.management.PointStatusObserver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CheckAreaDAOImpl implements CheckAreaDAO {

    private final List<PointStatusObserver> pointStatusObservers = new ArrayList<>();

    @Override
    public void addNewResult(Point result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            session.persist(result);
            session.getTransaction().commit();

            notifyObservers(result.isStatus());
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateResult(Long bus_id, Point result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            session.merge(result);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Point getResultById(Long result_id) throws SQLException {
        Session session = null;
        Point result;
        try {
            session = HibernateUtils.getFactory().openSession();
            result = session.getReference(Point.class, result_id);
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public Collection<Point> getAllResults() throws SQLException {
        Session session = null;
        List<Point> results;
        try {
            session = HibernateUtils.getFactory().openSession();
            var criteriaQuery = session.getCriteriaBuilder().createQuery(Point.class);
            Root<Point> root = criteriaQuery.from(Point.class);
            results = session.createQuery(criteriaQuery.select(root)).getResultList();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }

    @Override
    public void deleteResult(Point result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            session.remove(result);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public final String TABLE_NAME = "points";
    @Override
    public void clearResults() throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            String sql = "delete from " + TABLE_NAME;
            session.createNativeQuery(sql, this.getClass()).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public long countPoints() throws SQLException {
        Session session = null;
        long count;
        try {
            session = HibernateUtils.getFactory().openSession();
            count = session.createQuery("select count(*) from Point p", Long.class).uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public long countMissedPoints() throws SQLException {
        Session session = null;
        long count;
        try {
            session = HibernateUtils.getFactory().openSession();
            count = session.createQuery("select count(*) from Point p where p.status = false", Long.class).uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Something went wrong in DAO: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return count;
    }

    public void addPointStatusObserver(PointStatusObserver observer) {
        pointStatusObservers.add(observer);
    }

    private void notifyObservers(boolean status) {
        for (var observer : pointStatusObservers) {
            observer.updatePointStatus(status);
        }
    }
}