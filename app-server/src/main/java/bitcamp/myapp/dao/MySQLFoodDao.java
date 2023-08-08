package bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.myapp.vo.Food;

public class MySQLFoodDao implements FoodDao {
	
	  SqlSessionFactory sqlSessionFactory;

	  public MySQLFoodDao(SqlSessionFactory sqlSessionFactory) {
	    this.sqlSessionFactory = sqlSessionFactory;
	  }
	  
	@Override
	public List<Food> findAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
		 return sqlSession.selectList("bitcamp.myapp.dao.FoodDao.findAll");
	}

	@Override
	public Food findCaloriesByFoodNo(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession(false);
		 return sqlSession.selectOne("bitcamp.myapp.dao.FoodDao.findCaloriesByFoodNo", no);
	}

}
