package utility;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.open;

public class WebPage {


    public static void initializeDriver() {
        Configuration.browser = "firefox";
        open("https://www.way2automation.com/angularjs-protractor/webtables/");
    }

    public static void validateUser(ElementsCollection element, String value) {
        element.shouldBe(CollectionCondition.sizeGreaterThan(0));
        element.shouldHave(CollectionCondition.anyMatch("Validate the user", s -> s.getText().equals(value)));
    }

    public static void deleteUser(ElementsCollection elements, String value, SelenideElement element) {
        Boolean flag = elements.stream().anyMatch(s -> s.getText().equalsIgnoreCase(value));
        if (flag) {
            element.click();
        }
    }

}
