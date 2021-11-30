import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Set;

public class MobBrowserAutomation {

    public static void automateMobBrowser() {
        // Emulator and Simulators Configurations
        String chromedriver = "/Users/imostafa/SendingReports/src/test/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver",chromedriver);
        //Real Devices Configurations
//      WebDriverManager.chromedriver().clearDriverCache();
//      WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidPackage","com.android.chrome");//com.android.chrome
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://login.microsoftonline.com/");
        WebElement email= driver.findElement(By.id("i0116"));
        WebElement next = driver.findElement(By.id("idSIButton9"));
        email.clear();
        email.sendKeys("ihassanin@mafcarrefour.com");
        next.click();
    }
}
