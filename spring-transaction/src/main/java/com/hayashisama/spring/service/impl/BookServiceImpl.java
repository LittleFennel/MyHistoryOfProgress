package com.hayashisama.spring.service.impl;

import com.hayashisama.spring.dao.BookDao;
import com.hayashisama.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: MyHistoryOfProgress
 * @ClassName BookServiceImpl
 * @Description:
 * @Suthor: HayashiSama
 * @Create: 2023-03-08 17:45
 * @Version 1.0
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    @Transactional(
            // readOnly = true
            // timeout = 3
            // noRollbackFor = ArithmeticException.class
            noRollbackForClassName = "java.lang.ArithmeticException"
    )
    public void buyBook(Integer userId, Integer bookId) {

        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // 查询图书的价格
        Integer price =  bookDao.getPriceByBookId(bookId);

        // 更新图书的库存
        bookDao.updateStock(bookId);

        // 更新用户的余额
        bookDao.updateBalance(userId, price);

        System.out.println(1 / 0);
    }
}
