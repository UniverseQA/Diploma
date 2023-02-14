package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StartPage {

    private final SelenideElement startTitle = $(byText("Путешествие дня"));
    private final SelenideElement debitButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement continueButton = $x("//span[text()='Продолжить']");

    public void openPage() {
        open("http://localhost:8080");
        startTitle.shouldBe(Condition.visible);
        debitButton.shouldBe(Condition.visible);
        creditButton.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.hidden);
    }
}