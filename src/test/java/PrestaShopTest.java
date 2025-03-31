import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class PrestaShopTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            registerUser(driver);
            contactUsForm(driver);
        } finally {
            driver.quit();
        }
    }

    public static void registerUser(WebDriver driver) {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive"))); // Mudança para o iframe principal
        driver.findElement(By.cssSelector(".user-info a")).click(); // Clicar no botão de Sign in
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(By.cssSelector(".no-account a")).click(); // Clicar no botão de Create Account
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        driver.findElement(By.name("id_gender")).click(); // Selecionar gênero
        driver.findElement(By.name("firstname")).sendKeys("Gabriel");
        driver.findElement(By.name("lastname")).sendKeys("Borel");
        driver.findElement(By.name("email")).sendKeys("gabrielborel@hotmail.com");
        driver.findElement(By.name("password")).sendKeys("Borel2219");
        driver.findElement(By.name("birthday")).sendKeys("25/09/2000");
        driver.findElement(By.name("optin")).click(); // Newsletter
        driver.findElement(By.name("psgdpr")).click(); // Termos

        driver.findElement(By.cssSelector(".form-footer button")).click(); // Botão de submissão do formulário
        System.out.println("Usuário registrado com sucesso!");
    }

    public static void contactUsForm(WebDriver driver) {
        driver.get("https://demo.prestashop.com/#/en/front");
        driver.switchTo().frame(driver.findElement(By.id("framelive")));
        driver.findElement(By.linkText("Contact us")).click(); // Abrir formulário de contato

        Select subjectSelect = new Select(driver.findElement(By.name("id_contact")));
        subjectSelect.selectByVisibleText("Customer service");

        driver.findElement(By.name("from"))
                .sendKeys("testuser123@example.com"); // Email
        driver.findElement(By.name("message"))
                .sendKeys("Este é um teste de envio de formulário usando Selenium."); // Mensagem
        driver.findElement(By.cssSelector("input[type='submit']"))
                .click(); // Enviar formulário

        System.out.println("Formulário de contato enviado com sucesso!");
    }
}

