package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Food;

public interface FoodDao {
	List<Food> findAllFoods();
	Food findFoodByNo(int no);
}
