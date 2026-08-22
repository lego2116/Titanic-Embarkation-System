package model;

public class Passageiro extends Pessoa {
		
	public int idpassageiro;
	public int classe;
	public double passagem;
	public String embarque;
	
	public Passageiro(String nome, String sexo, int idade, int idpassageiro, int classe, double passagem,
			String embarque) {
		super(nome, sexo, idade);
		this.idpassageiro = idpassageiro;
		this.classe = classe;
		this.passagem = passagem;
		this.embarque = embarque;
	}

	public Passageiro(String nome, String sexo, int idade, int classe) {
	    super(nome, sexo, idade);
	    this.classe = classe;
	    this.idpassageiro = 0;
	    this.passagem = 0;
	    this.embarque = "S";
	}
	
	@Override
	public String getTipo(){
		return "Passageiro";
	}
	
	@Override
	public double calcularChanceSobrevivencia() {
	    if (idade < 13) {
	        if (classe == 1 || classe == 2) {
	            return 100.0;
	        } else {
	            return 45.0;
	        }
	    } else if (sexo.equalsIgnoreCase("feminino") || sexo.equalsIgnoreCase("feminino")) {
	        if (classe == 1) {
	            return 96.0;
	        } else if (classe == 2) {
	            return 89.0;
	        } else {
	            return 50.0;
	        }
	    } else {
	        if (classe == 1) {
	            return 36.0;
	        } else if (classe == 2) {
	            return 15.0;
	        } else {
	            return 13.0;
	        }
	    }
	}
	
	@Override
	public String toString() {
	    return super.toString() + " - Classe: " + classe + "ª - Chance: " + calcularChanceSobrevivencia() + "%";
	}
}
