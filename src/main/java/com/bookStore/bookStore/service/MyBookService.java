package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    @Autowired
    private MyBookRepository myBook;

    public void saveMyBooks(MyBookList book){
        myBook.save(book);
    }

    public List<MyBookList> getAllMyBooks(){
        return myBook.findAll();
    }

    public void deleteById(int id){
        myBook.deleteById(id);
    }
}
