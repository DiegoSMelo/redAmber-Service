package br.com.sistema.redAmber.DAO.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sistema.redAmber.DAO.DAOAluno;
import br.com.sistema.redAmber.DAO.DAOCurso;
import br.com.sistema.redAmber.DAO.DAODisciplina;
import br.com.sistema.redAmber.DAO.DAOFuncionario;
import br.com.sistema.redAmber.DAO.DAOMatricula;
import br.com.sistema.redAmber.DAO.DAOProfessor;
import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.IDAOCurso;
import br.com.sistema.redAmber.DAO.IDAODisciplina;
import br.com.sistema.redAmber.DAO.IDAOFuncionario;
import br.com.sistema.redAmber.DAO.IDAOMatricula;
import br.com.sistema.redAmber.DAO.IDAOProfessor;

public abstract class DAOFactory {
/////////////////////////ATRIBUTOS/////////////////////////////
//private static final EntityManagerFactory factory;

private static IDAOAluno daoAluno;
private static IDAOMatricula daoMatricula;
private static IDAOFuncionario daoFuncionario;
private static IDAOProfessor daoProfessor; 
private static IDAODisciplina daoDisciplina;
private static IDAOCurso daoCurso;
/////////////////////////ATRIBUTOS/////////////////////////////	




/////////////////////////INICIALIZACOES/////////////////////////////	
/*static{
	factory = Persistence.createEntityManagerFactory("DB_mysql");
}*/
/////////////////////////INICIALIZACOES/////////////////////////////	




/////////////////////////MÉTODOS DE CHAMADA DO DAO/////////////////////////////		
public static IDAOAluno getDaoAluno(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoAluno = new DAOAluno(factory.createEntityManager());
	return daoAluno;
}

public static IDAOMatricula getDaoMatricula(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoMatricula = new DAOMatricula(factory.createEntityManager());
	return daoMatricula;
}

public static IDAOFuncionario getDaoFuncionario(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoFuncionario = new DAOFuncionario(factory.createEntityManager());
	return daoFuncionario;
}

public static IDAOProfessor getDaoProfessor(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoProfessor = new DAOProfessor(factory.createEntityManager());
	return daoProfessor;
}

public static IDAODisciplina getDaoDisciplina(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoDisciplina = new DAODisciplina(factory.createEntityManager());
	return daoDisciplina;
}

public static IDAOCurso getDaoCurso(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoCurso = new DAOCurso(factory.createEntityManager());
	return daoCurso;
}
/////////////////////////MÉTODOS DE CHAMADA DO DAO/////////////////////////////			



/////////////////////////MÉTODOS/////////////////////////////			
public static void close(EntityManagerFactory factory){
	if (factory != null && factory.isOpen()) {
		factory.close();
	}
}
/////////////////////////MÉTODOS/////////////////////////////	
}
