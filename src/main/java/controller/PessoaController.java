package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import model.Pessoa;
import model.PessoaTableModel;
import repositorio.PessoaRepositorio;
import util.JPAUtil;
import view.PessoaForm;

public class PessoaController {

	private Pessoa pessoa;
	private PessoaForm pessoaForm;
	private PessoaTableModel pessoaTableModel;
	private PessoaRepositorio pessoaRepo;
	private EntityManager em;
	
	public PessoaController(PessoaForm pessoaForm) {
		this.pessoaForm = pessoaForm;
		pessoaTableModel = 
				(PessoaTableModel) pessoaForm.getTabelaPessoas().getModel();
		registraAcoes();
		
		em = JPAUtil.createEntityManager();
		pessoaRepo = new PessoaRepositorio(em);
		
		List<Pessoa> pessoas = pessoaRepo.todas();
		pessoaTableModel.setPessoas(pessoas);
		pessoaTableModel.adicionarSumario();
	}
	
	private void registraAcoes() {
		pessoaForm.getBotaoAdicionar()
			.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nome = pessoaForm.getNome();
					Integer idade = pessoaForm.getIdade();
					
					if (pessoa == null) {
						pessoa = new Pessoa(nome, idade);
					} else {
						pessoa.setNome(nome);
						pessoa.setIdade(idade);
					}
					
					if (nome == null || idade == null) {
						JOptionPane.showMessageDialog(pessoaForm, "Verifique se os campos estão preenchidos");
						return;
					}
					
					em.getTransaction().begin();
					pessoaRepo.adiciona(pessoa);
					em.getTransaction().commit();
					
					pessoaTableModel.adicionar(pessoa);
					pessoaForm.limpaCampos();
					pessoa = null;
				}
			});
		
		pessoaForm.getBotaoRemover().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int linhaSelecionada = pessoaForm.getTabelaPessoas().getSelectedRow();
						
						if (linhaSelecionada != -1) {
							pessoa = pessoaTableModel.getPessoa(linhaSelecionada);
							
							em.getTransaction().begin();
							pessoaRepo.remove(pessoa);
							em.getTransaction().commit();
							
							pessoaTableModel.remover(linhaSelecionada);
						}
						
						pessoaForm.limpaCampos();
						pessoa = null;
					}
				}
				);
		
		pessoaForm.getTabelaPessoas().addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int linhaSelecionada = pessoaForm.getTabelaPessoas().getSelectedRow();
						
						pessoa = pessoaTableModel.getPessoa(linhaSelecionada);
						pessoaForm.setNome(pessoa.getNome());
						pessoaForm.setIdade(pessoa.getIdade());
							
					}
				}
				);
		
		pessoaForm.getBotaoNovo().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						pessoa = null;
						pessoaForm.limpaCampos();
					}
				}
				);
		
	}
	
	
}
