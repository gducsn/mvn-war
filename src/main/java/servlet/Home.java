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

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();

	}

	String name;
	String pass;
	User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (checkUser(request, response) == true) {

			HttpSession session = request.getSession();

			session.setAttribute("userlogin", name);
			session.setAttribute("userpass", pass);

			RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("valueON", (Boolean) true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private Boolean checkUser(HttpServletRequest request, HttpServletResponse response) {
		boolean value = false;
		Crud daoCrud = new Crud();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		name = username;
		pass = password;

		user = daoCrud.retrieveUser(username, password);

		if (user != null) {
			String userDB = user.getUsername();
			String passDB = user.getPassword();
			if (username.equals(userDB) && password.equals(passDB)) {

				return true;
			}
		}

		return value;

	};

}
