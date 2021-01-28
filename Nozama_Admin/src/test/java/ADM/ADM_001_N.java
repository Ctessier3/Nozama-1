package ADM;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.Constants;
import Utilities.ExcelUtils;
import global_case.Connexion;

public class ADM_001_N {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	// Instanciation de la classe ExcelUtils
	static ExcelUtils excelUtils = new ExcelUtils();
	// Récupération du chemin complet du fichier de data
	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;
	// On instancie la classe Connexion afin de pouvoir exploiter les méthodes qui
	// la compose
	static Connexion connexion = new Connexion();

	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\cleme\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Récupération du document de données
		// Try permet d'essayer de réaliser l'action entre {}
		// Si l'action échoue, catch est appellé et affiche un message d'erreur, sinon
		// le script continue
		try {
			excelUtils.setExcelFile(excelFilePath, "loginAdmin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		// For démarre une boucle avec comme paramètres :
		// - la variable i qui débute à 0
		// - tant que i est plus petit que le nombre lignes du data sheet, on reste dans
		// la boucle
		// - i s'incrémente 1 à chaque passage dans la boucle
		for (int i = 0; i < excelUtils.getRowCountInSheet(); i++) {
			// On récupère le login à la ligne i qu'on implémente dans la variable login
			String login = excelUtils.getCellData(i + 1, 0);
			// On récupère le password à la ligne i qu'on implémente dans la variable
			// password
			String password = excelUtils.getCellData(i + 1, 1);
			try {
				// On appelle la méthode testGoogleSearch() appartenant à la classe instanciée
				// Connexion

				// On appelle la méthode testConnexion appartenant à la classe instanciée
				// Connexion avec en paramètres le login et le password demandé par les
				// arguments de la méthode
				connexion.testConnexion(driver, login, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Deconnexion
			try {
				connexion.testDeconnexion(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}