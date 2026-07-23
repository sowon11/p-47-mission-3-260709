package com.ll.wiseSaying;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm");

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
    public String getCreateDate(){
        if(createDate == null) return "";
        return createDate.format(FORMATTER);
    }

    // 수정일 가져오기
    public String getModifyDate(){
        if(modifyDate == null) return "";
        return modifyDate.format(FORMATTER);
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
