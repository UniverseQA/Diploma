package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
public class TicketBuyingPage {

    private final SelenideElement debitTitle = $(byText("Оплата по карте"));
    private final SelenideElement creditTitle = $(byText("Кредит по данным карты"));

    private final SelenideElement debitButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement continueButton = $x("//span[text()='Продолжить']");

    private final SelenideElement cardNumberInput = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthInput = $("input[placeholder='08'");
    private final SelenideElement yearInput = $("input[placeholder='22'");
    private final SelenideElement ownerInput = $x("//span[text()='Владелец']/../../..//input");
    private final SelenideElement cvcInput = $("input[placeholder='999'");

    private final SelenideElement cardNumberError = $x("//*[text()='Номер карты']/..//*[@class='input__sub']");
    private final SelenideElement monthError = $x("//*[text()='Месяц']/..//*[@class='input__sub']");
    private final SelenideElement yearError = $x("//*[text()='Год']/..//*[@class='input__sub']");
    private final SelenideElement ownerError = $x("//*[text()='Владелец']/..//*[@class='input__sub']");
    private final SelenideElement cvcError = $x("//*[text()='CVC/CVV']/..//*[@class='input__sub']");
    private final SelenideElement notificationError = $x("//div[contains(@class, 'notification_status_error')]");

    private final SelenideElement notificationSuccess = $(".notification_status_ok")
            .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(7));

    public void chooseDebitCard() {
        debitButton.click();
        debitTitle.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }

    public void chooseCreditCard() {
        creditButton.click();
        creditTitle.shouldBe(Condition.visible);
        continueButton.shouldBe(Condition.visible);
    }

    public void sendDataInForm(String number, String month, String year, String owner, String cvc) {
        cardNumberInput.setValue(number);
        monthInput.setValue(month);
        yearInput.setValue(year);
        ownerInput.setValue(owner);
        cvcInput.setValue(cvc);
        continueButton.click();
    }

    public void approved() {
        notificationSuccess.shouldBe(Condition.visible);
    }

    public void declined() {
        notificationError.shouldBe(Condition.visible);
    }

    public void monthError() {
        cardNumberError.shouldBe(Condition.hidden);
        monthError.shouldBe(Condition.visible);
        yearError.shouldBe(Condition.hidden);
        ownerError.shouldBe(Condition.hidden);
        cvcError.shouldBe(Condition.hidden);
    }
    public void cardNumberError() {
        cardNumberError.shouldBe(Condition.visible);
        monthError.shouldBe(Condition.hidden);
        yearError.shouldBe(Condition.hidden);
        ownerError.shouldBe(Condition.hidden);
        cvcError.shouldBe(Condition.hidden);
    }

    public void yearError() {
        cardNumberError.shouldBe(Condition.hidden);
        monthError.shouldBe(Condition.hidden);
        yearError.shouldBe(Condition.visible);
        ownerError.shouldBe(Condition.hidden);
        cvcError.shouldBe(Condition.hidden);
    }

    public void ownerError() {
        cardNumberError.shouldBe(Condition.hidden);
        monthError.shouldBe(Condition.hidden);
        yearError.shouldBe(Condition.hidden);
        ownerError.shouldBe(Condition.visible);
        cvcError.shouldBe(Condition.hidden);
    }

    public void cvcError() {
        cardNumberError.shouldBe(Condition.hidden);
        monthError.shouldBe(Condition.hidden);
        yearError.shouldBe(Condition.hidden);
        ownerError.shouldBe(Condition.hidden);
        cvcError.shouldBe(Condition.visible);
    }
}