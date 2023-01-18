package com.techtorial.techtorialsdetrecap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// page_url = https://microfeed.techtorialacademy.net/
public class MainPage {
  @FindBy(xpath = "//h1")
  public WebElement mainTitle; // Techtorial Microfeed

  @FindBy(xpath = "//h2[.='About']") // Contributed by Radu Muresan
  public WebElement aboutTitle; // "About"

  @FindBy(xpath = "//div/p") // Contributed by Rox M
  public WebElement aboutDetails;

  @FindAll(@FindBy(xpath = "//div[@class='mb-4']/a")) // Contributed by Radu Muresan
  List<WebElement> posts; // Find all posts

  MainPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
