# 원티드 프리온보딩 백엔드 인턴십 - 선발 과제
<br></br>

# 과제 제출 필수 사항
### 지원자의 성명

---
박정현

### 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)

---
1. 해당 repository clone
2. PreAssignmentApplication 실행
3. http://localhost:8080 으로 접근

### 데이터베이스 테이블 구조

---
![img.png](img.png)

### 구현한 API의 동작을 촬영한 데모 영상 링크

---
https://supark0206.notion.site/b8b64bb504564283a76c3216efbd74ea?pvs=4

### 구현 방법 및 이유에 대한 간략한 설명

---
- **모델링**
  - Board(게시판)와 User(사용자) 테이블을 만들었습니다.
  - @ManyToOne 관계이기 때문에 성능(이후 기능 추가가 되도 성능 리팩토링이 더 쉽다.)과 sql 추적을 위해 지연로딩을 사용하였습니다.

- **과제 1. 사용자 회원가입 [POST]/api/join**
  - Srping Security를 사용하여 PasswordEncorder를 통해 암호화 처리하였습니다. 
  - DTO에서 @Email 과 @Pattern 을 통해서 유효성 조건을 달아두고 Controller 에서 @Valid를 통해서 유효성 검사를 진행하였습니다.
  - 오류 발생시 GlobalExceptionHandler를 통해서 message와 에러코드를 출력하였습니다.

- **과제 2. 사용자 로그인 [POST]/api/login**
  - PasswordEncorder를 사용해서 비교한이후 로그인 진행하였습니다. 
  - 올바른 로그인 진행시 사용자 인증이후 JWT 토큰을 발급합니다 .
  - 로그인된 사용자는 @AuthenticationPrincipal 를 통해서 사용 할 수 있고 @LoginUser 어노테이션을 만들어 직관성 올렸습니다.

- **과제 3. 새로운 게시글을 생성 [POST]/api/board**
  - 로그인된 사용자를 확인하여 게시글 제목, 내용, 카테고리를 지정하여 게시글을 등록합니다.

- **과제 4. 게시글 목록을 조회 [GET]/api/board?page=1&size=10**
  - jpa Pageable 인스턴스를 활용하여 페이징 처리를 진행하였습니다.
  - @RequestParam 파라미터로 page,size를 받습니다. 
  - page,size 파라미터를 받아서 PageRequest를 통해 원하는 페이지, 한 페이지 나오는 게시글을 반환합니다.

- **과제 5. 특정 게시글을 조회 [GET]/api/board/{boardId}**
  - @PathVariable을 이용하여 url {boardId}에 원하는 게시글 아이디를 입력하여 조회 할 수있습니다.

- **과제 6. 특정 게시글을 수정 [PUT]/api/board/{boardId}**
  - @PathVariable을 이용하여 url {boardId}에 원하는 게시글 아이디를 입력하여 수정 할 수있습니다.

- **과제 7. 특정 게시글을 삭제 [DELETE]/api/board/{boardId}**
  - @PathVariable을 이용하여 url {boardId}에 원하는 게시글 아이디를 입력하여 삭제 할 수있습니다.

### API 명세(request/response 포함)

---
- **과제 1. 사용자 회원가입 [POST]/api/join**
  - request
  ```json
  {   
    "email":"testnaver.com",
    "password":"123456",
    "name":"이름"
  }
  ```
  
  - response
  ```json 
  {
    //성공

    "id": 2,
    "message": "회원가입에 성공하였습니다."
  }
  ```
  
  ```json 
  {
    //이메일 중복
  
    "status": 409,
    "error": "CONFLICT",
    "code": "EXIST_USER_EMAIL",
    "message": "중복된 이메일이 존재합니다."
  }
  ```

  ```json
  {
    //비밀번호 입력 오류
  
    "status": 400,
    "error": "joinUserRequest",
    "code": "BAD_REQUEST_JOIN_PATTERN",
    "message": "비밀번호는 8글자 이상이어야합니다."
  }
  ```
  
  ```json
  {
    //이메일 입력 오류
  
    "status": 400,
    "error": "joinUserRequest",
    "code": "BAD_REQUEST_JOIN_EMAIL",
    "message": "이메일 형식에 맞지 않습니다"
  }
  ```
 
- **과제 2. 사용자 로그인 [POST]/api/login**
  - request
  ```json
  {
    "email":"test@naver.com",
    "password":"12345678"
  }
  ```
  - response
  ```json
  {
    "grantType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QG5hdmVyLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTIyNzIxMTJ9.HdOdzlVf_1yco1KV-Z_tudK4DtcoAfrULg2wQBUcb3U",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTIyNzIxMTJ9.4g2TSU2jmwkZ2Sm-1sZsdFNMr8FH97b1EUn73jS95fQ"
  }
  ```

