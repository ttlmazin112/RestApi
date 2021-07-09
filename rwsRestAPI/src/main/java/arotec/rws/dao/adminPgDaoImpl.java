package arotec.rws.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arotec.rws.Rest.adminController;
import arotec.rws.vo.PointVO;

@Repository
public class adminPgDaoImpl implements adminPgDao {
	
	@Autowired
	@Resource(name="sqlSessionPg")
	private SqlSession sqlSessionPg;
	
	private static final String namespace = "arotec.rws.mapper.adminPgMapper";
	

	@Override
	public List<Map<String, Object>> getAllPgSelect() {
		
		return sqlSessionPg.selectList(namespace+".getAllPgSelect");
	}

	

}
