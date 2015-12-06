package util;

import javax.swing.text.MaskFormatter;

public class CepUtil {

	public static String formataCep(String cep) {		
		try{
			MaskFormatter cepMask = new MaskFormatter("#####-###");
			cepMask.setValueContainsLiteralCharacters(false);
			return cepMask.valueToString(cep);			
		}catch (Exception e){
			return cep;
		}		
	}
	
	public static boolean validaCep(String cep){		
		boolean retCepValido = false;
		String cepLimpo = limpaCep(cep);		
		try{
			Integer.parseInt(cepLimpo);
			retCepValido = true;
		} catch(Exception e){
			retCepValido = false;
		}		
		if (retCepValido){
			retCepValido = (cepLimpo.length()==8);
		}		
		return retCepValido;	}
	
	public static String limpaCep(String cep){		
		return cep.replaceAll("-","");
	}
	
	
}
