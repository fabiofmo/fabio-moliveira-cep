package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import util.JSONUtil;

@Path("/")
public class CepPOSTService {
	
	@POST
	@Path("/cadastro")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cepCadastro(String cepStr) throws JSONException {
		
		JSONObject jsonCep = new JSONObject(cepStr);
		jsonCep.append("id", 0);
		JSONObject jsonRetorno = JSONUtil.insertJson(jsonCep);

		return Response.status(201).entity(jsonRetorno.toString()).build();		

	}
}
