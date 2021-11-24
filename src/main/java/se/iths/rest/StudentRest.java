package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.NotInvalidException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("newStudent")
    @POST
    public Response addStudent(Student student){
        if (student.getEmail().contains("@mail.com")){
            studentService.addStudent(student);
            String msg = "Student with " + student.getEmail() + " has been added.";
            return Response.ok().entity(msg).build();
        }
        else if (student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing data.").type(MediaType.APPLICATION_JSON).build());
        }
        else {
            throw new NotInvalidException("Invalid email");
        }
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

    @Path("getAllStudents")
    @GET
    public Response getAllStudents(){
        List<Student> foundStudents = studentService.getAllStudents();
        if (foundStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Data available.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("getStudentByLastname")
    @GET
    public Response getStudentByLastname(@QueryParam("lastName") String lastName){
        List<Student> foundStudents = studentService.getAllStudents()
                .stream()
                .filter(student -> Objects.equals(student.getLastName(), lastName))
                .collect(Collectors.toList());

        if (foundStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Data available.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("updateStudent/{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }

    @Path("deleteStudent/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.findStudentById(id);
        if (foundStudent == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        studentService.deleteStudent(id);
        String msg = "Student with ID " + id + " has been removed.";
        return Response.ok().entity(msg).build();
    }
}
