package rahulshettyacademy.SeleniumFrameworkDesign.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public  LandingPage landingPage;
	public WebDriver IntializeDriver() throws IOException {
		//Properties class
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!= null ?  System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions chromeOption=new ChromeOptions();
			 if(browserName.contains("headless")) {
			 chromeOption.addArguments("headless");
			 }
			 driver=new ChromeDriver(chromeOption);
			 driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		 driver=IntializeDriver();
		 landingPage=new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String Filepath) throws IOException {
		//read Json to String
		String JsonContent=FileUtils.readFileToString(new File(Filepath),
				StandardCharsets.UTF_8);
		
		//convert String to HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		//{map,map}
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports"+testcaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports"+testcaseName+".png";
	}
	
}
