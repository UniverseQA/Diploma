# Diploma

### Проектная документация:
- [План автоматизации](docs/Plan.md)
- [Отчётный документ по итогам тестирования](docs/Report.md)
- [Отчётный документ по итогам автоматизации](docs/Summary.md)

### Процедура запусков автотестов:
#### 1. Убедиться, что порты 8080, 9999, 3306 5432 свободны
#### 2. Запустить настроенный docker командой ``docker-compose up``    
2.1. _в случае если вы запускаете docker у себя на локальной машине, то в файле [``application.properties``](https://github.com/UniverseQA/Diploma/blob/main/application.properties), 
который находится в корне проекта, необходимо внести следующие изменения:_  
```
spring.credit-gate.url=http://185.119.57.47:9999/credit 
spring.payment-gate.url=http://185.119.57.47:9999/payment
```
**Заменить на:**
```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
```
2.2. _в случае если вы запускаете docker на виртуальной машине с адресом, **например:** ``185.119.57.30``
то в файле [``application.properties``](https://github.com/UniverseQA/Diploma/blob/main/application.properties), который находится в корне проекта, необходимо внести следующие изменения:_  
```
spring.credit-gate.url=http://185.119.57.47:9999/credit 
spring.payment-gate.url=http://185.119.57.47:9999/payment
```
**Заменить на:**
```
spring.credit-gate.url=http://185.119.57.30:9999/credit
spring.payment-gate.url=http://185.119.57.30:9999/payment
```
2.2.1. Подключиться к виртуальной машине по адресу ``185.119.57.30``  
2.2.2. После подключения к виртуальной машине вы попадаете в директорию пользователя. Никуда не перемещаетесь и клонируете проект диплома с 
git-сервера командой ``git clone https://github.com/UniverseQA/Diploma``  
2.2.3. Перейти в директорию склонированного проекта командой ``cd Diploma``  
2.2.4. Запустить docker командой ``docker-compose up``  
#### 3. Запустить SUT [``aqa-shop.jar``](https://github.com/UniverseQA/Diploma/blob/main/artifacts/aqa-shop.jar) командой ``java -jar artifacts/aqa-shop.jar``    
3.1. В случае если вы запускаете ``docker`` на виртуальной машине по адресу ``185.119.57.47`` - для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``java "-Dspring.db.url=jdbc:mysql://185.119.57.47:3306/db_mysql" -jar artifacts/aqa-shop.jar``  
для **postgresql:**  
``java "-Dspring.db.url=jdbc:postgresql://185.119.57.47:5432/db_postgresql" -jar artifacts/aqa-shop.jar``  
3.2. В случае если вы запускаете ``docker``у себя на локальной машине - для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``java "-Dspring.db.url=jdbc:mysql://localhost:3306/db_mysql" -jar artifacts/aqa-shop.jar``  
для **postgresql:**  
``java "-Dspring.db.url=jdbc:postgresql://localhost:5432/db_postgresql" -jar artifacts/aqa-shop.jar``
#### 4. Запуск тестов можно осуществлять с указанием пути к базе данных в командной строке.  
4.1. В случае если вы запускаете ``docker`` на виртуальной машине по адресу ``187.119.57.47`` - для запуска тестов с указанием пути к базе данных можно использовать следующие команды:  
для **mysql**  
``./gradlew clean test "-Ddb.url=jdbc:mysql://185.119.57.47:3306/db_mysql"``  
для **postgresql:**  
``./gradlew clean test "-Ddb.url=jdbc:postgresql://185.119.57.47:5432/db_postgresql"``  
4.2. В случае если вы запускаете ``docker``у себя на локальной машине - для запуска тестов с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/db_mysql"``  
для **postgresql**  
``./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/db_postgresql"``
