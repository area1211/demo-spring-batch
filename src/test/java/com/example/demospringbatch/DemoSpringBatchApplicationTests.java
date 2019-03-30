package com.example.demospringbatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringBatchApplicationTests {

    final static String NAVER_URL = "https://www.naver.com";
    final static String ACCOUNT_ID = "inojng";
    final static String ACCOUNT_PASSWORD = "ruaend2sh";

    @Before
    public void before() {
        // 크롬 드라이버의 경로를 설정
//        System.setProperty("webdriver.chrome.driver", "{project-root}/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
    }

    @Test
    public void selenium_example3()  throws IOException {
        String keyword = "손흥민";
        WebDriver driver = new ChromeDriver();
        driver.get("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=" + keyword);

        // 스크린샷
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        byte[] imageByte = screenshot.getScreenshotAs(OutputType.BYTES);
        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/screenshot/" + keyword + ".png")) {
            fos.write(imageByte);
            fos.close();
        }

        // 드라이버 종료
        driver.quit();

    }
    @Test
    public void selenium_example2()  throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get(NAVER_URL); // 로그인 페이지로 이동 합니다.
        WebElement id = driver.findElement(By.id("id"));
        id.clear();
        id.sendKeys(ACCOUNT_ID); // 아이디 입력 필드에 '아이디'를 입력 합니다.
        WebElement pw = driver.findElement(By.id("pw"));
        pw.clear();
        pw.sendKeys(ACCOUNT_PASSWORD); // 비빌번호 입력 필드에 '비밀번호'를 입력 합니다.
        WebElement button = driver.findElement(By.cssSelector("#login > form > fieldset > button"));
        button.submit(); // Form 전송.
        // Form 전송 후 로그인 완료 페이지로 이동하여 페이지가 완전히 랜더링 될 때 까지 대기 헙니다.
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("로그인 완료 URL"));
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) d ->
                ((JavascriptExecutor) d).executeScript("return jQuery.active == 0").equals(Boolean.TRUE));
        driver.get("Scraping 대상 URL"); // Scraping 할 페이지로 이동 합니다.
        System.out.println(driver.getPageSource()); // 페이지 소스를 Scraping 합니다.
        driver.quit(); // 브라우저 종료

    }
    @Test
    public void selenium_example()  throws IOException {




        // 드라이버 실행
        WebDriver driver = new ChromeDriver();
        driver.get(NAVER_URL);

        // 로그인
        WebElement accountElement = driver.findElement(By.id("account"));
        WebElement idTextBox = accountElement.findElement(By.id("id"));
        idTextBox.sendKeys(ACCOUNT_ID);
        WebElement pwTextBox = accountElement.findElement(By.id("pw"));
        pwTextBox.sendKeys(ACCOUNT_PASSWORD);
        WebElement loginSpan = accountElement.findElement(By.className("btn_login"));
        WebElement loginButton = loginSpan.findElement(By.tagName("input"));
        loginButton.click();

        // 브라우져 등록 페이지
        WebElement registBrowser = driver.findElement(By.id("frmNIDLogin")).findElement(By.className("login_form")).findElement(By.className("btn_upload")).findElement(By.tagName("a"));
        registBrowser.click();
        WebElement loginPersistButton = driver.findElement(By.id("login_maintain")).findElement(By.className("btn_maintain")).findElement(By.tagName("a"));
        loginPersistButton.click();

        // 네이버 미니미
        WebElement minimeElement = driver.findElement(By.id("minime"));
        String minimeURL = minimeElement.getAttribute("src");
        System.out.println(minimeURL);

        // 스크린샷
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        byte[] imageByte = screenshot.getScreenshotAs(OutputType.BYTES);
        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/screenshot.png")) {
            fos.write(imageByte);
            fos.close();
        }

        // 드라이버 종료
        driver.quit();

    }

    @Test
    public void contextLoads() {
        System.out.println("셀레니움 테스트 해보자");
    }

}
