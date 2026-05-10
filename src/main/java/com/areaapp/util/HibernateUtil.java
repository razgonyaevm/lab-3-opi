package com.areaapp.util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Утилитный класс для настройки и получения фабрики сессий Hibernate.
 * <p>
 * Использует конфигурацию из файла {@code hibernate.cfg.xml} для подключения
 * к базе данных. Реализует паттерн Singleton для {@link SessionFactory}.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 * @see SessionFactory
 */
public class HibernateUtil {
  /** Единственный экземпляр фабрики сессий Hibernate */
  @Getter private static SessionFactory sessionFactory = buildSessionFactory();

  /**
   * Создаёт и настраивает фабрику сессий Hibernate.
   * <p>
   * Загружает конфигурацию из стандартного файла {@code hibernate.cfg.xml} и
   * строит метаданные на основе аннотированных классов сущностей.
   * </p>
   *
   * @return настроенная {@link SessionFactory}
   * @throws RuntimeException если не удалось создать фабрику сессий
   */
  private static SessionFactory buildSessionFactory() {
    if (sessionFactory == null) {
      try {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Error building SessionFactory: " + e.getMessage(), e);
      }
    }
    return sessionFactory;
  }
}
