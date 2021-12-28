import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class UploadHTMLFile {

        @Test
        public void uploadIndexHTMLFile(){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.addArguments("--incognito");
            options.setHeadless(false);
        }
}
