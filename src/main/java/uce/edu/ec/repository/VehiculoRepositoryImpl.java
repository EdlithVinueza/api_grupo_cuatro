package uce.edu.ec.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.modelo.Vehiculo;

@Transactional
@ApplicationScoped
public class VehiculoRepositoryImpl implements IVehiculoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Vehiculo> seleccionarTodosLosVehiculos() {
        try
        {
            return this.entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
        } catch (Exception e){
            return null;
        }
    }
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Vehiculo selecionarVehiculoPorPlaca(String placa) {
        try{
           TypedQuery<Vehiculo> query = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
            query.setParameter("placa", placa);
            return query.getSingleResult();
        } catch (Exception e){
            return Vehiculo.NoExiste();
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertarVehiculo(Vehiculo vehiculo) {
        try{
            this.entityManager.persist(vehiculo);
        } catch (Exception e){
            throw new UnsupportedOperationException("Unimplemented method 'insertarVehiculo'");
        }       
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizarVehiculo(Vehiculo vehiculo) {
        try {
            this.entityManager.merge(vehiculo);
        } catch (Exception e){
            throw new UnsupportedOperationException("Unimplemented method 'actualizarVehiculo'");
        }
    }


    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminarVehiculo(String placa) {
        try{
            this.entityManager.remove(this.selecionarVehiculoPorPlaca(placa));
        } catch (Exception e){
            throw new UnsupportedOperationException("Unimplemented method 'eliminarVehiculo'");
        }
       }

   
}
