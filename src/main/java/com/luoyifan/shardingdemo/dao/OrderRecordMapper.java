package com.luoyifan.shardingdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoyifan.shardingdemo.model.OrderRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author EvanLuo
 * @date 2018/11/12 16:26
 */
@Mapper
public interface OrderRecordMapper extends BaseMapper<OrderRecord> {
}
