package bs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsmodel.BsGame;

/**
 * Servlet implementation class StartBattleshipServlet
 */
@WebServlet("/StartBattleshipServlet")
public class StartBattleshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartBattleshipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String turn = request.getParameter("turn");
		BsGame game = new BsGame(turn);
		//the ib makes the String array for later use in business logic
		InitializeBattleship ib = new InitializeBattleship(game,turn);
		request.getSession().setAttribute("battleshipBean", game);
		response.sendRedirect("SetShips.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
