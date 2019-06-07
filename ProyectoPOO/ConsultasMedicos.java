import java.util.*;
public class ConsultasMedicos {  
    
    operArchMedico archMedicos;
    ArrayList<Medico> medicos;
    
    public ConsultasMedicos(){
        this.archMedicos = new operArchMedico("medicos.txt");
        this.actualizarArray();
    }

    public void actualizarArray(){
        this.archMedicos.Abrir("rw");
        int numMedicos = this.archMedicos.numReg();
        this.medicos = new ArrayList<Medico>();
        for(int i = 0; i < numMedicos; i++)
            this.medicos.add(this.archMedicos.Leer(i));
        this.archMedicos.Cerrar();
    }

    public Medico getMedico(int idMedico){
        int id;
        for (Medico medico : this.medicos) {
            id = medico.getIdMedico();
            if (id == idMedico)
                return medico;
        }
        return null;
    }

    public ArrayList<Medico> getMedicos(){
        return this.medicos;
    }

    public int total() {
        return medicos.size();
    }

    public boolean existe(int idMedico){
        int id;
        for (Medico medico : this.medicos){
            id = medico.getIdMedico();
            if (id == idMedico)
                return true;
        }
        return false;
    }

    public int posicion(int idMedico){
        int cont = 0;
        int id;
        for (Medico medico : this.medicos) {
            id = medico.getIdMedico();
            cont+=1;
            if (idMedico == id)
                return cont-1;
        }
        return -1;
    }
    
    public void abrir(String tipo)
    {
        this.archMedicos.Abrir(tipo);
    }

    public void cerrar()
    {
        this.archMedicos.Cerrar();
    }

    public void mostrar(){
        for (Medico medico : this.medicos){
            if (medico.Eliminado() == false) {
                System.out.println(medico);
            }
        }            
    }

    public void crear() {
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        ConsultasAreas consultasA = new ConsultasAreas();
        Medico medico = new Medico();
        System.out.print("Nombre: ");
        medico.putNombre(sc.nextLine());
        System.out.print("Direccion: ");
        medico.putDireccion(sc.nextLine());
        System.out.print("RFC: ");
        medico.putRFC(sc.nextLine());
        System.out.print("Edad: ");
        medico.putEdad(sc.nextInt());
        consultasA.mostrar();
        System.out.print("Id Area: ");
        medico.putIdArea(sc.nextInt());       
        archMedicos.Grabar(medico, this.total());
        this.cerrar();
        this.actualizarArray();
    }

    public void modificar(int idMedico){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        ConsultasAreas consultasA = new ConsultasAreas();
        if (!this.existe(idMedico)) {
            System.out.println("Medico no existe");
            System.out.println("");
            this.cerrar();
        }else{
            Medico medico = this.getMedico(idMedico);
            int posicion = this.posicion(idMedico);
            System.out.print("Nombre("+medico.getNombre()+"): ");
            medico.putNombre(sc.next());
            System.out.print("Direccion("+medico.getDireccion()+"): ");
            medico.putDireccion(sc.next());
            System.out.print("RFC("+medico.getRFC()+"): ");
            medico.putRFC(sc.next());
            System.out.print("Edad("+medico.getEdad()+"): ");
            medico.putEdad(sc.nextInt());
            consultasA.mostrar();
            System.out.print("Id Area("+medico.getIdArea()+"): ");
            medico.putIdArea(sc.nextInt());   
            this.archMedicos.Grabar(medico, posicion);
            this.cerrar();
            this. actualizarArray();
            System.out.println("");

        }
    }

    public void eliminar(int idMedico){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        if (!this.existe(idMedico)) {
            System.out.println("Medico no existe");
            System.out.println("");
        }else{
            Medico medico= this.getMedico(idMedico);
            int posicion = this.posicion(idMedico);
            System.out.println("Seguro que quieres eliminar a("+medico.getNombre()+")");
            System.out.print("Si(s) / No(n): ");
            if (sc.next().charAt(0) == 's') {
                medico.Eliminar(true);;
                archMedicos.Grabar(medico, posicion);
                System.out.println(medico.getNombre()+" ha sido eliminado");

            }else{
                System.out.println(medico.getNombre()+" no ha sido eliminado");
            }
            this.cerrar();
            this.actualizarArray();
            
        }
    }

    public void consultas(int idMedico) {
        Scanner sc = new Scanner(System.in);
        int opcConsulta = 0;
        if (!this.existe(idMedico)) {
            System.out.println("Medico no existe");
        }else{
            Medico medico = this.getMedico(idMedico);

            do {
                System.out.println("");
                System.out.println("        C O N S U L T A S ("+medico.getNombre()+")");
                System.out.println("        1) Informe ");
                System.out.println("        2) Medicos en Area ");
                System.out.println("        3) Pacientes");
                System.out.println("        4) Salir ");
                System.out.print("        Opcion: ");
                opcConsulta = sc.nextInt();
                System.out.println("");
                switch (opcConsulta) {
                    case 1:// Informe 
                        this.informe(idMedico);                                           
                        break;
                    case 2:// Medicos
                        this.medicos(idMedico);
                        break;    
                    case 3:// Pacientes 
                        this.pacientes(idMedico);
                        break;
                    case 4:// Salir                                            
                        break;
                    default:
                        System.out.println("   Opcion Incorrecta\n"); break;
                }
            } while (opcConsulta != 4); 
        }        
    }

    private void informe(int idMedico){
        Medico medico = this.getMedico(idMedico);
        Area area = new ConsultasAreas().getArea(medico.getIdArea());
        System.out.println("");
        System.out.println("        INFORME");
        System.out.println("        Id Medico: "+medico.getIdMedico());
        System.out.println("        Nombre: "+medico.getNombre());
        System.out.println("        Edad: "+medico.getEdad());
        System.out.println("        RFC: "+medico.getRFC());
        System.out.println("        Direccion: "+medico.getDireccion());
        System.out.println("        AREA:");
        System.out.println("            Id Area: "+medico.getIdArea());
        System.out.println("            Nombre: "+area.getNombreArea());
        System.out.println("            Descripcion: "+area.getDescA());
        System.out.println("            Piso: "+area.getPiso());
        System.out.println("            Direccion: "+area.getDireccionA());
        
        System.out.println("");
    }

    private void medicos(int idMedico){
        ArrayList<Medico> arrayMedicos = new ConsultasMedicos().getMedicos();
        int idArea = this.getMedico(idMedico).getIdArea();
        System.out.println("        MEDICOS EN AREA"); 
        for (Medico medico : arrayMedicos) {
            if (medico.getIdArea() == idArea) {
                System.out.println("        "+medico);
            }
        }
    }

    private void pacientes(int idMedico){
        ArrayList<Paciente> arrayPaciente = new ConsultasPacientes().getPacientes();
        System.out.println("        PACIENTES DEL MEDICO"); 
        for (Paciente paciente : arrayPaciente) {
            if (paciente.getIdMedico() == idMedico) {
                if (paciente.getAlta() == false)                 
                    System.out.println("        "+paciente+" - "+paciente.getPadecimiento());
                else
                    System.out.println("        X"+paciente+" - "+paciente.getPadecimiento()+"X");
            }
        }
    }
}