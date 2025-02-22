package uce.edu.ec.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.ec.service.IUsuarioToService;
import uce.edu.ec.service.to.UsuarioTo;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    private IUsuarioToService iUsuarioToService;


    @GET 
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioToPorNombre(@PathParam("nombre") String nombre) {
        try{
            UsuarioTo tmp = iUsuarioToService.buscarUsuarioToPorNombre(nombre);
        if (tmp != null && !tmp.getNombre().equals("No existe")) {
            return Response.ok(tmp)
                           .header("mensaje", "Usuario encontrado")
                           .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Usuario no encontrado")
                           .entity(UsuarioTo.NoExiste())
                           .build();
        }
        }
        catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Error en el servidor")
                           .entity(UsuarioTo.NoExiste())
                           .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioToPorId(@PathParam("id") Integer id) {
      
       try{
        UsuarioTo tmp = iUsuarioToService.buscarUsuarioToPorId(id);
        if (tmp != null && !tmp.getNombre().equals("No existe")) {
            return Response.ok(tmp)
                           .header("mensaje", "Usuario encontrado")
                           .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Usuario no encontrado")
                           .entity(UsuarioTo.NoExiste())
                           .build();
        }
       }    
       catch (Exception e){
           return Response.status(Response.Status.NOT_FOUND)
                          .header("mensaje", "Error en el servidor")
                          .entity(UsuarioTo.NoExiste())
                          .build();
       }
    }

    @GET
    @Path("/{usuario}/{contrasenia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorUsuarioToYContrasenia(@PathParam("usuario") String usuario, @PathParam("contrasenia") String contrasenia) {
        try{
            UsuarioTo tmp = iUsuarioToService.buscarPorUsuarioToYContrasenia(usuario, contrasenia);
        if (tmp != null && !tmp.getNombre().equals("No existe")) {
            return Response.ok(tmp)
                           .header("mensaje", "Usuario encontrado")
                           .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Usuario no encontrado")
                           .entity(UsuarioTo.NoExiste())
                           .build();
        }
        }
        catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Error en el servidor")
                           .entity(UsuarioTo.NoExiste())
                           .build();
        }
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarUsuarioTo(UsuarioTo usuarioTo){
      try {
        iUsuarioToService.guardarUsuarioTo(usuarioTo);
        return Response.ok(usuarioTo)
                       .header("mensaje", "Usuario guardado")
                       .build();
      } catch (Exception e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .header("mensaje", "Error en el servidor")
                       .entity(UsuarioTo.NoExiste())
                       .build();
      }
    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuarioTo(UsuarioTo usuarioTo){
        try {
            iUsuarioToService.actualizarUsuarioTo(usuarioTo);
            return Response.ok(usuarioTo)
                           .header("mensaje", "Usuario actualizado")
                           .build();
          } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Error en el servidor")
                           .entity(UsuarioTo.NoExiste())
                           .build();
          }
    }

    @DELETE
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarUsuarioTo(@PathParam("nombre") String nombre){
        try {
            iUsuarioToService.borrarUsuarioTo(nombre);
            return Response.ok()
                           .header("mensaje", "Usuario eliminado")
                           .build();
          } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .header("mensaje", "Error en el servidor")
                           .entity(UsuarioTo.NoExiste())
                           .build();
          }
    }

}
