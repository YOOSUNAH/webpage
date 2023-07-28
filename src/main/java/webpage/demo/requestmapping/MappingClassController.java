package webpage.demo.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import webpage.demo.domain.Article;
import webpage.demo.domain.ArticleRepository;

import java.util.List;

@Slf4j
// @RestController
@Controller
@RequestMapping("/posts")
public class MappingClassController {
    private final ArticleRepository repo;

    public MappingClassController(ArticleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String article(Model model) {
        List<Article> article = repo.findAll();
        model.addAttribute("Article", article);
        return "post/list";
    }

   @GetMapping("/list")
    public String articleForm(Model model) {
        List<Article> articles = repo.findAll();
        model.addAttribute("articles", articles);
        return "listForm";
    }

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
    public String save() {
        return "addForm";
    }



    public <model> String addArticleV1(
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
        return "/list";
    }
}



