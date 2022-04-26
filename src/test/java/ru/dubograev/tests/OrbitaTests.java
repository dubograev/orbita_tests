package ru.dubograev.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.dubograev.helpers.DriverUtils;
import ru.dubograev.pages.Vacancies;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class OrbitaTests extends TestBase {
    Vacancies vacancies = new Vacancies();
    private final static String CAREER_URL = "/career";
    private final static String VACANCIES_URL = "/vacancies";

    @Test
    @AllureId("9689")
    @DisplayName("Vacancies test")
    void checkAmountOfVacanciesCards() {
        open(CAREER_URL);
        vacancies
                .clickOnButton("Присоединиться к команде")
                .checkAmountOfCards(5);
    }

//    @ParameterizedTest
//    @EnumSource(VacanciesCards.class)
//    void vacanciesCardNames(VacanciesCards title, VacanciesCards description) {
//        open(VACANCIES_URL);
//        $(byText(String.valueOf(title))).sibling(0).shouldHave(text(String.valueOf(description)));
//
////        ElementsCollection cards = $$("[class^='specialization-card__card']");
////        for (SelenideElement card : cards) {
////            $(byText(title).sibling(0).shouldHave(text(String.valueOf(description)));
////        }
//    }

    @AllureId("9687")
    @ParameterizedTest(name = "Card with title {0} has the following description: {1}")
    @CsvSource(value = {
            "Разработка; Java, JavaScript, BigData, DevOps, Architect",
            "Тестирование; QA auto (java), QA auto (python), QA manual",
            "Бэк-офис; Recruiter",
            "Аналитика; Бизнес-аналитик, Системный Аналитик",
            "Дизайн; UI/UX designer, графический дизайнер"
    }, delimiter = ';')
    void vacanciesCardNamesCSV(String title, String description) {
        open(VACANCIES_URL);
        $(byText(title)).sibling(0).shouldHave(text(description));
    }

    @AllureId("9688")
    @Test
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open test url " + Configuration.baseUrl, () ->
            open("/"));

        step("Page title should have text 'О компании - Карьера в Центре Орбита'", () -> {
            String expectedTitle = "О компании - Карьера в Центре Орбита";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @AllureId("9690")
    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open test url " + CAREER_URL, () ->
            open(CAREER_URL));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}