package bitcamp.myapp;

import bitcamp.myapp.config.AppConfig;
import bitcamp.util.ApplicationContext;
import bitcamp.util.DispatcherServlet;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

//Reactor Netty 사용하여 웹 서버 구현하기


//ServerApp : 웹 서버 생성하고 실행하는 역할
//ServerHandler : Netty를 기반으로 하는 웹 서버에서 클라이언트의 요청을 처리하는 핸들러 클래스
//DispatcherServlet : 요청에 맞는 서블릿을 찾아서 실행

// 1) ServerApp 클래스가 웹 서버를 생성 및 실행
// 2) 클라이언트의 요청이 들어오면 ServerHandelr클래스가 요청을 처리
// 3) DispatcherServlet 클래스가 처리된 클라이언트의 요청을 서블릿으로 전달하고 응답을 생성하여 클라이언트에게 반환


public class ServerApp {

	  ApplicationContext iocContainer;
	  //iocContainer = 스프링 컨테이너, 애플리케이션 컨텍스트 관리
	  DispatcherServlet dispatcherServlet;
	  //dispatcherServlet 멤버 변수 선언

	  int port;
	  //포트번호
	  
	  public ServerApp(int port) throws Exception {
	    this.port = port;
	    iocContainer = new ApplicationContext(AppConfig.class);
	    dispatcherServlet = new DispatcherServlet(iocContainer);
	  }

	  public void close() throws Exception {

	  }

	  public static void main(String[] args) throws Exception {
	    ServerApp app = new ServerApp(8888);
	    app.execute();
	    app.close();
	  }

	  public void execute() throws Exception { //execute = 웹 서버 생성 및 실행하는 메소드 
		    DisposableServer server = HttpServer //Reactor Netty HTTP 서버 생성
		        .create()
		        .port(8888) //서버가 리스닝할 포트 번호 설정
		        .handle((request, response) -> processRequest(request, response))
		        .bindNow();
		    System.out.println("서버 실행됨!");

		    server.onDispose().block();
		    System.out.println("서버 종료됨!");
	  }

	  private NettyOutbound processRequest(HttpServerRequest request, HttpServerResponse response) {
	    try {
	    	HttpServletRequest request2 = new HttpServletRequest(request);
	        HttpServletResponse response2 = new HttpServletResponse(response);
	    	//HttpServletRequest, HttpServletResponse 객체 생성
	    	//server -> servlet 으로 가공해서 객체생성
	    	//Reactor Netty 객체를 서블릿의 객체로 변환하여 서블릿을 실행할 수 있음
	        
	       
	        dispatcherServlet.service(request2, response2);
	        //서블릿 객체를 이용하여 dispatcherServlet의 service 메서드를 호출
	    	
	    	
	        response.addHeader("Content-Type", response2.getContentType());
	        //getContentType() 메서드를 이용하여 서블릿이 생성한 응답의 컨텐츠 타입(html,MIME등)을 얻어옴
	        //얻어온 컨텐츠 타입을 HTTP 응답 프로토콜의 헤더에 추가
	    	
	    	return response.sendString(Mono.just(response2.getContent()));
	    	//서블릿이 생성한 응답 데이터를 얻어옴
	    	//얻어온 응답 데이터를 mono타입으로 변환
	    	//mono데이터를 HTTP프로토콜에 맞춰 클라이언트로 전송
	    	//서버가 처리한 결과를 클라이언트에게 반환함!
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return response.sendString(Mono.just("Error!"));
	    } finally {
//	      SqlSessionFactoryProxy sqlSessionFactoryProxy =
//	          (SqlSessionFactoryProxy) iocContainer.getBean(SqlSessionFactory.class);
//	      sqlSessionFactoryProxy.clean();
	    }
	  }
	}