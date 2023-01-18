package com.techtorial.techtorialsdetrecap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainPageTest {
  private WebDriver driver;
  private MainPage mainPage;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://microfeed.techtorialacademy.net/");

    mainPage = new MainPage(driver);
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }


  @Test
  void mainTitleTest() {
    // User Story 1:
    // As a user, I want to be able to visit the homepage
    // so that I can read about the company
    WebElement title = mainPage.mainTitle;
    assertTrue(title.isDisplayed(), "Title is not displayed");
    assertEquals("Techtorial Microfeed", title.getText(),
            "Title's text is unexpected");

    WebElement aboutTitle = mainPage.aboutTitle;
    assertTrue(aboutTitle.isDisplayed(), "About title is not displayed");
    assertEquals("About", aboutTitle.getText(), "About title's text is unexpected");

    WebElement aboutDetails = mainPage.aboutDetails;
    assertTrue(aboutDetails.isDisplayed(), "About details is not displayed");
    assertEquals("Techtorial Microfeed description example.",
            aboutDetails.getText(),
            "About details' text is unexpected");
  }

}
