package webpage.demo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import webpage.demo.article.domain.Article;
import webpage.demo.article.domain.ArticleRepository;

class ArticleRepositoryTest {
    ArticleRepository articleRepository = new ArticleRepository();

    @AfterEach
    void afterEach(){
        articleRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Article article = new Article(1L, "category1", "title1", "context1", "writer1");
        //when
        Article savedArticle = articleRepository.save(article);
        //then
        Article findArticle = articleRepository.findById(article.getId());
        Assertions.assertThat(findArticle).isEqualTo(savedArticle);
    }
}