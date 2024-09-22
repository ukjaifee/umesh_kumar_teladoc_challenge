package com.teladoc.definitions;

import Pages.PageManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import utility.WebPage;

public class UserDefinition {

    PageManager pageManager = new PageManager();

    @Given("Click the add user button")
    public void add() {
        WebPage.initializeDriver();
        pageManager.landingPage().clickAddUserBtn();
    }

    @When("Add user details")
    public void addUserDetails(DataTable dataTable) {
        dataTable.asMaps().forEach(s -> {
            pageManager.landingPage().fillUserField(s.get("firstName"), s.get("firstNameValue"));
            pageManager.landingPage().fillUserField(s.get("lastName"), s.get("lastNameValue"));
            pageManager.landingPage().fillUserField(s.get("userName"), s.get("userNameValue"));
            pageManager.landingPage().fillUserField(s.get("passWord"), s.get("passWordValue"));
            pageManager.landingPage().fillUserField(s.get("email"), s.get("emailValue"));
            pageManager.landingPage().fillUserField(s.get("cellPhone"), s.get("cellPhoneValue"));
        });

    }

    @And("Select Customer and Role {string}")
    public void selectCustomerAndRole(String dropDown) {
        pageManager.landingPage().setSetDropDownValue(dropDown);
        pageManager.landingPage().selectRadioButton();
    }

    @And("Click the Save button")
    public void clickSaveButton() {
        pageManager.landingPage().clickSaveButton();
    }


    @Then("Validate the user {string}")
    public void validateTheUser(String addedUser){
        pageManager.landingPage().validateUser(addedUser);
    }

    @And("Click the {string}")
    public void clickOkButton(String buttonName) {
        pageManager.landingPage().clickOKButton(buttonName);
    }

    @When("Delete the user {string}")
    public void deleteUser(String userToDelete) {
        pageManager.landingPage().deleteUser(userToDelete);

    }

    @When("Validate the delete confirmation message {string}")
    public void validateDeleteConfirmationMessage(String confirmationMessage) {
        Assert.assertEquals(pageManager.landingPage().deleteConfirmationMsg(), confirmationMessage);
    }

}
