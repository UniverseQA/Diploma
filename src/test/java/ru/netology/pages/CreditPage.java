package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreditPage {

    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement creditTitle = $(byText("Кредит по данным карты"));
    private final SelenideElement continueButton = $x("//span[text()='Продолжить']");

    public void chooseCreditCard() {
        creditButton.click();
        creditTitle.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }
}