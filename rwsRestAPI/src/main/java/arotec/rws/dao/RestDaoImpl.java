package arotec.rws.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arotec.rws.vo.PointVO;

@Repository
public class RestDaoImpl implements RestDao {
	
	@Autowired
	@Resource(name="sqlSessionPg")
	private SqlSession sqlSessionPg;
	
	private static final String namespace = "arotec.rws.mapper.mapper";
	
	@Override
	public  List<Map<String, Object>>  getMultiLine() {
		return sqlSessionPg.selectList(namespace+".MultiLineString");
	}

	@Override
	public List<Map<String, Object>> getMultiPoly() {
		return sqlSessionPg.selectList(namespace+".MultiPolygon");
	}

	@Override
	public List<PointVO> getPoint() {
		return sqlSessionPg.selectList(namespace+".Point");
	}

}
