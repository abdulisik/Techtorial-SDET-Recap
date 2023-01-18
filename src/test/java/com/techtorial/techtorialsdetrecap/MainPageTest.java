package com.techtorial.techtorialsdetrecap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
  private WebDriver driver;
  private MainPage mainPage;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://www.jetbrains.com/");

    mainPage = new MainPage(driver);
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void search() {
    mainPage.searchButton.click();

    WebElement searchField = driver.findElement(By.cssSelector("[data-test='search-input']"));
    searchField.sendKeys("Selenium");

    WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
    submitButton.click();

    WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test='search-input']"));
    assertEquals("Selenium", searchPageField.getAttribute("value"));
  }

  @Test
  public void toolsMenu() {
    mainPage.toolsMenu.click();

    WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
    assertTrue(menuPopup.isDisplayed());
  }

  @Test
  public void navigationToAllTools() {
    mainPage.seeDeveloperToolsButton.click();
    mainPage.findYourToolsButton.click();

    WebElement productsList = driver.findElement(By.id("products-page"));
    assertTrue(productsList.isDisplayed());
    assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
  }
}
