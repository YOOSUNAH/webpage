package webpage.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class Article {
    private Long id;
    private String category;
    private String title;
    private String context;
    private String writer;


    public Article(){

    }

    public Article(Long id, String category, String title, String context, String writer) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.context = context;
        this.writer = writer;
    }
}
