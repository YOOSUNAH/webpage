package webpage.demo.web.basic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webpage.demo.domain.Article;
import webpage.demo.domain.ArticleRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/article")
@RequiredArgsConstructor
public class BasicArticleController {
    private final ArticleRepository articleRepository;


    @GetMapping
    public String article(Model model){
        List<Article> article = articleRepository.findAll();
        model.addAttribute("article", article);
        return("/posts/article");
    }
    @PostConstruct
    public void init(){
        final List<Article> articles = List.of(
                new Article(1L, "A", "제목 A", "내용 A", "sun"),
                new Article(2L, "B", "제목 B", "내용 B", "sun"),
                new Article(3L, "C", "제목 C", "내용 C", "sun"),
                new Article(4L, "A", "제목 A", "내용 A", "sun"),
                new Article(5L, "B", "제목 B", "내용 B", "sun"),
                new Article(6L, "C", "제목 C", "내용 C", "sun"),
                new Article(7L, "A", "제목 A", "내용 A", "sun"),
                new Article(8L, "B", "제목 B", "내용 B", "sun"),
                new Article(9L, "C", "제목 C", "내용 C", "sun"),
                new Article(10L, "A", "제목A", "내용 A", "sun")
        );

        articleRepository.saveAll(articles);
    }
}
