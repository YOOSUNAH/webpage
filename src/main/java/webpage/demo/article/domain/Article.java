package webpage.demo.article.domain;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class Article {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String writerName;


    public Article(){

    }

    public Article(Long id, String category, String title, String content, String writerName) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
    }
}
