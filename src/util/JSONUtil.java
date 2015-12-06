package util;

import org.json.JSONException;
import org.json.JSONObject;

import dao.CepDAO;
import beans.Cep;

public class JSONUtil {

	/*
	 * Retorna um objeto JSON com informações do CEP
	 */
	public static JSONObject toJson(Cep cep) throws JSONException{		
		if (cep != null && cep.getId() != null){			
			return toJson("SUCESSO", cep);			
		} else{
			return toJson("ERRO", "O CEP informado nao foi encontrado");
		}		
	}	
	
	/* Recebe um Status e um objeto CEP
	 * Retorna um objeto JSON com Status e informações de CEP
	 */
	public static JSONObject toJson(String status, Cep cep) throws JSONException{		
		
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("status", status);
		jsonObj.put("id", cep.getId());
		jsonObj.put("cep", CepUtil.formataCep(cep.getCep()));
		jsonObj.put("endereco", cep.getEndereco());
		jsonObj.put("bairro", cep.getBairro());
		jsonObj.put("cidade", cep.getCidade());
		jsonObj.put("estado", cep.getEstado());		
		
		return jsonObj;
	}
	
	/* Recebe strings: Status e Mensagem
	 * Retorna um objeto JSON com Status e Mensagem
	 */
	public static JSONObject toJson(String status, String msg) throws JSONException{		

		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("status", status);
		jsonObj.put("mensagem", msg);			

		return jsonObj;
	}
	
	
	/* Recebe um objeto JSON
	 * Retorna um objeto CEP
	 */
	public static Cep toCep(JSONObject jsonObj) throws JSONException{		
		Cep cep = new Cep();
		//cep.setId((jsonObj.get("id")==null)?0: (Integer) jsonObj.get("id"));
		cep.setId(0);
		cep.setCep( CepUtil.limpaCep((String) jsonObj.get("cep")) );
		cep.setEndereco( (String) jsonObj.get("endereco") );
		cep.setBairro( (String) jsonObj.get("bairro") );
		cep.setCidade( (String) jsonObj.get("cidade") );
		cep.setEstado( (String) jsonObj.get("estado") );			

		return cep;
	}
	
	/*
	 * Recebe um JSON com informações de CEP ( Aqui receberá o JSON via POST para inclusão na Base)
	 * - se o CEP nao existir na base, tenta cadastrar
	 * - se existir, retorna JSON com msg de Já Cadastrado
	 */
	public static JSONObject insertJson(JSONObject jsonObj) throws JSONException{
		
		JSONObject jsonRet = new JSONObject();
		CepDAO cepDAO = new CepDAO();
		Cep cep = JSONUtil.toCep(jsonObj);
		
		if (!cepDAO.cepExists(cep.getCep())){			
			if (cepDAO.insertCep(cep)){
				jsonRet = JSONUtil.toJson("SUCESSO", "O endereço foi cadastrado com sucesso.");
			}else{
				jsonRet = JSONUtil.toJson("ERRO", "Erro Nao Identificado ao Cadastrar.");
			}
		}else{
			jsonRet = JSONUtil.toJson("ERRO", "O endereco informado ja foi cadastrado.");
		}		
		return jsonRet;
	}
	
}
