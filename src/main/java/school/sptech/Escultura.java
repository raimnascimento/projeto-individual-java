package school.sptech;

import school.sptech.Exceptions.DadoInvalido;

public class Escultura extends ObraDeArte{
    private String material;
    private Double peso;

    public Escultura(Integer id, String titulo, String autor, Integer anoCriacao, String material, Double peso) {
        if (material == null || material.isBlank()){
            throw new DadoInvalido("Material inválido");
        }
        if (peso == null || peso <= 0){
            throw new DadoInvalido("Peso inválido");
        }
        super(id, titulo, autor, anoCriacao);
        this.material = material;
        this.peso = peso;
    }

    @Override
    public String obterDetalhes() {
        return super.obterDetalhes() + String.format(" | Material: %s | Peso: %s", material, peso);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Escultura{" +
                "material='" + material + '\'' +
                ", peso=" + peso +
                '}';
    }
}
