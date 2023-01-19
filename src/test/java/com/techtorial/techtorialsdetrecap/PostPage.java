package com.techtorial.techtorialsdetrecap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://microfeed.techtorialacademy.net/admin
public class PostPage {
  @FindBy(xpath = "//h1")
  public WebElement postTitle;

  @FindBy(xpath = "//a[@rel='noopener nofollow']")
  public WebElement postedUrl;

  @FindBy(xpath = "//p")
  public WebElement postDescription;


  PostPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
