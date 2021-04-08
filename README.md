# REST API를 이용한 Admin Page Api 제작
---

## 01. 수행 프로젝트

- 프로젝트명

  > Admin Page API

- 결과물 형태

  > 웹애플리케이션

- 수행 기간

  > 2021.02 ~ 2021.03 (총 2개월)

- 주요 언어

  > Java<br>
  > JPA<br>
  > REST API<br>
  > mysql

- 프레임워크

  > Spring Boot

- 팀 구성

  > 총 1명 (개인 프로젝트)

- 개발 참여율

  > 100%

- 결과물

  > 소스코드 : [Github](https://github.com/dhdbswl/RESTful_AdminPage)<br>
  > 결과보고서 : [pdf](https://drive.google.com/file/d/1YzgT8xBnfnDl4Wx6kAIzSH6NnObdR0IR/view?usp=sharing)

---

## 02. 결과물 디테일

- Entity 연관관계 설정

> 각 Entity끼리의 연관관계를 설정하고자 <@OneToMany>, <@ManyToOne> Annotation을 사용하여 정의

```java
// User : OrderGroup -> 1 : N
@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
private List<OrderGroup> orderGroupList;
```

- 등록자 데이터 자동 생성

  > AuditorAware를 implements 받아 각 필드에 생성자 이름을 자동으로 설정하는 클래스 생성

```java
@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Autowired
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer");
    }
}
```

- ResponseBody 공통부

  > 클라이언트 서버와 통신하기 위한 데이터를 JSON 형태로 처리<br>
  > API 통신시간, 응답 코드, 부가 설명, DATA부로 이루어짐<br>

- ResponseBody Data부

  > CRUD로 생성된 데이터를 클라이언트 서버와 통신하기 위해 JSON 형태로 처리<br>
  > Lombok @Builder를 사용하여 객체 return

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    // json body에 대해서 header 부분 공통으로 가져갈 때

    // api 통신시간
    private LocalDateTime transactionTime;
    // api 응답 코드
    private String resultCode;
    // api 부가 설명
    private String description;
    // data부
    private T data;

    // 정상적인 통신 -> ok
    public static <T> Header<T> OK() {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // data가 존재할 경우 data를 가지는 ok
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // 비정상적인 통신 -> error
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
```

- HTTP Method

  > HTTP Method(POST, GET, PUT, DELETE)를 사용하여 CRUD를 수행하는 함수 작성

- 추상 클래스

  > 클래스마다 반복적인 CRUD를 표준화시키고자 공통 함수를 추상 클래스로 작성

```java
// 추상 class controller 설정
@Component
@Slf4j
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

    // 상속받는 클래스에서만 접근 가능
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
```

- Enum을 사용한 데이터 값 관리

  > JPA Enum을 사용하여 변하는 형태의 값을 지정해두고 Entity 내에서 매핑 후 사용<br>
  > 상태를 나타내는 필드 데이터 값을 저장할 때에 주로 사용 (예: 개인 유저의 가입 상태 유무)

```java
@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0, "등록상태", "사용자 등록 상태"),
    UNREGISTERED(1, "해지", "사용자 해지 상태");

    private Integer id;
    private String title;
    private String description;
}
```
