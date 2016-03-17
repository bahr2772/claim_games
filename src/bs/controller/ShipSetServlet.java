package bs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsmodel.BsGame;

/**
 * Servlet implementation class ShipSetServlet
 */
@WebServlet("/ShipSetServlet")
public class ShipSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShipSetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placementRow = request.getParameter("row");
		String placementColumn = request.getParameter("column");
		String placementOrientation = request.getParameter("orientation");
		BsGame gameBean = (BsGame)request.getSession().getAttribute("gameBean");
		ValidateShip vs = new ValidateShip(gameBean, placementRow, placementColumn, placementOrientation);
		//by checking for a user ship, the ValidateShip object creates invisible string arrays and tries to place a ship
		if(vs.stillMoreUserShips()){
			vs.tryToSetShipOnUserBoard();
		}
		if(gameBean.getUser().getDefBoard().getShipsOnBoard()<5){
			//send all current ship positions to the jsp to display
			request.getSession().setAttribute("gameBean", gameBean);
			request.getRequestDispatcher("SetShips.jsp").forward(request, response);
		}
		
		if(gameBean.getUser().getDefBoard().getShipsOnBoard()==5){
			request.getSession().setAttribute("gameBean", gameBean);
			request.getRequestDispatcher("DisplayBattleship.jsp").forward(request, response);
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
