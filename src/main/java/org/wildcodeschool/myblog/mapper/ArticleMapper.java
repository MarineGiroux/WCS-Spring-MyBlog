package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.ArticleCreateDTO;
import org.wildcodeschool.myblog.dto.ArticleDTO;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryName(article.getCategory().getName());
        }
        if (article.getImages() != null) {
            articleDTO.setImageUrls(article.getImages().stream().map(Image::getUrl).collect(Collectors.toList()));
        }
        if (article.getArticleAuthors() != null) {
            articleDTO.setAuthors(article.getArticleAuthors().stream()
                    .filter(articleAuthor -> articleAuthor.getAuthor() != null)
                    .map(articleAuthor -> {
                        AuthorDTO authorDTO = new AuthorDTO();
                        authorDTO.setId(articleAuthor.getAuthor().getId());
                        authorDTO.setFirstname(articleAuthor.getAuthor().getFirstname());
                        authorDTO.setLastname(articleAuthor.getAuthor().getLastname());
                        return authorDTO;
                    })
                    .collect(Collectors.toList()));
        }
        return articleDTO;
    }

    public Article convertToEntity(ArticleCreateDTO articleCreateDTO) {
        Article article = new Article();
        article.setTitle(articleCreateDTO.getTitle());
        article.setContent(articleCreateDTO.getContent());
        article.setUpdatedAt(LocalDateTime.now());
        if (articleCreateDTO.getCategoryId() != null) {
            Category category = new Category();
            category.setId(articleCreateDTO.getCategoryId());
            article.setCategory(category);
        }
        if (articleCreateDTO.getImages() != null) {
            List<Image> images = articleCreateDTO.getImages().stream()
                    .map(imageDTO -> {
                        Image image = new Image();
                        image.setUrl(imageDTO.getUrl());
                        return image;
                    })
                    .collect(Collectors.toList());
            article.setImages(images);
        }
        if (articleCreateDTO.getAuthors() != null) {
            List<ArticleAuthor> articleAuthors = articleCreateDTO.getAuthors().stream()
                    .map(authorDTO -> {
                        ArticleAuthor articleAuthor = new ArticleAuthor();
                        Author author = new Author();
                        author.setId(authorDTO.getAuthorId());
                        articleAuthor.setAuthor(author);
                        articleAuthor.setContribution(authorDTO.getContribution());
                        return articleAuthor;
                    })
                    .collect(Collectors.toList());
            article.setArticleAuthors(articleAuthors);
        }

        return article;
    }

}