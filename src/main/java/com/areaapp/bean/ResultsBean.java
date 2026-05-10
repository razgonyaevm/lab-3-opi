package com.areaapp.bean;

import com.areaapp.entity.PointResult;
import com.areaapp.service.PointService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Managed bean для отображения и управления результатами проверок.
 * <p>
 * Предоставляет доступ к списку всех результатов, их очистку и
 * формирование JSON-представления для отрисовки на клиенте.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 * @see PointResult
 * @see PointService
 */
@Named
@ApplicationScoped
public class ResultsBean {

  @Inject private PointService pointService;

  /**
   * Возвращает список всех сохранённых результатов проверок.
   *
   * @return список объектов {@link PointResult}
   */
  public List<PointResult> getResults() {
    return pointService.getAllResults();
  }

  /**
   * Удаляет все сохранённые результаты проверок.
   */
  public void clearResults() {
    pointService.clearAllResults();
  }

  /**
   * Формирует JSON-строку со всеми результатами для отрисовки точек на клиенте.
   *
   * @return JSON-строка в формате массива объектов
   */
  public String getPointsJson() {
    List<PointResult> list = getResults();
    if (list.isEmpty()) return "[]";
    return list.stream()
        .map(
            p ->
                String.format(
                    Locale.US,
                    "{\"x\":%.2f,\"y\":%.2f,\"r\":%.2f,\"result\":%s}",
                    p.getX(),
                    p.getY(),
                    p.getR(),
                    p.getResult()))
        .collect(Collectors.joining(",", "[", "]"));
  }
}
