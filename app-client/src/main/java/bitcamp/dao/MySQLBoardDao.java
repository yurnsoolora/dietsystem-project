package bitcamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;

public class MySQLBoardDao implements BoardDao {
	
  //데이터베이스 연결을 위한 connection 객체 선언
  //connection객체를 담을 변수 con 선언
  Connection con;
  
  //생성자 정의
  //생성자의 역할 = Connection 객체를 인자로받아서 con변수에 저장함
  public MySQLBoardDao(Connection con) {
    this.con = con;
  }

  @Override 
  //MySQLBoardDao 클래스가 BoardDao 인터페이스를 구현했음
  //BoardDao 인터페이스에 정의된 insert() 메서드를 오버라이딩
  //어노테이션이 없다면, 새로운 메소드를 정의한 것으로 간주될 수 있음
  public void insert(Board board) { 
  //insert 메서드 정의 (게시글 추가)
  //Board 객체 = BoardAddListner에서 입력받아온 값을 인자로 받아서 데이터베이스에 저장
  //반환값 없음
    try (PreparedStatement stmt = con.prepareStatement( //PreparedStatement객체 사용(쿼리 미리 컴파일하여 성능개선, 보안강화, 유지보수)
          "insert into myapp_board(title,content,writer,password)"
              + " values(?,?,?,sha1(?))")) {
    	//board테이블에 제목, 내용, 작성자, 비밀번호를 저장하라는 sql쿼리
    	// ? : placeholder = 사용자로부터 입력받는 값들이 들어감
    	// sha1(?) : 비밀번호를 해쉬함수로 암호화하여 저장
        stmt.setString(1, board.getTitle());
        //1번 물음표 = title = board객체에서 가져온 제목
        stmt.setString(2, board.getContent());
        //2번 물음표 = content= board객체에서 가져온 내용
        stmt.setInt(3, board.getWriter().getNo());
        //3번 물음표 = writer = board객체에서 가져온 작성자의 번호
        //board객체->member객체->작성자번호 가져오기
        stmt.setString(4, board.getPassword());
        //4번 물음표 = password = board객체에서 가져온 암호
        stmt.executeUpdate();
        //데이터베이스에 INSERT 쿼리가 실행되고, 새로운 행이 데이터베이스에 추가

    } catch (Exception e) { 
      throw new RuntimeException(e);
    }
  }