- **과제 3. 새로운 게시글을 생성 [POST]/api/board**
  - request
  ```json
  {   
    "title":"test11",
    "content":"ffzz",
    "boardCategory":"FREE"
  }
  ```
  - response
  ```json
  {   
    "id": 10,
    "message": "게시글을 등록하였습니다."
  }
  ```

- **과제 4. 게시글 목록을 조회 [GET]/api/board?page=1&size=10**
  - request
    - request parameter : (Integer)page, (Integer)size
  - response
  ```json
  {
    "boardList": [
      {
        "id": 4,
        "title": "test2",
        "createBy": "이름",
        "createDate": "2023-08-16T21:53:41.93374"
      },
      {
        "id": 5,
        "title": "test3",
        "createBy": "이름",
        "createDate": "2023-08-16T21:53:44.651862"
      },
      {
        "id": 6,
        "title": "test3",
        "createBy": "이름",
        "createDate": "2023-08-16T21:53:48.569843"
      }
    ],
    "totalPages": 3
  }
  ```

- **과제 5. 특정 게시글을 조회 [GET]/api/board/{boardId}**
  - request
    - request path : type : Long, {boardId} : 게시글 아이디
  - response
  ```json
  {
    "id": 1,
    "title": "제목",
    "content": "내용",
    "boardCategory": "QNA",
    "createBy": "이름",
    "createDate": "2023-08-16T20:35:15.018386",
    "updateDate": "2023-08-16T20:37:29.50288"
  }
  ```

- **과제 6. 특정 게시글을 수정 [PUT]/api/board/{boardId}**
  - request
    - request path : type : Long, {boardId} : 게시글 아이디
  - response
  ```json
  {   
    "id": 10,
    "message": "게시글을 수정하였습니다."
  }
  ```

- **과제 7. 특정 게시글을 삭제 [DELETE]/api/board/{boardId}**
  - request 
    - request path : type : Long, {boardId} : 게시글 아이디
  - response
  ```json
  {   
    "id": 1,
    "message": "게시글을 삭제하였습니다."
  }
  ```

## 3. API 요구 사항
게시판을 관리하는 RESTful API를 개발해 주세요. 이때, 다음의 기능을 구현해야 합니다. 데이터베이스의 테이블 설계는 지원자분의 판단에 맡겨져 있습니다. 요구사항을 충족시키는 데 필요하다고 생각되는 구조로 자유롭게 설계해 주세요.

- **과제 1. 사용자 회원가입 엔드포인트**
    - 이메일과 비밀번호로 회원가입할 수 있는 엔드포인트를 구현해 주세요.
    - 이메일과 비밀번호에 대한 유효성 검사를 구현해 주세요.
        - 이메일 조건: **@** 포함
        - 비밀번호 조건: 8자 이상
        - 비밀번호는 반드시 암호화하여 저장해 주세요.
        - 이메일과 비밀번호의 유효성 검사는 위의 조건만으로 진행해 주세요. 추가적인 유효성 검사 조건은 포함하지 마세요.
- **과제 2. 사용자 로그인 엔드포인트**
    - 사용자가 올바른 이메일과 비밀번호를 제공하면, 사용자 인증을 거친 후에 JWT(JSON Web Token)를 생성하여 사용자에게 반환하도록 해주세요.
    - 과제 1과 마찬가지로 회원가입 엔드포인트에 이메일과 비밀번호의 유효성 검사기능을 구현해주세요.
- **과제 3. 새로운 게시글을 생성하는 엔드포인트**
- **과제 4. 게시글 목록을 조회하는 엔드포인트**
    - 반드시 Pagination 기능을 구현해 주세요.
- **과제 5. 특정 게시글을 조회하는 엔드포인트**
    - 게시글의 ID를 받아 해당 게시글을 조회하는 엔드포인트를 구현해 주세요.
- **과제 6. 특정 게시글을 수정하는 엔드포인트**
    - 게시글의 ID와 수정 내용을 받아 해당 게시글을 수정하는 엔드포인트를 구현해 주세요.
    - 게시글을 수정할 수 있는 사용자는 게시글 작성자만이어야 합니다.
- **과제 7. 특정 게시글을 삭제하는 엔드포인트**
    - 게시글의 ID를 받아 해당 게시글을 삭제하는 엔드포인트를 구현해 주세요.
    - 게시글을 삭제할 수 있는 사용자는 게시글 작성자만이어야 합니다.