package uce.edu.ec.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.modelo.Usuario;

@Transactional
@ApplicationScoped
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED) // por que no vamos a modificar nada en la base
    public Usuario seleccionarUsuarioPorNombre(String nombre) {
        try{
            return this.entityManager.find(Usuario.class, nombre);
        } catch (Exception e){
            return Usuario.NoExiste();
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED) // por que no vamos a modificar nada en la base
    public Usuario seleccionarUsuarioPorId(Integer id) {
        try{
            return this.entityManager.find(Usuario.class, id);
        }catch (Exception e){
            return Usuario.NoExiste();
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertarUsuario(Usuario usuario) {
        this.entityManager.persist(usuario);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizarUsuario(Usuario usuario) {
        this.entityManager.merge(usuario);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminarUsuario(String nombre) {
        this.entityManager.remove(this.seleccionarUsuarioPorNombre(nombre));
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public Usuario seleccionarPorUsuarioYContrasenia(String usuario, String contrasenia) {
        try{
        return this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasenia = :contrasenia", Usuario.class)
                .setParameter("usuario", usuario)
                .setParameter("contrasenia", contrasenia)
                .getSingleResult();
        }catch (Exception e){
            return Usuario.NoExiste();
        }
    }

}
