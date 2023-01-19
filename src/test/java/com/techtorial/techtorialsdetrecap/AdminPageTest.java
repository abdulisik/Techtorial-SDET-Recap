package com.techtorial.techtorialsdetrecap;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminPageTest {
  private Faker faker = new Faker();
  private WebDriver driver;
  private AdminPage adminPage;
  private AddNewItemPage addNewItemPage;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://microfeed.techtorialacademy.net/admin/");

    adminPage = new AdminPage(driver);
  }

  @Test
  void publishAndClick() {
    // As an admin,
    // I want to publish a new url,
    // so that I can share a post on my blog.
    var newButton = adminPage.addNewItemButton;
    newButton.click();
    String url = driver.getCurrentUrl();
    assertTrue(url.endsWith("/new/"), "The url of the new item page should end in /new/");

    addNewItemPage = new AddNewItemPage(driver);
    addNewItemPage.externalUrlRadio.click();

    // Post fake data
    String postedUrl = "https://" + faker.internet().url() + '/';
    addNewItemPage.urlField.sendKeys(postedUrl);
    String postedTitle = faker.company().buzzword();
    addNewItemPage.titleField.sendKeys(postedTitle);
    String postedDescription = faker.lorem().paragraph(4);
    addNewItemPage.descriptionField.sendKeys(postedDescription);
    addNewItemPage.createButton.click();

    // Visit the newly published post
    addNewItemPage.publishedUrl.click();

    // Switch to the newly opened window
    var windows = driver.getWindowHandles();
    assertEquals(2, windows.size(), "Clicking on the published URL did not open a new tab.");
    var currentWindow = driver.getWindowHandle();
    var otherWindows = windows.stream().filter(e -> !e.equals(currentWindow)).collect(Collectors.toList());
    assertEquals(1, otherWindows.size(), "There should only be one windows other than the current.");
    driver.switchTo().window(otherWindows.get(0));

    // Assert the expected data
    var postPage = new PostPage(driver);
    assertEquals(postedTitle, postPage.postTitle.getText(), "Title does not match");
    assertEquals(postedDescription, postPage.postDescription.getText(), "Description does not match");
    assertEquals(postedUrl, postPage.postedUrl.getAttribute("href"), "Posted URL does not match");
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

}
