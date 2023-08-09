package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
	
    private static final long serialVersionUID = 1L;

	private int foodNo;
	private String food;
	private int calories;
	
	@Override
	  public int hashCode() {
	    return Objects.hash(foodNo);
	  }
	
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Food other = (Food) obj;
	    return foodNo == other.foodNo;
	  }
	  
	  public Food(int foodNo, String food, int calories) {
	        this.foodNo = foodNo;
	        this.food = food;
	        this.calories = calories;
	    }

	    public int getFoodNo() {
	        return foodNo;
	    }

	    public String getFood() {
	        return food;
	    }

	    public int getCalories() {
	        return calories;
	    }
	}

	
