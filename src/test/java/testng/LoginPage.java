package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

	WebDriver driver;
	
	By email = By.id("user-name");
	By password = By.id("password");
	By btnlogin = By.id("login-button");
	
	By addtocart = By.id("add-to-cart-sauce-labs-backpack");
	By cart = By.className("shopping_cart_link");
	By numberofcart = By.xpath("//span[@class='shopping_cart_badge']");
	
	By checkout = By.id("checkout");
	
	By fnamechekout = By.id("first-name");
	By lsanmechekout = By.id("last-name");
	By postalcode = By.id("postal-code");
	By btnContinue = By.id("continue");
	
	By BtnFinish =By.id("finish");
	
	By SuccesMsg = By.className("complete-header");
	By erreurmsg = By.xpath("//h3[@data-test='error']");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enteremail(String mail) {
		driver.findElement(email).sendKeys(mail);		
	}
	
	public void enterpass(String pass) {
		driver.findElement(password).sendKeys(pass);
	}
	
	public void cicklogin() {
		driver.findElement(btnlogin).click();
	}
	
	
	public void addtocartM() {
		driver.findElement(addtocart).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(numberofcart));
	}
	
	public void clickcart() {
		driver.findElement(cart).click();
	}
	
	
	public void clickchekout() {
		driver.findElement(checkout).click();
	}
	
	public void fname(String firstname) {
		driver.findElement(fnamechekout).sendKeys(firstname);
	}
	
	public void lname(String lastname) {
		driver.findElement(lsanmechekout).sendKeys(lastname);
	}
	
	public void Sendcodepostal(String codepostal) {
		driver.findElement(postalcode).sendKeys(codepostal);
	}
	
	public void ClickContinue() {
		driver.findElement(btnContinue).click();
	}
	
	public void ClickFinish() {
		driver.findElement(BtnFinish).click();
	}
	
	public boolean succesmessage() {
	    return driver.findElement(SuccesMsg).isDisplayed();
	}
	
	public boolean loginerreurmsg() {
		return driver.findElements(erreurmsg).size() > 0;
	}
	
	
	
}
