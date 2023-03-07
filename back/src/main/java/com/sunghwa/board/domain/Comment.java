package com.sunghwa.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private Long commentId;//    commentId 			bigint				auto_increment,
    private String writer;//    writer				varchar(30)			NOT NULL,
    private String contents;//    contents			varchar(5000)		NOT NULL,
    private LocalDate createdDate;//    createdDate			timestamp			NOT NULL,
    private Long postId;//    postId			bigint				NOT NULL,

    public Comment() {};

    public Comment(Long commentId, String contents) {
        this.commentId = commentId;
        this.contents = contents;
    }

    public Comment(String writer, String contents, LocalDate createdDate, Long postId) {
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.postId = postId;
    }
}
