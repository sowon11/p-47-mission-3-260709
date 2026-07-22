import java.time.LocalDateTime;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    // 생성자
    public WiseSaying(String content, String author){
        this.content = content;
        this.author = author;
    }

    // id가 있나 확인
    public boolean isNew(){
        return id == 0;
    }

    // id 가져오기
    public int getId(){
        return id;
    }

    // 명언 가져오기
    public String getContent(){
        return content;
    }

    // 작가 가져오기
    public String getAuthor(){
        return author;
    }

    // 작성일 가져오기
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    // 수정일 가져오기
    public LocalDateTime getModifyDate(){
        return modifyDate;
    }

    // id 설정
    public void setId(int id){
        this.id = id;
    }

    // 명언 설정
    public void setContent(String content){
        this.content = content;
    }

    // 작가 설정
    public void setAuthor(String author){
        this.author = author;
    }

    // 작성일 설정
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    // 수정일 설정
    public void setModifyDate(LocalDateTime modifyDate){
        this.modifyDate = modifyDate;
    }
}
