package br.com.sistema.redAmber.DAO.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sistema.redAmber.DAO.DAOAluno;
import br.com.sistema.redAmber.DAO.DAOCurso;
import br.com.sistema.redAmber.DAO.DAODisciplina;
import br.com.sistema.redAmber.DAO.DAOEquipamento;
import br.com.sistema.redAmber.DAO.DAOFuncionario;
import br.com.sistema.redAmber.DAO.DAOGrade;
import br.com.sistema.redAmber.DAO.DAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.DAOMatricula;
import br.com.sistema.redAmber.DAO.DAOProfessor;
import br.com.sistema.redAmber.DAO.DAOSala;
import br.com.sistema.redAmber.DAO.DAOTurma;
import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.IDAOCurso;
import br.com.sistema.redAmber.DAO.IDAODisciplina;
import br.com.sistema.redAmber.DAO.IDAOEquipamento;
import br.com.sistema.redAmber.DAO.IDAOFuncionario;
import br.com.sistema.redAmber.DAO.IDAOGrade;
import br.com.sistema.redAmber.DAO.IDAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.IDAOMatricula;
import br.com.sistema.redAmber.DAO.IDAOProfessor;
import br.com.sistema.redAmber.DAO.IDAOSala;
import br.com.sistema.redAmber.DAO.IDAOTurma;

public class DAOFactory {
/////////////////////////ATRIBUTOS/////////////////////////////
private static final EntityManagerFactory factory;

private static IDAOAluno daoAluno;
private static IDAOMatricula daoMatricula;
private static IDAOFuncionario daoFuncionario;
private static IDAOProfessor daoProfessor; 
private static IDAODisciplina daoDisciplina;
private static IDAOCurso daoCurso;
private static IDAOTurma daoTurma;
private static IDAOGrade daoGrade;
private static IDAOGrade_Disciplina daoGrade_Disciplina;
private static IDAOEquipamento daoEquipamento;
private static IDAOSala daoSala;
/////////////////////////ATRIBUTOS/////////////////////////////	




/////////////////////////INICIALIZACOES/////////////////////////////	

static{
	factory = Persistence.createEntityManagerFactory("DB_mysql");
}

/////////////////////////INICIALIZACOES/////////////////////////////	




/////////////////////////MÉTODOS DE CHAMADA DO DAO/////////////////////////////		
public static IDAOAluno getDaoAluno(){
	daoAluno = new DAOAluno(factory.createEntityManager());
	return daoAluno;
}

public static IDAOMatricula getDaoMatricula(){
	daoMatricula = new DAOMatricula(factory.createEntityManager());
	return daoMatricula;
}

public static IDAOFuncionario getDaoFuncionario(){
	daoFuncionario = new DAOFuncionario(factory.createEntityManager());
	return daoFuncionario;
}

public static IDAOProfessor getDaoProfessor(){
	daoProfessor = new DAOProfessor(factory.createEntityManager());
	return daoProfessor;
}

public static IDAODisciplina getDaoDisciplina(){
	daoDisciplina = new DAODisciplina(factory.createEntityManager());
	return daoDisciplina;
}

public static IDAOCurso getDaoCurso(){
	daoCurso = new DAOCurso(factory.createEntityManager());
	return daoCurso;
}

public static IDAOTurma getDaoTurma(){
	daoTurma = new DAOTurma(factory.createEntityManager());
	return daoTurma;
}

public static IDAOGrade getDaoGrade(){
	daoGrade = new DAOGrade(factory.createEntityManager());
	return daoGrade;
}

public static IDAOGrade_Disciplina getDaoGrade_Disciplina(){
	daoGrade_Disciplina = new DAOGrade_Disciplina(factory.createEntityManager());
	return daoGrade_Disciplina;
}

public static IDAOEquipamento getDaoEquipamento(){
	daoEquipamento = new DAOEquipamento(factory.createEntityManager());
	return daoEquipamento;
}

public static IDAOSala getDaoSala(){
	daoSala = new DAOSala(factory.createEntityManager());
	return daoSala;
}
/////////////////////////MÉTODOS DE CHAMADA DO DAO/////////////////////////////			



/////////////////////////MÉTODOS/////////////////////////////			
public static void close(){
	if (factory != null && factory.isOpen()) {
		factory.close();
	}
}
/////////////////////////MÉTODOS/////////////////////////////	
}
