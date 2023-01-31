package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.pages.TicketBuyingPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;
import static ru.netology.data.DbHelper.*;

public class TicketBuyingTest {

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
//        Configuration.headless = true;
        open("http://localhost:8080");
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        databaseCleanUp();
    }

    @Nested
    public class PositiveScenarios {

        @Test
        @SneakyThrows
        @DisplayName("№1 Buying with a valid debit card")
        public void shouldBuyWithValidCard() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            var expected = "APPROVED";
            var paymentInfo = getPaymentInfo();
            var orderInfo = getOrderInfo();
            assertEquals(expected, paymentInfo.getStatus());
            assertEquals(paymentInfo.getTransaction_id(), orderInfo.getPayment_id());
            ticketBuyingPage.approved();
        }


        @Test
        @SneakyThrows
        @DisplayName("№2 Buying in credit with a valid card")
        public void shouldBuyWithCreditValidCard() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseCreditCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            var expected = "APPROVED";
            var creditInfo = getCreditRequestInfo();
            var orderInfo = getOrderInfo();
            assertEquals(expected, creditInfo.getStatus());
            assertEquals(creditInfo.getId(), orderInfo.getCredit_id());
            ticketBuyingPage.approved();
        }
    }

    @Nested
    public class DeclinedCard {

        @Test
        @SneakyThrows
        @DisplayName("№3 Buying with a declined debit card")
        public void buyWithDeclinedCard() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            var expected = "DECLINED";
            var paymentInfo = getPaymentInfo();
            var orderInfo = getOrderInfo();
            assertEquals(expected, paymentInfo.getStatus());
            assertEquals(paymentInfo.getTransaction_id(), orderInfo.getPayment_id());
            ticketBuyingPage.declined();
        }

        @Test
        @SneakyThrows
        @DisplayName("№4 Buying in credit with a declined card")
        public void buyCreditDeclinedCard() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseCreditCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            var expected = "DECLINED";
            var paymentInfo = getCreditRequestInfo();
            var orderInfo = getOrderInfo();
            assertEquals(expected, paymentInfo.getStatus());
            assertEquals(paymentInfo.getBank_id(), orderInfo.getPayment_id());
            ticketBuyingPage.declined();
        }
    }

    @Nested
    public class CardNumberField {

        @Test
        @SneakyThrows
        @DisplayName("№5 13 digits in the card number field")
        public void thirteenDigitsCardNumber() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(getCardNumberWith13Digits(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.cardNumberError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№6 16 zero in the card number field")
        public void sixteenZeroCardNumber() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(getCardNumberWith16Zero(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            ticketBuyingPage.declined();
        }

        @Test
        @SneakyThrows
        @DisplayName("№7 Not existing card number is in the card number field")
        public void invalidCardNumber() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(getInvalidCardNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            TimeUnit.SECONDS.sleep(5);
            ticketBuyingPage.declined();
        }

        @Test
        @SneakyThrows
        @DisplayName("№8 The card number field is empty")
        public void shouldAppearCardNumberError() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(getEmptyCardNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.cardNumberError();
        }
    }

    @Nested
    public class MonthField {

        @Test
        @SneakyThrows
        @DisplayName("№9 The figure more than 12 is in the month field")
        public void MoreThan12InMonth() {
            var cardData = getValidDeclinedCard();
            var month = DataGenerator.getTwoDigitsGreaterThan12();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), month, cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.monthError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№10 Figure 0 is in the month field")
        public void zeroDigitInMonth() {
            var cardData = getValidDeclinedCard();
            var month = getZeroDigit();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), month, cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.monthError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№11 Two 0 are in the month field")
        public void twoZeroInMonth() {
            var cardData = getValidDeclinedCard();
            var month = getTwoZeroDigits();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), month, cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.monthError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№12 A digit is in the month field")
        public void oneDigitInMonth() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), getOneDigit(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.monthError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№13 The month field is empty")
        public void shouldAppearMonthError() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), getEmptyMonth(), cardData.getYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.monthError();
        }
    }

    @Nested
    public class YearField {

        @Test
        @SneakyThrows
        @DisplayName("№14 Previous years are in year field")
        public void oldYear() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), getYear(-1),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.yearError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№15 Two zero are in year field")
        public void twoZeroYear() {
            var cardData = getValidApprovedCard();
            var year = "00";
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), year,
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.yearError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№16 Previous month and current year are in their fields")
        public void previousMonthAndCurrentYear() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), getMonth(-1), getYear(0),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.yearError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№17 The Year greater than the current year on 6 is in year field")
        public void greatestYear() {
            var cardData = getValidApprovedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), getTwoDigitsGreaterThan28(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.yearError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№18 The year field is empty")
        public void shouldAppearYearError() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), getEmptyYear(),
                    cardData.getOwner(), cardData.getCvc());
            ticketBuyingPage.yearError();
        }
    }

    @Nested
    public class CardholdersNameField {

        @Test
        @SneakyThrows
        @DisplayName("№19 Figures are in cardholder's name")
        public void cardholderFigures() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithFigures(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№20 Symbols are in cardholder's name")
        public void cardholderSymbols() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithSymbols(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№21 Cardholder's name is on Cyrillic")
        public void cardholderOnCyrillic() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithCyrillic(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№22 Cardholder's name is on lower case")
        public void cardholderLowerCase() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithLatinLowerCase(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№23 Cardholder's name is on capital letters")
        public void cardholderCapitalLetters() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithCapitalLetters(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№24 Cardholder's name is greater than 85 symbols")
        public void cardholderGreaterThan85() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    getOwnerWithLatinUpperCaseMoreThan85Symbols(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№25 The cardholder's name field is empty")
        public void shouldAppearOwnerError() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(), getEmptyOwner(), cardData.getCvc());
            ticketBuyingPage.ownerError();
        }
    }

    @Nested
    public class CvcField {

        @Test
        @SneakyThrows
        @DisplayName("№26 Three zero are in CVC field")
        public void threeZeroCvc() {
            var cardData = getValidDeclinedCard();
            var cvc = "000";
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), cvc);
            ticketBuyingPage.cvcError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№27 Less than 3 digits are in CVC field")
        public void lessThan3DigitsCvc() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), getLessThan3Cvc());
            ticketBuyingPage.cvcError();
        }

        @Test
        @SneakyThrows
        @DisplayName("№28 The cvc field is empty")
        public void shouldAppearCvcError() {
            var cardData = getValidDeclinedCard();
            var ticketBuyingPage = new TicketBuyingPage();
            ticketBuyingPage.chooseDebitCard();
            ticketBuyingPage.sendDataInForm(cardData.getNumber(), cardData.getMonth(), cardData.getYear(),
                    cardData.getOwner(), getEmptyCVC());
            ticketBuyingPage.cvcError();
        }
    }
}