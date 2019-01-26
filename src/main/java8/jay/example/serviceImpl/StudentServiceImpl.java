package jay.example.serviceImpl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jay.example.bean.Student;
import jay.example.dao.HibernateJpaDao;
import jay.example.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	HibernateJpaDao<Student,Serializable>	studentDao;
	
	@Override
	@Transactional
	public JSONArray save(JSONObject request) throws Exception {
		Student stu = null;
		JSONObject jsonObj =null;
		JSONObject returnJSONObject=new JSONObject();
		JSONArray jsonArrayReturn=new JSONArray();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Student student = new Student();
			map.put("studentEmail", request.getString("stuEmail"));
			List<Student> list =null;
			//List<Student> list=studentDao.getWhereListEnt(map, new Student());
			if(list!=null) {
				returnJSONObject.put("message","Email Address Already Exist.");
				returnJSONObject.put("status","false");
				jsonArrayReturn.put(returnJSONObject);
				return jsonArrayReturn;
			}else {
				student.setStudentName(request.getString("stuName"));
				student.setStudentEmail(request.getString("stuEmail"));
				student.setStudentMobNo(request.getString("stuMobileNo"));
				student.setStuAddress(request.getString("stuAddress"));
				studentDao.persist(student);
				returnJSONObject.put("message","Student '"+student.getStudentName()+"' Successfully Registered.");
				returnJSONObject.put("status","true");
				jsonArrayReturn.put(returnJSONObject);
				return jsonArrayReturn;
			}
		}catch(Exception e) {
			e.printStackTrace();
			returnJSONObject.put("message","Internal Server Error..");
			returnJSONObject.put("status","false");
			jsonArrayReturn.put(returnJSONObject);
		}
		return jsonArrayReturn;
	}

	@Override
	public List<Student> getStudentList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
