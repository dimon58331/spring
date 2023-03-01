//package com.spring.course.entity;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BookMapper implements RowMapper<Book> {
//    @Override
//    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Book book = new Book();
//
//        book.setBookId(rs.getInt("book_id"));
//        book.setBookName(rs.getString("book_name"));
//        book.setAuthor(rs.getString("author"));
//        book.setReleaseYear(rs.getInt("release_year"));
//
//        return book;
//    }
//}
