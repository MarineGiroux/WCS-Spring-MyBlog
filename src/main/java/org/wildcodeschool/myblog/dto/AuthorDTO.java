package org.wildcodeschool.myblog.dto;

import jakarta.persistence.*;
import org.wildcodeschool.myblog.model.ArticleAuthor;

import java.util.List;

public class AuthorDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private List<ArticleAuthorDTO> articleAuthors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<ArticleAuthorDTO> getArticleAuthors() {
        return articleAuthors;
    }

    public void setArticleAuthors(List<ArticleAuthorDTO> articleAuthors) {
        this.articleAuthors = articleAuthors;
    }
}
