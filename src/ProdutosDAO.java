import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.execute();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao cadastrar produto: " + erro.getMessage()
            );
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        listagem.clear();

        conn = new conectaDAO().connectDB();

        String sql = "SELECT * FROM produtos";

        try {

            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                null,
                "Erro ao listar produtos: " + erro.getMessage()
            );
        }

        return listagem;
    }
    public void venderProduto(Integer id) {

    conn = new conectaDAO().connectDB();

    String sql = "UPDATE produtos SET status = ? WHERE id = ?";

    try {

        prep = conn.prepareStatement(sql);

        prep.setString(1, "Vendido");
        prep.setInt(2, id);

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao vender produto: " + erro.getMessage()
        );
    }}
    public ArrayList<ProdutosDTO> listarVendas() {

    ArrayList<ProdutosDTO> vendas = new ArrayList<>();

    conn = new conectaDAO().connectDB();

    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    try {

        prep = conn.prepareStatement(sql);

        resultset = prep.executeQuery();

        while (resultset.next()) {

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            vendas.add(produto);
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao listar vendas: " + erro.getMessage()
        );
    }

    return vendas;

}
}