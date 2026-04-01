package testng;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Basetest{
	
	/*@DataProvider(name="loginData")
    public Object[][] getData() {

        return new Object[][]{

            {"standard_user", "secret_sauce","firstname","lastname","10000",true},
            {"problem_user", "secret_sauce","firstname","lastname","10000",true},
            {"performance_glitch_user", "secret_sauce","firstname","lastname","10000",true},
            {"wroguser", "wrongpass","firstname","lastname","10000",false}

        };
    }*/
	
	@DataProvider(name="excelData")
	public Object[][] getExcelData(){

	    String path = System.getProperty("user.dir") + "/TestData/Classeur1.xlsx";

	    return ExcelUtils.getExcelData(path, "Feuil1");
	}

	@Test(dataProvider = "excelData")
	public void logintest(String username, String password,
	                      String firstname, String lastname,
	                      String postal, String validStr)  throws InterruptedException {
		boolean isValid = Boolean.parseBoolean(validStr);
		
		test.info("Opening website");
		
		driver.get("https://www.saucedemo.com/");
		
		LoginPage login = new LoginPage(driver);
		
		test.info("Entering credentials");
		login.enteremail(username);
		login.enterpass(password);
		
		test.info("Click login");
		login.cicklogin();
		
		if(isValid) {
			test.info("Valid login → continue flow");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
			wait.until(ExpectedConditions.elementToBeClickable(login.addtocart));
		
		
		
		
		test.info("addtocart");
		login.addtocartM();
		
		test.info("clickcart");
		login.clickcart();
		
		test.info("click-chekout");
		login.clickchekout();
		
		test.info("Entering chekout infos");
		login.fname(firstname);
		login.lname(lastname);
		login.Sendcodepostal(postal);
		
		test.info("click continue");
		login.ClickContinue();
		
		test.info("click finich");
		login.ClickFinish();
		
		test.info("Checking success message");
		Assert.assertTrue(login.succesmessage());
		
		test.pass("Test passed successfully");
		}
		
		else {
			test.info("invalid login = erreur message");
			Assert.assertTrue(login.loginerreurmsg());
			test.pass("Message d'erreur affiché correctement");
		}
	}
}
