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

### 메서드	엔드포인트	설명
### POST	/api/signup	회원가입
### POST	/api/login	로그인
### GET	/api/posts	게시글 목록 조회
### POST	/api/posts	게시글 작성
### PUT	/api/posts/{id}	게시글 수정
### DELETE	/api/posts/{id}	게시글 삭제
### POST	/api/comments	댓글 작성
### DELETE	/api/comments/{id}	댓글 삭제
### POST	/api/likes	좋아요 등록
### DELETE	/api/likes	좋아요 취소
