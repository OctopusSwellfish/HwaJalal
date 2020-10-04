package eunju.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eunju.domain.*;
import eunju.persistence.*;
/**
 * Servlet implementation class NotificationServlet
 */
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		String cmd;
		cmd = request.getParameter("key");
		session.getAttribute("pk");
		if("list".equals(cmd)) {
			NotificationDAO notiDAO = new NotificationDAO();
			List<NotificationVO> notivoList =notiDAO.getNotificationList();
			request.setAttribute("NotificationList",  notivoList);
			RequestDispatcher view = request.getRequestDispatcher("notificationlist.jsp");
			view.forward(request,  response);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		
		System.out.println(session.getAttribute("id"));
		System.out.println(session.getAttribute("name"));
		System.out.println(session.getAttribute("memberVO"));
		String cmd;
		cmd = request.getParameter("key");
		if("createNotification".equals(cmd)) {
				String title, content;
				
				NotificationVO notificationVO = new NotificationVO();
				NotificationDAO notificationDAO = new NotificationDAO();
				
				RegisterNotificationVO registerVO = new RegisterNotificationVO();
				RegisterNotificationDAO registerDAO = new RegisterNotificationDAO();

				title = request.getParameter("title");
				content = request.getParameter("content");
				content.replaceAll("\r\n", "<br>");
				
				System.out.println(content);
				notificationVO.setNotificationTitle(title);
				notificationVO.setNotificationContent(content);

				NotificationVO nvo = notificationDAO.add(notificationVO);
				
				String pkSession = session.getAttribute("pk").toString();
				registerVO.setMemberID(Integer.parseInt(pkSession));
				registerVO.setNotificationID(nvo.getNotificationID());
				
				registerDAO.add(registerVO);
				
				

				request.setAttribute("notification", notificationVO);
				
				
				RequestDispatcher view = request.getRequestDispatcher("/successcreate.jsp");
				view.forward(request, response);
			//	notificationDAO.getNotificationList();
				
		}
	}

}
