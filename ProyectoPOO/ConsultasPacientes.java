import java.util.*;
public class ConsultasPacientes {  
    
    operArchPaciente archPacientes;
    ArrayList<Paciente> pacientes;
    
    public ConsultasPacientes(){
        this.archPacientes = new operArchPaciente("pacientes.txt");
        this.actualizarArray();
    }

    public void actualizarArray(){
        this.archPacientes.Abrir("rw");
        int numPacientes = this.archPacientes.numReg();
        this.pacientes = new ArrayList<Paciente>();
        for(int i = 0; i < numPacientes; i++)
            this.pacientes.add(this.archPacientes.Leer(i));
        this.archPacientes.Cerrar();
    }

    public Paciente getPaciente(int idPaciente){
        int id;
        for (Paciente paciente : this.pacientes) {
            id = paciente.getIdPaciente();
            if (id == idPaciente)
                return paciente;
        }
        return null;
    }

    public ArrayList<Paciente> getPacientes(){
        return this.pacientes;
    }

    public int total() {
        return pacientes.size();
    }

    public boolean existe(int idPaciente){
        int id;
        for (Paciente paciente : this.pacientes){
            id = paciente.getIdPaciente();
            if (id == idPaciente)
                return true;
        }
        return false;
    }

    public int posicion(int idPaciente){
        int cont = 0;
        int id;
        for (Paciente paciente : this.pacientes) {
            id = paciente.getIdPaciente();
            cont+=1;
            if (idPaciente == id)
                return cont-1;
        }
        return -1;
    }
    
    public void abrir(String tipo)
    {
        this.archPacientes.Abrir(tipo);
    }

    public void cerrar()
    {
        this.archPacientes.Cerrar();
    }

    public void mostrar(){
        for (Paciente paciente : this.pacientes){
            if (paciente.Eliminado() == false) {
                System.out.println(paciente);
            }
        }            
    }

    public void crear() {
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        ConsultasMedicos consultasM = new ConsultasMedicos();
        Paciente paciente = new Paciente();
        System.out.print("Nombre: ");
        paciente.putNombre(sc.nextLine());
        System.out.print("Tipo de Sangre: ");
        paciente.putTipoSangre(sc.nextLine());
        System.out.print("Padecimiento: ");
        paciente.putPadecimiento(sc.nextLine());
        System.out.print("Edad: ");
        paciente.putEdad(sc.nextInt());
        System.out.print("Sexo: ");
        paciente.putSexo(sc.next().charAt(0));
        System.out.print("RFC: ");
        paciente.putRFC(sc.next());
        consultasM.mostrar();
        System.out.print("Id Medico: ");
        paciente.putIdMedico(sc.nextInt());
           
        archPacientes.Grabar(paciente, this.total());
        this.cerrar();
        this.actualizarArray();
    }

    public void modificar(int idPaciente){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        ConsultasMedicos consultasM = new ConsultasMedicos();
        if (!this.existe(idPaciente)) {
            System.out.println("Paciente no existe");
            System.out.println("");
            this.cerrar();
        }else{
            Paciente paciente = this.getPaciente(idPaciente);
            int posicion = this.posicion(idPaciente);
            System.out.print("Nombre("+paciente.getNombre()+"): ");
            paciente.putNombre(sc.nextLine());
            System.out.print("Tipo de Sangre("+paciente.getTipoSangre()+"): ");
            paciente.putTipoSangre(sc.nextLine());
            System.out.print("Padecimiento("+paciente.getPadecimiento()+"): ");
            paciente.putPadecimiento(sc.nextLine());
            System.out.print("Edad("+paciente.getEdad()+"): ");
            paciente.putEdad(sc.nextInt());
            System.out.print("Sexo("+paciente.getSexo()+"): ");
            paciente.putSexo(sc.next().charAt(0));
            System.out.print("RFC("+paciente.getRFC()+"): ");
            paciente.putRFC(sc.next());
            consultasM.mostrar();
            System.out.print("Id Medico("+paciente.getIdMedico()+"): ");
            paciente.putIdMedico(sc.nextInt());
            this.archPacientes.Grabar(paciente, posicion);
            this.cerrar();
            this. actualizarArray();
            System.out.println("");

        }
    }

