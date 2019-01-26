package jay.example.daoImpl;

import jay.example.dao.HibernateJpaDao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
//import org.springframework.orm.hibernate3.HibernateTemplate;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Primary
@Repository
public class HibernateJpaDaoImpl<T,Id extends Serializable> implements HibernateJpaDao<T,Id> {
//	@Autowired
//	SessionFactory sessionFactory;

	@Autowired
	EntityManager template;

		
	public void persist(T entity) {
		// TODO Auto-generated method stub
		template.persist(entity);
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		template.merge(entity);
	}

	public T findById(T Entity,Id id) {
		T newEntity = template.find((Class<T>) Entity, id);
//		Session session = sessionFactory.getCurrentSession();
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Entity> query = builder.createQuery(Entity.class);
//        Root<Entity> root = query.from(Entity.class);
//        query.select(root).where(builder.equal(root.get("id"), 1l));
//        Query<Entity> q=session.createQuery(query);
//        T newEntity = (T) q.getSingleResult();
		return newEntity;
	}
	
	public void delete(T entity) {
		// TODO Auto-generated method stub
		template.remove(entity);
	}

	public List<T> findAll(T Entity) {
		CriteriaBuilder builder = template.getCriteriaBuilder();
        CriteriaQuery<Entity> query = builder.createQuery(Entity.class);
        Root<T> root = (Root<T>) query.from(Entity.class);
        query.select((Selection<? extends javax.persistence.Entity>) root);
        Query<T> q=(Query<T>) template.createQuery(query);
        List<T> list=q.getResultList();
		return list;
	}

//	public void deleteAll() {
//		
//	}

//	public T getWhereObject(Map<String, Object> map, T Entity) throws Exception {
//		// TODO Auto-generated method stub
//		//Session session = template.getCurrentSession();
//		CriteriaQuery<Entity> criteriaQuery = template.getCriteriaBuilder().createQuery(Entity.class);
//        criteriaQuery.from(Entity.class);
//		return Entity;
//	}

//	public Integer count(T Entity) throws Exception {
//		
//		return null;
//	}
	
	@Transactional
	@Override
	public List<T> getWhereListEnt(Map<String, Object> map, T Entity) throws Exception {
		/*Session session = template.getCurrentSession();*/
		CriteriaBuilder builder = template.getCriteriaBuilder();
		CriteriaQuery<Entity> query = builder.createQuery(Entity.class);
		Root<T> root = (Root<T>) query.from(Entity.class);
		//String column = (String) map.get(cloumnName);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    System.out.println("Key and  : "  + key +"value is:"  + value );
		    if(key!=null) {
				query.select((Selection<? extends javax.persistence.Entity>) root).where(builder.equal(root.get(key), map.get(value)));
			}else {
				throw new Exception("Column does not exist.");
			}
		}
		Query<T> q = (Query<T>) template.createQuery(query);
		List<T> list = q.getResultList();
        return list;
	}

}
