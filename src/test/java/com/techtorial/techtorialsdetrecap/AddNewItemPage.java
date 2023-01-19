package com.techtorial.techtorialsdetrecap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://microfeed.techtorialacademy.net/admin/items/new/
public class AddNewItemPage {
  @FindBy(xpath = "//input[@value='external_url']")
  public WebElement externalUrlRadio;

  @FindBy(xpath = "//input[@type='url']")
  public WebElement urlField;

  @FindBy(xpath = "//div[2]/div[1]/div[2]/label/div[2]/input")
  public WebElement titleField;

  @FindBy(xpath = "//div[@data-gramm='false']")
  public WebElement descriptionField;

  @FindBy(xpath = "//button[@type='submit']")
  public WebElement createButton;

  @FindBy(xpath = "//div[1]/div[2]/div[1]/div[1]/a/div")
  public WebElement publishedUrl;

  AddNewItemPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
