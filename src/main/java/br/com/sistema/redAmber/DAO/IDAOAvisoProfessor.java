package br.com.sistema.redAmber.DAO;

import java.util.Calendar;
import java.util.List;

import br.com.sistema.redAmber.DAO.generics.IDAOGeneric;
import br.com.sistema.redAmber.basicas.AvisoProfessor;
import br.com.sistema.redAmber.exceptions.DAOException;

public interface IDAOAvisoProfessor extends IDAOGeneric<AvisoProfessor> {
	
	public List<AvisoProfessor> consultarPorData(Calendar data) throws DAOException;
	public List<AvisoProfessor> consultarPendentes() throws DAOException;
	public List<AvisoProfessor> consultarPorProfessorData(Long idProfessor, 
			Calendar data) throws DAOException;
	public Integer consultarQuantidadeDeHoje();
}