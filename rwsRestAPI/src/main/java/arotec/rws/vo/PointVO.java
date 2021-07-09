package arotec.rws.vo;

import org.postgis.Geometry;

public class PointVO {
	private int sngid;
	private Object _geometry;
	
	public int getSngid() {
		return sngid;
	}
	public void setSngid(int sngid) {
		this.sngid = sngid;
	}
	public Object getGeom() {
		return _geometry;
	}
	public void setGeom(Object geom) {
		this._geometry = geom;
	}
	
	
	
}
