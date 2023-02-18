package com.spring.course.repository;

import com.spring.course.entity.Book;
import com.spring.course.entity.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, author, release_year) VALUES(?, ?, ?)"
                , book.getBookName(), book.getAuthor(), book.getReleaseYear());
    }

    public Optional<Book> findById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new BookMapper(), id)
                .stream().findAny();
    }

    public void update(Book book){
        jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, release_year=? WHERE book_id=?"
                , book.getBookName(), book.getAuthor(), book.getReleaseYear(), book.getBookId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public List<Book> findAllByPersonId(int personId){
        return jdbcTemplate
                .query("SELECT Book.* FROM Book JOIN Person " +
                                "ON Book.person_id=Person.person_id WHERE Person.person_id=?"
                , new BookMapper(), personId);
    }

    public void updateByPersonId(Book book, int personId){
        Optional<Book> optionalBook = findById(book.getBookId());
        optionalBook.ifPresent(value -> {
            if (personId == 0){
                jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, release_year=?, person_id=? WHERE book_id=?"
                    , value.getBookName(), value.getAuthor(), value.getReleaseYear(), null, value.getBookId());

            }else{
                jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, release_year=?, person_id=? WHERE book_id=?"
                    , value.getBookName(), value.getAuthor(), value.getReleaseYear(), personId, value.getBookId());
            }
        });
    }


}
