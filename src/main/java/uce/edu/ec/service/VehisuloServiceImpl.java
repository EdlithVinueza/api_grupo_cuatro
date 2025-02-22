package uce.edu.ec.service;

import java.util.List;
import java.util.function.Function;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.IVehiculoRepository;
import uce.edu.ec.repository.modelo.Vehiculo;
import uce.edu.ec.service.to.VehiculoTo;

@ApplicationScoped
public class VehisuloServiceImpl implements IVehisuloService {

    @Inject
    private IVehiculoRepository iVehiculoRepository;

    // Convertir de VehiculoTo y Vehiculo
    private Function<VehiculoTo, Vehiculo> convertirVehiculoToAVehiculo = VehiculoTo -> {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(VehiculoTo.getId());
        vehiculo.setPlaca(VehiculoTo.getPlaca());
        vehiculo.setMarca(VehiculoTo.getMarca());
        vehiculo.setFechaFabricacion(VehiculoTo.getFechaFabricacion());
        vehiculo.setColor(VehiculoTo.getColor());
        vehiculo.setCosto(VehiculoTo.getCosto());
        vehiculo.setActivo(VehiculoTo.getActivo());
        vehiculo.setOculto(VehiculoTo.getOculto());
        vehiculo.setFotoUrl(VehiculoTo.getFotoUrl());
        return vehiculo;
    };

    /// Convertir de Vehiculo y Vehiculo
    private Function<Vehiculo, VehiculoTo> convertirVehiculoAVehiculoTo = Vehiculo -> {
        VehiculoTo vehiculoTo = new VehiculoTo();
        vehiculoTo.setId(Vehiculo.getId());
        vehiculoTo.setPlaca(Vehiculo.getPlaca());
        vehiculoTo.setMarca(Vehiculo.getMarca());
        vehiculoTo.setFechaFabricacion(Vehiculo.getFechaFabricacion());
        vehiculoTo.setColor(Vehiculo.getColor());
        vehiculoTo.setCosto(Vehiculo.getCosto());
        vehiculoTo.setActivo(Vehiculo.getActivo());
        vehiculoTo.setOculto(Vehiculo.getOculto());
        vehiculoTo.setFotoUrl(Vehiculo.getFotoUrl());
        return vehiculoTo;
    };

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public List<VehiculoTo> buscarTodosLosVehiculos() {
        List<Vehiculo> tmp = iVehiculoRepository.seleccionarTodosLosVehiculos();
        return tmp.stream().map(convertirVehiculoAVehiculoTo).toList();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public VehiculoTo buscarVehiculoPorPlaca(String placa) {
        Vehiculo tmp = iVehiculoRepository.selecionarVehiculoPorPlaca(placa);
        return convertirVehiculoAVehiculoTo.apply(tmp);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarVehiculo(VehiculoTo vehiculoTo) {

        iVehiculoRepository.insertarVehiculo(convertirVehiculoToAVehiculo.apply(vehiculoTo));
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizarVehiculo(VehiculoTo vehiculoTo) {

        iVehiculoRepository.actualizarVehiculo(convertirVehiculoToAVehiculo.apply(vehiculoTo));
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void borrarVehiculo(String placa) {

        iVehiculoRepository.eliminarVehiculo(placa);
    }

}
