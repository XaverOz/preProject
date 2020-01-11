package servlet;

import model.User;
import util.UserDBService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/edit")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserDBService.getUserDBService().getUserById(Long.valueOf(request.getParameter("id")));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        user.setName(request.getParameter("name"));
        UserDBService.getUserDBService().updateUser(user);
        response.sendRedirect("../admin/users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        request.setAttribute("user", UserDBService.getUserDBService().getUserById(Long.valueOf(request.getParameter("id"))));
        requestDispatcher.forward(request, response);
    }
}
