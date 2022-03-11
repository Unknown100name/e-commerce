package org.unknown100name.ecommercebackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.dto.EvaluateDTO;
import org.unknown100name.ecommercebackend.pojo.entity.Evaluate;

import java.util.List;

/**
 * @author unknown100name
 * @description 评论数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface EvaluateMapper extends BaseMapper<Evaluate> {

    /**
     * 根据 itemId 获取评价
     * @param itemId
     * @return
     */
    List<EvaluateDTO> getEvaluateByItemId(String itemId);
}
