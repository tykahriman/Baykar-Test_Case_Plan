package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private Driver(){

    }
    static WebDriver driver;
    ChromeOptions ops = new ChromeOptions();
    public static WebDriver getDriver(){
        String browser=ConfigReader.getProperty("browser");
        if (driver==null) {

            switch (browser){
                case "chrome":
                    ChromeOptions ops = new ChromeOptions();
                    ops.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver(ops);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        }

        return driver;
    }

    public static void closeDriver() {
        if (driver!=null)
        {
            driver.close();
            driver=null;
        }

    }
    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver=null;
        }
    }

}
