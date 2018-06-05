﻿Погодный брокер: формирование JMS-очереди (часть 1)
=======================
Деплой на сервер приложения 
 - 
 В качестве сервера приложений используется [WildFly 11. ](http://wildfly.org/downloads/)
 Для запуска использовать standalone.bat (standalone.sh если Linux) из папки bin.
 Использовать последовательность команд в командной строке для формирования и отправки war-файла  
       
    mvn clean wildfly:deploy -Pdep
 

Менеджер управления WildFly: 

    http://localhost:9990/

На сервере приложения сконфигурировать DataSource. Зайти в менеджер управления, далее Configuration -> Subsystems -> Datasources -> Non-XA. 
Сформировать новый DataSource. Основные параметры: 

Attributes
 - JNDI:
   java:jboss/datasources/PostgresDS
 - Name:
   PostgresDS
   
Connection
 - Connection URL:
   jdbc:postgresql://localhost:5432/postgres
   
В Security указать UserName и Password

Используя вкладку Deployments, добавить [postgresql-42.2.2.jar или другую версию](https://jdbc.postgresql.org/download.html)

ActiveMQ
 - 
В качестве message broker используется [ActiveMQ.](http://activemq.apache.org/download-archives.html)
Для запуска использовать activemq.bat из папки bin.

 Менеджер управления ActiveMQ: 
 
     http://localhost:8161/
     
Логин и пароль по умолчанию **admin**