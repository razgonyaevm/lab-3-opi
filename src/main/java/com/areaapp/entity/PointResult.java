package com.areaapp.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность JPA, представляющая результат проверки точки.
 * <p>
 * Хранит координаты точки, радиус области, результат проверки (попала/не попала),
 * время выполнения запроса и временную метку.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 */
@Entity
@Table(name = "point_result")
@NoArgsConstructor
@Getter
@Setter
public class PointResult {
  /** Уникальный идентификатор записи */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Координата X точки */
  @Column(name = "x_value", nullable = false)
  private Double x;

  /** Координата Y точки */
  @Column(name = "y_value", nullable = false)
  private Double y;

  /** Радиус области проверки */
  @Column(name = "r_value", nullable = false)
  private Double r;

  /** Результат проверки: true — точка попала в область */
  @Column(name = "result", nullable = false)
  private Boolean result;

  /** Временная метка выполнения проверки */
  @Column(name = "timestamp", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  /** Время выполнения запроса в наносекундах */
  @Column(name = "execution_time_ms")
  private long executionTimeMs;

  /**
   * Создаёт новый результат проверки точки.
   *
   * @param x координата X
   * @param y координата Y
   * @param r радиус области
   * @param result результат попадания
   * @param executionTimeMs время выполнения запроса
   */
  public PointResult(Double x, Double y, Double r, Boolean result, long executionTimeMs) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.result = result;
    this.executionTimeMs = executionTimeMs;
    this.timestamp = new Date(); // Используем java.util.Date
  }
}
