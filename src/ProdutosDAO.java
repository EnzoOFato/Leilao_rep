

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn = new conectaDAO().connectDB();
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> vendas = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){              
        String comando = "insert into produtos values (?,?,?,?)";              
        try {
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
        String consulta = "Select * from produtos";
        try{
            prep = conn.prepareStatement(consulta);
            resultset = prep.executeQuery();
            while(resultset.next()){
                ProdutosDTO pro = new ProdutosDTO();
                pro.setId(resultset.getInt("id"));
                pro.setNome(resultset.getString("nome"));
                pro.setValor(resultset.getInt("valor"));
                pro.setStatus(resultset.getString("status"));
                listagem.add(pro);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha na listagem de produtos");
        }
        return listagem;
    }
    
    public void venderProduto(int id){
        String update = "update produtos set status = 'vendido' where id = ?";
        try{
            prep = conn.prepareStatement(update);
            prep.setInt(1, id);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "O status do produto " + id + " foi alterado com sucesso!");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha ao atualizar status: \n"+e.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        String consulta = "select * from produtos where status = 'Vendido' ";
        try{
            prep = conn.prepareStatement(consulta);
            resultset = prep.executeQuery();
            while(resultset.next()){
                ProdutosDTO pro = new ProdutosDTO();
                pro.setId(resultset.getInt("id"));
                pro.setNome(resultset.getString("nome"));
                pro.setValor(resultset.getInt("valor"));
                pro.setStatus(resultset.getString("status"));
                vendas.add(pro);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar vendas " + e.getMessage());
        }
        return vendas;
    }
}

