package com.areaapp.service;

import com.areaapp.entity.PointResult;
import com.areaapp.util.HibernateUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Сервис для работы с результатами проверок точек.
 * <p>
 * Предоставляет методы для сохранения, получения и удаления результатов
 * с использованием Hibernate и базы данных PostgreSQL.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 * @see PointResult
 * @see HibernateUtil
 */
@Named
@ApplicationScoped
public class PointService {

  /**
   * Сохраняет результат проверки точки в базу данных.
   * <p>
   * При ошибке сохранения выполняет откат транзакции.
   * </p>
   *
   * @param result объект {@link PointResult} для сохранения
   */
  public void saveResult(PointResult result) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = null;
      try {
        transaction = session.beginTransaction();
        session.persist(result);
        transaction.commit();
      } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
      }
    }
  }

  /**
   * Возвращает все результаты проверок, отсортированные по времени (сначала новые).
   *
   * @return список всех результатов
   */
  public List<PointResult> getAllResults() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      return session
          .createQuery("FROM PointResult ORDER BY timestamp DESC", PointResult.class)
          .list();
    }
  }

  /**
   * Удаляет все сохранённые результаты проверок из базы данных.
   */
  public void clearAllResults() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = null;
      try {
        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM PointResult").executeUpdate();
        transaction.commit();
      } catch (Exception e) {
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
      }
    }
  }
}
