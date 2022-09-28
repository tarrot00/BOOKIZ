package com.ssafy.bookiz.controller;

import com.ssafy.bookiz.domain.Book;
import com.ssafy.bookiz.domain.BookDto;
import com.ssafy.bookiz.domain.RequestBookContent;
import com.ssafy.bookiz.service.BookCategoryService;
import com.ssafy.bookiz.service.BookContentService;
import com.ssafy.bookiz.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookCategoryService bookCategoryService;

    @Autowired
    BookContentService bookContentService;

    private ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() throws Exception {
        try {
            List<Object> allBooks = bookService.findAll();
            if (allBooks.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Object>>(allBooks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getBooksByCategoryId(@RequestParam Long id) {
        try {
            List<Object> books = bookCategoryService.getBooksByCategoryId(id);
            return new ResponseEntity<List<Object>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/rank")
    public ResponseEntity<?> getBestBooks() {
        try {
            List<Object> books = bookService.getBestBooks();
            return new ResponseEntity<List<Object>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<?> getNewBooks() {
        try {
            List<Object> books = bookService.getNewBooks();
            return new ResponseEntity<List<Object>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getBookContents(@RequestParam Long id) {
        try {
            List<Object> books = bookContentService.getBookContents(id);
            return new ResponseEntity<List<Object>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getBook(@RequestParam Long id) {
        try {
            BookDto book = bookService.findById(id);
//            BookDto bookDto = modelMapper.map(book, BookDto.class);
//            bookDto.setId(book.getId());
//            bookDto.setCreate_date(book.getCreate_date());
//            bookDto.setTitle(book.getTitle());
//            bookDto.setImage(book.getImage());
//            bookDto.setInfo(book.getInfo());
//            bookDto.setCnt(book.getCnt());
//            bookDto.setPage(book.getPage());

            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam String word) {
        try {
            List<Object> books = bookService.findAllByTitle(word);
            if (books.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Object>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/pluscnt")
    public ResponseEntity<?> plusCnt(@RequestBody BookDto bookDto) {
        BookDto book = bookService.plusCnt(bookDto.getId());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book bookInput) {
        System.out.println("addBook 호출");
        //List<Object> books = bookService.findAllByTitle(bookInput.getTitle());
        Book book = bookInput;
//        if(books.size() > 0) {
//            BookDto bookDto = (BookDto) books.get(0);
//            book = bookService.findById2(bookDto.getId());
//        }else {
//            book = bookInput;
//        }
        bookService.addBook(book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addContents")
    public ResponseEntity<?> addContents(@RequestBody List<RequestBookContent> reqs) {
        System.out.println("addContents 호출");
        Book book = bookService.findById2(reqs.get(0).getBook_id());
        bookContentService.addContents(reqs, book);
        return new ResponseEntity(HttpStatus.OK);
    }
}
