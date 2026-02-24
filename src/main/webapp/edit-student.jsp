<%@ page import="com.example.student.model.Student" %>
<%
Student s = (Student) request.getAttribute("student");

if (s == null) {
%>
    <h2>Student Not Found</h2>
<%
    return;
}
%>

<h2>Edit Student</h2>

<form action="students" method="post">
    <input type="hidden" name="id" value="<%= s.getId() %>"/>

    Name: <input type="text" name="name"
                 value="<%= s.getName() %>"/><br><br>

    Email: <input type="text" name="email"
                  value="<%= s.getEmail() %>"/><br><br>

    Course: <input type="text" name="course"
                   value="<%= s.getCourse() %>"/><br><br>

    <input type="submit" value="Update"/>
</form>