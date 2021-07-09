package arotec.rws.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;
import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.MultiPolygon;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.google.gson.Gson;


import arotec.rws.service.RestService;
import arotec.rws.transCoordinates.Coordinates;
import arotec.rws.vo.PointVO;

@Controller
public class GeomController {

	@Autowired
	RestService rservice;

	@RequestMapping(value = "/getMultiLine", method = RequestMethod.GET)
	public ResponseEntity<String> getMultiLine(HttpServletRequest req, HttpServletResponse res) {

		List<Map<String, Object>> multiline = rservice.getMultiLine();
		List<List<List<double[]>>> ml =new ArrayList<>(); 
		Gson gson = new Gson();
		double x = 0;
		double y = 0;
		
		for (int k = 0; k < multiline.size(); k++) {
		List<List<double[]>> ml1 = new ArrayList<>();
			Object id=multiline.get(k).get("sngid");
			PGgeometry geom = (PGgeometry) multiline.get(k).get("_geometry");
			MultiLineString point = (MultiLineString) geom.getGeometry();
			for (int i = 0; i < point.numLines(); i++) {
				List<double[]> ml2 = new ArrayList<>();
				LineString lineObj = point.getLine(i);
				for (int j = 0; j < lineObj.numPoints(); j++) {
					Point p = lineObj.getPoint(j);
					x = point.getLine(i).getPoint(j).getX();
					y = point.getLine(i).getPoint(j).getY();

					Coordinates cd = new Coordinates();
					
					
					double[] data = cd.xypoint(x, y); ;
				
					ml2.add(data);
					
					point.getLine(i).getPoint(j).setX(data[0]);
					point.getLine(i).getPoint(j).setY(data[1]);

				}
				ml1.add(ml2);
			}
			ml.add(ml1);
			
		}
			
		String result = gson.toJson(multiline);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8"); 
		responseHeaders.add("Access-Control-Allow-Origin", "*"); 

		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/getMultiPoly", method = RequestMethod.GET)
	public ResponseEntity<String> getMultiPoly(HttpServletRequest req, HttpServletResponse res) {

		List<Map<String, Object>> multipoly = rservice.getMultiPoly();
	
		Gson gson = new Gson();

		for (int i = 0; i < multipoly.size(); i++) {
			PGgeometry geom = (PGgeometry) multipoly.get(i).get("_geometry");
			MultiPolygon mp = (MultiPolygon) geom.getGeometry();

			for (int r = 0; r < mp.numPolygons(); r++) {
				Polygon polygonObj = mp.getPolygon(r);
				// System.out.println(multipoly.get(i).get("gid"));
				for (int p = 0; p < polygonObj.numPoints(); p++) {
					Point pt = polygonObj.getPoint(p);
					double x = mp.getPolygon(r).getPoint(p).getX();
					double y = mp.getPolygon(r).getPoint(p).getY();
					
				/*	Coordinates cd = new Coordinates();
					double[] xy =cd.xypoint(x, y);
					
					mp.getPolygon(r).getPoint(p).setX(xy[0]);
					mp.getPolygon(r).getPoint(p).setY(xy[1]);
				*/

				}
			
			}
		
		}

		String result = gson.toJson(multipoly);
				
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8"); 
		responseHeaders.add("Access-Control-Allow-Origin", "*"); 
		return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/getPoint", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getPoint(HttpServletRequest req, HttpServletResponse res){
				
		List<PointVO> list = rservice.getPoint(); // dao에서 VO로 리스트 데이터 넣어줌  
		
		for(int i=0; i<list.size(); i++){
			PGgeometry pt = (PGgeometry) list.get(i).getGeom();
			int id=list.get(i).getSngid();
			
			double x = pt.getGeometry().getPoint(0).getX();
			double y = pt.getGeometry().getPoint(0).getY();
			
			Coordinates cd = new Coordinates();
			double[] xy=cd.xypoint(x, y);
			
			
			
			pt.getGeometry().getPoint(0).setX(xy[0]);
			pt.getGeometry().getPoint(0).setY(xy[1]);
		}
		
		Gson gson = new Gson(); 

		String result  = gson.toJson(list);
	    
		HttpHeaders responseHeaders = new HttpHeaders(); 
	    
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
	    responseHeaders.add("Access-Control-Allow-Origin", "*"); 
	    
	    return new ResponseEntity<String>(result, responseHeaders, HttpStatus.CREATED);	    
	}

}
