package arotec.rws.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arotec.rws.dao.RestDao;
import arotec.rws.vo.PointVO;

@Service
public class RestServiceImpl implements RestService {

	@Autowired
	RestDao rdao;
	
	@Override
	public List<Map<String, Object>>  getMultiLine() {
		return rdao.getMultiLine();
	}

	@Override
	public List<Map<String, Object>> getMultiPoly() {
		return rdao.getMultiPoly();
	}

	@Override
	public List<PointVO> getPoint() {
		return rdao.getPoint();
	}

}
