package webpage.demo.article.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
    private static final Map<Long, Article> store = new HashMap<>();
    private static long sequence = 0L;

    public Article save(Article article){
        article.setId(++sequence);
        store.put(article.getId(), article);
        return article;
    }

    public Article findById(Long id){
        return store.get(id);
    }
    public List<Article> findAll(){
        return new ArrayList<>(store.values());
    }
    public void update(Long articleId, Article updateParam){
        Article findArticle = findById(articleId);
        findArticle.setCategory(updateParam.getCategory());
    }

    public void clearStore(){
        store.clear();
    }


    public void saveAll(List<Article> articles) {
        for (Article article : articles) {
            store.put(article.getId(), article);
        }

    }
}
