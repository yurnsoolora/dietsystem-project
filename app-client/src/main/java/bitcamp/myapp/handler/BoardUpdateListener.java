package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardUpdateListener implements ActionListener {

  BoardDao boardDao;
//BoardDao 인터페이스를 구현한 객체를 담을 변수 boardDao 선언

//생성자 정의
  //생성자의 역할 = BoardDao객체를 인자로 받아서 boardDao변수에 저장함
  public BoardUpdateListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");
  //사용자로부터 번호를 입력받아서 boardNo변수에 저장

    Board board = boardDao.findBy(boardNo);
    //MySQLBoardDao의 findBy()메소드 호출(매개변수 boardNo) -> board객체가 반환됨
    //반환된 board객체 board에 저장
    
    if (board == null) {
    	//findby()메소드 실행 후 반환값이 없다면(게시글이 없다면)
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

   //반환값이 있다면 입력받은 번호에 해당하는 게시글 update
    board.setTitle(prompt.inputString("제목(%s)? ", board.getTitle()));
  //사용자로부터 제목을 입력받고 board객체의 제목에 저장
    board.setContent(prompt.inputString("내용(%s)? ", board.getContent()));
  //사용자로부터 내용을 입력받고 board객체의 내용에 저장
    board.setPassword(prompt.inputString("암호? "));
  //사용자로부터 암호를 입력받고 board객체의 암호에 저장
    
    if (boardDao.update(board) == 0) {
    	//MySQLBoardDao의 update()메소드 호출(매개변수 board) -> 0 혹은 다른숫자중 반환됨
    	//0이 반환된다면 (암호가 일치하지 않거나 게시글을 못찾는경우)
      System.out.println("암호가 일치하지 않습니다!");
    } else { //다른 숫자가 반환된다면 (update된 행갯수)
      System.out.println("변경했습니다!");
      
      //update(board)하는 순간 update메소드 호출되고, update()메소드 안의 쿼리 실행됨 -> 게시글 업뎃
    }
  }
}











