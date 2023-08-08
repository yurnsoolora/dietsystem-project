package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
	
    private static final long serialVersionUID = 1L;

	private int no;
	private String food;
	private int calories;
	
	@Override
	  public int hashCode() {
	    return Objects.hash(no);
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
	    return no == other.no;
	  }
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
}
