package SeleniumPractice;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Practice {
    WebDriver driver;
    String baseUrl = "https://www.letskodeit.com/practice";

    @BeforeClass
    public void startDriver() throws Exception{
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void dropDownTest() throws Exception{
        WebElement cardropDown = driver.findElement(By.xpath("//select[@id='carselect']"));
        Select sel = new Select(cardropDown);
        System.out.println(sel.getFirstSelectedOption());

        sel.selectByIndex(1);
        sel.selectByVisibleText("Honda");
        sel.selectByValue("bmw");
        Thread.sleep(2000);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/Screenshots" + "testAmit2"+".png"));

        List<WebElement> selectedOptions = sel.getAllSelectedOptions();
        for(WebElement option : selectedOptions){
            System.out.println(option);
        }



    }
    @Test
    public void openWindowTest() throws Exception{
        WebElement openwindowBtn = driver.findElement(By.xpath("//button[@id='openwindow']"));
        openwindowBtn.click();

        String parentHandle = driver.getWindowHandle();

        Set<String>handles = driver.getWindowHandles();
        for(String handle: handles){
            System.out.println(handle);
            if(!handle.equals(parentHandle)){
                driver.switchTo().window(handle);
                WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
                searchBox.sendKeys("python");
                Thread.sleep(2000);
                WebElement searchBtn = driver.findElement(By.xpath("//button[@type='submit']"));
                searchBtn.click();
                Thread.sleep(2000);
                File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file,new File("src/test/Screenshots" + "testAmit3"+".png"));
                driver.close();

            }
        }

    }
    @AfterClass
    public void tearDown() throws Exception{
        driver.quit();
    }
}
