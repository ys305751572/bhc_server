package com.gcs.sysmgr.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.gcs.sysmgr.service.GenericManager;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.sysmgr.vo.TableReturnJson;


@SuppressWarnings("unchecked")
public class GenericManagerImpl<E, D extends IBaseJpaRepository<E>> implements GenericManager<E>{

	private D entityDAO;
	@Autowired
	public EntityManagerFactory entityManagerFactory;
	public GenericManagerImpl() {
	}

	/***
	 * 反射获取entiyDao
	 * @return
	 */
	public D getEntityDAO() {
		if(entityDAO==null){
			try {
				Field field = this.getClass().getDeclaredFields()[0];
				boolean accessible = field.isAccessible();
				field.setAccessible(true);
				entityDAO = (D) field.get(this);
				field.setAccessible(accessible);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			};
		}
		return this.entityDAO;
	}


	/****
	 * 查询分页实现
	 * @param entity  实体类
	 * @param start  开始数
	 * @param length  长度
	 * @param sort  排序
	 * @param order
	 * @return
	 */
	public Page<E> query(E entity, PageParameters pp) {
//		暂时写死，按照时间排序
		pp.setSort("createTime");
		pp.setSSortDir_0("desc");
		Order o = new Order(Direction.DESC, pp.getSort());
		if ("asc".equals(pp.getSSortDir_0().toLowerCase())) {
			o = new Order(Direction.ASC, pp.getSort());
		}
		Specification<E> spec = buildSpecification(entity);
		Page<E> page = getEntityDAO().findAll(spec, new PageRequest(pp.getStart(),pp.getLength(),new Sort(o)));
		return page;
	}
	
	/**
	 * 按照指定字段查询指定条数的记录
	 */
	
	public List<E> queryTop(String sort,boolean isAsc,int count){
		Order o = new Order(Direction.DESC, sort);
		if(isAsc){
			o = new Order(Direction.ASC, sort);
		}
		Page<E> page = getEntityDAO().findAll(new PageRequest(0,count,new Sort(o)));
		return page.getContent();
	}
	
	
	/**
	 * 按照指定字段查询指定条数的记录
	 */
	
	public List<E> queryTop(String sort,final String propertyName,final Object value,boolean isAsc,int count){
		Order o = new Order(Direction.DESC, sort);
		if(isAsc){
			o = new Order(Direction.ASC, sort);
		}
		Page<E> page = getEntityDAO().findAll(new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  List<Predicate> list = new ArrayList<Predicate>();  
				  if(value!=null){
					  list.add(cb.equal(root.get(propertyName), value));
				  }
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}},new PageRequest(0,count,new Sort(o)));
		return page.getContent();
	}
	
	/****
	 * 分页规则构建
	 * 	public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
	 *			CriteriaBuilder cb) {
	 *			  List<Predicate> list = new ArrayList<Predicate>();  
	 *			  if(value!=null){
	 *			  list.add(cb.equal(root.get(propertyName), value));
	 *		  }
	 *		  cb.not
	 *		    if(um.getName()!=null && um.getName().trim().length()>0){  
	 *		        list.add(cb.like(root.get("name").as(String.class), "%"+um.getName()+"%"));  
	 *		    }  
	 *		    if(um.getUuid()>0){  
	 *		        list.add(cb.equal(root.get("uuid").as(Integer.class), um.getUuid()));  
	 *		    }  
	 *		    Predicate[] p = new Predicate[list.size()];  
	 *		    return cb.and(list.toArray(p));  
	 *	}
	 * @param entity 实体类
	 * @return
	 */
	public Specification<E> buildSpecification(E entity) {
		return new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  	List<Predicate> list = new ArrayList<Predicate>();  
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}};
	}


	public void delete(E object) {
		getEntityDAO().delete(object);
	}


	public void deleteByPK(Serializable id) {
		getEntityDAO().delete(id);
	}


	public void deleteByPKs(Serializable[] ids) {
		for(Serializable s:ids){
			getEntityDAO().delete(s);
		}
		
	}


	public boolean exists(Serializable id) {
		return getEntityDAO().exists(id);
	}


	public Class getEntityClass() {
		return null;
	}


	public Class getIdClass() {
		return null;
	}


	public String getIdName() {
		return null;
	}


	public E insert(E object) {
		return getEntityDAO().saveAndFlush(object);
	}


	public boolean isUnique(Object entity, String... uniquePropertyName) {
		return false;
	}


	public List<E> query(String hql, Object... values) {
		return null;
	}


	public List<E> query(Criterion... criterions) {
		return null;
	}


	public List<E> queryAll() {
		return getEntityDAO().findAll();
	}


	public List<E> queryAll(String orderBy, boolean isAsc) {
		Order o ;
		if(isAsc){
			o = new Order(Direction.ASC, orderBy);
		}else{
			o = new Order(Direction.DESC, orderBy);
		}
		Sort s = new Sort(o);
		return this.getEntityDAO().findAll(s);
	}


	public E queryByPK(Serializable id) {
		return getEntityDAO().findOne(id);
	}


	public List<E> queryByProperty(final String propertyName, final Object value) {
		return getEntityDAO().findAll(new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  List<Predicate> list = new ArrayList<Predicate>();  
				  if(value!=null){
					  list.add(cb.equal(root.get(propertyName), value));
				  }
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}});
	}


	public List<E> queryByProperty(final String propertyName, final Object value,String orderBy, boolean isAsc) {
		Order o ;
		if(isAsc){
			o = new Order(Direction.ASC, orderBy);
		}else{
			o = new Order(Direction.DESC, orderBy);
		}
		Sort s = new Sort(o);
		return getEntityDAO().findAll(new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  List<Predicate> list = new ArrayList<Predicate>();  
				  if(value!=null){
					  list.add(cb.equal(root.get(propertyName), value));
				  }
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}},s);
	}


	public E queryUniqueBy(final String propertyName, final Object value) {
		return getEntityDAO().findOne(new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  List<Predicate> list = new ArrayList<Predicate>();  
				  if(value!=null){
					  list.add(cb.equal(root.get(propertyName), value));
				  }
				    Predicate[] p = new Predicate[list.size()];  
				    return cb.and(list.toArray(p));  
			}});
	}


	public E save(E object) {
		return  getEntityDAO().save(object);
	}


	public E update(E object) {
		return null;
	}

	public List<E> query(final Predicate... predicate) {
		return getEntityDAO().findAll(new Specification<E>(){
			public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				  List<Predicate> list = new ArrayList<Predicate>(); 
				  for(Predicate p:predicate){
				  list.add(p);
				  }
				  Predicate[] p = new Predicate[list.size()];  
				  return cb.and(list.toArray(p));  
			}});
	}
	
	
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
	public TableReturnJson scroll(final int firstindex, final int maxresult, final String sql) {
		TableReturnJson qr = new TableReturnJson();
		List retrunList = new ArrayList();
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createNativeQuery(sql);
		query.setFirstResult(firstindex);
		query.setMaxResults(maxresult);
		retrunList = query.getResultList();
		qr.setTotal(getCount(sql));
		qr.setRows(retrunList);
		em.close();
		return qr;
	}
	
	public long getCount(String sql){
		EntityManager em = entityManagerFactory.createEntityManager();
		String countSql = "select count(*) as count from ("+sql+") t";
		Query query = em.createNativeQuery(countSql);
		Long l = Long.valueOf(query.getSingleResult().toString());
		em.close();
		return l;
	}

	public long count() {
		return getEntityDAO().count();
	}
	
	public Page<E> queryPage(final int firstindex, final int maxresult, final String sql) {
		
		return null;
	}
}
