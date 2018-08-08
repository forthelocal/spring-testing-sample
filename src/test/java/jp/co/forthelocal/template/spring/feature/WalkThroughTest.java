package jp.co.forthelocal.template.spring.feature;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import jp.co.forthelocal.template.spring.application.Application;
import jp.co.forthelocal.template.spring.test.Bootstrap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static com.codeborne.selenide.Selenide.screenshot;

@ExtendWith({Bootstrap.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class WalkThroughTest {

	private static WebDriver driver;

	@LocalServerPort
	private int randomServerPort;

	private String baseUrl;

	@BeforeAll
	static void setup() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");

		driver = new ChromeDriver(chromeOptions);
		WebDriverRunner.setWebDriver(driver);
	}

	@AfterAll
	static void teardown() {
		WebDriverRunner.closeWebDriver();
	}

	@BeforeEach
	void beforeEach() {
		this.baseUrl = "http://localhost:" + randomServerPort;
	}

	@Test
	@DisplayName("/でHello Worldが出力される")
	void home() {
		open(baseUrl);
		$("*").shouldHave(text("Hello World"));
	}

	@Test
	@DisplayName("/greetingでnameで指定した値が出力される")
	void greeting() {
		open(baseUrl + "/greeting?name=foo");
		$("*").shouldHave(text("foo"));
		screenshot("name_is_foo");


		open(baseUrl + "/greeting?name=bar");
		screenshot("name_is_bar");
		$("*").shouldHave(text("bar"));
		$("*").shouldNotHave(text("foo"));
	}
}
