package model;

public abstract class Pessoa implements Embarcavel {

	public String nome;
	public String sexo;
	public int idade;
	
	public Pessoa(String nome, String sexo, int idade) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
	}
	
	public abstract String getTipo();
	
	@Override 
	public String toString() {
		return nome + " - " + sexo + " - " + idade + " anos";
		
	}
	
	
}
