package com.luoyifan.shardingdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luoyifan.shardingdemo.dao.OrderRecordMapper;
import com.luoyifan.shardingdemo.model.OrderRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author EvanLuo
 * @date 2018/11/12 16:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingTest {

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Test
    public void testInsert() {
        IntStream.range(2017, 2020)
                .forEach(year -> {
                    IntStream.range(0, 10)
                            .forEach(i -> {
                                OrderRecord r = new OrderRecord();
                                r.setProductName(year + "-product-" + i);
                                r.setOrderTime(LocalDateTime.of(year, i + 1, 1, 0, 0, 0));
                                orderRecordMapper.insert(r);
                            });
                });
    }

    @Test
    public void testSelect() {
        IntStream.range(2017, 2020)
                .forEach(year -> {
                    List<OrderRecord> orderRecords = orderRecordMapper.selectList(new LambdaQueryWrapper<OrderRecord>()
                            .like(OrderRecord::getOrderTime, year));
                    System.out.println("==========" + year + "==========");
                    orderRecords.forEach(System.out::println);
                });
    }

    @Test
    public void testSelect2() {
        List<OrderRecord> orderRecords = orderRecordMapper.selectList(new LambdaQueryWrapper<OrderRecord>()
                .eq(OrderRecord::getOrderTime, LocalDateTime.of(2019,1,1,0,0,0)));
        orderRecords.forEach(System.out::println);
    }
}