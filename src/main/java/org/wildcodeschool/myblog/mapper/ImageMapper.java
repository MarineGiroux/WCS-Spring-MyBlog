package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.ImageDTO;
import org.wildcodeschool.myblog.model.Article;
import org.wildcodeschool.myblog.model.Image;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    public ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return imageDTO;
    }

    public List<ImageDTO> convertToDTOList(List<Image> images) {
        return images.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Image convertToEntity(ImageDTO imageDTO) {
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setUrl(imageDTO.getUrl());
        if (imageDTO.getArticleIds() != null) {
            List<Article> articles = imageDTO.getArticleIds().stream()
                    .map(articleId -> {
                        Article article = new Article();
                        article.setId(articleId);
                        return article;
                    })
                    .collect(Collectors.toList());
            image.setArticles(articles);
        }

        return image;
    }

    public List<Image> convertToEntityList(List<ImageDTO> imageDTOs) {
        return imageDTOs.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
