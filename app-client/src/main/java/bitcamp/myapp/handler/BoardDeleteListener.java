package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardDeleteListener implements ActionListener {

  BoardDao boardDao;
//BoardDao 인터페이스를 구현한 객체를 담을 변수 boardDao 선언
  
 //생성자 정의
  //생성자의 역할 = BoardDao객체를 인자로 받아서 boardDao변수에 저장함
  public BoardDeleteListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {

    Board b = new Board();
    //Board객체 생성
    b.setNo(prompt.inputInt("번호? "));
    //사용자로부터 번호를 입력받아서 board객체에 저장
    b.setPassword(prompt.inputString("암호? "));
    //사용자로부터 번호를 입력받아서 board객체에 저장

    if (boardDao.delete(b) == 0) {
	//MySQLBoardDao의 delete()메소드 호출(매개변수 board) -> 0 혹은 다른숫자중 반환됨
	//0이 반환된다면 (암호가 일치하지 않거나 게시글을 못찾는경우)
      System.out.println("해당 번호의 게시글이 없거나 암호가 맞지 않습니다.");
    } else { //다른 숫자가 반환된다면 (delete된 행갯수)
      System.out.println("삭제했습니다.");
 
      //delete(b)하는 순간 delete메소드 호출되고, delete()메소드 안의 쿼리 실행됨 -> 게시글 삭제
    }
  }
}


