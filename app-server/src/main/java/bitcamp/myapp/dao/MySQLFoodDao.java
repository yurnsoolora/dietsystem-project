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
	  public List<Food> findAllFoods() {
		  try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
		        return sqlSession.selectList("bitcamp.myapp.dao.FoodDao.findAllFoods");
		    }
	  }
		  
	  @Override
	    public Food findFoodByNo(int no) { // 추가된 메서드
	        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	            return sqlSession.selectOne("bitcamp.myapp.dao.FoodDao.findFoodByNo", no);
	        }
	    }
//	  @Override
//	    public int findCaloriesByFoodName(String foodName) {
//	        SqlSession sqlSession = sqlSessionFactory.openSession(false);
//	        return sqlSession.selectOne("bitcamp.myapp.dao.FoodDao.findCaloriesByFoodName", foodName);
//	    }

}
