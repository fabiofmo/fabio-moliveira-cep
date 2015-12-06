package services;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CepServiceClient {

	public static void main(String[] args) throws JSONException {
				
		JSONObject jsonObj = new JSONObject();		
		jsonObj.put("cep", "00000-000");
		jsonObj.put("endereco", "Rua Joao da Silva");
		jsonObj.put("bairro", "Vilda Da Silva");
		jsonObj.put("cidade", "Cidade do Interior");
		jsonObj.put("estado", "SP");
		
		System.out.println("JSON Enviado ao Servidor:");
		System.out.println(jsonObj.toString());
		
		try {							
			WebResource webResource = Client.create().resource("http://localhost:8080/fabio-moliveira-cep/rest-api/cadastro"); 
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonObj.toString());
			
			System.out.println("\n\nResposta do Servidor: ");
			System.out.println(response.getEntity(String.class));
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}

}
