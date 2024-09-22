package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utility.WebPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LandingPage extends WebPage {

    private final SelenideElement ADD_USER_BTN = $x("//button[@type='add']");
    private final String FILL_FIELD = "//input[@name='%s']";
    private final SelenideElement SET_CUSTOMER = $x("//input[@type='radio']");
    private final ElementsCollection SET_DROP_DOWN_VALUE = $$x("//tbody/tr/td/select/option");
    private final SelenideElement CLICK_SAVE = $x("//button[@class='btn btn-success']");
    private final ElementsCollection VALIDATE_USER = $$x("//tbody/tr/td");
    private final SelenideElement DELETE_USER = $x("//button[@ng-click='delUser()']");
    private final SelenideElement DELETE_CONF_MSG = $x("//p[contains(text(),'Do you really want to delete the user?')]");
    private final String CONF_MES_BTN = "//button[text()='%s']";

    public void fillUserField(String fieldNames, String values) {

        $x(String.format(FILL_FIELD, fieldNames)).shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(values);

    }

    public void clickAddUserBtn() {
        ADD_USER_BTN.shouldBe(Condition.visible).click();
    }

    public void selectRadioButton() {
        SET_CUSTOMER.shouldBe(Condition.visible).click();
    }

    public void setSetDropDownValue(String value) {
        SET_DROP_DOWN_VALUE.stream().filter(s -> s.getText().equalsIgnoreCase(value))
                .forEach(s -> s.click());
    }

    public void clickSaveButton() {
        CLICK_SAVE.click();
    }

    public void validateUser(String value)  {
        validateUser(VALIDATE_USER, value);
    }


    public void deleteUser(String value) {
        deleteUser(VALIDATE_USER, value, DELETE_USER);

    }

    public String deleteConfirmationMsg() {
        return DELETE_CONF_MSG.shouldBe(Condition.visible).getText().trim();
    }

    public void clickOKButton(String buttonName) {
        $x(String.format(CONF_MES_BTN, buttonName)).shouldBe(Condition.visible).click();


    }

}
