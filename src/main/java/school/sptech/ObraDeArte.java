package school.sptech;

import school.sptech.Exceptions.DadoInvalido;

import java.time.LocalDate;
import java.util.zip.DataFormatException;

public class ObraDeArte {
    private Integer id;
    private String titulo;
    private String autor;
    private Integer anoCriacao;
    private LocalDate dataAtual = LocalDate.now();

    public ObraDeArte(Integer id, String titulo, String autor, Integer anoCriacao) {
        if (titulo == null || titulo.isBlank()){
            throw new DadoInvalido("Título inválido");
        }
        if (autor == null || autor.isBlank()){
            throw new DadoInvalido("Autor inválido");
        }
        if (anoCriacao == null || anoCriacao > dataAtual.getYear()){
            throw new DadoInvalido("Ano de criação inválido");
        }

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoCriacao = anoCriacao;
    }

    public String obterDetalhes() {
        return String.format("ID: %d | Título: '%s' | Autor: %s | Ano: %d",
                id, titulo, autor, anoCriacao);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoCriacao() {
        return anoCriacao;
    }

    public void setAnoCriacao(Integer anoCriacao) {
        this.anoCriacao = anoCriacao;
    }

    @Override
    public String toString() {
        return "ObraDeArte{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoCriacao=" + anoCriacao +
                '}';
    }
}
