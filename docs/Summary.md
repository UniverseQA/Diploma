# Отчётный документ по итогам автоматизации
### Что было запланировано и что реализовано:
Было запланировано провести [29 автоматизируемых сценариев:](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%BF%D0%BE%D0%BB%D0%B5-%D0%BD%D0%BE%D0%BC%D0%B5%D1%80-%D0%BA%D0%B0%D1%80%D1%82%D1%8B) **_2_** позитивных и **_27_** негативных.  
**_15_ (51%)** [сценариев](https://github.com/UniverseQA/Diploma/blob/4c94e8a2690b9e0e2282d8d6bc22c07eb682934e/src/test/java/ru/netology/test/TicketBuyingTest.java) были **успешно** пройдены, а **_14_ (49%)** [**провалились**.](https://github.com/UniverseQA/Diploma/issues)  
### Причины, по которым что-то не было реализовано:
- Успешно пройденная оплата картой со статусом [**"Declined(Отклонена)"**](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%BF%D0%BE%D0%BB%D0%B5-%D0%BD%D0%BE%D0%BC%D0%B5%D1%80-%D0%BA%D0%B0%D1%80%D1%82%D1%8B)  
- Указана неверная надпись под полем при вводе [невалидных значений](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%BD%D1%8B%D0%B5-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5)  
- Отсутствует надпись под полем при вводе [невалидных значений](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%BD%D1%8B%D0%B5-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5), и успешно проходит оплата **_8/14_**
### Cработавшие риски:
**Прекращение/ограничение доступа к виртуальной машине** - по собственной невнимательности допускал ошибки в коде файлов
[``docker-compose.yml``](https://github.com/UniverseQA/Diploma/blob/main/docker-compose.yml), [``build.gradle``](https://github.com/UniverseQA/Diploma/blob/main/build.gradle) и  [``application.properties``](https://github.com/UniverseQA/Diploma/blob/main/application.properties), что приводило к ошибкам запуска ``Docker'a`` 
на виртуальной машине.  
**Неработоспособность или некорректная работа подготовленного симулятора банковских сервисов** - по причине неработоспособности
``Docker'a`` не было возможности запустить сервис [``aqa-shop.jar``](https://github.com/UniverseQA/Diploma/blob/main/artifacts/aqa-shop.jar)
### Общий итог по времени: сколько запланировали и сколько выполнили с обоснованием расхождения.
#### Было [запланировано](https://github.com/UniverseQA/Diploma/blob/main/docs/Plan.md#%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2-%D0%B2-%D1%87%D0%B0%D1%81%D0%B0%D1%85):
Интервальная оценка - 120 часов  
Готовность тестов - **до 08.02.2023 г.**  
Результаты их прогона - **до 11.02.2023 г.**  
Сдача на проверку и исправление ошибок - **до 18.02.2023 г.**
#### Фактически выполнено:
Фактически времени было затрачено - 112 часов  
Готовность тестов - **30.01.2023 г.**  
Результаты их прогона - **03.02.2023 г.**  
Сдача на проверку и исправление ошибок - **10.02.2023 г.**




