package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.CepUtil;
import util.HibernateUtil;
import beans.Cep;

public class CepDAO {

	private Session sessao = null;
	private Transaction transacao = null;
	
	public boolean insertCep(Cep cep){
		
		boolean retorno = false;
		try {
			cep.setCep(CepUtil.limpaCep(cep.getCep()));
			sessao = HibernateUtil.getSessionFactory().openSession();			
			transacao = sessao.beginTransaction();
			sessao.persist(cep);
			transacao.commit();			
			retorno = true;
		} catch (HibernateException e) {
			transacao.rollback();
			e.printStackTrace();
		} finally {
			sessao.close();
		}
		return retorno;	
	}
	
	@SuppressWarnings("unchecked")
	public Cep findCep(String cep){			
		Cep cepFound = new Cep();
		try{
			sessao = HibernateUtil.getSessionFactory().openSession();
			List<Cep> cepList = sessao.createQuery("FROM Cep WHERE cep='" + cep + "' ").list();	
			for (Iterator<Cep> i = cepList.iterator(); i.hasNext();){
				cepFound = (Cep) i.next();
			}		
		} catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			sessao.close();
		}
		return cepFound;		
	}
	
	@SuppressWarnings("unchecked")
	public boolean cepExists(String cep){			
		boolean ret = false;
		try{
			sessao = HibernateUtil.getSessionFactory().openSession();
			List<Cep> cepList = sessao.createQuery("FROM Cep WHERE cep='" + cep + "' ").list();	
			ret = (cepList.size()==1)? true : false;
			
		} catch(HibernateException e){
			e.printStackTrace();			
		}finally{
			sessao.close();
		}
		return ret;		
	}	
}
