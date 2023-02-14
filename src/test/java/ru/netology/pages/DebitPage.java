package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DebitPage {

    private final SelenideElement debitButton = $(byText("Купить"));
    private final SelenideElement debitTitle = $(byText("Оплата по карте"));
    private final SelenideElement continueButton = $x("//span[text()='Продолжить']");

    public void chooseDebitCard() {
        debitButton.click();
        debitTitle.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }
}