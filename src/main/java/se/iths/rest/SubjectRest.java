package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectServices;

    @Path("newSubject")
    @POST
    public Response addSubject(Subject subject){
     if (subject.getTitle().isEmpty() || subject.getCategory().isEmpty()){
         throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                 .entity("Missing data.").type(MediaType.APPLICATION_JSON).build());
     }
        subjectServices.addSubject(subject);
        String msg = "Subject " + subject.getTitle() + " has been added.";
        return Response.ok().entity(msg).build();
    }

    @Path("getSubject/{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectServices.findSubjectById(id);
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubject).build();
    }

    @Path("getAllSubjects")
    @GET
    public Response getAllSubjects(){
        List<Subject> foundSubjects = subjectServices.getAllSubjects();
        if (foundSubjects.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Data available.").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("updateSubject/{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject){
        Subject foundSubject = subjectServices.findSubjectById(id);
        if (foundSubject == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        subjectServices.updateSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("deleteSubject/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectServices.findSubjectById(id);
        if (foundSubject == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found.")
                    .type(MediaType.APPLICATION_JSON).build();
        }
        subjectServices.deleteSubject(id);
        String msg = "Subject with ID " + id + " has been removed.";
        return Response.ok().entity(msg).build();
    }
}
