# Cara Menjalankan Aplikasi

Stack yang digunakan
1. JDK 17
2. spring boot version 3.3.0
3. postgresql
4. Apache Maven
5. Apache Kafka
6. Rabbit MQ
7. postman --> postman collections

sebelum menjalankan aplikasi ini, buat dahulu database. disini saya menggunakan database PostgreSQL

jalankan script ini untuk mengcreate database baru `create database db_message_broker`

jika sudah membuat database, dipastikan dahulu di database local, apakah terkoneksi dengan baik atau tidak, jika berhasil maka akan seperti ini

![Screenshot from 2024-06-18 17-21-09](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/6155c586-cfee-44d6-9e9f-d6fcfb621737)

kemudian jalankan aplikasi dengan script berikut ini `mvn clean install spring-boot:run`

![Screenshot from 2024-06-18 17-24-51](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/0f2dec40-8f0d-462d-9d20-d05cbe1a023d)
![Screenshot from 2024-06-18 17-25-46](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/3123239a-94ef-47e9-9641-bda97bf69804)

jika sudah berhasil jalan, mari kita test..

1. test insert with postman (kafka / rabbitMQ)
   berikut ini adalah sampel untuk DTO insert account

``{
    "code" : "AC001",
    "fullname" : "dickanirwansyah@gmail.com",
    "email" : "dickanirwansyah@gmail.com@gmail.com",
    "phoneNumber" : "0889929292"
}``

![Screenshot from 2024-06-18 17-30-07](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/6e72a464-bd20-484b-a6a4-ea9d347ed5a5)

check database apakah datanya berhasil ke insert, jika berhasil ke insert akan seperti ini.

![Screenshot from 2024-06-18 17-30-40](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/a09561aa-8962-4b01-88fd-c987f96872b5)


2. test update with postman (kafka / rabbitMQ)
   berikut ini adalah sampel untuk DTO update account 
  
   ``{
    "id" : 6,
    "code" : "AC001",
    "fullname" : "dicka testing update",
    "email" : "dickaupdate@gmail.com",
    "phoneNumber" : "08782929292"
  }
   ``
   ![Screenshot from 2024-06-18 17-33-56](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/1600e604-71e3-4d45-96c9-3e3275060e63)

   check database apakah datanya berhasil ke update, jika berhasil ke update data akan berubah sesuai apa yang di request.

   ![Screenshot from 2024-06-18 17-35-40](https://github.com/dickanirwansyah/spring-kafka-rabbitmq/assets/24214234/fdfb60f2-a28f-448c-8f1b-ac4defca40d5)


   


