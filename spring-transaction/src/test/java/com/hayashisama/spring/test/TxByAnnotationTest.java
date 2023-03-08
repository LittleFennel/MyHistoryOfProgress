package com.hayashisama.spring.test;

import com.hayashisama.spring.controller.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: MyHistoryOfProgress
 * @ClassName TxByAnnotationTest
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-03-08 19:20
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx-annotation.xml")
public class TxByAnnotationTest {

    @Autowired
    private BookController bookController;

    @Test
    public void tesyByBook() {
        bookController.buyBook(1, 1);
    }
}
