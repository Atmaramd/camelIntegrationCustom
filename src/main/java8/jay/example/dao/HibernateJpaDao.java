package jay.example.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
public interface HibernateJpaDao<T,Id extends Serializable> {
	
		public void persist(T entity);
	
	    public void update(T entity);
	    
	    public T findById(T entity,Id id);
	
	    public void delete(T entity);
	
	    public List<T> findAll(T Entity);
	    
	    //public void deleteAll();
	    
	   // public T getWhereObject(Map<String, Object> map,T Entity) throws Exception;
	    
	    //public Integer count(T Entity) throws Exception;
	    
	    public List<T> getWhereListEnt(Map<String, Object> map,T Entity) throws Exception;

}
