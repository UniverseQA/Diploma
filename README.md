# Diploma
### Процедура запусков автотестов:
#### 1. Запустить настроенный docker командой ``docker-compose up``    
1.1. _в случае если вы запускаете docker у себя на локальной машине, то в файле ``application.properties``, 
который находится в корне проекта, необходимо внести следующие изменения:_  
```
spring.credit-gate.url=http://185.119.57.47:9999/credit 
spring.payment-gate.url=http://185.119.57.47:9999/payment
spring.datasource.url=jdbc:mysql://185.119.57.47:3306/db_mysql
```
**Заменить на:**
```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:mysql://localhost:3306/db_mysql
```
1.2. _в случае если вы запускаете docker на виртуальной машине с адресом, **например:** ``185.119.57.30``
то в файле ``application.properties``, который находится в корне проекта, необходимо внести следующие изменения:_  
```
spring.credit-gate.url=http://185.119.57.47:9999/credit 
spring.payment-gate.url=http://185.119.57.47:9999/payment
spring.datasource.url=jdbc:mysql://185.119.57.47:3306/db_mysql
```
**Заменить на:**
```
spring.credit-gate.url=http://185.119.57.30:9999/credit
spring.payment-gate.url=http://185.119.57.30:9999/payment
spring.datasource.url=jdbc:mysql://185.119.57.30:3306/db_mysql
```
1.2.1. Подключиться к виртуальной машине по адресу ``185.119.57.30``  
1.2.2. После подключения к виртуальной машине вы попадаете в директорию пользователя. Никуда не перемещаетесь и клонируете проект диплома с 
git-сервера командой ``git clone https://github.com/UniverseQA/Diploma``  
1.2.3. Перейти в директорию склонированного проекта командой ``cd Diploma``  
1.2.4. Запустить docker командой ``docker-compose up``  
#### 2. Запустить SUT aqa-shop.jar командой ``java -jar artifacts/aqa-shop.jar``
#### 3. Зайти в файл TicketBuyingTest.java по адресу ``Diploma/src/test/java/ru/netology/test``
#### 4. Запустить тесты ``public class TicketBuyingTest``