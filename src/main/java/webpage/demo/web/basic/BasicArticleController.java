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
        articleRepository.save(new Article(1L,"A", "제목A", "내용B", "sun" ));
    }
}
