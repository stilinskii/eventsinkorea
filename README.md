# :laughing: KoreanBuddy

> 한국어 의사소통이 어려운 외국인 여행객과 언어를 연습하고싶어하는 한국인 매칭 및 축제 정보 제공 사이트.

</br>


사이트 : http://3.37.145.69:8081/
<br/>
테스트 아이디/비번 (관리자 권한O) : admin / admin123!

## 1. 제작 기간 & 참여 인원

- 2022년 7월 11일 ~ 9월 12일
- 개인 프로젝트
- 개발목적 : mybatis대신 JPA를 이용해보자

</br>

## 2. 사용 기술

#### `Back-end`

- Java 11
- Spring Boot 2.7
- Gradle
- Spring Data JPA
- QueryDSL
- H2
- MariaDB
- Spring Security
- thymeleaf
- Ajax

#### `Server`

- AWS EC2
- AWS S3

#### `Front-end`

- bootstrap
- JQuery

</br>
</br>

## 3. ERD 설계

![image](https://user-images.githubusercontent.com/96387509/189764470-4b496296-110b-41d7-ae92-d1809d8efcd5.png)

</br>
</br>
</br>

## 4. 핵심 기능

![image](https://user-images.githubusercontent.com/96387509/189721371-a2ab355a-a77b-4da0-9a49-54a777a3884b.png)

이 웹사이트의 핵심 기능은 3가지가 있습니다.

1.  여행객과 현지인 매칭 기능 </br>

    - 흐름: 사용자A가 사용자B에게 버디신청 > 사용자B에게 알림메일 전송 > 사용자B가 신청을 수락 or 거절 > 수락 후 사용자A는 사용자B에 대해 리뷰를 남길 수 있음.
      </br>

2.  축제정보 조회 기능</br>

    - Tour open API와 Google Maps API를 이용한 실제 축제 정보와 위치 불러오기

3.  웹사이트 관리자 기능</br>
    - 회원관리, 페이지CRUD, 카테고리CRUD 기능

</br>
<hr>
</br>

<details>
<summary><b>버디페이지 설명 펼치기</b></summary>
<div markdown="1">
</br>

### 1. 버디 필터링 정렬, 더보기 페이징

- QueryDSL을 사용하여 필터링과 정렬이 동시에 되도록 구현하였습니다.
    -[코드보기](https://github.com/stilinskii/eventsinkorea/blob/b2bd8300e30cfc045161312102981e1f5cfafa3d/src/main/java/com/jenn/eventsinkorea/domain/buddy/repository/BuddyDAOImpl.java#L27)
- Ajax를 이용하여 필터링이나 정렬 조건이 바뀔때마다 해당되는 데이터를 바로바로 불러오도록 하였습니다.
    -[코드보기](https://github.com/stilinskii/eventsinkorea/blob/b2bd8300e30cfc045161312102981e1f5cfafa3d/src/main/resources/static/js/buddies.js#L15)

![buddysortfilter](https://user-images.githubusercontent.com/96387509/189753591-e2f7ee2c-7398-4a7f-90a9-1db22c90fd76.gif)

</br>

### 2. 좋아요 기능

- Ajax로 비동기로 구현하였습니다.
  ![buddylike](https://user-images.githubusercontent.com/96387509/189754080-b3b809b8-8f4d-4889-9bde-b665182d194a.gif)

</br>

### 3. 마이버디 페이지

- 나의 버디 프로필 수정/삭제를 할 수 있고
- 받은 request와 보낸 request 목록, 상태를 확인할 수 있으며
- 리뷰를 쓰고 조회할 수 있습니다.
![mubuddy](https://user-images.githubusercontent.com/96387509/189756822-e3c6f313-e3ad-433b-b6e1-7035bdfd704f.gif)
</div>
</details>

</br>

<details>
<summary><b>축제페이지 설명 펼치기</b></summary>
<div markdown="1">
</br>

### 1. TourAPI와 Google Maps API 활용

![image](https://user-images.githubusercontent.com/96387509/189717523-1e9b7f0b-fb1b-4e7e-a0b5-cd4d369e898f.png)

</br>

### 2. 최근 본 축제 목록 floating banner

-session으로 구현하였습니다.
![recentview](https://user-images.githubusercontent.com/96387509/189757777-94d73de3-2fc2-4131-8ff4-17fc41841121.gif)

</div>
</details>
</br>
<details>
<summary><b> 웹사이트 관리자 페이지 설명 펼치기</b></summary>
<div markdown="1">
</br>

### 1. 유저 조건검색 및 페이징

- QueryDSL로 구현해보았습니다.
- [코드보기](https://github.com/stilinskii/eventsinkorea/blob/b2bd8300e30cfc045161312102981e1f5cfafa3d/src/main/java/com/jenn/eventsinkorea/domain/user/repository/UserDAOImpl.java#L39)

![adminusers](https://user-images.githubusercontent.com/96387509/189759563-dd9b0725-db5f-4414-97e1-929bbe699e0a.gif)

</br>

### 2. 페이지 추가 기능

- 관리자에서 페이지를 추가하면 메인 사이트에 바로 반영이 됩니다.
  ![adminpage](https://user-images.githubusercontent.com/96387509/189761130-a8b8270a-50cc-4372-b20d-82edbc26ffab.gif)

</br>

### 3. 카테고리 추가 기능

- 페이지마다 카테고리를 추가할 수 있습니다. 데이터에 저장되어 메인페이지에 활용할 수 있습니다.
![admincategory](https://user-images.githubusercontent.com/96387509/189761279-55a691e7-3d6b-4221-b505-2c8d2f081c6f.gif)
</div>
</details>

</br>
<hr>
</br>
</br>

## 5. 트러블 슈팅

### 5.1. 별점 평균점수 데이터에 저장 or 필요할때 계산

데이터에 저장 좋은 점 - 버디리스트 정렬이나 값을 불러올 때 바로 가져올 수 있어서 편하다.

단점 - 리뷰가 추가, 삭제될 때마다 값을 계산해서 바꿔줘야 한다.

</br>

@Transient로 하면 좋은 점 - 필요할 때 계산해서 쓰면 되기 때문에 리뷰가 추가될 때마다 계산해서 값을 바꾸지 않아도 된다.

단점 - 버디리스트 별점 평균점수로 정렬을할때 복잡한 쿼리를 써야한다.

 </br>

결과 : 데이터에 저장 선택.

왜냐하면

1. 버디 페이지에 들어오면 수많은 버디의 별점 평균점수를 불러와야 하므로 로딩될 때마다 계산을 하는 건 비효율적이다.
2. 정렬에 비동기 방식을 사용해야 하고 있기 때문에 때문에 속도가 빨라야 한다.
3. 기본적으로 리뷰가 추가되는 거보다 평균 별점을 그냥 불러와야 하는 일이 많기 때문에 리뷰가 추가될 때 계산이 들어가는 것이 평균 별점을 불러올 때마다 계산하는 거보다 계산이 들어가는 횟수가 적을 것이다.

 </br>

### 5.2. JPA복합키 문제

버디신청 테이블의 컬럼

- 신청자 (User)

- 신청받은 버디 (Buddy)

- 신청상태

신청자는 여러 버디한테 신청할 수 있고

버디는 여러 신청자한테 신청을 받을 수 있음 (다대다)

그러나 같은 신청자가 같은 버디에게 중복되게 신청할 수 없음. (복합키)

그래서 복합키로 해야할 거 같음.

그래서 @EmbeddedId로 하기로했으나.

JPA책에서는 복합키를 만드는 건 사용하는 과정에서 복잡해져서 대리키를 만드는걸 권장한다고 되어있음.

대리키를 만들어서 신청자, 버디를 그냥 외래키로만 만드는 방식이 편리하다고 함.

시도해본 결과 그렇게 하면 같은 신청자가 같은 버디에게 중복되게 신청하는 게 가능해짐. (물론 자바에서 막을 수 있지만 데이터 자체에는 중복되게 저장이 가능함)

결국 한번 연습도 해볼 겸 그대로 복합키로(@EmbeddedId로) 진행하기로 함.

![image](https://user-images.githubusercontent.com/96387509/189750320-fb6960e6-74b0-44af-8fc5-65a092a1a18a.png)

### - [축제페이지 트러블슈팅](https://herongmirong.tistory.com/139?category=1285038)

</br>

## 6. 회고 / 느낀점

- 아쉬운 점 <br>

  - 팀프로젝트와 개발기간이 겹쳐서 개인프로젝트에 할애할 시간이 많지 않아 너무 오래 끌게되었다. 그러다보니 마지막에는 조금 지쳐서 급하게 마무리를 짓게 되어버려서 아쉽다.
  - AWS EC2에 배포하였으나 free tier라 메모리가 작아서그런지 사이트가 계속 다운된다...

- 느낀 점
  - QueryDSL을 처음 써봤는데 신세계였다. QueryDSL을 자유자재로 쓰려면 JPA자체를 잘 이해하고 있어야 할 것 같다는 느낌이 들었다. Spring Data JPA를 해봤으니 기본적인 JPA를 공부하고싶다.
  - AWS를 사용해보면서 우분투, 리눅스를 처음 접했는데 그것도 신세계였다. 리눅스를 더 잘하고싶다.
  - 프론트는 사용자 입장에서 매우 중요한 요소인 만큼 정말 어렵다.
