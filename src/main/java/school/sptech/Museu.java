package school.sptech;

import school.sptech.Exceptions.DadoInvalido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Museu {
    private String nome;

    public Museu(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadoInvalido("Nome inválido");
        }
        this.nome = nome;
    }

    public void cadastrarObra(ObraDeArte obra) {
        if (obra == null) {
            throw new DadoInvalido("Obra inválida, preencha corretamente");
        }

        String sql = "INSERT INTO obra_de_arte (id, titulo, autor, ano_criacao, tipo_obra, tipo_tinta, tecnica, material, peso) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, obra.getId());
            stmt.setString(2, obra.getTitulo());
            stmt.setString(3, obra.getAutor());
            stmt.setInt(4, obra.getAnoCriacao());

            if (obra instanceof Pintura) {
                Pintura p = (Pintura) obra;
                stmt.setString(5, "PINTURA");
                stmt.setString(6, p.getTipoTinta());
                stmt.setString(7, p.getTecnica());
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.DOUBLE);
            } else if (obra instanceof Escultura) {
                Escultura e = (Escultura) obra;
                stmt.setString(5, "ESCULTURA");
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setString(8, e.getMaterial());
                stmt.setDouble(9, e.getPeso());
            }

            stmt.executeUpdate();
            System.out.println("\nObra cadastrada com sucesso!");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("\n[!] Erro: Já existe uma obra cadastrada com o ID " + obra.getId());
            } else {
                System.out.println("\n[!] Erro ao salvar: " + e.getMessage());
            }
        }
    }

    public void removerObraPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new DadoInvalido("ID inválido");
        }

        String sql = "DELETE FROM obra_de_arte WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("\n[-] Obra removida com sucesso!");
            } else {
                System.out.println("\n[!] Nenhuma obra encontrada com o ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("\n[!] Erro ao remover do banco: " + e.getMessage());
        }
    }

    public List<ObraDeArte> listarObras() {
        List<ObraDeArte> acervo = new ArrayList<>();
        String sql = "SELECT * FROM obra_de_arte";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoCriacao = rs.getInt("ano_criacao");
                String tipoObra = rs.getString("tipo_obra");

                if ("PINTURA".equalsIgnoreCase(tipoObra)) {
                    String tipoTinta = rs.getString("tipo_tinta");
                    String tecnica = rs.getString("tecnica");
                    acervo.add(new Pintura(id, titulo, autor, anoCriacao, tipoTinta, tecnica));
                } else if ("ESCULTURA".equalsIgnoreCase(tipoObra)) {
                    String material = rs.getString("material");
                    double peso = rs.getDouble("peso");
                    acervo.add(new Escultura(id, titulo, autor, anoCriacao, material, peso));
                }
            }

        } catch (SQLException e) {
            System.out.println("\n[!] Erro ao consultar no banco: " + e.getMessage());
        }

        return acervo;
    }

    public List<Pintura> listarPinturas() {
        List<Pintura> pinturas = new ArrayList<>();
        String sql = "SELECT * FROM obra_de_arte WHERE tipo_obra = 'PINTURA'";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoCriacao = rs.getInt("ano_criacao");
                String tipoTinta = rs.getString("tipo_tinta");
                String tecnica = rs.getString("tecnica");

                Pintura pintura = new Pintura(id, titulo, autor, anoCriacao, tipoTinta, tecnica);
                pinturas.add(pintura);
            }

        } catch (SQLException e) {
            System.out.println("\n[!] Erro ao consultar pinturas no banco: " + e.getMessage());
        }

        return pinturas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Museu{" +
                "nome='" + nome + '\'' +
                '}';
    }
}