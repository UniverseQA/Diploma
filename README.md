# Diploma

### Проектная документация:
- [План автоматизации](docs/Plan.md)

### Процедура запусков автотестов:
#### 1. Запустить настроенный docker командой ``docker-compose up``    
1.1. _в случае если вы запускаете docker у себя на локальной машине, то в файле ``application.properties``, 
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
1.2. _в случае если вы запускаете docker на виртуальной машине с адресом, **например:** ``185.119.57.30``
то в файле ``application.properties``, который находится в корне проекта, необходимо внести следующие изменения:_  
```
spring.credit-gate.url=http://185.119.57.47:9999/credit 
spring.payment-gate.url=http://185.119.57.47:9999/payment
```
**Заменить на:**
```
spring.credit-gate.url=http://185.119.57.30:9999/credit
spring.payment-gate.url=http://185.119.57.30:9999/payment
```
1.2.1. Подключиться к виртуальной машине по адресу ``185.119.57.30``  
1.2.2. После подключения к виртуальной машине вы попадаете в директорию пользователя. Никуда не перемещаетесь и клонируете проект диплома с 
git-сервера командой ``git clone https://github.com/UniverseQA/Diploma``  
1.2.3. Перейти в директорию склонированного проекта командой ``cd Diploma``  
1.2.4. Запустить docker командой ``docker-compose up``  
#### 2. Запустить SUT ``aqa-shop.jar`` командой ``java -jar artifacts/aqa-shop.jar``    
2.1. В случае если вы запускаете ``docker`` на виртуальной машине по адресу ``187.119.57.47`` - для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``java "-Dspring.db.url=jdbc:mysql://187.119.57.47:3306/db_mysql" -jar artifacts/aqa-shop.jar``  
для **postgresql:**  
``java "-Dspring.db.url=jdbc:postgresql://187.119.57.47:5432/db_postgresql" -jar artifacts/aqa-shop.jar``  
2.2. В случае если вы запускаете ``docker``у себя на локальной машине - для запуска сервиса с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``java "-Dspring.db.url=jdbc:mysql://localhost:3306/db_mysql" -jar artifacts/aqa-shop.jar``  
для **postgresql:**  
``java "-Dspring.db.url=jdbc:postgresql://localhost:5432/db_postgresql" -jar artifacts/aqa-shop.jar``
#### 3. Запуск тестов можно осуществлять с указанием пути к базе данных в командной строке.  
3.1. В случае если вы запускаете ``docker`` на виртуальной машине по адресу ``187.119.57.47`` - для запуска тестов с указанием пути к базе данных можно использовать следующие команды:  
для **mysql**  
``./gradlew clean test "-Ddb.url=jdbc:mysql://187.119.57.47:3306/db_mysql"``  
для **postgresql:**  
``./gradlew clean test "-Ddb.url=jdbc:postgresql://187.119.57.47:5432/db_postgresql"``  
3.2. В случае если вы запускаете ``docker``у себя на локальной машине - для запуска тестов с указанием пути к базе данных можно использовать следующие команды:  
для **mysql:**  
``./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/db_mysql"``  
для **postgresql**  
``./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/db_postgresql"``
