package jay.example.service;

import jay.example.bean.Student;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public interface StudentService {
	
	public JSONArray save(JSONObject request) throws Exception;
	
	public List<Student> getStudentList(Map<String, Object> map);
	
}
