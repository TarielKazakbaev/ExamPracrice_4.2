import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Connection1 {
    Scanner scan = new Scanner(System.in);

    private final String url ="jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "19041988";

    public Connection connect(){
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public  void insertNews(News news){
        String SQL = "INSERT INTO \"exam_practice_4.2\".news"+
                "( news_headline,news_text )"+
                " VALUES ( ?, ?); ";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1,news.getNews_headline());
            stmt.setString(2,news.getNews_text());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void selectNews(News news){
        String SQL = "SELECT * FROM \"exam_practice_4.2\".news";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println("|"+rs.getString("news_headline")+"|"+rs.getString("news_text")+"|");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void deleteNews(News news){
        String SQL = "DELETE  FROM \"exam_practice_4.2\".news WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1,news.getId());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void updateNews(News news){
        String SQL = "UPDATE \"exam_practice_4.2\".news SET " +
                "news_headline = ? , news_text =? " +
                "where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1,news.getNews_headline());
            stmt.setString(2,news.getNews_text());
            stmt.setInt(3,news.getId());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void chooseAction(Connection1 connection1){
        News news = new News();
         System.out.println("1)- создания новой новости.\n" +
                            "2)- отображения новости (заголовок и текст)\n" +
                            "3)- удаления новости по Id\n" +
                            "4)- изменения текста и заголовка новости, по Id");
        int enterAction1 = scan.nextInt();
        if(enterAction1==1){
            System.out.println("Введите название заголовки");
            String enterAction2 = scan.next();
            System.out.println("Введите текст");
            String enterAction3 = scan.next();
            news.setNews_headline(enterAction2);
            news.setNews_text(enterAction3);
            connection1.insertNews(news);
        }
        else if(enterAction1==2){
            connection1.selectNews(news);
        }
        else if(enterAction1==3){
            System.out.println("Какую новость хотите удалить? Введите номер ID");
            int enterAction = scan.nextInt();
            news.setId(enterAction);
            connection1.deleteNews(news);
        }
        else if(enterAction1==4){
            System.out.println("Какую загаловку хотите изменить? Введите номер ID");
            int enterAction = scan.nextInt();
            news.setId(enterAction);
            System.out.println("Введите новый заголовок");
            String enterAction2 = scan.next();
            news.setNews_headline(enterAction2);
            System.out.println("Введите новый текст");
            String enterAction3 = scan.next();
            news.setNews_text(enterAction3);
            connection1.updateNews(news);
        }
    }
}
