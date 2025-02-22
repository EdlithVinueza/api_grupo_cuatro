package uce.edu.ec.repository;

import uce.edu.ec.repository.modelo.Usuario;

public interface IUsuarioRepository {

    Usuario seleccionarUsuarioPorNombre(String nombre);

    Usuario seleccionarUsuarioPorId(Integer id);

    void insertarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(String nombre);

    Usuario seleccionarPorUsuarioYContrasenia(String usuario, String contrasenia);

}
