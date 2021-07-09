package arotec.rws.dao;

import java.util.List;
import java.util.Map;

import arotec.rws.vo.PointVO;

public interface RestDao {

	public List<Map<String, Object>>  getMultiLine();
	public List<Map<String, Object>> getMultiPoly();
	public List<PointVO> getPoint();
	
	
}
