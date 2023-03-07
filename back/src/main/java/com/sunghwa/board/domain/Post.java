package com.sunghwa.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Post {
    private Long postId;//    questionId 			bigint				auto_increment,
    private String writer;//    writer				varchar(30)			NOT NULL,
    private String title;//    title				varchar(50)			NOT NULL,
    private String contents;//    contents			varchar(5000)		NOT NULL,
    private LocalDate createDate;//    createdDate			timestamp			NOT NULL,
    private int countOfAnswer;//    countOfAnswer int,

    public Post() {}

    public Post(Long postId, String title, String contents) {
        this.postId = postId;
        this.title = title;
        this.contents = contents;
    }

    public Post(String writer, String title, String contents, LocalDate createDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.countOfAnswer = countOfAnswer;
    }
}