    public void eliminar(int idPaciente){
        this.abrir("rw");
        Scanner sc = new Scanner(System.in);
        if (!this.existe(idPaciente)) {
            System.out.println("Paciente no existe");
            System.out.println("");
        }else{
            Paciente paciente= this.getPaciente(idPaciente);
            int posicion = this.posicion(idPaciente);
            System.out.println("Seguro que quieres eliminar a("+paciente.getNombre()+")");
            System.out.print("Si(s) / No(n): ");
            if (sc.next().charAt(0) == 's') {
                paciente.Eliminar(true);;
                archPacientes.Grabar(paciente, posicion);
                System.out.println(paciente.getNombre()+" ha sido eliminado");
            }else{
                System.out.println(paciente.getNombre()+" no ha sido eliminado");
            }
            this.cerrar();
            this.actualizarArray();
            
        }
    }

    public void consultas(int idPaciente) {
        Scanner sc = new Scanner(System.in);
        int opcConsulta = 0;
        if (!this.existe(idPaciente)) {
            System.out.println("Paciente no existe");
        }else{
            Paciente paciente = new ConsultasPacientes().getPaciente(idPaciente);
            opcConsulta = 0;
            do {
                System.out.println("");
                System.out.println("        C O N S U L T A S ("+paciente.getNombre()+")");
                System.out.println("        1) Informe ");
                System.out.println("        2) Dar de alta");
                System.out.println("        3) Companeros de Area ");
                System.out.println("        4) Salir ");
                System.out.print("        Opcion: ");
                opcConsulta = sc.nextInt();
                System.out.println("");
                switch (opcConsulta) {
                    case 1:// Informe 
                        this.informe(idPaciente);                                           
                        break;
                    case 2:// Dar de Alta 
                        this.alta(idPaciente);                                          
                        break;    
                    case 3:// Compañeros 
                        this.companeros(idPaciente);                                        
                        break;
                    case 4:// Salir                                            
                        break;
                    default:
                        System.out.println("   Opcion Incorrecta\n"); break;
                }
            } while (opcConsulta != 4); 
    
        }        
    }

    private void informe(int idPaciente){
        Paciente paciente= this.getPaciente(idPaciente);
        Medico medico = new ConsultasMedicos().getMedico(paciente.getIdMedico());
        System.out.println("");
        System.out.println("        INFORME");
        System.out.println("        Id Paciente: "+paciente.getIdPaciente());
        System.out.println("        Nombre: "+paciente.getNombre());
        System.out.println("        Edad: "+paciente.getEdad());
        System.out.println("        Sexo: "+paciente.getSexo());
        System.out.println("        Padecimiento: "+paciente.getPadecimiento());
        System.out.println("        Tipo de Sangre: "+paciente.getTipoSangre());
        System.out.println("        Dado de alta: "+paciente.getAlta());
        System.out.println("        RFC: "+paciente.getRFC());
        System.out.println("        MEDICO");
        System.out.println("            Id Medico: "+medico.getIdMedico());
        System.out.println("            Id Area: "+medico.getIdArea());
        System.out.println("            Nombre: "+medico.getNombre());
        System.out.println("            Edad: "+medico.getEdad());
        System.out.println("");
    }

    private void alta(int idPaciente){
        Scanner sc = new Scanner(System.in);
        this.abrir("rw");
        int posicion =  this.posicion(idPaciente);
        Paciente paciente= this.getPaciente(idPaciente);
        System.out.println("Seguro que quieres dar de alta a("+paciente.getNombre()+")");
            System.out.print("Si(s) / No(n): ");
            if (sc.next().charAt(0) == 's') {
                paciente.putAlta(false);
                archPacientes.Grabar(paciente, posicion);
                System.out.println(paciente.getNombre()+" ha sido dado de alta");
            }else{
                System.out.println(paciente.getNombre()+" no ha sido dado de alta");
            }  
        this.crear();
        this.actualizarArray();    
    }

    private void companeros(int idPaciente){
        ArrayList<Paciente> arrayPaciente = new ConsultasPacientes().getPacientes();
        int idArea = this.getPaciente(idPaciente).getIdArea();
        System.out.println("        COMPANEROS DE AREA"); 
        for (Paciente paciente : arrayPaciente) {
            if (paciente.getIdArea() == idArea) {
                if (paciente.getAlta() == false)                 
                    System.out.println("        "+paciente+" - "+paciente.getPadecimiento());
            }
        }
    }
}