package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import dao.StudentDao;
import daoimpl.StudentDaoImpl;
import models.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDao studentDaoImpl = new StudentDaoImpl();

	/**
	 * Default constructor.
	 */
	public StudentController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		Gson gson = new Gson();
		String listData = "";

		if (action.equals("getAll")) {

			response.setContentType("javascript/json");

			List<Student> students = studentDaoImpl.getAllStudent();

			String jsonList = gson.toJson(students);

			// Convert java object to json
			JsonElement element = gson.toJsonTree(students, new TypeToken<List<Student>>() {
			}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			listData = jsonArray.toString();

			// Return json in the format required by jTable plugin

			listData = "{\"Result\":\"OK\", \"Records\":" + listData + "}";

			response.getWriter().print(listData);
		}

		if (action.equals("create")) {

			Student student = new Student();

			student.setName(request.getParameter("name"));
			student.setEmail(request.getParameter("email"));
			student.setContact(request.getParameter("contact"));
			student.setGender(request.getParameter("gender"));
			student.setCountry(request.getParameter("country"));

			studentDaoImpl.addStudent(student);

			// Get student object to send back to jtable

			Student student2 = studentDaoImpl.getStudentById(studentDaoImpl.getStudentIdByName(student.getName()));

			response.setContentType("javascript/json");

			// Convert java object to json

			String json = gson.toJson(student2);

			// Return json in the format required by jTable plugin

			listData = "{\"Result\":\"OK\", \"Record\":" + json + "}";

			response.getWriter().print(listData);

		}
		
		
		if (action.equals("update")) {

			Integer id = Integer.parseInt(request.getParameter("id"));
			
			Student student = studentDaoImpl.getStudentById(id);

			student.setName(request.getParameter("name"));
			student.setEmail(request.getParameter("email"));
			student.setContact(request.getParameter("contact"));
			student.setGender(request.getParameter("gender"));
			student.setCountry(request.getParameter("country"));

			studentDaoImpl.updateStudent(student);

			// Get student object to send back to jtable


			response.setContentType("javascript/json");

			// Convert java object to json

			String json = gson.toJson(student);

			// Return json in the format required by jTable plugin

			listData = "{\"Result\":\"OK\", \"Record\":" + json + "}";

			response.getWriter().print(listData);

		}
		
		if (action.equals("delete")) {

			Integer id = Integer.parseInt(request.getParameter("id"));
			
			studentDaoImpl.deleteStudent(id);


			response.setContentType("javascript/json");

			// Return json in the format required by jTable plugin

			listData = "{\"Result\":\"OK\"}";

			response.getWriter().print(listData);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
