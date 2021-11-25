package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("newSubject")
    @POST
    public Response addSubject(Subject subject){
     if (subject.getTitle().isEmpty() || subject.getCategory().isEmpty()){
         throw new BadRequestException("Missing data.");
     }
        subjectService.addSubject(subject);
        String msg = "Subject " + subject.getTitle() + " has been added.";
        return Response.ok().entity(msg).build();
    }

    @Path("getSubject/{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null) {
            throw new NotFoundException("Subject with ID " + id + " was not found.");
        }
        return Response.ok(foundSubject).build();
    }

    @Path("getAllSubjects")
    @GET
    public Response getAllSubjects(){
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        if (foundSubjects.isEmpty()) {
            throw new NotFoundException("No Data available.");
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("getSubjectsByCategory")
    @GET
    public Response getSubjectsByCategory(@QueryParam("category") String category){
        List<Subject> foundSubjects = subjectService.getAllSubjects()
                .stream()
                .filter(subject -> Objects.equals(subject.getCategory(), category))
                .collect(Collectors.toList());

        if (foundSubjects.isEmpty()) {
            throw new NotFoundException("No Data available.");
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("updateSubject/{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        subjectService.updateSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("deleteSubject/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        if (foundSubject == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        subjectService.deleteSubject(id);
        String msg = "Subject with ID " + id + " has been removed.";
        return Response.ok().entity(msg).build();
    }
}
