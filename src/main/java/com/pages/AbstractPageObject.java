package com.pages;


import lombok.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


@Data
@Getter
@Setter
@ToString
public abstract class AbstractPageObject {

    private WebDriver driver;
    private WebDriverWait wait;

    public AbstractPageObject(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
    //initializing page objects using initElements() method from PageFactory Class, once we call initElements() method, all elements will get initialized.
    //Page Factory will initialize every WebElement variable with a reference to a corresponding element on the actual web page based on "locators" defined.
    // This is done by using @FindBy annotations.

}
