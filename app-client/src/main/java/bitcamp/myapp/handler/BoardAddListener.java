package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener {

  BoardDao boardDao;
  //BoardDao 인터페이스를 구현한 객체를 담을 변수 boardDao 선언

  //생성자 정의
  //생성자의 역할 = BoardDao객체를 인자로 받아서 boardDao변수에 저장함
  public BoardAddListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();
    //Board 객체 생성
    board.setTitle(prompt.inputString("제목? "));
    //사용자로부터 제목을 입력받고 board객체의 제목에 저장
    board.setContent(prompt.inputString("식단? "));
    //사용자로부터 내용을 입력받고 board객체의 내용에 저장
    Member writer = new Member(); 
    //Member 객체 생성
    writer.setNo(prompt.inputInt("작성자? ")); 
    //사용자로부터 작성자를 <번호>로 입력받아서 member객체의 번호에 저장
    board.setWriter(writer);
    //setWriter = Board클래스의 member객체를 반환하는 게터세터
    //작성자정보를 board 객체에 저장
    //결과적으로, board객체에는 입력받은 작성자 번호에 해당하는 회원의 정보가 저장됨
    //board, member 정보가 서로 연결되어 관리됨
    
    board.setPassword(prompt.inputString("암호? "));
    //사용자로부터 암호를 입력받아서 board객체의 비밀번호에 저장

    boardDao.insert(board);
    //MySQLBoardDao의 insert()메소드 호출
    //insert() 메소드 안의 쿼리가 실행되고,
    //위에서 입력받아 저장한 값들이 insert됨
  }
}


