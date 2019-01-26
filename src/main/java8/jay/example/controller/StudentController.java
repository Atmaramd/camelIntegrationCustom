package jay.example.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jay.example.service.StudentService;

@RestController
@RequestMapping(value="/studentRest")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value ="/",method = RequestMethod.GET)
	public String getGreetings() {
		System.out.println("Controller called..");
		return "Hi How Are you ?";
	}
	
	@RequestMapping(value = "/saveStudent",method = RequestMethod.POST,consumes = "text/plain")
	
	public @ResponseBody String saveStudent(@RequestBody String payload) {
		System.out.println("Payload is :"+payload ) ;
		JSONArray returnJsonArray = null;
		try {
			JSONArray parseJsonArray = new JSONArray(payload);
			JSONObject jsonObject = (JSONObject) parseJsonArray.get(0);
			returnJsonArray = studentService.save(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnJsonArray.toString();
	}
}
