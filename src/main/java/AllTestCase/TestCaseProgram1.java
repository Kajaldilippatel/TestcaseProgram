package AllTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestCaseProgram1 {
  @Test
  //Test case to Register user successfully
    public  void userRegisteration()
    {
        String expectedRegisterSuccessMessage="Your registration completed";

        //Creating web driver object and open site
        System.setProperty("webdriver.chrome.driver","src\\main\\java\\AllTestCase\\chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");

        //find element and perform action
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("gender-female")).click();
        driver.findElement(By.id("FirstName")).sendKeys("FirstNameTest");
        driver.findElement(By.id("LastName")).sendKeys("LastNameTest");
        Select dateday= new Select(driver.findElement(By.name("DateOfBirthDay")));
        dateday.selectByIndex(3);
        Select month= new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("May");
        Select year= new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1920");

        //date function use to enter unique email id every time
        DateFormat dateFormat=new SimpleDateFormat("MMddyyyyHHmmss");
        Date date=new Date();
        String date1=dateFormat.format(date);
        driver.findElement(By.id("Email")).sendKeys("testing+"+date1+"@test.com");
        driver.findElement(By.id("Company")).sendKeys("TestingCompany");
        driver.findElement(By.id("Newsletter")).isSelected();
        driver.findElement(By.id("Password")).sendKeys("testing");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("testing");
        driver.findElement(By.id("register-button")).click();
        String actualRegisterSuccessMessage=driver.findElement(By.xpath("//div[@class='result']")).getText();

        //assert use to compare expected and actual result are same or not
        Assert.assertEquals("Register Test Case Fail",expectedRegisterSuccessMessage,actualRegisterSuccessMessage);
        driver.findElement(By.linkText("Log out")).click();
        driver.quit();
    }
   @Test
   //Test case user should be able to change currency successfully
    public void changeCurrency()
    {
            String expectedRegisterSuccessMessage="Euro";
            System.setProperty("webdriver.chrome.driver","src\\main\\java\\AllTestCase\\chromedriver.exe");
            WebDriver driver =new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();
            driver.get("https://demo.nopcommerce.com/");

            Select currency= new Select(driver.findElement(By.id("customerCurrency")));
            currency.selectByIndex(1);

            String actualRegisterSuccessMessage=driver.findElement(By.xpath("//div[@class='currency-selector']/select/option[2]")).getText();
            Assert.assertEquals("Currency Test Case Fail",expectedRegisterSuccessMessage,actualRegisterSuccessMessage);
           driver.quit();
    }
   @Test
   //Test case To verify error message that only register user can send product information by email a friend
    public void emailFriend()
   {
       String expectedRegisterSuccessMessage="Only registered customers can use email a friend feature";
       System.setProperty("webdriver.chrome.driver","src\\main\\java\\AllTestCase\\chromedriver.exe");
        WebDriver driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");

        driver.findElement(By.xpath("//div[@class='product-item'][@data-productid='4']/div/a/img")).click();
        driver.findElement(By.xpath("//div[@class='email-a-friend']/input")).click();
         driver.findElement(By.xpath("//input[@class='friend-email']")).sendKeys("Abcd@gmail.com");
        driver.findElement(By.xpath("//input[@class='your-email']")).sendKeys("abc1@gmail.com");
        driver.findElement(By.xpath("//textarea[@class='your-email']")).sendKeys("Hi");
        driver.findElement(By.xpath("//input[@name='send-email']")).click();

        String actualRegisterSuccessMessage=driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']/ul/li")).getText();
        Assert.assertEquals("Email Friend Test Case Fail",expectedRegisterSuccessMessage,actualRegisterSuccessMessage);
        driver.quit();
   }
}
