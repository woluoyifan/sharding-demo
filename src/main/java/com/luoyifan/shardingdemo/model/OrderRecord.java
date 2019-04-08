package com.luoyifan.shardingdemo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author EvanLuo
 * @date 2018/11/12 16:25
 */
@Data
public class OrderRecord {
    @TableId
    private Long id;
    private String productName;
    @TableField("order_record.order_time")
    private LocalDateTime orderTime;
}
