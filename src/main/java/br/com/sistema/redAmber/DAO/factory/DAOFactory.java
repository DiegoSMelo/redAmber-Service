package br.com.sistema.redAmber.DAO.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sistema.redAmber.DAO.DAOAluno;
import br.com.sistema.redAmber.DAO.DAOAula;
import br.com.sistema.redAmber.DAO.DAOAvisoAluno;
import br.com.sistema.redAmber.DAO.DAOAvisoProfessor;
import br.com.sistema.redAmber.DAO.DAOCurso;
import br.com.sistema.redAmber.DAO.DAODisciplina;
import br.com.sistema.redAmber.DAO.DAODuracaoAula;
import br.com.sistema.redAmber.DAO.DAOEquipamento;
import br.com.sistema.redAmber.DAO.DAOFuncionario;
import br.com.sistema.redAmber.DAO.DAOGrade;
import br.com.sistema.redAmber.DAO.DAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.DAOHoraAula;
import br.com.sistema.redAmber.DAO.DAOMatricula;
import br.com.sistema.redAmber.DAO.DAOMatriculaIntegracao;
import br.com.sistema.redAmber.DAO.DAOProfessor;
import br.com.sistema.redAmber.DAO.DAOReservaEquipamento;
import br.com.sistema.redAmber.DAO.DAOReservaSala;
import br.com.sistema.redAmber.DAO.DAOSala;
import br.com.sistema.redAmber.DAO.DAOTurma;
import br.com.sistema.redAmber.DAO.IDAOAluno;
import br.com.sistema.redAmber.DAO.IDAOAula;
import br.com.sistema.redAmber.DAO.IDAOAvisoAluno;
import br.com.sistema.redAmber.DAO.IDAOAvisoProfessor;
import br.com.sistema.redAmber.DAO.IDAOCurso;
import br.com.sistema.redAmber.DAO.IDAODisciplina;
import br.com.sistema.redAmber.DAO.IDAODuracaoAula;
import br.com.sistema.redAmber.DAO.IDAOEquipamento;
import br.com.sistema.redAmber.DAO.IDAOFuncionario;
import br.com.sistema.redAmber.DAO.IDAOGrade;
import br.com.sistema.redAmber.DAO.IDAOGrade_Disciplina;
import br.com.sistema.redAmber.DAO.IDAOHoraAula;
import br.com.sistema.redAmber.DAO.IDAOMatricula;
import br.com.sistema.redAmber.DAO.IDAOMatriculaIntegracao;
import br.com.sistema.redAmber.DAO.IDAOProfessor;
import br.com.sistema.redAmber.DAO.IDAOReservaEquipamento;
import br.com.sistema.redAmber.DAO.IDAOReservaSala;
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
private static IDAOHoraAula daoHoraAula;
private static IDAOReservaEquipamento daoReservaEquipamento;
private static IDAOReservaSala daoReservaSala;
private static IDAOAvisoProfessor daoAvisoProfessor;
private static IDAOAvisoAluno daoAvisoAluno;
private static IDAOAula daoAula;
private static IDAODuracaoAula daoDuracaoAula;
private static IDAOMatriculaIntegracao daoMatriculaIntegracao;
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

public static IDAOEquipamento getDaoEquipamento() {
	daoEquipamento = new DAOEquipamento(factory.createEntityManager());
	return daoEquipamento;
}

public static IDAOSala getDaoSala() {
	daoSala = new DAOSala(factory.createEntityManager());
	return daoSala;
}

public static IDAOHoraAula getDaoHoraAula() {
	daoHoraAula = new DAOHoraAula(factory.createEntityManager());
	return daoHoraAula;
}

public static IDAOReservaEquipamento getDaoReservaEquipamento() {
	daoReservaEquipamento = new DAOReservaEquipamento(factory.createEntityManager());
	return daoReservaEquipamento;
}

public static IDAOReservaSala getDaoReservaSala() {
	daoReservaSala = new DAOReservaSala(factory.createEntityManager());
	return daoReservaSala;
}

public static IDAOAvisoProfessor getDaoAvisoProfessor() {
	daoAvisoProfessor = new DAOAvisoProfessor(factory.createEntityManager());
	return daoAvisoProfessor;
}

public static IDAOAvisoAluno getDaoAvisoAluno() {
	daoAvisoAluno = new DAOAvisoAluno(factory.createEntityManager());
	return daoAvisoAluno;
}

public static IDAOAula getDaoAula() {
	daoAula = new DAOAula(factory.createEntityManager());
	return daoAula;
}

public static IDAODuracaoAula getDaoDuracaoAula() {
	daoDuracaoAula = new DAODuracaoAula(factory.createEntityManager());
	return daoDuracaoAula;
}

public static IDAOMatriculaIntegracao getDaoMatriculaIntegracao() {
	daoMatriculaIntegracao = new DAOMatriculaIntegracao(factory.createEntityManager());
	return daoMatriculaIntegracao;
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
