# Basic Microservices

### 1. 스프링 마이크로서비스 코딩 공작소 책 실습 예제 기반

* [스프링 마이크로서비스 코딩 공작소](https://kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9791160506815&orderClick=JAj "스프링 마이크로서비스 코딩 공작소") 참고

### 2. 차이점

* Spring Boot 2, Gradle 6 기반  
* Spring Cloud Hoxton SR3으로 변경
    * 원래는 Spring Cloud Greenwich SR3 기반으로 동작 확인이 필요함

### 3. TODO

* 리팩토링
* yml 공통화 및 설정 (DB 설정, config server, eureka server 정보 등)
* model 클래스 공통으로 쓸 수 있는 방법
* RestTemplate 이름 사용 시 Feign 적용 여부
* Zipkin 과 MessageBroker 연결 (RabbitMQ, ApacheKafka), 데이터 저장소 구성 (MySQL, Cassandra, Elasticsearch)
