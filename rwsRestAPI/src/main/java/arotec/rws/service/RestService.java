package arotec.rws.service;

import java.util.List;
import java.util.Map;

import arotec.rws.vo.PointVO;

public interface RestService {
	public List<Map<String, Object>> getMultiLine();
	public List<Map<String, Object>> getMultiPoly();
	public List<PointVO> getPoint();
}
