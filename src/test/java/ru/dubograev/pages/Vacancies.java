package ru.dubograev.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class Vacancies {

    @Step("Click on 'Присоединиться к команде'")
    public Vacancies clickOnButton(String buttonName) {
            $(byText(buttonName)).click();
            return this;
    }

    @Step("Check the number of vacancies cards")
    public void checkAmountOfCards(int size) {
            $("[class^='specialization-list__list']").$$("[class^='specialization-card__card']")
                    .shouldHave(size(size));
    }
}
