package com.sunghwa.board.repository;

import com.sunghwa.board.domain.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostJdbcRepository implements PostRepository{
    private final JdbcTemplate jdbcTemplate;

    public PostJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from post", postRowMapper());
    }

    @Override
    public Optional<Post> findById(Long postId) {
        List<Post> result = jdbcTemplate.query("select * from post where postId = ?", postRowMapper(), postId);
        return result.stream().findAny();
    }

    @Override
    public Post insert(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("postId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", post.getWriter());
        parameters.put("title", post.getTitle());
        parameters.put("contents", post.getContents());
        parameters.put("createdDate", post.getCreateDate());
        parameters.put("countOfAnswer", post.getCountOfAnswer());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setPostId(key.longValue());

        return post;
    }

    @Override
    public Post update(Post post) {
        jdbcTemplate.update("update post set title = ?, contents = ? where postId = ?",
                post.getTitle(), post.getContents(), post.getPostId());
        return post;
    }

    @Override
    public Long delete(Long postId) {
        jdbcTemplate.update("delete from post where postId = ?", postId);
        return postId;
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getLong("postId"));
            post.setWriter(rs.getString("writer"));
            post.setTitle(rs.getString("title"));
            post.setContents(rs.getString("contents"));
            post.setCreateDate(rs.getDate("createdDate").toLocalDate());
            post.setCountOfAnswer(rs.getInt("countOfAnswer"));
            return post;
        };
    }
}
