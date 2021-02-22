import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Flight_Search_Automation {

	
	//li element output
	public static void selected(List<WebElement> li2, String button) {
		int j=0;
		int k=0;

		for (WebElement i : li2) {
			k++;
			if (i.getAttribute("class").equalsIgnoreCase(button)) {
				j=1;
				System.out.println(i.getText() + " is Selected");
				break;
				
			}
		}
//		System.out.println(j+""+k);
	}
	

	//li element click
	public static void click_button(List<WebElement> li1, String button) {


		for(WebElement i: li1) {
			
			if(i.getText().equalsIgnoreCase(button)) {
				i.click();
				break;
			}
	}
	}
	
	//click from-to-destination
	public static void location(WebDriver driver,String path1,String path2, String searchWord ) throws InterruptedException {
		Thread.sleep(2000l);
		WebElement x= driver.findElement(By.cssSelector(path1));
		x.clear();
		x.sendKeys(searchWord);
		Thread.sleep(5000l);
		
		driver.findElement(By.id(path2)).click();
		
	}

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\geckodriver.exe");

		WebDriver driver = new ChromeDriver();
//		WebDriver driver= new FirefoxDriver();

		driver.manage().window().maximize();

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
		
		//selected button
		String class_select = "selected";

		List<WebElement> li = driver.findElements(By.cssSelector("[class='fswTabs latoBlack greyText'] li"));
		
		String city="OneWay";
//		System.out.println("Total elements: " + li.size());
		
		selected(li,class_select);
		
//		Thread.sleep(3000l);
		
		click_button(li,city);
		
		selected(li,class_select);
		


		Thread.sleep(1000l);


		//From
		driver.findElement(By.cssSelector("[class='fsw_inputBox searchCity inactiveWidget ']")).click();
		
		String path1From="input[placeholder='From']";
		String fromLoc="dha";
		String path2From="react-autowhatever-1-section-0-item-2";
		
		location(driver,path1From,path2From,fromLoc);
		
		//To
		
		String path1To="input[placeholder='To']";
		String toLoc="mu";
		String path2To="react-autowhatever-1-section-0-item-2";
		
		location(driver,path1To,path2To,toLoc);
		
		
		//Dates- Current date
		driver.findElement(By.cssSelector(".DayPicker-Day.DayPicker-Day--selected")).click();
		
		//Travellers class and Passenger
		
		Thread.sleep(1000l);
		
		driver.findElement(By.cssSelector(".fsw_inputBox.flightTravllers.inactiveWidget ")).click();
		
		Thread.sleep(2000l);
		
		
		//adult number
		
		List<WebElement> adultLi=  driver.findElements(By.xpath("//div[@class='appendBottom20'] //p[@data-cy='adultRange']/following-sibling::ul[1]/li"));
		
//		System.out.println("adult: "+adultLi.size());
		
		String adult= ">9";
		click_button(adultLi,adult);
		selected(adultLi,class_select);
		
		//children number
		List<WebElement> childrenLi=  driver.findElements(By.xpath("//div[@class='makeFlex column childCounter']/ul/li"));
		
//		System.out.println("child: "+childrenLi.size());
		
		String children= ">6";
		click_button(childrenLi,children);
		selected(childrenLi,class_select);
		
		//Infant Number
		
		List<WebElement> infantLi=  driver.findElements(By.cssSelector("div[class='makeFlex column pushRight infantCounter'] ul li"));
		
//		System.out.println("infant: "+infantLi.size());
		
		String infant= "1";
		click_button(infantLi,infant);
		selected(infantLi,class_select);
		
		//Travel class
		List<WebElement> travel_class=  driver.findElements(By.xpath("//p[@data-cy='adultRange']/following-sibling::ul[2]/li"));
		
//		System.out.println("class: "+travel_class.size());
		
		String tr_class= "Premium Economy";
		click_button(travel_class,tr_class);
		selected(travel_class,class_select);
		
		Thread.sleep(2000l);
		//apply button
		driver.findElement(By.cssSelector("button[data-cy='travellerApplyBtn']")).click();
		
		//search button
		driver.findElement(By.cssSelector(".primaryBtn.font24.latoBold.widgetSearchBtn ")).click();
		
	}

}
