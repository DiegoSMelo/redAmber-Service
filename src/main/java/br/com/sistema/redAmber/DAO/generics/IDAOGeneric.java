package br.com.sistema.redAmber.DAO.generics;
 
import java.util.List;
 
public interface IDAOGeneric<Entidade> {
 
    public void inserir(Entidade entidade);
     
    public void alterar(Entidade entidade);
     
    public void remover(Entidade entidade);
     
    public Entidade consultarPorId(Long id);
     
    public List<Entidade> consultarTodos();
     
    public List<Entidade> consultarTodos(Integer indiceInicial,   Integer quantidade);
     
 
}