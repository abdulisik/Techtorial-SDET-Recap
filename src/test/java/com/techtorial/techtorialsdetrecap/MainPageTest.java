package com.techtorial.techtorialsdetrecap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainPageTest {
  private WebDriver driver;
  private MainPage mainPage;
  private final Random random = new Random();

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

  @Test
  void postClickTest() {
    // As a user,
    // I want to see and click on the latest posts on the website
    // so that I can follow the news
    List<WebElement> posts = mainPage.posts;
    assertFalse(posts.isEmpty(), "There are no posts on the website");

    // Randomly choose one of the posts
    int index = random.nextInt(posts.size());
    WebElement post = posts.get(index);
    assertFalse(post.getText().isBlank(), "Selected post does not have a title or a date");

    // Assert the href before clicking on it
    assertNotNull(post.getAttribute("href"), "There's no link");
    assertFalse(post.getAttribute("href").isBlank(),
            "Selected post does not have a link");

    // Grab the href, compare with the URL after clicking on it
    String href = post.getAttribute("href");
    post.click();
    String currentUrl = driver.getCurrentUrl();
    assertEquals(href, currentUrl, "Unexpected URL.");

    // Make sure the response is not 404
    assertFalse(driver.getTitle().contains("404"), "Page cannot be reached");
  }

  @Test
  void postDateTest() {
    // As a user,
    // I want to see the date of each post
    // so that I know how recent they are.
    var dates = mainPage.datesOfPosts;
    for (int i = 0; i < dates.size(); i++) {
      var date = dates.get(i);
      String text = date.getText();
      assertNotNull(text, "Post no. " + i + " doesn't have a date.");
      assertTrue(text.contains(" "), "Post no. " + i + " doesn't have a proper date, space character expected.");
    }
  }

}
