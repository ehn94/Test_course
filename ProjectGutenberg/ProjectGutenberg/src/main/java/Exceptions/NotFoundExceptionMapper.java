package Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper extends Exception implements ExceptionMapper<NotFoundExceptionMapper> {

    public NotFoundExceptionMapper(){
        
    }
    
    public NotFoundExceptionMapper(String string) {
        super(string);
    }

    @Override
    public Response toResponse(NotFoundExceptionMapper exception) {
        return Response.status(404).entity(exception.getMessage())
                .type("text/plain").build();
    }
}
