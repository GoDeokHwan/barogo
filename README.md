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

