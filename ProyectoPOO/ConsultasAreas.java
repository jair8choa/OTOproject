import java.util.*;
public class ConsultasAreas {  
    
    operArchArea archAreas;
    ArrayList<Area> areas;
    
    public ConsultasAreas(){
        this.archAreas = new operArchArea("areas.txt");
        this.actualizarArray();
    }

    public void actualizarArray(){
        this.archAreas.Abrir("rw");
        int numAreas = this.archAreas.numReg();
        this.areas = new ArrayList<Area>();
        for(int i = 0; i < numAreas; i++)
            this.areas.add(this.archAreas.Leer(i));
        this.archAreas.Cerrar();
    }

    public Area getArea(int idArea){
        int id;
        for (Area area : this.areas) {
            id = area.getIdArea();
            if (id == idArea)
            return area;
        }
        return null;
    }

    public ArrayList<Area> getAreas(){
        return this.areas;
    }

    public int total() {
        return this.areas.size();
    }

    public boolean existe(int idArea){
        int id;
        for (Area area : this.areas) {
            id = area.getIdArea();
            if (id == idArea)
                return true;
        }
        return false;
    }

    public int posicion(int idArea){
        int cont = 0;
        int id;
        for (Area area : this.areas) {
            id = area.getIdArea();
            cont+=1;
            if (idArea == id)
                return cont-1;
        }
        return -1;
    }

    public void abrir(String tipo)
    {
        this.archAreas.Abrir(tipo);
    }

    public void cerrar()
    {
        this.archAreas.Cerrar();
    }

    public void mostrar(){
        for (Area area : this.areas){
            if (area.Eliminado() == false) {
                System.out.println(area);
            }
        }            
    }

    public void crear() {
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        Area area = new Area();
        System.out.print("Nombre: ");
        area.putNombreArea(sc.next());
        System.out.print("Descripcion: ");
        area.putDescAct(sc.next());
        System.out.print("Piso: ");
        area.putPiso(sc.nextInt());
        System.out.print("Direccion: ");
        area.putDireccion(sc.next());
        System.out.println("");
        archAreas.Grabar(area, this.total());
        this.cerrar();
        this.actualizarArray();
    }

    public void modificar(int idArea){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        if (!this.existe(idArea)) {
            System.out.println("Ususario no existe");
            System.out.println("");
            this.cerrar();
        }else{
            Area area= this.getArea(idArea);
            int posicion = this.posicion(idArea);
            System.out.print("Nombre("+area.getNombreArea()+"): ");
            area.putNombreArea(sc.next());
            System.out.print("Descripcion("+area.getDescA()+"): ");
            area.putDescAct(sc.next());
            System.out.print("Piso("+area.getPiso()+"): ");
            area.putPiso(sc.nextInt());
            System.out.print("Direccion("+area.getDireccionA()+"): ");
            area.putDireccion(sc.next());
            archAreas.Grabar(area, posicion);
            this.cerrar();
            this. actualizarArray();
            System.out.println("");

        }
    }

    public void eliminar(int idArea){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        if (!this.existe(idArea)) {
            System.out.println("Area no existe");
            System.out.println("");
        }else{
            Area area= this.getArea(idArea);
            int posicion = this.posicion(idArea);
            System.out.println("Seguro que quieres eliminar("+area.getNombreArea()+")");
            System.out.print("Si(s) / No(n): ");
            if (sc.next().charAt(0) == 's') {
                area.Eliminado(true);
                archAreas.Grabar(area, posicion);
                System.out.println(area.getNombreArea()+" ha sido eliminada");

            }else{
                System.out.println(area.getNombreArea()+" no ha sido eliminada");
            }
            this.cerrar();
            this.actualizarArray();
            
        }
    }

    public void consultas(int idArea) {
        Scanner sc = new Scanner(System.in);
        int opcConsulta = 0;
        if (!this.existe(idArea)) {
            System.out.println("Area no existe");
        }else{
            Area area = new ConsultasAreas().getArea(idArea);
            do {
                System.out.println("");
                System.out.println("        C O N S U L T A S ("+area.getNombreArea()+")");
                System.out.println("        1) Informe ");
                System.out.println("        2) Medicos en Area ");
                System.out.println("        3) Pacientes en Area ");
                System.out.println("        4) Salir ");
                System.out.print("      Opcion: ");
                opcConsulta = sc.nextInt();
                System.out.println("");
                switch (opcConsulta) {
                    case 1:// Informe 
                        this.informe(idArea);                                           
                        break;
                    case 2:// Medicos
                        this.medicos(idArea);                                            
                        break;    
                    case 3:// Pacientes 
                        this.pacientes(idArea);                                           
                        break;
                    case 4:// Salir                                            
                        break;
                    default:
                        System.out.println("   Opcion Incorrecta\n"); break;
                }
            } while (opcConsulta != 4); 
        }        
    }

    private void informe(int idArea){
        Area area = this.getArea(idArea);
        System.out.println("");
        System.out.println("        INFORME");
        System.out.println("        Id Area: "+area.getIdArea());
        System.out.println("        Nombre: "+area.getNombreArea());
        System.out.println("        Descripcion: "+area.getDescA());
        System.out.println("        Piso: "+area.getPiso());
        System.out.println("        Direccion: "+area.getDireccionA());
        System.out.println("");
    }

    private void medicos(int idArea){
        ArrayList<Medico> arrayMedicos = new ConsultasMedicos().getMedicos();
        System.out.println("        MEDICOS EN AREA"); 
        for (Medico medico : arrayMedicos) {
            if (medico.getIdArea() == idArea) {
                System.out.println("        "+medico);
            }
        }

    }

    private void pacientes(int idArea){
        ArrayList<Paciente> arrayPaciente = new ConsultasPacientes().getPacientes();
        System.out.println("        PACIENTES EN AREA"); 
        for (Paciente paciente : arrayPaciente) {
            if (paciente.getIdArea() == idArea) {
                if (paciente.getAlta() == false) {
                    System.out.println("        "+paciente);
                }                
            }
        }

    }
}