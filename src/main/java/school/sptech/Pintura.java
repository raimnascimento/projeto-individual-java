package school.sptech;

import school.sptech.Exceptions.DadoInvalido;

public class Pintura extends ObraDeArte{
    private String tipoTinta;
    private String tecnica;

    public Pintura(Integer id, String titulo, String autor, Integer anoCriacao, String tipoTinta, String tecnica) {
        if (tipoTinta == null || tipoTinta.isBlank()){
            throw new DadoInvalido("Dado inválido");
        }
        if (tecnica == null || tecnica.isBlank()){
            throw new DadoInvalido("Técnica inválida");
        }

        super(id, titulo, autor, anoCriacao);
        this.tipoTinta = tipoTinta;
        this.tecnica = tecnica;
    }

    @Override
    public String obterDetalhes() {
        return super.obterDetalhes() + String.format(" | Tipo de Tinta: %s | Técnica: %s", tipoTinta, tecnica);
    }

    public String getTipoTinta() {
        return tipoTinta;
    }

    public void setTipoTinta(String tipoTinta) {
        this.tipoTinta = tipoTinta;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    @Override
    public String toString() {
        return "Pintura{" +
                "tipoTinta='" + tipoTinta + '\'' +
                ", tecnica='" + tecnica + '\'' +
                '}';
    }
}
