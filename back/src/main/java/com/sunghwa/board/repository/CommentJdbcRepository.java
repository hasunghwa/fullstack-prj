package com.sunghwa.board.repository;

import com.sunghwa.board.domain.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentJdbcRepository implements CommentRepository{
    private final JdbcTemplate jdbcTemplate;

    public CommentJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        List<Comment> result = jdbcTemplate.query("select * from comment where postId = ?", commentRowMapper(), postId);
        return result;
    }

    @Override
    public Comment insert(Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("comment").usingGeneratedKeyColumns("commentId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", comment.getWriter());
        parameters.put("contents", comment.getContents());
        parameters.put("createdDate", comment.getCreatedDate());
        parameters.put("postId", comment.getPostId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        comment.setCommentId(key.longValue());
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        jdbcTemplate.update("update comment set contents = ? where commentId = ?",
                comment.getContents(), comment.getCommentId());
        return comment;
    }

    @Override
    public Long delete(Long commentId) {
        jdbcTemplate.update("delete from comment where commentId = ?", commentId);
        return commentId;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setCommentId(rs.getLong("commentId"));
            comment.setContents(rs.getString("contents"));
            comment.setWriter(rs.getString("writer"));
            comment.setCreatedDate(rs.getDate("createdDate").toLocalDate());
            comment.setPostId(rs.getLong("postId"));
            return comment;
        };
    }
}
