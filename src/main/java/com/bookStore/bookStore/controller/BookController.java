package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.service.BookService;
import com.bookStore.bookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookService myBookService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegsiter(){
        return "bookRegister";
    }

    @GetMapping("/avaliable_books")
    public ModelAndView avaliableBooks(){
        List<Book> list = bookService.getAllBook();
        ModelAndView m= new ModelAndView();
        m.setViewName("bookList");
        m.addObject("book", list);
        return m;
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        bookService.save(b);
        return "redirect:/avaliable_books";
    }

    @RequestMapping("/myList/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b = bookService.getBookById(id);
        MyBookList mb= new MyBookList(b.getId(), b.getName(), b.getAuthor(),b.getPrice());
        myBookService.saveMyBooks(mb);
       return "redirect:/my_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList> list = myBookService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id")int id, Model model) {
        Book b  = bookService.getBookById(id);
        model.addAttribute("book", b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteMyBook(@PathVariable("id") int id){
        bookService.deleteById(id);
        return "redirect:/avaliable_books";
    }
}
