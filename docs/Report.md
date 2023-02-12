# Отчётный документ по итогам тестирования

Целью тестирования сервиса **"Путешествие дня"** была - проверка работоспособности оплаты 
дебетовой картой и оплатой в кредит.   
В ходе тестирования сервиса было написано и запущено [**_29 автоматизируемых сценариев:_**](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%BF%D0%BE%D0%BB%D0%B5-%D0%BD%D0%BE%D0%BC%D0%B5%D1%80-%D0%BA%D0%B0%D1%80%D1%82%D1%8B)  
**_15_ (51%)** из которых были **успешно** [пройдены](https://github.com/UniverseQA/Diploma/blob/4c94e8a2690b9e0e2282d8d6bc22c07eb682934e/src/test/java/ru/netology/test/TicketBuyingTest.java), а [**_14_ (49%)** **провалились**.](https://github.com/UniverseQA/Diploma/issues)  

Ошибки, которые встречаются в [**проваленных** тестах:](https://github.com/UniverseQA/Diploma/issues)
- Успешно пройденная оплата картой со статусом [**"Declined(Отклонена)"**](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%BF%D0%BE%D0%BB%D0%B5-%D0%BD%D0%BE%D0%BC%D0%B5%D1%80-%D0%BA%D0%B0%D1%80%D1%82%D1%8B) **_2/14_**  
- Указана неверная надпись под полем при вводе невалидных значений **_10/14_**  
- Отсутствует надпись под полем при вводе невалидных значений, и успешно проходит оплата **_8/14_**  

![image](https://github.com/UniverseQA/Diploma/blob/main/png/allure-report.png?raw=true)
![image](https://github.com/UniverseQA/Diploma/blob/main/png/a-r-1.png?raw=true)
![image](https://github.com/UniverseQA/Diploma/blob/main/png/a-r-2.png?raw=true)

### Общие рекомендации 
- Исправить все найденные ошибки;  
- Добавить пару возможностей - например, автоматически делать буквы заглавными при вводе строчных;    
- Убрать возможность ввода любых других символов в поле [**"Владелец"**](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%BF%D0%BE%D0%BB%D0%B5-%D0%B2%D0%BB%D0%B0%D0%B4%D0%B5%D0%BB%D0%B5%D1%86) кроме заглавных латинских букв;  
- Поставить валидацию на поля со значениями **"0, 00, 000"**.