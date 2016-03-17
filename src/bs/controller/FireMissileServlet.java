package bs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsmodel.BsGame;

/**
 * Servlet implementation class FireMissileServlet
 */
@WebServlet("/FireMissileServlet")
public class FireMissileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FireMissileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clickedRow = request.getParameter("clickedRow");
		String clickedColumn = request.getParameter("clickedColumn");
		BsGame battleshipBean = (BsGame)request.getSession().getAttribute("battleshipBean");
		MissileHandler mh = new MissileHandler(clickedRow, clickedColumn, battleshipBean);
		//either the user or computer fires a missile
		mh.fireSomeMissile();
		if(!battleshipBean.getGameStatus()){
			request.getSession().setAttribute("battleshipBean", battleshipBean);
			request.getRequestDispatcher("DisplayBattleship.jsp").forward(request, response);
		}else{
			request.getSession().setAttribute("battleshipBean", battleshipBean);
			request.getRequestDispatcher("BattleshipGameOver.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
