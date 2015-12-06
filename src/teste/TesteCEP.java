package teste;

import java.util.Scanner;

import org.json.JSONException;

import util.CepUtil;
import util.JSONUtil;
import beans.Cep;
import dao.CepDAO;

public class TesteCEP {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws JSONException {
		
		while (true){
						
			System.out.println("Escolha uma opção: ");
			System.out.println("1 - Pesquisar CEP");
			System.out.println("2 - Inserir CEP");

			Integer opcao = (new Scanner(System.in)).nextInt();

			if (opcao == 1){
				System.out.print("Entre com um CEP: ");		
				String cep = CepUtil.limpaCep((new Scanner(System.in)).nextLine());			
				//String cep = "00000002";  /// automatizando os testes
				
				if(CepUtil.validaCep(cep)){		
					CepDAO cepDAO = new CepDAO();
					Cep cepSearch = cepDAO.findCep(cep);			
					System.out.println(JSONUtil.toJson(cepSearch));
				}else{
					System.out.println(JSONUtil.toJson("ERRO", "O CEP informado é inválido"));		
				}			
			}
			
			if (opcao ==2){
				// teste para inserir
				// recebe um JSON de CEP
				String novoCep = "12345-678";
				System.out.println("Cadastrando CEP: " + novoCep);
				Cep cepNovo = new Cep();
				
				/*
				 * Aqui recebe um DAO e transforma em JSON
				 * e tenta INSERIR o CEP, recebido via JSON, na Base
				 */
				cepNovo.setId(0);
				cepNovo.setCep(CepUtil.limpaCep(novoCep));
				cepNovo.setEndereco("Rua Nova");
				cepNovo.setBairro("Bairro Novo");
				cepNovo.setCidade("Cidade Nova");
				cepNovo.setEstado("SP");
				
				System.out.println(JSONUtil.insertJson(JSONUtil.toJson(cepNovo))); // webservice: JSONUtil.insertJson() vai receber um JSON direto
				System.out.println(JSONUtil.toJson(new CepDAO().findCep((String) cepNovo.getCep())));  
			}

			System.out.println("");
			System.out.println("<ENTER> para continuar ...");
			new Scanner(System.in).nextLine();				
			for (int i = 0; i < 100; ++i)  {System.out.println();}

		}	
	}	
}
