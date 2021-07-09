package arotec.rws.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import arotec.rws.dao.adminOrDao;
import arotec.rws.dao.adminPgDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class adminController {

	@Inject
	adminPgDao adminDao;
	@Inject
	adminOrDao adminOrDao;

	@RequestMapping(value = "/adminPgViwe", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> adminViwe(HttpServletRequest request, HttpServletResponse response, Model model) {

		List<Map<String, Object>> pgList = adminDao.getAllPgSelect();
		List<Map<String, Object>> list1 = adminOrDao.getAllOrSelect();
		List<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();
		
		
		for(int i=0; i<pgList.size(); i++){
		String pgName= (String) pgList.get(i).get("table_name");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbName", "PgAdmin");
		map.put("tableName", pgName);
		allList.add(map);		
		}
		for(int j=0; j<list1.size(); j++){
		String oraName = (String) list1.get(j).get("Oracle");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dbName", "Oracle");
		map.put("tableName", oraName);
		allList.add(map);
		}
	
		//System.out.println(allList);
		
		Gson gson = new Gson();
		String result = gson.toJson(allList);		
		System.out.println(result);
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		responseHeaders.add("Access-Control-Allow-Origin", "*");

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/admin",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> admin(String list, HttpServletRequest req, HttpServletResponse res){
		List<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();		
		
	/*	String tableName = req.getParameter("tableName");		
		map.put("tableName", tableName);		*/
		//System.out.println(list);
		//allList.add(param);
		
	
		/*Map<String, Object> map = gson.fromJson(list, Map.class);
		System.out.println(map);
		 map.remove("dbName");
		Iterator<String> keys = map.keySet().iterator();
        String key;
        Object value;
		while (keys.hasNext()){
		Map<String, Object> map1 = new HashMap<String, Object>();	
             key = keys.next();            
             value = map.get(key);
            map1.put("table_name", value);
            allList.add(map1);
		}*/
		
		String result = list;	
		System.out.println(result);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	    responseHeaders.add("Access-Control-Allow-Origin", "*");
	    
	    return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
	}
	



}
