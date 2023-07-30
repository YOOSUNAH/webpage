package webpage.demo.article.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import webpage.demo.article.domain.Article;
import webpage.demo.article.domain.ArticleRepository;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
public class ArticleController {
    private final ArticleRepository repo;

    public ArticleController(ArticleRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/items")
    public String articleForm(Model model, HttpSession session) {
        List<Article> articles = repo.findAll();
        model.addAttribute("articles", articles);
        session.setAttribute("loginUser", true);
        return "items.html";
    }



    @GetMapping("/items/{itemId}")
    public String articleForm(Model model, @PathVariable Long itemId) {
        Article article = repo.findById(itemId);
        model.addAttribute("article", article);
        return "item";
    }

// ---------------------------------------------------------------//
    @GetMapping("/write")
    public String write() {
        return "addForm";
    }
    @PostMapping("/write")
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
        return "/items";
    }
}


// GET - 가져오다 ... 페이지 요청, 데이터 조회
// POST - 보낸다 ... 자원 생성, 데이터를 입력한다
// PUT - 넣는다 ... 데이터를 넣는것 -> 전체 모든 데이터를 주는 것
// PATCH - 부분적으로 수정한다 ... 데이터를 넣는 것 -> 일부 데이터만 주는 것
// DELETE - 삭제s

