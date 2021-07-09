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
public class adminOrDaoImpl implements adminOrDao {
	
	@Autowired
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	private static final String namespace = "arotec.rws.mapper.adminOrMapper";
	

	@Override
	public List<Map<String, Object>> getAllOrSelect() {
		
		return sqlSession.selectList(namespace+".getAllOrSelect");
	}

	

}
