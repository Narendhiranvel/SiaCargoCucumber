package siacargo_stepdefinition;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TrackShipment extends BaseClass {
	String textbox2Value;

	@Given("Launch browser and load url and maximize screen")
	public void precondition() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.siacargo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@And("click Track Shipment button and the Track Shipment page will be loaded in seperate page")
	public void clickTrackShipmentButton() throws InterruptedException {
		String homePage = driver.getTitle();
		System.out.println("You are in -" + homePage + "- home page");
		driver.findElement(By.xpath("(//span[@class='icon-tag'])[2]")).click();
		Set<String> trackShipWindow = driver.getWindowHandles();
		List<String> trackShipWindow2 = new ArrayList<String>(trackShipWindow);
		driver.switchTo().window(trackShipWindow2.get(1));
		Thread.sleep(1000);

		String trackShipment = driver.getTitle();
		if (trackShipment.contains("Track Shipment")) {
			System.out.println("You have navigated to Track Shipment Page");
		} else {
			System.out.println("You are not in Track Shipment page ");
		}
	}

	@And("1st text box field of Air Waybill record should have 618 as default value and enter vaild waybill number in 2nd text box as {string}")
	public void enterWaybillNo(String wb1) throws InterruptedException {
		WebElement textbox1 = driver.findElement(By.xpath("//input[@id = 'Prefix1']"));
		String textBox1Value = textbox1.getAttribute("value");

		if (textBox1Value.equals("618")) {
			WebElement textbox2 = driver.findElement(By.xpath("//input[@id = 'Suffix1']"));
			textbox2.sendKeys(wb1);
			Thread.sleep(2000);
			textbox2Value = textbox2.getText();
		} else {
			WebElement textbox2 = driver.findElement(By.xpath("//input[@id = 'Suffix1']"));
			textbox2.sendKeys(wb1);
			driver.findElement(By.id("btnQuery")).click();

			WebElement awbNotFound = driver.findElement(By.xpath("//td[@class ='error-awb']"));
			String anf = awbNotFound.getText();
			System.out.println(anf);
		}
	}

	@When("clicked Submit button and shipment details page will be opened")
	public void clickSubmit() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("btnQuery")).click();
		Thread.sleep(1000);
	}

	@And("AWB number will be same as Air Waybill number entered on previous Track Shipment page")
	public void checkAwbNo() {
		WebElement awbNo = driver.findElement(By.xpath("//td[@class= 'size-14']"));
		String awbText = awbNo.getText();

		if (awbText.contains(textbox2Value)) {
			System.out.println(awbText + " Verified Successfully (AWB number and Air Waybill 1 number are same)");
		} else {
			System.out.println("Verification unsuccessful");
		}
	}
	@And("click New Search button")
	public void clickNewSearch() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("btnSearchAgain")).click();
		Thread.sleep(1000);
	}
	@And("previous Track Shipment page should be opened and it will have Air waybill 1 number")
	public void navigateToPrevious() {
		WebElement airWaybill1 = driver.findElement(By.id("Suffix1"));
		String getText = airWaybill1.getAttribute("value");
		if (getText.contains(textbox2Value)) {
			System.out.println("Navigated back to track shipment page");
		} else {
			System.out.println("Not Navigated correctly");
		}
	}
	@And("enter Air waybill 2 number as {string}")
	public void entervalidDataTwo(String wb2) {
		driver.findElement(By.id("Suffix2")).sendKeys(wb2);
	}

	@And("enter Air waybill 3 number as {string}")
	public void entervalidDataThree(String wb3) throws InterruptedException {
		driver.findElement(By.id("Suffix3")).sendKeys(wb3);
		Thread.sleep(1000);
	}

	@Then("navigate to Shipment Details page")
	public void navigateToShip() throws InterruptedException {
		WebElement errorMessage = driver.findElement(By.id("Label7"));
		String errorMess = errorMessage.getText();
		String errorMessage2 = errorMess.replace("*", "");
		if (errorMessage2.contains("Please check entries highlighted")) {
			System.out.println("Error message: " + errorMessage2);
		} else {
			driver.findElement(By.id("btnQuery")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
		}
	}

	@But("Error message should be displayed")
	public void errorMessage() throws InterruptedException {
		WebElement errorMessage = driver.findElement(By.id("Label7"));
		String errorMess = errorMessage.getText();
		String errorMessage2 = errorMess.replace("*", "");
		if (errorMessage2.contains("Please check entries highlighted")) {
			System.out.println("Error message: " + errorMessage2);
		} else {
			driver.findElement(By.id("btnQuery")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
		}
	}

	@And("close all tabs")
	public void closeAlltabs() throws InterruptedException {
		Thread.sleep(1000);
		driver.close();
		driver.quit();
	}
}
