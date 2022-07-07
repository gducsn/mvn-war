package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import utility.Crud;

@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Insert() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String namein = (String) session.getAttribute("userlogin");
		String passin = (String) session.getAttribute("userpass");

		Crud daoCrud = new Crud();
		User userin = daoCrud.retrieveUser(namein, passin);

		String[] checkbox = request.getParameterValues("check");
		String radiobox = request.getParameter("radio");
		String text = request.getParameter("texting");
		request.setAttribute("text", text);
		daoCrud.insertEntity(checkbox, radiobox, userin);

		RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
