package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PessoaController;
import model.PessoaTableModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PessoaForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTable tabelaPessoas;
	private JButton btnAdicionar = new JButton("Adicionar");
	private JButton btnRemover = new JButton("Remover");
	private JButton btnNovo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PessoaForm pessoaForm = new PessoaForm();
					new PessoaController(pessoaForm);
					pessoaForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PessoaForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnNovo = new JButton("Nova Pessoa");
		contentPane.add(btnNovo, BorderLayout.NORTH);
		
		JPanel painelInferior = new JPanel();
		contentPane.add(painelInferior, BorderLayout.SOUTH);
		painelInferior.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblNome = new JLabel("Nome");
		painelInferior.add(lblNome);
		
		txtNome = new JTextField();
		painelInferior.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade");
		painelInferior.add(lblIdade);
		
		txtIdade = new JTextField();
		painelInferior.add(txtIdade);
		txtIdade.setColumns(10);
		
		painelInferior.add(btnAdicionar);
		
		painelInferior.add(btnRemover);
		
		JScrollPane painelTabela = new JScrollPane();
		contentPane.add(painelTabela, BorderLayout.CENTER);
		
		tabelaPessoas = new JTable();
		PessoaTableModel pessoaTableModel 
			= new PessoaTableModel();
		tabelaPessoas.setModel(pessoaTableModel);
		painelTabela.setViewportView(tabelaPessoas);
	}
	
	public JButton getBotaoAdicionar() {
		return btnAdicionar;
	}
	
	public JButton getBotaoRemover() {
		return btnRemover;
	}
	
	public JButton getBotaoNovo() {
		return btnNovo;
	}
	
	public String getNome() {
		return txtNome.getText();
	}
	
	public Integer getIdade() {
		if (txtIdade.getText() == null
				|| txtIdade.getText().equals(""))
			return null;
		return Integer.valueOf(txtIdade.getText());
	}
	
	public void setNome(String nome) {
		txtNome.setText(nome);
	}
	
	public void setIdade(Integer idade) {
		txtIdade.setText(idade.toString());
	}
	
	public JTable getTabelaPessoas() {
		return tabelaPessoas;
	}
	
	public void limpaCampos() {
		txtNome.setText(null);
		txtIdade.setText(null);
		txtNome.grabFocus();
	}

}
