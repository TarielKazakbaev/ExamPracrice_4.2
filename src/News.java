import java.sql.Date;
import java.time.LocalDateTime;

public class News {
    private Integer id;
    private String news_headline;
    private String news_text;
    private Integer date;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNews_headline() {
        return news_headline;
    }

    public void setNews_headline(String news_headline) {
        this.news_headline = news_headline;
    }

    public String getNews_text() {
        return news_text;
    }

    public void setNews_text(String news_text) {
        this.news_text = news_text;
    }

}
