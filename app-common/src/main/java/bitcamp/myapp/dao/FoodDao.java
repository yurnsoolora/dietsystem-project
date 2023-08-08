package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Food;

public interface FoodDao {
//	void insert(Food food);
	  List<Food> findAll();
	  Food findCaloriesByFoodNo(int no);
//	  int update(Board board);
//	  int updateCount(Board board);
//	  int delete(Board board);
}
