package br.com.todo.dao;

import br.com.todo.model.Categoria;
import br.com.todo.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void addCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias(nome, descricao) VALUES (?, ?)";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setString(2, categoria.getDescricao());
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<CategoriaDAO> getCategorias() {
        String sql = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<>();

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                categoria.setDescricao(resultSet.getString("descricao"));

                categorias.add(categoria);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public void updateCategoria(Categoria categoria) {
        String sql = "UPDATE categorias SET nome = ?, descricao = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setString(2, categoria.getDescricao());
            preparedStatement.setInt(3, categoria.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoria(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection connection = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //estou fazendo uma aplicação crud DAO em java
    //, porém nao sei ainda como fazer uma interface 
    //simples de email e senha, e acessar os meus códigos, 
    //consegue me orientar?
}
    