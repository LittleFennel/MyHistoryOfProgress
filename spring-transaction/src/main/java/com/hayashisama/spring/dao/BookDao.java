package com.hayashisama.spring.dao;

/**
 * @program: MyHistoryOfProgress
 * @ClassName BookDao
 * @Description:
 * @Author: HayashiSama
 * @Create: 2023-03-08 17:46
 * @Version 1.0
 **/
public interface BookDao {
    // 根据图书ID查询图书价格
    Integer getPriceByBookId(Integer bookId);

    // 更新图书库存
    void updateStock(Integer bookId);

    // 更新用户余额
    void updateBalance(Integer userId, Integer price);
}
