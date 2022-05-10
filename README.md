# spring_sending

# ДЛЯ ЗАПУСКА ПРОЕКТА НУЖНО ПЕРЕЙТИ В ДЕРИКТОРИЮ MYSQL
Для создания базы данных:
cd C:\Program Files\MySQL\MySQL Server 8.0\bin
./mysql -u root -p
create database db_sending
use use db_sending; 

# Методы обращения к сущностям:
## Client(КЛИЕНТЫ):
@GET ВСЕХ КЛИЕНТОВ
http://localhost:8080/client

@POST ДОБАВИТЬ КЛИЕНТА
http://localhost:8080/client/create
{
  "phone": 75435436,
  "phoneCode": "7",
  "tags": "r"
}

@PUT ИЗМЕНИТЬ ИНФОРМАЦИЮ О КЛИЕНТЕ
http://localhost:8080/client/{id}
{
  "phone": 75435436,
  "phoneCode": "7",
  "tags": "r"
}

@DELETE УДАЛИТЬ КЛИЕНТА
http://localhost:8080/client/{id}

## Sending(РАССЫЛКА):
@GET ВСЕ РАССЫЛКИ
http://localhost:8080/sending/all

@GET ПОЛУЧИТЬ ОПРЕДЕЛЕННУЮ ИНФОРМАЦИЮ О РАССЫЛКЕ
http://localhost:8080/sending?id={id}

@POST ДОБАВИТЬ РАССЫЛКУ
http://localhost:8080/sending/create?client_id={id}
{
  "mobile_codes": "4",
  "tags": "u",
  "text": "fefefdgfdgfdfdfdfgtgrgr"
}

@PUT ИЗМЕНИТЬ ИНФОРМАЦИЮ О РАССЫЛКЕ
http://localhost:8080/sending/{id}
{
  "mobile_codes": "4",
  "tags": "u",
  "text": "fefefdgfdgfdfdfdfgtgrgr"
}

@DELETE УДАЛИТЬ РАССЫЛКУ
http://localhost:8080/sending/{id}

## Message(СООБЩЕНИЯ):
@GET ВСЕ СООБЩЕНИЯ ПО СТАТУСУ ОТПРАВКИ 
http://localhost:8080/message/all?status=true OR status=false

@GET ПОЛУЧИТЬ ОПРЕДЕЛЕННУЮ ИНФОРМАЦИЮ О СООБЩЕНИИ
http://localhost:8080/message?id={id}

@POST ДОБАВИТЬ РАССЫЛКУ
http://localhost:8080/message?sendingId={sendingID}&clientId={clientID}

# ОТПРАВКА СООБЩЕНИЙ НА ВНЕШНИЙ API
@POST ОТПРАВКА СООБЩЕНИЯ КЛИЕНТУ НА ВНЕШНИЙ API
http://localhost:8080/message/send?id={messageID}


