package eunju.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import eunju.domain.MemberVO;
import eunju.persistence.MemberDAO;
import eunju.service.MemberService;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		HttpSession session = request.getSession();
		String cmd;
		cmd = request.getParameter("key");
		if("logout".equals(cmd)) {
			if(session!=null) {
				session.invalidate();
				response.sendRedirect("/eunju_free/index.jsp");
			}
		}else if("register".equals(cmd)) {
			RequestDispatcher view = request.getRequestDispatcher("/notificationcreate.jsp");
			view.forward(request, response);
		}else if("mypage".equals(cmd)) {
			RequestDispatcher view = request.getRequestDispatcher("/successlogin.jsp");
			view.forward(request, response);
		}else if("gotohome".equals(cmd)) {
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
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
			
		String cmd;
		cmd = request.getParameter("key"); 
		
		if("join".equals(cmd)) {
			String memberloginID, memberPassword, memberName, memberBirth;
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = new MemberVO();
			
			memberloginID = request.getParameter("memberloginID");
			memberPassword = request.getParameter("memberPassword");
			memberName = request.getParameter("memberName");
			memberBirth = request.getParameter("memberBirth");
			
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(memberBirth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			

			
			memberVO.setMemberBirth(sqlDate);
			memberVO.setMemberloginID(memberloginID);
			memberVO.setMemberID(1);
			
			memberVO.setMemberName(memberName);
			memberVO.setMemberPassword(memberPassword);
			
			memberDAO.add(memberVO);

			request.setAttribute("member", memberVO);
			
			RequestDispatcher view = request.getRequestDispatcher("successjoin.jsp");
			view.forward(request, response);
		
		}
		else if("login".equals(cmd)) {

			String loginID, loginPassword;
			MemberVO memberVO = new MemberVO();
			MemberService memberservice = new MemberService();
			
			loginID = request.getParameter("memberloginID");
			loginPassword = request.getParameter("memberPassword");
			
			memberVO.setMemberloginID(loginID);
			memberVO.setMemberPassword(loginPassword);
			
			memberVO = memberservice.login(memberVO);
			if(memberVO != null) {
				session.setAttribute("name", memberVO.getMemberName());
				session.setAttribute("memberVO", memberVO);
				session.setAttribute("ID", memberVO.getMemberloginID());
				session.setAttribute("pk", memberVO.getMemberID());
				System.out.println(memberVO.getMemberID());
				
				
				if(memberservice.isAdmin(memberVO)) {
					session.setAttribute("admin", memberVO.getMemberID());
				}
				request.setAttribute("member", memberVO);
				
				
				RequestDispatcher view = request.getRequestDispatcher("/successlogin.jsp");
				view.forward(request, response);
			}else {
				response.sendRedirect("loginfail.html");
			}
			
		}
		else if("createNotification".equals(cmd)) {
			
		}
		
		
	}

}
