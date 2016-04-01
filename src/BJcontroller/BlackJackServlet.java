package BJcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BJmodel.BlackJackGame;
import BJmodel.GameLogic;

/**
 * Servlet implementation class BlackJackServlet
 */
@WebServlet("/BlackJackServlet")
public class BlackJackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlackJackServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BlackJackGame bjGame = (BlackJackGame)request.getSession().getAttribute("BJBean");
		GameLogic BJLogic = new GameLogic();
		if(bjGame.isBet() == 0){

			int playerChips = Integer.parseInt((String)request.getParameter("chipCount"));

			bjGame = new BlackJackGame(playerChips);

			request.getSession().setAttribute("BJBean", bjGame);
			bjGame.setBet(1);
			
		}
		else if(bjGame.isBet() == 1) {
			int betAmount = Integer.parseInt( (String)request.getParameter("betArea"));

			bjGame = BJLogic.useBets(betAmount, bjGame);
			bjGame.setBet(2);
			
		}
		else if(bjGame.isBet() == 2) {
			String choice = (String)request.getParameter("hitOrStay");
			String H = choice.substring(0, 1);

			bjGame = BJLogic.useDecision(H, bjGame);

			
		}
		else {
			String playMore = (String)request.getParameter("playAgain");
			String YN = playMore.substring(0, 1);

			System.out.println(YN);
			
			if(YN.equals("Y")){
				bjGame.setBet(1);
				
				response.sendRedirect("BJPlayGame.jsp");
				return;
				
			}
			else 
				bjGame.setBet(0);
				response.sendRedirect("BJStartingPoint.jsp");
			return;
		
		}
		request.getSession().setAttribute("BJBean", bjGame);
		response.sendRedirect("BJPlayGame.jsp");
	}

}
