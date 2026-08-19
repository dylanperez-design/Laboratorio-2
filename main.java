import java.util.List;
import java.util.ArrayList;

public class Main
{ 
    public static abstract class Sensor{
        private final String id;
        private final String ubicacion;
        private volatile boolean activo;
        
        public Sensor(String id, String ubicacion, boolean activo){
            this.id= id;
            this.ubicacion=ubicacion;
            this.activo=activo;
        }
        public String getId(){return id;}

        public String getUbicacion(){return ubicacion;}    

        public boolean isActivo(){return activo;}
        public void setActivo(boolean activo){this.activo = activo;}

        public abstract double tomarLectura();
        public abstract String evaluarEstado();
    }

    public static class SensorHumedadSuelo extends Sensor{
        private double humedadPct;

        public SensorHumedadSuelo(String id, String ubicacion, boolean activo, double humedadPct){
            super(id,ubicacion,activo);
            this.humedadPct=humedadPct;
        }
        
        @Override
        public double tomarLectura(){
            humedadPct = Math.random() * 100;
            return humedadPct;
        }

        @Override
        public String evaluarEstado(){
            if(!isActivo()){
                return "inactivo";
            }
            if(humedadPct < 20.0){
                return "critico";
            }else{
                return "estable";
            }
        }
    }

    public static class SensorTemperatura extends Sensor{
        private double celsius;

        public SensorTemperatura(String id, String ubicacion, boolean activo, double celsius){
            super(id,ubicacion,activo);
            this.celsius=celsius;
        }

        @Override
        public  double tomarLectura(){
            celsius = 15 + Math.random() * (30);
            return celsius;
        }

        @Override
        public String evaluarEstado(){
            if(!isActivo()){
                return "inactivo";
            }
            if(celsius > 38.0){
                return "critico";
            }else{
                return "estable";
            }
        }
    }

    public static class EstacionMonitoreo{
        private List<Sensor> sensores;

        public EstacionMonitoreo(){
            sensores = new ArrayList<>();
        }
        
        public void agregarSensor(Sensor sensor){
            sensores.add(sensor);
        }

        public void procesarlecturas(){
            for(Sensor sensor:sensores){
                double lectura = sensor.tomarLectura();

                System.out.println("Sensor " + sensor.getId() + " - Lectura:" + lectura);
            }
        }

        public List<Sensor> filtrarCriticos(){
            List<Sensor> criticos = new ArrayList<>();

            for(Sensor sensor: sensores){
                if(sensor.evaluarEstado().equals("critico")){
                    criticos.add(sensor);
                }
            }
            return criticos;
        }
    }  

    public static void main(String[] args) {
        	
        
    }
}
