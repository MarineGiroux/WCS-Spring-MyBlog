package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.exception.ResourceNotFoundException;
import org.wildcodeschool.myblog.mapper.AuthorMapper;
import org.wildcodeschool.myblog.model.Author;
import org.wildcodeschool.myblog.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.convertToDTOList(authors);
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'auteur avec l'id " + id + " n'a pas été trouvé"));
        return authorMapper.convertToDTO(author);
    }

    public AuthorDTO createAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.convertToDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'auteur avec l'id " + id + " n'a pas été trouvé"));

        author.setFirstname(authorDetails.getFirstname());
        author.setLastname(authorDetails.getLastname());
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.convertToDTO(updatedAuthor);
    }

    public boolean deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("L'auteur avec l'id " + id + " n'a pas été trouvé"));

        authorRepository.delete(author);
        return true;
    }

}
