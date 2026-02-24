<%@ page import="java.util.*,com.example.student.model.Student" %>

<h2>Student List</h2>

<a href="add-student.jsp">Add Student</a>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Course</th>
    <th>Action</th>
</tr>

<%
List<Student> list =
    (List<Student>) request.getAttribute("students");

if(list != null){
    for(Student s : list){
%>
<tr>
    <td><%= s.getId() %></td>
    <td><%= s.getName() %></td>
    <td><%= s.getEmail() %></td>
    <td><%= s.getCourse() %></td>
    <td>
        <a href="students?action=delete&id=<%= s.getId() %>">
            Delete
        </a>
    </td>
</tr>
<%
    }
}
%>
</table>