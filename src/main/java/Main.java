import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//actividad: agregar codigo de factura agregar cantidad de producto
// agregar fecha de factura poder hacer mas filtros.
// -por fecha < por fecha >
// -cantidad = < >
// -codigo de factura especifico =


// clase factura
class Factura{
    String codigo;
    LocalDate fecha;
    String descripcion;
    int cantidad;
    int importe;

    public Factura(String codigo, LocalDate fecha, String descripcion, int cantidad, int importe) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getImporte() {
        return importe;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", importe=" + importe +
                '}';
    }
}

public class Main {


    public static void main(String[] args) {
        // registros de la data
        Factura f=new Factura("F0004",LocalDate.of(2022,03,16),"ordenador", 1, 800);
        Factura f2=new Factura("F0003",LocalDate.of(2022,03,16),"teclado", 2, 200);
        Factura f3=new Factura("F0002",LocalDate.of(2022,01,16),"pantalla", 3, 900);
        Factura f4=new Factura("F0001",LocalDate.of(2022,01,16),"mouse", 1, 100);

        // generar una lista
        List<Factura> lista=new ArrayList<Factura>();

        // agregar los productos de la factura
        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);

        Predicate<Factura> predicadoImporte= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getImporte()>799;
            }
        };

        Predicate<Factura> predicadoCodigo= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getCodigo().contains("F0003");
            }
        };

        Predicate<Factura> predicadoDescripcion= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getDescripcion().contains("teclado");
            }
        };

        Predicate<Factura> predicadoFechaAnterior= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getFecha().isBefore(LocalDate.of(2022,02,16));
            }
        };

        Predicate<Factura> predicadoFechaPosterior= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getFecha().isAfter(LocalDate.of(2022,02,16));
            }
        };

        Predicate<Factura> predicadoCantidadIgual= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getCantidad()==2;
            }
        };

        Predicate<Factura> predicadoCantidadMenor= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getCantidad()<2;
            }
        };

        Predicate<Factura> predicadoCantidadMayor= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getCantidad()>2;
            }
        };

        Factura facturaFiltro=lista.stream()
                .filter(predicadoCantidadMayor).findFirst().get();

        System.out.println("FACTURA UNICA : "+facturaFiltro.toString());


        /*
        // filtraje funcional con streams
        Factura facturaFiltro=lista.stream()
                .filter(elemento->elemento.getImporte()>300)
                .findFirst()
                .get();
        System.out.println(facturaFiltro.getImporte());
        */
    }

}
