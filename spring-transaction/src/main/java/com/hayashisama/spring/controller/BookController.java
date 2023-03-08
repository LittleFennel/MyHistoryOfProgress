package com.hayashisama.spring.controller;

import com.hayashisama.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @program: MyHistoryOfProgress
 * @ClassName BookController
 * @Description: 书籍类控制层组件
 * @Author: HayashiSama
 * @Create: 2023-03-08 17:42
 * @Version 1.0
 **/
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(Integer userId, Integer bookId) {
        bookService.buyBook(userId, bookId);
    }
}
