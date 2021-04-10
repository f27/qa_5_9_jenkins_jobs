package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FormPage {
    private static final SelenideElement
            formTitleField = $(".practice-form-wrapper"),
            firstNameField = $("#firstName"),
            lastNameField = $("#lastName"),
            emailField = $("#userEmail"),
            genderField = $("#genterWrapper"),
            mobileField = $("#userNumber"),
            datePickerField = $("#dateOfBirthInput"),
            yearField = $(".react-datepicker__year-select"),
            monthField = $(".react-datepicker__month-select"),
            subjectsInput = $("#subjectsInput"),
            firstSubject = $("#react-select-2-option-0"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressField = $("#currentAddress"),
            stateField = $("#state"),
            cityField = $("#city"),
            submitButton = $("#submit"),
            closeModalButton = $("#closeLargeModal");
    private static final String
            classOfDay = ".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)",
            dataTable = ".table-responsive tbody tr",
            dataTd = "td";

    @Step("Open page with form")
    public FormPage openFormPage() {
        open("/automation-practice-form");

        return this;
    }

    @Step("Fill form")
    public FormPage fillForm(Map<String, String> userData) {
        formTitleField.shouldHave(text(userData.get("Form Title")));
        firstNameField.setValue(userData.get("First Name"));
        lastNameField.setValue(userData.get("Last Name"));
        emailField.setValue(userData.get("Email"));
        genderField.$(byText(userData.get("Gender"))).scrollTo().click();
        mobileField.setValue(userData.get("Mobile"));
        datePickerField.scrollTo().click();
        fillDatePicker(userData.get("Year Of Birth"), userData.get("Month Of Birth"), userData.get("Day Of Birth"));
        for (String subject : userData.get("Subjects").split(", ")) {
            addSubject(subject);
        }
        for (String hobby : userData.get("Hobbies").split(", ")) {
            addHobby(hobby);
        }
        pictureUpload.uploadFromClasspath(userData.get("Picture"));
        addressField.setValue(userData.get("Address"));
        stateField.scrollTo().click();
        stateField.find(byText(userData.get("State"))).scrollTo().click();
        cityField.scrollTo().click();
        cityField.find(byText(userData.get("City"))).scrollTo().click();
        submitButton.scrollTo().click();

        return this;
    }

    @Step("Close modal window")
    public FormPage closeModal() {
        closeModalButton.scrollIntoView(true).click();

        return this;
    }

    @Step("Check data")
    public void checkData(Map<String, String> expectedData) {
        Map<String, String> actualData = getTableData();

        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            assertThat(actualData.get(entry.getKey())).isEqualTo(entry.getValue());
        }

    }

    private static Map<String, String> getTableData() {
        Map<String, String> returnMap = new HashMap<>();
        for (SelenideElement element : $$(FormPage.dataTable).snapshot()) {
            returnMap.put(element.$(FormPage.dataTd).getText(), element.$(FormPage.dataTd, 1).getText());
        }
        return returnMap;
    }

    private static void fillDatePicker(String year, String month, String day) {
        monthField.selectOption(month);
        yearField.selectOption(year);
        $(String.format(classOfDay, day)).scrollTo().click();
    }

    private static void addSubject(String subject) {
        subjectsInput.setValue(subject);
        firstSubject.scrollTo().click();
    }

    private static void addHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).scrollTo().click(usingJavaScript());
    }

}