package amazonSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.sql.*;



public class Amazon {
    static WebDriver driver;
    static ActionClass obj;

    public static void main(String args[]) throws InterruptedException, SQLException {

        //Amazon obj = new Amazon();
        launch_amazon_Application();
        //Read_Amazon_Search_Text_using_JDBC();
        Locate_WebElements();
        closeApplication();

    }

// Launch Amazon application
    public static void launch_amazon_Application() {

        System.setProperty("webdriver.chrome.driver", "C://Users//koda1anu//Downloads//chromedriver_win32 (3)//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        System.out.println("Amazon application launched successfully");
        obj = new ActionClass(driver);
    }
    //Database connectivity
    public static String Read_Amazon_Search_Text_using_JDBC() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "********");
            Statement stmt = conn.createStatement();
            // use the database

            stmt.execute("use ecommerce");

            ResultSet rs = stmt.executeQuery("select searchitem from amazon");
            String searchtext = null;

            while (rs.next()) {
                searchtext = rs.getString("searchitem");
                System.out.println("Search Text Item is --->" + searchtext);
            }

          return searchtext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Locate web elements
    public static void Locate_WebElements() throws InterruptedException, SQLException {

        obj.ClickAndWait(By.xpath(" //div[@id = 'nav-search-dropdown-card']"));
        Select selectObj = new Select(driver.findElement(By.xpath("//div[@id= 'nav-search-dropdown-card']/div/select")));
        selectObj.selectByVisibleText("Electronics");
        Thread.sleep(1000);
        Thread.sleep(1000);
        String searchText = Read_Amazon_Search_Text_using_JDBC();
        obj.TypeOnATextBox(By.id("twotabsearchtextbox"), searchText);
        obj.ClickAndWait(By.className("nav-right"));
        System.out.println("image captured with 24 items displaying");
               long itemCount = driver.findElements(By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])")).size();
        long price = driver.findElements(By.xpath("(//span[@class='a-price'])")).size();
               for (long i = 1, j = 1; i <= 24 && j <= price; i++, j++) {

            String itemDescription = driver.findElement(By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])[" + i + "]")).getText();
                        String itemPrice = driver.findElement(By.xpath("(//span[@class='a-price'])["+j+"]")).getText();
            System.out.println("Item Price: " + itemPrice + "    Item Description: " + itemDescription);

        }

    }
    //Close application
        public static void closeApplication () {

            driver.quit();
        }


    }
