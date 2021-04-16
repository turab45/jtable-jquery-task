package daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import dao.StudentDao;
import models.Student;
import util.Database;

public class StudentDaoImpl implements StudentDao{
	
	Connection conn = Database.getConnection();

	@Override
	public Integer addStudent(Student student) {
		Integer row = null;
        try {
            
            
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student(`name`,email,contact,gender,country) VALUES(?,?,?,?,?)");
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getContact());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getCountry());
           
           
                        
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
	}

	@Override
	public Integer updateStudent(Student student) {
		Integer row = null;
		
		try {
			 PreparedStatement pstmt = conn.prepareStatement("UPDATE student set name=?,email=?,contact=?,gender=?,country=? WHERE id=?");
			 pstmt.setString(1, student.getName());
			 pstmt.setString(2, student.getEmail());
			 pstmt.setString(3, student.getContact());
			 pstmt.setString(4, student.getGender());
			 pstmt.setString(5, student.getCountry());
			 pstmt.setInt(6, student.getId());
			 
			 row = pstmt.executeUpdate();
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		return row;
	}

	@Override
	public Integer deleteStudent(Integer studentId) {
		Integer row = null;
		
		try {
			 PreparedStatement pstmt = conn.prepareStatement("Delete from student WHERE id=?");
			 pstmt.setInt(1, studentId);
			 
			 
			 row = pstmt.executeUpdate();
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		return row;
	}

	@Override
	public Student getStudentById(Integer studentId) {
		Student student = null;
		ResultSet rs = null;
		try {
			 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE id=?");
			 pstmt.setInt(1, studentId);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 student = new Student();
				 student.setId(rs.getInt("id"));
				 student.setName(rs.getString("name"));
				 student.setEmail(rs.getString("email"));
				 student.setContact(rs.getString("contact"));
				 student.setGender(rs.getString("gender"));
				 student.setCountry(rs.getString("country"));
				 
				 
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		return student;
	}

	@Override
	public Integer getStudentIdByName(String studentName) {
		Integer id = null;
		ResultSet rs = null;
		try {
			 PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM student WHERE name=?");
			 pstmt.setString(1, studentName);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				id = rs.getInt("id");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		return id;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> allStudent = new ArrayList<Student>();
		ResultSet rs = null;
		try {
			 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student");
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 Student student = new Student();
				 student.setId(rs.getInt("id"));
				 student.setName(rs.getString("name"));
				 student.setEmail(rs.getString("email"));
				 student.setContact(rs.getString("contact"));
				 student.setGender(rs.getString("gender"));
				 student.setCountry(rs.getString("country"));
				 
				 
				 allStudent.add(student);
			 }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : "+e.getMessage());
		}
		return allStudent;
	}

	 
}
