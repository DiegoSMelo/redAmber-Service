package br.com.sistema.redAmber.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class Service {

	@GET
	@Path("vai")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public String vai(){
		return "oi";
	}
}
