package global_case;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Objects;

import Utilities.Constants;
import Utilities.ExcelUtils;

public class Connexion {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\cleme\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	// La méthode testConnexion attend en arguments deux paramètres : login & password
	public void testConnexion(WebDriver driver, String login, String password) throws Exception {
			String URL = Constants.URL;
			driver.get(URL);
			isElementPresent(driver, By.id("edit-name"));
			isElementPresent(driver, By.id("edit-pass"));
			isElementPresent(driver, By.linkText("Se connecter"));
			WebElement editName = driver.findElement(By.id("edit-name"));
			WebElement editPass = driver.findElement(By.id("edit-pass"));
			WebElement logButton = driver.findElement(By.linkText("Se connecter"));
			editName.clear();
			// On utilise variable login et password créés précedemment contenant les informations de connexion
			editName.sendKeys(login);
			editPass.clear();
			editPass.sendKeys(password);
			String loginInput = editName.getAttribute("value");
			String passwordInput = editPass.getAttribute("value");
			if (!Objects.equal(loginInput, login) || loginInput.length() == 0 ) {
				System.out.println("login faux" + login + loginInput);
				assert false;
			}
			if (!Objects.equal(passwordInput, password) || passwordInput.length() == 0 ) {
				System.out.println("password faux");
				assert false;
			}
			logButton.click();
			
		}
	@Test
	public void testDeconnexion(WebDriver driver) throws Exception {
		driver.findElement(By.linkText("Se déconnecter")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
 
	private boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
