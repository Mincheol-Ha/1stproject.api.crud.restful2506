# 1차 개인 프로젝트 : API CRUD
### 회원가입, 로그인, 게시글, 댓글, 좋아요

## 📖 소개
### 이 프로젝트는 
### Spring Boot 기반 REST API 서버로, 회원, 게시글, 댓글, 좋아요 기능을 구현하였습니다.

---

## 🛠️ 기술 스택

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- H2/MySQL
- Lombok
- Gradle

## Board API 엔드포인트

### 1. 회원가입 및 로그인
- **POST /api/signup**
    - RequestBody: SignupRequestDto
    - Response: SignupResponseDto
- **POST /api/login**
    - RequestBody: LoginRequestDto
    - Response: LoginResponseDto

### 2. 게시글(Post)
- **POST /api/posts**
    - RequestBody: PostRequestDto
    - Response: PostResponseDto
- **PUT /api/posts/{postId}**
    - PathVariable: postId
    - RequestBody: PostRequestDto
    - Response: PostResponseDto
- **DELETE /api/posts/{postId}**
    - PathVariable: postId
    - Response: "게시글 삭제"

### 3. 댓글(Comment)
- **POST /api/comments**
    - RequestBody: CommentRequestDto
    - Response: CommentResponseDto
- **GET /api/comments/post/{postId}**
    - PathVariable: postId
    - Response: List<CommentResponseDto>
- **PUT /api/comments/{id}**
    - PathVariable: id
    - RequestBody: CommentRequestDto
    - Response: "댓글 수정 완료"
- **DELETE /api/comments/{id}**
    - PathVariable: id
    - Response: "삭제 되었습니다."

### 4. 댓글 좋아요(Comment Like)
- **POST /api/commentlikes**
    - RequestBody: CommentLikeRequestDto
    - Response: CommentLikeResponseDto
- **DELETE /api/commentlikes**
    - RequestBody: CommentLikeDeleteRequestDto
    - Response: CommentLikeDeleteResponseDto

### 5. 게시글 좋아요(Post Like)
- **DELETE /api/{postId}/likes**
    - PathVariable: postId
    - RequestBody: PostLikeRequestDto
    - Response: "좋아요 취소!"
- **GET /api/{postId}/likes/count**
    - PathVariable: postId
    - Response: Integer (좋아요 개수)
