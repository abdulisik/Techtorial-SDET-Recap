package com.techtorial.techtorialsdetrecap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://microfeed.techtorialacademy.net/
public class MainPage {
  @FindBy(xpath = "//h1")
  public WebElement mainTitle; // Techtorial Microfeed

  @FindBy(xpath = "//h2[.='About']") // Contributed by Radu Muresan
  public WebElement aboutTitle; // About

  @FindBy(xpath = "//div/p") // Contributed by Rox M
  public WebElement aboutDetails;

  MainPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
