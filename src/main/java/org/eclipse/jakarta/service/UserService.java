package org.eclipse.jakarta.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import org.eclipse.jakarta.model.Professor;
import org.eclipse.jakarta.model.Student;

import java.util.List;

public class UserService {

    private EntityManagerFactory entityManagerFactory;

    public UserService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
    }

    public void createUser(Professor professor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(professor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void createStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Professor> getAllProfessors() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT p FROM Professor p", Professor.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Student> getAllStudents() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Professor getProfessorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Professor.class, id);
        } finally {
            entityManager.close();
        }
    }

    public Student getStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Student.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void updateProfessor(Professor professor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(professor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void updateStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteProfessor(Professor professor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(entityManager.merge(professor));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(entityManager.merge(student));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
