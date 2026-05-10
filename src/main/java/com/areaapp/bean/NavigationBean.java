package com.areaapp.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Managed bean для навигации между страницами приложения.
 * <p>
 * Предоставляет методы для перехода на главную страницу и страницу индекса.
 * Используется в JSF-представлениях для навигации.
 * </p>
 *
 * @author Maksim Razgonyaev
 * @version 1.0
 */
// @FacesConfig(version = FacesConfig.Version.JSF_4_0)
@Named
@SessionScoped
public class NavigationBean implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Переход на главную страницу приложения.
   *
   * @return строка навигации "main"
   */
  public String goToMain() {
    return "main";
  }

  /**
   * Переход на индексную страницу приложения.
   *
   * @return строка навигации "index"
   */
  public String goToIndex() {
    return "index";
  }
}
