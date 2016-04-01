package BJcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BJmodel.BlackJackGame;



/**
 * Servlet implementation class StartingBJServlet
 */
@WebServlet("/StartingBJServlet")
public class StartingBJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartingBJServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String letsPlay = (String)request.getParameter("play");
		String play = letsPlay.substring(0, 1);
		if(play.equals("L")){
			BlackJackGame bjGame = new BlackJackGame();
			request.getSession().setAttribute("BJBean", bjGame);
		response.sendRedirect("BJPlayGame.jsp");
		}
		else
			response.sendRedirect("BJStartingPoint.jsp");
		
	}

}
