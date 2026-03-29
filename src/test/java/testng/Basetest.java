package testng;



import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {

	WebDriver driver;
	
    ExtentReports extent;
    ExtentTest test;
    
    public String takeScreenshot(String testName){
    	
    	String path = System.getProperty("user.dir") + "/screenshots/" 
                + testName + "_" + System.currentTimeMillis() + ".png";

        //String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";

        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
        } catch(Exception e){
            e.printStackTrace();
        }

        return path;
    }

	
	@BeforeMethod
	public void setup(Method method) {
		 extent = ExtentManager.getReport();
	     test = extent.createTest(method.getName());
	        
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions ChromOpt = new ChromeOptions();
		
		ChromOpt.addArguments("--incognito");
		
		ChromOpt.addArguments("--headless");
		ChromOpt.addArguments("--no-sandbox");
		ChromOpt.addArguments("--disable-dev-shm-usage");
		
		driver = new ChromeDriver(ChromOpt);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void off(ITestResult result) {

	    if(result.getStatus() == ITestResult.FAILURE){

	        String screenshotPath = takeScreenshot(result.getName());

	        test.fail("Test Failed");
	        test.addScreenCaptureFromPath(screenshotPath);
	    }

	    else if(result.getStatus() == ITestResult.SUCCESS){
	        test.pass("Test Passed");
	    }

	    driver.quit();
	    extent.flush();
	}
	
	
	
	
}
