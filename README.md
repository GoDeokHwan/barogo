# barogo

바로고 과제

# DB 환경

- H2 DB 사용
- URL : http://localhost:8080/h2-console
- 접속

    - JDBC URL : jdbc:h2:mem:barogodb
    - User, Password는 디폴트로 Connect

# Redis 설치
선행 작업으로 도커 설치 필요

- Redis 설치 현재 위치에서 실행
```
cd ./docker/redis/; docker-compose up -d
```
- 사용이유 :
  Token 저장 용도

# 스웨거 연결

- URL : http://localhost:8080/swagger-ui.html


# 과제 순서
전제조건 (1) : 회원 가입 API를 구현해 주세요.
1. 회원가입시 필요한 정보는 ID, 비밀번호, 사용자 이름 입니다.
2. 비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로
      12자리 이상의 문자열로 생성해야 합니다.

=> 

URL : POST http://localhost:8080/api/accounts/join

Param 예시 : 
```
{
    "loginId": "system1"
    , "password": "1234Qqasdfsd2342342"
    , "name": "시스템적이다"
}
```
Response : 
```
{
    "id": 2,
    "loginId": "system1",
    "name": "시스템적이다"
}
```

전제조건 (2) : 로그인 API 를 구현해 주세요.
1. 사용자로부터 ID, 비밀번호를 입력받아 로그인을 처리합니다.
2. ID와 비밀번호가 이미 가입되어 있는 회원의 정보와 일치하면 로그인이 되었다는
      응답으로 AccessToken 을 제공합니다.

=>

URL : POST http://localhost:8080/api/login

Param 예시 :
```
{
    "loginId": "system1"
    , "password": "1234Qqasdfsd2342342"
}
```

Response :
```
{
    "AccessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzeXN0ZW0iLCJleHAiOjE2NzU3Nzk0MDh9.G-ChiquyM8stHTtOjKMUCLKyq5bOO-Qe3o_zF61ijZU"
}
```

전제조건 (3) : 배달 조회 API 를 구현해 주세요.
1. 배달 조회 시 필요한 정보는 기간 입니다. (기간은 최대 3일)
2. 기간 내에 사용자가 주문한 배달의 리스트를 제공합니다.

=> 

URL : GET http://localhost:8080/api/accounts/1/delivery?startDate=2023-02-07&endDate=2023-02-09

Response :
```
[
    {
        "id": 1,
        "requestTime": "2023-02-07 12:58:57",
        "endTime": "2023-02-08 01:58:57",
        "address": "서울시 천호구",
        "status": "COMPLETED"
    },
    {
        "id": 2,
        "requestTime": "2023-02-08 12:58:57",
        "endTime": null,
        "address": "서울시 천호구",
        "status": "IN_DELIVERY"
    },
    {
        "id": 3,
        "requestTime": "2023-02-09 12:58:57",
        "endTime": null,
        "address": "서울시 천호구",
        "status": "WAITING"
    }
]
```

전제조건 (4) : 배달 주문 수정 API (도착지 주소 변경) 를 구현해 주세요.
1.	사용자로부터 도착지 주소를 요청 받아 처리합니다.
2.	사용자가 변경 가능한 배달인 경우에만 수정이 가능합니다.

