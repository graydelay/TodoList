## Todo List 서버 개발

> Todo List 관리 서비스 API 개발

### 개요

가지고 있는 지식과 검색 능력을 활용하여 TODO List 서비스 만들기..

### Prerequisites

- Java 11
- Spring Boot 2.7.5
- JPA
- Lombok
- Gradle

#### Run Development Server
```
gradle bootRun
or
./gradlew bootRun
```

### API Specifications

#### 회원 가입 / join

POST /auth/joinProc

* Request

```json
{
  "username": "test",
  "password": "1234",
  "nickname": "lee",
  "email": "test@test.com"
}
```

* Response

```json
{
  "id": null,
  "username": "test",
  "nickname": "lee",
  "email": "test@test.com",
  "modifiedDate": null,
  "role": "USER"
}
```

#### 로그인 / login

POST /loginProc

* Request

```json
{
    "username" : "test",
    "password" : "1234"
}
```

* Response

null

#### 회원 정보 수정 / update

PUT /auth/user/{id}

* Request

```json
{
	"password": "123456",
	"nickname": "leeeel",
	"email": "testtt@test.com"
}
```

* Response

```json
{
  "id": 1,
  "username": "test",
  "nickname": "leeeel",
  "email": "testtt@test.com",
  "modifiedDate": "2022-10-29T23:57:36.689696",
  "role": "USER"
}
```

#### 회원 삭제 / delete

DELETE /auth/user/{id}

* Response

null


#### Todo 생성 / create

POST /todo

* Request

```json
{
	"title": "myTitle",
	"describe": "myDescribe"
}
```

* Response

```
id
```

#### Todo 전체 조회 / read

GET /todo

* Response

```json
[
  {
    "id": 5,
    "title": "myTitle",
    "describe": "myDescribe",
    "writer": "test",
    "status": "TODO",
    "createDate": "2022-10-30T00:20:34.581959",
    "modifiedDate": "2022-10-30T00:20:34.581959",
    "userId": 3
  },
  {
    "id": 2,
    "title": "myTitle",
    "describe": "myDescribe",
    "writer": "test",
    "status": "TODO",
    "createDate": "2022-10-30T00:19:59.796649",
    "modifiedDate": "2022-10-30T00:19:59.796649",
    "userId": 3
  },...
]
```

#### Todo 가장 최근 조회 / read

GET /todo/recent

* Response

```json
[
  {
    "id": 5,
    "title": "myTitle",
    "describe": "myDescribe",
    "writer": "test",
    "status": "TODO",
    "createDate": "2022-10-30T00:20:34.581959",
    "modifiedDate": "2022-10-30T00:20:34.581959",
    "userId": 3
  }
]
```

#### Todo 수정 / update

PUT /todo/{id}

* Request

```json
{
  "title": "바꿈",
  "describe": "바꿈",
  "status": "DOING"
}
```

* Response

```
id
```

#### Todo 삭제 / delete

DELETE /todo/{id}

* Request

```json
{
  "title": "바꿈",
  "describe": "바꿈",
  "status": "DOING"
}
```

* Response

```
id
```


### Entity
#### user
- 사용자 정보를 가지고 있음 / 논리적으로 todo 테이블과 1:n 관계

| 컬럼명           | 데이터 타입   | 조건              | 설명   |
|---------------|----------|-----------------|------|
| id            | bigint   | PK              | 고유 값 |
| username      | varchar  | not null,unique | 아이디  |
| password      | varchar  | not null        | 비밀번호 |
| nickname      | varchar  | not null,unique | 닉네임  |
| email         | varchar  | not null        | 이메일  |
| role          | varchar  | not null        | 권한   |
| created_date  | datetime | not null        | 생성일  |
| modified_date | datetime | not null        | 수정일  |

#### todo
- todo list에 대한 정보를 가지고 있음

| 컬럼명           | 데이터 타입   | 조건           | 설명     |
|---------------|----------|--------------|--------|
| id            | bigint   | PK           | 고유 값   |
| title         | varchar  | not null     | 제목     |
| describe      | text     | not null     | 설명     |
| write         | varchar  | not null     | 작성자    |
| user_id       | bigint   | FK,not null | 작성자 FK |
| created_date  | datetime | not null     | 생성일    |
| modified_date | datetime | not null     | 수정일    |


<!-- ### 요구 사항 및 문제해결 전략 -->
