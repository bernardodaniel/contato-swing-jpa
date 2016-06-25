package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PessoaTableModel 
	extends AbstractTableModel {

	private List<Pessoa> pessoas 
		= new ArrayList<>();
	private String[] colunas = {"Nome", "Idade"};
			
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void adicionar(Pessoa pessoa) {
		if (pessoas.contains(pessoa)) {
			pessoas.remove(pessoa);
		}
		pessoas.add(pessoa);
		fireTableDataChanged();
	}
	
	public void remover(int rowIndex) {
		pessoas.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public Pessoa getPessoa(int rowIndex) {
		return pessoas.get(rowIndex);
	}
	
	@Override
	public int getRowCount() {
		return pessoas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pessoa pessoa = pessoas.get(rowIndex);
		
		if (columnIndex == 0)
			return pessoa.getNome();
		
		if (columnIndex == 1)
			return pessoa.getIdade();
		
		return null;
	}

	public void adicionarSumario() {
		int soma = 0;
		
		for (Pessoa pessoa : pessoas) {
			soma += pessoa.getIdade();
		}
		
		adicionar(new Pessoa("Total", soma));
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		Object texto = getValueAt(rowIndex, 0);
		if (!(texto instanceof String))
			return true;
		
		if ("Total".equals(texto)) {
			return false;
		}
		
		return super.isCellEditable(rowIndex, columnIndex);
	}

}
