package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.logging.Filter;
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
        studentService.addStudent(student);
//        if (){
//            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
//                    .entity("Email " +  " is already taken").type(MediaType.APPLICATION_JSON).build());
//        }
        String msg = "A student has been added to the database";
        return Response.ok(student).entity(msg).build();
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
        studentService.updateStudent(student);
        if (student.getId() == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student do not exist.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(student).build();
    }

    @Path("deleteStudent/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id, Student student){
        studentService.deleteStudent(id);
//        if(student.getId() == null){
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
//                    .entity("Student with ID " + id + " was not found.").type(MediaType.APPLICATION_JSON).build());
//        }
        String msg = "Student with ID " + id + " has been removed.";
        return Response.ok().entity(msg).build();
    }
}
