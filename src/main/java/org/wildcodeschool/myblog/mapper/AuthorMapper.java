package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.ArticleAuthorDTO;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.model.Author;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstname(author.getFirstname());
        authorDTO.setLastname(author.getLastname());
        if (author.getArticleAuthors() != null) {
            List<ArticleAuthorDTO> articleAuthorDTOs = author.getArticleAuthors().stream()
                    .map(articleAuthor -> {
                        ArticleAuthorDTO dto = new ArticleAuthorDTO();
                        dto.setId(articleAuthor.getId());
                        dto.setContribution(articleAuthor.getContribution());
                        return dto;
                    })
                    .collect(Collectors.toList());
            authorDTO.setArticleAuthors(articleAuthorDTOs);
        }
        return authorDTO;
    }

    public List<AuthorDTO> convertToDTOList(List<Author> authors) {
        return authors.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
