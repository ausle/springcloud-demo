package com.asule.springcloud.dao;


import com.asule.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/*mapper接口使用@Res@Repository无法扫描加载到spring容器中。这里需使用@Mapper*/
@Mapper
public interface PaymentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    int insertSelective(Payment record);

    Payment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}