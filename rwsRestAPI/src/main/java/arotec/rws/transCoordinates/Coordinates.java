package arotec.rws.transCoordinates;

import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.CoordinateTransform;
import org.osgeo.proj4j.CoordinateTransformFactory;
import org.osgeo.proj4j.ProjCoordinate;

public class Coordinates {

public double[] xypoint(double x, double y){	
		
		double [] xy = new double[2];
		
		CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
		
		CRSFactory csFactory = new CRSFactory();
		
		CoordinateReferenceSystem EPSG5181 = csFactory.createFromParameters("EPSG:5181",
				"+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=GRS80 +units=m +no_defs");
		CoordinateReferenceSystem EPSG900913 = csFactory.createFromParameters("EPSG:900913",
				"+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +no_defs");
		
		CoordinateTransform trans = ctFactory.createTransform(EPSG5181,EPSG900913);
		ProjCoordinate point = new ProjCoordinate();
		ProjCoordinate point2 = new ProjCoordinate();
		
		point.x = x;
		point.y = y;
		trans.transform(point, point2);
		
		xy[0] = point2.x;
		xy[1] = point2.y;
		
		return xy;
	}

}
