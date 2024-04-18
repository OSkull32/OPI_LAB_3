package ru.ifmo.web_lab_3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.util.List;

public class PointStore implements Serializable {
    private EntityManager entityManager;
    public void createEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("points").createEntityManager();
    }

    public void addDotToDB(Point point) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(point);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
    }

    public List<Point> getDotsFromDB() {
        return entityManager.createQuery("select points from Point points", Point.class).getResultList();
    }

    public void clearDotsInBD() {
        entityManager.getTransaction().begin();
        try {
            entityManager.createQuery("delete from Point").executeUpdate();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
    }
}
