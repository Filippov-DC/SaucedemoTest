package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public abstract class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        if (driver == null) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\danya\\Desktop\\msedgedriver\\msedgedriver.exe");
            
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            
            EdgeDriverService service = new EdgeDriverService.Builder()
                    .usingDriverExecutable(new File("C:\\Users\\danya\\Desktop\\msedgedriver\\msedgedriver.exe"))
                    .build();
            
            driver = new EdgeDriver(service, options);
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        }
    }
    
    @AfterMethod
    public void tearDown() {
    }
    
    @AfterSuite
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}