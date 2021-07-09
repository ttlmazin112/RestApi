package arotec.rws.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arotec.rws.Rest.adminController;
import arotec.rws.dao.RestDao;
import arotec.rws.dao.adminPgDao;
import arotec.rws.vo.PointVO;

@Service
public class adminPgServiceImpl implements adminPgService {

	@Autowired
	adminPgDao adminDao;

	@Override
	public List<Map<String, Object>> getAllPgSelect() {
		// TODO Auto-generated method stub
		return adminDao.getAllPgSelect();
	}



}
