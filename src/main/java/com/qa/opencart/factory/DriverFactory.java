package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optinonsManager;
	FileInputStream ip;
	
	
	/**
	 * Method is used to initialize the Driver
	 * @param browserName
	 * @return driver
	 */
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		optinonsManager = new OptionsManager(prop);
		
		System.out.println("the Browser name is " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//remote Execution
				init_remoteDriver("chrome");
				
			}else {
				//Local Execution
				WebDriverManager.chromedriver().setup();
				//driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver(optinonsManager.getChromeOptions()));
			}
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optinonsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optinonsManager.getEdgeOptions()));
		}
		
		else {
			System.out.println("Please provid the righ browser name" + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		
		
		return getDriver();
	}
	
	private void init_remoteDriver(String browserName) {
		
		System.out.println("Running the testst cases of Remote Machine Docker/ Cloud");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optinonsManager.getChromeOptions()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optinonsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}

	/**
	 * get the thread local copy of driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
    /**
     * Method is used to Initialize the Properties
     * 
     */
	
	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="naveen"
		String envName = System.getProperty("env");
		System.out.println("Running tests on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given....hence running it on QA enviornment");
			try {
				ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("running it on QA env");
					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("running it on stage env");
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "dev":
					System.out.println("running it on dev env");
					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "uat":
					System.out.println("running it on uat env");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					System.out.println("running it on QA env");
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					break;

				default:
					System.out.println("Please pass the right environment...." + envName);
					throw new FrameworkException("no env is found exception....");
				// break;
				}

			} catch (Exception e) {

			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
