package tests.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.FormPage;

import static tests.TestData.expectedData;
import static tests.TestData.userData;

@Owner("f27")
@DisplayName("Form tests")
public class FormTests extends TestBase {
    FormPage formPage;

    @Test
    @Tag("positive")
    @DisplayName("Successful form test")
    void successfulFormTest() {
        formPage = new FormPage();
        formPage.openFormPage()
                .fillForm(userData)
                .checkData(expectedData);
    }

    @Test
    @Tag("negative")
    @DisplayName("Failed form test")
    void unsuccessfulFormTest() {
        formPage = new FormPage();
        formPage.openFormPage()
                .fillForm(userData)
                .closeModal()
                .checkData(expectedData);
    }
}
