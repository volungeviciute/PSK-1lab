package lt.vu.mif.PSK_1lab.Rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.PSK_1lab.Entities.School;
import lt.vu.mif.PSK_1lab.persistence.SchoolsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/school")
public class SchoolController {
    @Inject
    @Getter @Setter
    private SchoolsDAO schoolsDAO;

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        List<School> schools = schoolsDAO.loadAll();
        List<SchoolDTO> schoolsDTO = schools.stream().map(school -> {
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setName(school.getName());
            schoolDTO.setAddress(school.getAddress());
            schoolDTO.setId(school.getId());
            return schoolDTO;
        }).collect(Collectors.toList());

        return Response.ok(schoolsDTO).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){
        School school = schoolsDAO.findOne(id);
        if(school == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setName(school.getName());
        schoolDTO.setId(school.getId());
        schoolDTO.setAddress(school.getAddress());

        return Response.ok(schoolDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, SchoolDTO schoolData){
        try {
            School existingSchool = schoolsDAO.findOne(id);
            if(existingSchool == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSchool.setName(schoolData.getName());
            existingSchool.setAddress(schoolData.getAddress());
            schoolsDAO.update(existingSchool);
            return Response.ok().build();
        } catch(OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(SchoolDTO schoolDTO){
        try{
            School schoolToCreate = new School();
            schoolToCreate.setName(schoolDTO.getName());
            schoolToCreate.setAddress(schoolDTO.getAddress());
            schoolsDAO.persist(schoolToCreate);

            return Response.ok().build();
        }catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
