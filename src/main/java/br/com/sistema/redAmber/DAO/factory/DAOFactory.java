package br.com.sistema.redAmber.DAO.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sistema.redAmber.DAO.DAOAluno;
import br.com.sistema.redAmber.DAO.DAOFuncionario;
import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.IDAOFuncionario;

public abstract class DAOFactory {
/////////////////////////ATRIBUTOS/////////////////////////////
//private static final EntityManagerFactory factory;

private static IDAOAluno daoAluno;
private static IDAOFuncionario daoFuncionario;
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

public static IDAOFuncionario getDaoFuncionario(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoFuncionario = new DAOFuncionario(factory.createEntityManager());
	return daoFuncionario;
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
