package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("newStudent")
    @POST
    public Response addStudent(Student student){
        studentService.addStudent(student);
        return Response.ok(student).build();
    }

    @Path("getStudent/{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudent).build();
    }

    @Path("deleteStudent/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        studentService.deleteStudent(id);
        if(foundStudent == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok().build();
    }

    @Path("getallStudents")
    @GET
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        if(foundStudents == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No data available.")
                    .type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("updateStudent/{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student){
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("getStudentByLastname")
    @GET
    public Response getStudentByLastname(@QueryParam("lastName") String lastName){
        String responseString = "List of student with lastname: " + lastName;
        return Response.ok(responseString).build();
    }
}
