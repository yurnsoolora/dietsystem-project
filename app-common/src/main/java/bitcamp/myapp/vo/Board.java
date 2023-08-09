package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Board implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private char meal;
//  private String food;
  private String content;
  private Food diet;
  private Member writer;
  private String password;
  private int viewCount;
  private Timestamp createdDate;

private int calories;
//  private int foodNo;

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
    Board other = (Board) obj;
    return no == other.no;
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
    
  }
  
  public char getMeal() {
      return meal;
  }
  public void setMeal (char meal) {
      this.meal = meal;
  }
  
//  public String getTitle() {
//    return title;
//  }
//  public void setTitle(String title) {
//    this.title = title;
//  }
//  
  
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  
//  public int getFoodNo() {
//	    return foodNo;
//	  }
//	  
//  public void setFoodNo(int foodNo) {
//    this.foodNo = foodNo;
//  }

  public Food getDiet() {
	  return diet;
  }
  
  public void setDiet(Food diet) {
	  this.diet = diet;
  }
  
public Member getWriter() { 
    return writer;
    //member객체 반환
    //writer = member의 새로운 이름!
    //객체의 이름을 writer로 설정하여 작성자의 정보를 담고 있다는것을 표현
  }
  public void setWriter(Member writer) { 
	  this.writer = writer;
	//반환된 작성자 정보 설정
	//이제부터 board객체는 member객체의 인스턴스들을 사용하거나 조작할 수 있다. 
  }
  
  public void setCalories(int calories) {
      this.calories = calories;
  }
  
  //음식정보
//  public Food getDiet() {
//	  return diet;
//  }
//  
//  public void setDiet(Food diet) {
//	  this.diet = diet;
//  }
  
  public int getViewCount() {
    return viewCount;
  }
  
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public Timestamp getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
}
