package controles;

import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;

import Banco.Conexao;
import entidades.Professor;



public class EscutadorDaLista implements ListChangeListener<Professor> {

	private ObservableList<Professor> list;

	public EscutadorDaLista(ObservableList<Professor> list) {
		this.list = list;
	}

	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Professor> c) {

		while (c.next()) {

			// Removes entities
			if (c.getRemoved().size() > 0) {
				this.purgeEntities(c.getRemoved());
				break;
			}

			// Updates entities
			if (c.wasUpdated()) {
				this.updateContato(list.get(c.getTo() - 1));
				break;
			}

			// Insert new entities
			if (c.getAddedSubList().size() > 0) {
				this.insertContatos(c.getAddedSubList());
				break;
			}
		}

	}

	private void purgeEntities(List<? extends Professor> ContatosList) {

		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();

		// Removes entities
		for (Professor a : ContatosList) {
			gerenciador.remove(gerenciador.find(Professor.class, a.getId_professor()));
			System.out.println("Removeu " + a.getNome_professor());
		}

		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

	private void updateContato(Professor a) {
		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();
		gerenciador.merge(a);
		System.out.println("Atualizou " + a.getNome_professor());
		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

	private void insertContatos(List<? extends Professor> ContatosList) {

		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();

		for (Professor a : ContatosList) {
			gerenciador.persist(a);
			System.out.println("Inseriu " + a.getNome_professor());
		}

		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

}
