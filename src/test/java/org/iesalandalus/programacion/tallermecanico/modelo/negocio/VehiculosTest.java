package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VehiculosTest {

    private static Vehiculo vehiculo1;
    private static Vehiculo vehiculo2;
    private IVehiculos iVehiculos;

    @BeforeAll
    static void setup() {
        vehiculo1 = mock();
        when(vehiculo1.matricula()).thenReturn("1234BCD");
        vehiculo2 = mock();
        when(vehiculo2.matricula()).thenReturn("1111BBB");
    }

    @BeforeEach
    void init() {
        iVehiculos = new Vehiculos();
    }

    @Test
    void constructorCreaVehiculosCorrectamente() {
        assertNotNull(iVehiculos);
        assertEquals(0, iVehiculos.get().size());
    }

    @Test
    void getDevuelveVehiculosCorrectamente() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo2));
        List<Vehiculo> copiaVehiculos = iVehiculos.get();
        assertEquals(vehiculo1, copiaVehiculos.get(0));
        assertSame(vehiculo1, copiaVehiculos.get(0));
        assertEquals(vehiculo2, copiaVehiculos.get(1));
        assertSame(vehiculo2, copiaVehiculos.get(1));
    }

    @Test
    void insertarVehiculoValidoInsertaCorrectamente() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        assertEquals(vehiculo1, iVehiculos.buscar(vehiculo1));
        assertSame(vehiculo1, iVehiculos.buscar(vehiculo1));
    }

    @Test
    void insertarVehiculoNuloLanzaExcepcion() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> iVehiculos.insertar(null));
        assertEquals("No se puede insertar un vehículo nulo.", npe.getMessage());
    }

    @Test
    void insertarVehiculoRepetidoLanzaExcepcion() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        TallerMecanicoExcepcion tme = assertThrows(TallerMecanicoExcepcion.class, () -> iVehiculos.insertar(vehiculo1));
        assertEquals("Ya existe un vehículo con esa matrícula.", tme.getMessage());
    }

    @Test
    void borrarVehiculoExistenteBorraVehiculoCorrectamente() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        assertDoesNotThrow(() -> iVehiculos.borrar(vehiculo1));
        assertNull(iVehiculos.buscar(vehiculo1));
    }

    @Test
    void borrarVehiculoNoExistenteLanzaExcepcion() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        TallerMecanicoExcepcion tme = assertThrows(TallerMecanicoExcepcion.class, () -> iVehiculos.borrar(vehiculo2));
        assertEquals("No existe ningún vehículo con esa matrícula.", tme.getMessage());
    }

    @Test
    void borrarVehiculoNuloLanzaExcepcion() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        NullPointerException npe = assertThrows(NullPointerException.class, () -> iVehiculos.borrar(null));
        assertEquals("No se puede borrar un vehículo nulo.", npe.getMessage());
    }

    @Test
    void busarVehiculoExistenteDevuelveVehiculoCorrectamente() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        assertEquals(vehiculo1, iVehiculos.buscar(vehiculo1));
        assertSame(vehiculo1, iVehiculos.buscar(vehiculo1));
    }

    @Test
    void busarVehiculoNoExistenteDevuelveVehiculoNulo() {
        assertNull(iVehiculos.buscar(vehiculo1));
    }

    @Test
    void buscarVehiculoNuloLanzaExcepcion() {
        assertDoesNotThrow(() -> iVehiculos.insertar(vehiculo1));
        NullPointerException npe = assertThrows(NullPointerException.class, () -> iVehiculos.buscar(null));
        assertEquals("No se puede buscar un vehículo nulo.", npe.getMessage());
    }
}