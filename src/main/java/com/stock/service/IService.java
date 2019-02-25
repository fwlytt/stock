package com.stock.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

    //说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
    T selectByKey(Object key);

    //说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
    int save(T entity);

    //说明：根据主键字段进行删除，方法参数必须包含完整的主键属性
    int delete(Object key);

    //说明：根据主键更新实体全部字段，null值会被更新
    int updateAll(T entity);

    //根据主键更新属性不为null的值
    int updateNotNull(T entity);

    //说明：根据Example条件进行查询
    //重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
    List<T> selectByExample(Object example);
}
