package uce.edu.ec.service;

import uce.edu.ec.service.to.UsuarioTo;

public interface IUsuarioToService {

    UsuarioTo buscarUsuarioToPorNombre(String nombre);

    UsuarioTo buscarUsuarioToPorId(Integer id);

    void guardarUsuarioTo(UsuarioTo UsuarioTo);

    void actualizarUsuarioTo(UsuarioTo UsuarioTo);

    void borrarUsuarioTo(String nombre);

    UsuarioTo buscarPorUsuarioToYContrasenia(String UsuarioTo, String contrasenia);

}