  @Override
  //MySQLBoardDao 클래스가 BoardDao 인터페이스를 구현했음
  //BoardDao 인터페이스에 정의된 list() 메서드를 오버라이딩
  public List<Board> list() {
	//list() 메서드 정의 (게시글 목록)
	//반환값 : Board객체의 리스트
    try (PreparedStatement stmt = con.prepareStatement(
    		// member, board 테이블 조인
            "select" +
            "  b.board_no, " +
            "  b.title, " +
            "  b.view_count, " +
            "  b.created_date, " +
            "  m.member_no, " +
            "  m.name " +
            " from " +
            "  myapp_board b inner join myapp_member m on b.writer=m.member_no" +
            " order by " +
            "  board_no desc"
            )) {

      try (ResultSet rs = stmt.executeQuery()) {
    	  //데이터베이스 쿼리를 실행한 결과를 저장하는 객체 rs 선언
    	  List<Board> list = new ArrayList<>();
    	  //Board객체를 저장하는 리스트 list 선언
      while (rs.next()) {
    	  //데이터베이스 행 끝날때까지 무한반복
        Board b = new Board();
        //board 객체 선언
        
        b.setNo(rs.getInt("board_no")); 
        //DB에서 가져온 게시글번호를 board객체 b에 저장
        b.setTitle(rs.getString("title"));
        //DB에서 가져온 제목을 b에 저장
        b.setViewCount(rs.getInt("view_count"));
        //DB에서 가져온 조회수를 b에 저장
        b.setCreatedDate(rs.getTimestamp("created_date"));
        //DB에서 가져온 날짜를 b에 저장

        Member writer = new Member();
        //member 객체 선언
        
        //member 테이블에서 읽어온 값들 writer에 저장
        writer.setNo(rs.getInt("member_no"));
        //DB에서 가져온 회원번호를 member객체 writer에 저장
        writer.setName(rs.getString("name"));
        //DB에서 가져온 이름을 member객체 writer에 저장
        b.setWriter(writer);
        //회원번호, 이름을 board객체 b에 저장

        list.add(b); //board객체 b를 리스트에 담기
      }
      return list; //리스트 반환
    }
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  //MySQLBoardDao 클래스가 BoardDao 인터페이스를 구현했음
  //BoardDao 인터페이스에 정의된 findby() 메서드를 오버라이딩
  //반환값 : Board 객체
  public Board findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
    		// member, board 조인
         "select" +
            "  b.board_no, " +
            "  b.title, " +
            "  b.content," +
            "  b.view_count, " +
            "  b.created_date, " +
            "  m.member_no, " +
            "  m.name " +
            " from " +
            "  myapp_board b inner join myapp_member m on b.writer=m.member_no" +
            "  board_no=?"
            )) {
    	
        stmt.setInt(1, no);
      //1번 물음표 = 사용자가 입력한 번호 no
        
      try (ResultSet rs = stmt.executeQuery()) {
    	//데이터베이스 쿼리를 실행한 결과를 저장하는 객체 rs 선언
      if (rs.next()) {
    	//위의 쿼리를 실행한 레코드가 존재하면
        Board b = new Board();
        //board 객체 선언
       
        //board 테이블에서 읽어온 값들 b에 저장
        b.setNo(rs.getInt("board_no"));
      //DB에서 가져온 게시글번호를 board객체 b에 저장
        b.setTitle(rs.getString("title"));
      //DB에서 가져온 제목을 board객체 b에 저장
        b.setContent(rs.getString("content"));
      //DB에서 가져온 내용을 board객체 b에 저장
        b.setViewCount(rs.getInt("view_count"));
      //DB에서 가져온 조회수를 board객체 b에 저장
        b.setCreatedDate(rs.getTimestamp("created_date"));
      //DB에서 가져온 날짜를 board객체 b에 저장

        Member writer = new Member();
        //member 객체 선언
        
        writer.setNo(rs.getInt("member_no"));
        //DB에서 가져온 회원번호를 member객체 writer에 저장
        writer.setName(rs.getString("name"));
        //DB에서 가져온 이름을 member객체 writer에 저장
        b.setWriter(writer);
        //회원번호, 이름을 board객체 b에 저장
        
        stmt.executeUpdate("update myapp_board set"
            + " view_count=view_count + 1"
            + " where board_no=" + no); 
        //update문 실행하여 조회수 컬럼값 1증가

        return b;
        //board객체 반환
        }
      return null;
      //위의 쿼리를 실행한 후 레코드가 존재하지 않으면(no에 해당하는 게시글을 찾지 못했다면)
      //null반환 = 게시글이 없다는 뜻
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  //MySQLBoardDao 클래스가 BoardDao 인터페이스를 구현했음
  //BoardDao 인터페이스에 정의된 update() 메서드를 오버라이딩
  //반환값 : int형 (0 혹은 다른숫자=update된 행개수)
  public int update(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
          "update myapp_board set"
              + " title=?,"
              + " content=?"
              + " where board_no=? and password=sha1(?)")) {

        stmt.setString(1, board.getTitle());
      //1번 물음표 = title = board객체에서 가져온 제목
        stmt.setString(2, board.getContent());
      //2번 물음표 = content = board객체에서 가져온 내용
        stmt.setInt(3, board.getNo());
      //3번 물음표 = no = board객체에서 가져온 번호
        stmt.setString(4, board.getPassword());
      //4번 물음표 = password = board객체에서 가져온 암호
        return stmt.executeUpdate();
        // update된 행 갯수 반환
        // 0을 반환한다 = update된 행이 없다 = 게시글 번호나 암호가 일치하지 않는다

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  //MySQLBoardDao 클래스가 BoardDao 인터페이스를 구현했음
  //BoardDao 인터페이스에 정의된 delete() 메서드를 오버라이딩
  //반환값 : int형 (0 혹은 다른숫자=delete된 행개수)
  public int delete(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
          "delete from myapp_board"
          + " where board_no=? and password=sha1(?)")) {
      
      stmt.setInt(1,board.getNo());
      // 1번 물음표 = no = board객체에서 가져온 번호
      stmt.setString(2,board.getPassword());
      // 2번 물음표 = password = board객체에서 가져온 암호

      return stmt.executeUpdate();
      // delete된 행 갯수 반환
      // 0을 반환한다 = delete된 행이 없다 = 게시글번호나 암호가 일치하지 않다.

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
