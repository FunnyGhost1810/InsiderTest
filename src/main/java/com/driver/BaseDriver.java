package com.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Data
@Getter
@Setter
public class BaseDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    private String URL = "https://useinsider.com/";
    private static int TIMEOUT = 30;


    public BaseDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public void get(String URL){
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
    }

    public void quit(){
        driver.quit();
    }
}
