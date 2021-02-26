import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Flight_Search_Automation {

	static public WebDriverWait w;
	static public WebDriver driver;

	// li element output
	public static void selected(List<WebElement> li2, String button) {

		for (WebElement i : li2) {

			if (i.getAttribute("class").equalsIgnoreCase(button)) {
				System.out.println(i.getText() + " is Selected");
				break;

			}
		}
	}

	// li element click
	public static void click_button(List<WebElement> li1, String button) {

		for (WebElement i : li1) {

			if (i.getText().equalsIgnoreCase(button)) {
				i.click();
				break;
			}
		}
	}

	// click from-to-destination
	public static void location(String place, String path1, String searchWord) throws InterruptedException {
		WebElement x = driver.findElement(By.cssSelector(path1));
		x.clear();
		x.sendKeys(searchWord);

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='SUGGESTIONS ']")));
		
			List <WebElement> ele= driver.findElements(By.xpath("//div[@class='calc60']/p[1]"));
			System.out.println(ele.size());

			for(int i=0;i<ele.size();i++) {
				
				String text= ele.get(i).getText();
				System.out.println(text+ "i= "+i);
				if(text.contains(place)) {
					
					driver.findElements(By.xpath("//li[@role='option']/div[1]//div[@class='calc60']")).get(i).click();
					break;
				}
				
			}

	}

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\geckodriver.exe");

		driver = new ChromeDriver();
//		WebDriver driver= new FirefoxDriver();

		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Explicit wait
		w = new WebDriverWait(driver, 10);

		driver.get("https://www.makemytrip.com/");
		

		// actual title and urls
		String url_actual = "https://www.makemytrip.com/";
		String title_actual = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";

		// expected titles and urls
		String url_expected = driver.getCurrentUrl();
		String title_expected = driver.getTitle();

		// validating url and title
		Assert.assertEquals(url_actual, url_expected);
		Assert.assertEquals(title_actual, title_expected);
		
		//Handling modal box
		List <WebElement> zz=driver.findElements(By.cssSelector("div[data-cy='outsideModal']"));		
		
		if(zz.size()>0) {
			driver.findElement(By.cssSelector("div[data-cy='outsideModal']")).click();
			System.out.println("Modal showed and handled");
		}else {
			System.out.println("Modal not showed");
			
		}


		// selected button
		String class_select = "selected";

		List<WebElement> li = driver.findElements(By.cssSelector("[class='fswTabs latoBlack greyText'] li"));

		String way = "OneWay";

		selected(li, class_select);

		click_button(li, way);

		selected(li, class_select);

//		Thread.sleep(1000l);

		// From
		driver.findElement(By.cssSelector("[class='fsw_inputBox searchCity inactiveWidget ']")).click();

		String path1From = "input[placeholder='From']";
		String fromLoc = "dha";
		String placeFrom= "Dhaka";

		location(placeFrom, path1From,fromLoc);

		// To

		String path1To = "input[placeholder='To']";
		String toLoc = "mu";
		String placeTo = "Munich";

		location(placeTo, path1To,toLoc);

		// Dates- Current date
		driver.findElement(By.cssSelector(".DayPicker-Day.DayPicker-Day--selected")).click();

		// Travellers class and Passenger


		driver.findElement(By.cssSelector(".fsw_inputBox.flightTravllers.inactiveWidget ")).click();

		// adult number

		List<WebElement> adultLi = driver.findElements(
				By.xpath("//div[@class='appendBottom20'] //p[@data-cy='adultRange']/following-sibling::ul[1]/li"));


		String adult = ">9";
		click_button(adultLi, adult);
		selected(adultLi, class_select);

		// children number
		List<WebElement> childrenLi = driver
				.findElements(By.xpath("//div[@class='makeFlex column childCounter']/ul/li"));


		String children = ">6";
		click_button(childrenLi, children);
		selected(childrenLi, class_select);

		// Infant Number

		List<WebElement> infantLi = driver
				.findElements(By.cssSelector("div[class='makeFlex column pushRight infantCounter'] ul li"));


		String infant = "1";
		click_button(infantLi, infant);
		selected(infantLi, class_select);

		// Travel class
		List<WebElement> travel_class = driver
				.findElements(By.xpath("//p[@data-cy='adultRange']/following-sibling::ul[2]/li"));


		String tr_class = "Premium Economy";
		click_button(travel_class, tr_class);
		selected(travel_class, class_select);

		// apply button
		driver.findElement(By.cssSelector("button[data-cy='travellerApplyBtn']")).click();

		// search button
		driver.findElement(By.cssSelector(".primaryBtn.font24.latoBold.widgetSearchBtn ")).click();
		
		driver.close();

	}

}
