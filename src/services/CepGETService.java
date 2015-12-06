package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import util.CepUtil;
import util.JSONUtil;
import dao.CepDAO;

@Path("/busca")
public class CepGETService {
	
	@Path("{_cep}")
	@GET
	@Produces("application/json")
	public Response buscaCEP(@PathParam("_cep") String _cep) throws JSONException {		
		CepDAO cepDAO = new CepDAO();		
		if (CepUtil.validaCep(_cep)){
			return Response.status(200).entity("" + JSONUtil.toJson(cepDAO.findCep(CepUtil.limpaCep(_cep)))).build();
		}else{
			return Response.status(201).entity("" + JSONUtil.toJson("ERRO", "O CEP informado eh invalido")).build();
		}						
	}

}



