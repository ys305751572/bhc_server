package com.gcs.sysmgr.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.hibernate.criterion.Criterion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.sysmgr.vo.TableReturnJson;

@SuppressWarnings("unchecked")
public interface GenericManager<T> {
	/****
	 * 查询分页实现
	 * @param entity  实体类
	 * @param start  开始数
	 * @param length  长度
	 * @param sort  排序
	 * @param order
	 * @return
	 */
	public Page<T> query(T entity,PageParameters pp);
	
	
	/**
	 * 按照指定字段查询指定条数的记录
	 */
	
	public List<T> queryTop(String sort,boolean isAsc,int count);
	
	/****
	 * 分页规则构建
	 * if(StringUtils.isNotBlank(czexpert.getName())){
	 * 		filters.put("name",new SearchFilter("name", Operator.LIKE,czexpert.getName()) );
	 * }
	 * if(StringUtils.isNotBlank(czexpert.getTelephone())){
	 * 		filters.put("telephone",new SearchFilter("telephone",Operator.LIKE, czexpert.getTelephone()) );
	 * }
	 * @param entity 实体类
	 * @return
	 */	
	public Specification<T> buildSpecification(T t);
	/**
	 * 查询所有的对象
	 * @return
	 */
	List<T> queryAll();

	/**
	 * 查询所有对象，并进行排序
	 * @param orderBy  排序属性
	 * @param isAsc  是否asc
	 * @return
	 */
	List<T> queryAll(String orderBy, boolean isAsc);

	/**
	 * 根据查询语句进行查询
	 * @param hql 查询语句
	 * @param values  语句对应的值
	 * @return
	 */
	List<T> query(String hql, Object... values);

	/**
	 * 根据类属性进行查询
	 * @param propertyName   属性名
	 * @param value   属性对应的值
	 * @return
	 */
	List<T> queryByProperty(String propertyName, Object value);

	/**
	 * 根据类属性进行查询（支持排序）
	 * @param propertyName   属性名
	 * @param value   属性对应的值
	 * @param orderBy   排序字段
	 * @param isAsc    是否增序
	 * @return
	 */
	List<T> queryByProperty(String propertyName, Object value, String orderBy,
			boolean isAsc);

	/**
	 * 根据类属性进行查询，返回唯一对象
	 * @param propertyName    属性名
	 * @param value    属性对应的值
	 * @return
	 */
	T queryUniqueBy(String propertyName, Object value);

	/**
	 * 根据条件查询
	 * @param criterions
	 * @return
	 */
	List<T> query(Criterion... criterions);
	
	/**
	 * 根据条件查询
	 * @param criterions
	 * @return
	 */
	List<T> query(Predicate... predicate);
	
	boolean isUnique(Object entity, String... uniquePropertyName);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	T queryByPK(Serializable id);

	/**
	 * 对象是否存在
	 * @param id
	 * @return
	 */
	boolean exists(Serializable id);

	/**
	 * 新增对象保存
	 * @param object
	 * @return
	 */
	T insert(T object);

	T save(T object);

	/**
	 * 更新对象保存
	 * @param object
	 * @return
	 */
	T update(T object);

	/**
	 * 根据主键删除对象
	 * @param id
	 */
	void deleteByPK(Serializable id);

	/**
	 * 根据主键删除对象
	 * @param ids
	 */
	void deleteByPKs(Serializable[] ids);

	/**
	 * 删除对象
	 * @param object
	 */
	void delete(T object);

	public String getIdName();

	public Class getIdClass();

	public Class getEntityClass();
	
	public List<T> queryTop(String sort,final String propertyName,final Object value,boolean isAsc,int count);
	/**
	 * 根据条件查询某个实体的列表
	 * 
	 * @author zb
	 * @param firstindex
	 *            开始行
	 * @param maxresult
	 *            结束行
	 * @param sql
	 * 			  sql语句	
	 * @return
	 */
	public TableReturnJson scroll(final int firstindex, final int maxresult, final String sql);
	
	public long count();
}
