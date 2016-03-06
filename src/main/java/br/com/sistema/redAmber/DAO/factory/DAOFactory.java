package br.com.sistema.redAmber.DAO.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sistema.redAmber.DAO.DAOAluno;
import br.com.sistema.redAmber.DAO.IDAOAluno;

public abstract class DAOFactory {
/////////////////////////ATRIBUTOS/////////////////////////////
//private static final EntityManagerFactory factory;

private static IDAOAluno daoAluno;

/////////////////////////ATRIBUTOS/////////////////////////////	




/////////////////////////INICIALIZACOES/////////////////////////////	
/*static{
	factory = Persistence.createEntityManagerFactory("DB_mysql");
}*/
/////////////////////////INICIALIZACOES/////////////////////////////	




/////////////////////////M�TODOS DE CHAMADA DO DAO/////////////////////////////		
public static IDAOAluno getDaoAluno(){
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_mysql");
	daoAluno = new DAOAluno(factory.createEntityManager());
	return daoAluno;
}


/////////////////////////M�TODOS DE CHAMADA DO DAO/////////////////////////////			



/////////////////////////M�TODOS/////////////////////////////			
public static void close(EntityManagerFactory factory){
	if (factory != null && factory.isOpen()) {
		factory.close();
	}
}
/////////////////////////M�TODOS/////////////////////////////	
}
