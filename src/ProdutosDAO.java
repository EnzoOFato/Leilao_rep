

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){              
        String comando = "insert into produtos values (?,?,?,?)";              
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(comando);
            prep.setInt(1, produto.getId());
            prep.setString(2,produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "O produto foi cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar produtos");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

