package model;

public class Tripulante extends Pessoa {
    public String cargo;

    public Tripulante(String name, String sex, int idade, String cargo) {
        super(name, sex, idade);
        this.cargo = cargo;
    }

    @Override
    public String getTipo() {
        return "Tripulante";
    }

    @Override
    public double calcularChanceSobrevivencia() {
        // Mulheres da tripulação tiveram prioridade nos botes
        if (sexo.equalsIgnoreCase("female") || sexo.equalsIgnoreCase("feminino")) {
            return 87.0;
        }

        // Homens da tripulação de acordo com a função a bordo:
        if (cargo.equalsIgnoreCase("Capitão")) {
            return 0.0; // O Capitão Smith afundou com o navio
        } else if (cargo.equalsIgnoreCase("Oficial de Bordo")) {
            return 28.0; // Poucos oficiais nos botes de comando
        } else if (cargo.equalsIgnoreCase("Marinheiro / Vigia")) {
            return 45.0; // Precisavam de homens para manobrar/remar os botes
        } else if (cargo.equalsIgnoreCase("Comissário (Steward)")) {
            return 18.0;
        } else if (cargo.equalsIgnoreCase("Engenheiro / Foguista")) {
            return 10.0; // Ficaram nas caldeiras até o último minuto
        } else {
            return 22.0; // Valor padrão para outros cargos (ex: Cozinheiro)
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - Cargo: " + cargo + " - Chance: " + calcularChanceSobrevivencia() + "%";
    }
}