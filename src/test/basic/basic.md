## 스프링 부트 개념 정리
### 문자를 눌렀을 때 원하는 링크로 연결시키는 방법
* controller class에는 @GetMapping을 만들고
* resource 밑에 templates에는 링크를 눌렀을 때 보여주고 싶은 화면의 내용이 담긴 html 파일이 있어야 한다.

* @GetMapping(”/write”)
* 위와 같이 GetMapping 뒤에는 url end 포인트를 써준다.
* 만약 GetMapping 위에 @RequestMapping(”/posts”)를 써두면
* GetMapping 주소와 만나 -> url end 포인트가 /posts/write 가 된다.

* return “addForm” 이라고 하면
* templates에 있는 addForm.html 파일을 연다는 뜻이다.

>GET - 가져오다 ... 페이지 요청, 데이터 조회 
POST - 보낸다 ... 자원 생성, 데이터를 입력한다 
 PUT - 넣는다 ... 데이터를 넣는것 -> 전체 모든 데이터를 주는 것 
PATCH - 부분적으로 수정한다 ... 데이터를 넣는 것 -> 일부 데이터만 주는 것 
DELETE - 삭제

* 입력한 값들을 저장해야하는 데이터를 입력할 때는 @PostMapping 을 쓴다.
* (ex) 게시글을 작성한다던지, 아이디 패스워드를 입력한다던지 등

``` @RequestParam 입력한 데이터를 가져온다.
` @PostMapping("/write")
  public String save(
  @RequestParam String category,
  @RequestParam String title,
  @RequestParam String content,
  @RequestParam String  writerName,
  Model model
  ) {

        Article article = new Article();
        article.setCategory(category);
        article.setTitle(title);
        article.setContent(content);
        article.setWriterName(writerName);

        ArticleRepository.save(article);

        model.addAttribute("article",article);

        return "item";
  } 
  ```

* Model은 model.addAttribute(”article”, article); 이렇게 해야지
* item.html에서  아래처럼 (article)데이터에 접근할 수 있다.

```
<div>
 <label for="category">카테고리</label>
<input type="text" id="category" name="category" class="form-control"
value="A" th:value="${article.category}" readonly>
</div>
```

* Thymeleaf를 쓰기 위해서는 위에서 아래와 같이 선언한 이후에 사용해야 한다.
```
<html xmlns:th="http://www.thymeleaf.org">
 ```

* thymeleaf 문법은 찾아보면서 사용하면 된다. th:   ${.  }

```agsl
<h2 th:if="${param.status}" th:text="'저장완료'"></h2>

<tr th:each="article : ${articles}">
                <td>
                    <button th:onclick="goDetailPage([[${article.id}]])">
                        [[${article.id}]]
                    </button>
                </td>
                <td th:text="${article.category}"></td>
                <td th:text="${article.title}"></td>
                <td th:text="${article.content}"></td>
                <td th:text="${article.writerName}"></td>
            </tr>
```

* script 안에는 무조건 자바 스크립트 를 써야 한다.
* <script>



