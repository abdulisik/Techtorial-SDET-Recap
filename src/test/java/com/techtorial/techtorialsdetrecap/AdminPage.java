package com.techtorial.techtorialsdetrecap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://microfeed.techtorialacademy.net/admin
public class AdminPage {

  @FindBy(xpath = "//a[3]")
  public WebElement addNewItemButton;

  AdminPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
