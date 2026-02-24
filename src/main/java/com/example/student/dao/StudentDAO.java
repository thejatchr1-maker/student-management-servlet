package com.example.student.dao;

import com.example.student.model.Student;
import com.example.student.util.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final Logger logger =
            LogManager.getLogger(StudentDAO.class);

    // CREATE
    public void addStudent(Student student) {

        String sql =
                "INSERT INTO students (name,email,course) VALUES (?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());

            ps.executeUpdate();
            logger.info("Student added: {}", student.getName());

        } catch (Exception e) {
            logger.error("Error adding student", e);
        }
    }

    // READ ALL
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                ));
            }

            logger.info("Fetched all students");

        } catch (Exception e) {
            logger.error("Error fetching students", e);
        }

        return list;
    }

    // READ BY ID
    public Student getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id=?";
        Student student = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course")
                );
            }

            logger.info("Fetched student id: {}", id);

        } catch (Exception e) {
            logger.error("Error fetching student by id", e);
        }

        return student;
    }

    // UPDATE
    public void updateStudent(Student student) {

        String sql =
                "UPDATE students SET name=?, email=?, course=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setInt(4, student.getId());

            ps.executeUpdate();
            logger.info("Updated student id: {}", student.getId());

        } catch (Exception e) {
            logger.error("Error updating student", e);
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Deleted student id: {}", id);

        } catch (Exception e) {
            logger.error("Error deleting student", e);
        }
    }
}