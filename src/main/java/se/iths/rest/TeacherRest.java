package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("newTeacher")
    @POST
    public Response addTeacher(Teacher teacher){
        if (teacher.getEmail().contains("@mail.com")){
            teacherService.addTeacher(teacher);
            String msg = "Teacher with " + teacher.getEmail() + " has been added.";
            return Response.ok().entity(msg).build();
        }
        else if (teacher.getFirstName().isEmpty() || teacher.getLastName().isEmpty() || teacher.getEmail().isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing data.").type(MediaType.APPLICATION_JSON).build());
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid email")
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }

    @Path("getTeacher/{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Teacher with ID " + id + " was not found.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeacher).build();
    }

    @Path("getAllTeachers")
    @GET
    public Response getAllTeachers(){
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        if (foundTeachers.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Data available.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("getTeacherByLastname")
    @GET
    public Response getTeacherByLastname(@QueryParam("lastName") String lastName){
        List<Teacher> foundTeachers = teacherService.getAllTeachers()
                .stream()
                .filter(teacher -> Objects.equals(teacher.getLastName(), lastName))
                .collect(Collectors.toList());

        if (foundTeachers.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Data available.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("updateTeacher/{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Teacher with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("deleteTeacher/{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if (foundTeacher == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Teacher with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        teacherService.deleteTeacher(id);
        String msg = "Teacher with ID " + id + " has been removed.";
        return Response.ok().entity(msg).build();
    }
}
