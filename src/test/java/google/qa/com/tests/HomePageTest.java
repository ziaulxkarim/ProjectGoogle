package google.qa.com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HomePageTest{
	
	private Page page;
	private Browser browser;
	private Playwright playwright;
	private BrowserContext browserContext;
	
	@Parameters("browser")
	@BeforeTest
	public void setup() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		browserContext = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
		page = browserContext.newPage();
	}
	
	@AfterTest
	public void tearDown() {
		browser.close();
	}
	
	@Test
	public void homePageTitleTest() {
		page.navigate("http://google.com");
		System.out.println(page.title());
		Assert.assertEquals(page.title(), "Google");
	}
	
	@Test
	public void homePageURLTest() {
		System.out.println(page.url());
		Assert.assertEquals(page.url(), "http://google.com");
	}
	
	@Test
	public void homePageLinkTest() {
		System.out.println(page.url());
		Assert.assertEquals(page.url(), "http://google.com");
	}
			
	@Test
	public void searchTest() {
		page.fill("#input", "Macbook");
		page.keyboard().press("Enter");
		System.out.println(page.title());
		Assert.assertEquals(page.title(), "macbook - Google Search");
	}
	
}
