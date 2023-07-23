package webpage.demo.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import webpage.demo.domain.Article;
import webpage.demo.domain.ArticleRepository;

@Slf4j
// @RestController
@Controller
@RequestMapping("/posts")
public class MappingClassController {

    // GET - 가져오다 ... 페이지 요청, 데이터 조회
    // POST - 보낸다 ... 자원 생성, 데이터를 입력한다
    // PUT - 넣는다 ... 데이터를 넣는것 -> 전체 모든 데이터를 주는 것
    // PATCH - 부분적으로 수정한다 ... 데이터를 넣는 것 -> 일부 데이터만 주는 것
    // DELETE - 삭제s

    @GetMapping("/write")
    public String write() {
        return "addForm";
    }
    @PostMapping("/write")
    public <model> String save(
            @RequestParam String category,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String writerName
    ) {
        Article article = new Article();
        article.setCategory(category);
        article.setTitle(title);
        article.setContent(content);
        article.setWriterName(writerName);

        ArticleRepository.save(article);
        return "addForm";
    }
    @GetMapping("/list")
    public String post() {
        return "listForm";
    }
//    @PostMapping("/list")
//    public

    @GetMapping("/{postId}")
    public String findPost(@PathVariable long postId) {
        Long articleId = null;
        Article article = ArticleRepository.findById(articleId);
        return "post posts";
    }
}


