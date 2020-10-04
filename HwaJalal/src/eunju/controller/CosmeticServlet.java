package eunju.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eunju.domain.CosmeticVO;
import eunju.domain.RegisterCosmeticVO;
import eunju.persistence.CosmeticDAO;
import eunju.persistence.RegisterCosmeticDAO;
import eunju.service.CosmeticService;

/**
 * Servlet implementation class CosmetieServlet
 */
@WebServlet("/CosmeticServlet")
public class CosmeticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CosmeticServlet() {
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
		if("showmyall".equals(cmd)) {
			
			String temp = session.getAttribute("pk").toString();
			
			CosmeticService cosService = new CosmeticService();
			
			List<CosmeticVO> cosVOList = cosService.getMyCosmeList(Integer.parseInt(temp));
			
			
			request.setAttribute("CosmeticList", cosVOList);
			
			
			RequestDispatcher view = request.getRequestDispatcher("/AllCosmetics.jsp");
			view.forward(request, response);
		}else if("showalldata".equals(cmd)) {
			CosmeticDAO cosDAO = new CosmeticDAO();
		
			List<CosmeticVO> cosVOList = cosDAO.getCosmeticList();
			
			request.setAttribute("AllCosmeticLists", cosVOList);
			
			RequestDispatcher view = request.getRequestDispatcher("/showAllData.jsp");
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
		if("register".equals(cmd)) {
			String cosmeticName, cosmeticBrand, cosmeticType, cosmeticShape;
			CosmeticDAO cosDAO = new CosmeticDAO();
			CosmeticVO cosVO = new CosmeticVO();
			
			RegisterCosmeticDAO regisDAO = new RegisterCosmeticDAO();
			RegisterCosmeticVO regisVO = new RegisterCosmeticVO();
			
			cosmeticName = request.getParameter("cosmeticname");
			cosmeticBrand = request.getParameter("cosmeticbrand");
			cosmeticType = request.getParameter("cosmetictype");
			cosmeticShape = request.getParameter("cosmeticshape");
			
			cosVO.setCosmeticName(cosmeticName);
			cosVO.setCosmeticBrand(cosmeticBrand);
			cosVO.setCosmeticType(cosmeticType);
			cosVO.setCosmeticShape(cosmeticShape);
			
			java.util.Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("cosmeticopendate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			cosVO.setCosmeticOpendate(sqlDate);
			
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("cosmeticexpirationdate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(date.getTime());
			
			cosVO.setCosmeticExpirationdate(sqlDate);
			
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("cosmeticopenexpirationdate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(date.getTime());
			
			cosVO.setCosmeticOpenExpirationdate(sqlDate);
			
			
			
			CosmeticVO cvo = cosDAO.add(cosVO);
			
			String pkSession = session.getAttribute("pk").toString();
			
			regisVO.setMemberID(Integer.parseInt(pkSession));
			regisVO.setCosmeticID(cvo.getCosmeticID());
			
			regisDAO.add(regisVO);
			
			request.setAttribute("cosmetic", cosVO);
			
			RequestDispatcher view = request.getRequestDispatcher("successregister.jsp");
			view.forward(request, response);
			
			
			
		}

	}

}
