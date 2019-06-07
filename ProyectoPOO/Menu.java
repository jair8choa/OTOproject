import java.util.*;

public class Menu {
    public static void main(String[] args)
    {
    
        Scanner sc=new Scanner(System.in);
        int opc = 0;
        String opciones[]={"M E N U","1) Areas","2) Medicos","3) Pacientes","4) Salir"};  
	 	do {
            for(int i=0;i<opciones.length;i++)
                System.out.println(opciones[i]);
            System.out.print("Opcion: ");
            opc=sc.nextInt();
            System.out.println("");
            switch(opc){

                case 1: // A R E A S
                    ConsultasAreas consultasA = new ConsultasAreas();
                    int opcArea = 0;
                    do{
                        System.out.println("");
                        System.out.println("    A R E A S ");
                        System.out.println("    1) Mostrar Area ");
                        System.out.println("    2) Consultas ");
                        System.out.println("    3) Crear Area ");
                        System.out.println("    4) Modificar Area ");
                        System.out.println("    5) Eliminar Area ");
                        System.out.println("    6) Salir ");
                        System.out.print("    Opcion: ");
                        opcArea = sc.nextInt();
                        System.out.println("");
                        switch(opcArea){
                            case 1: //Mostrar
                                consultasA.mostrar();
                                break;
                            case 2: //Consultas
                                consultasA.mostrar();
                                System.out.print("Id del Area: ");
                                consultasA.consultas(sc.nextInt());             
                                break;
                            case 3: //Crear
                                consultasA.crear();
                                break;
                            case 4: //Modificar
                                consultasA.mostrar();
                                System.out.print("Id del Area: ");
                                consultasA.modificar(sc.nextInt());
                                break;
                            case 5: //Eliminar
                                consultasA.mostrar();
                                System.out.print("Id del Area: ");
                                consultasA.eliminar(sc.nextInt());
                                break;
                            case 6: //Salir
                                break;
                            default: //Default
                                System.out.println("   Opcion Incorrecta\n"); break;
                        }
                    }while(opcArea != 6);
                break;

                case 2: // M E D I C O S
                ConsultasMedicos consultasM = new ConsultasMedicos();
                int opcMed=0;
                do{
                    System.out.println("");
                    System.out.println("    M E D I C O S ");
                    System.out.println("    1) Mostrar Medico ");
                    System.out.println("    2) Consultas ");
                    System.out.println("    3) Crear Medico ");
                    System.out.println("    4) Modificar Medico ");
                    System.out.println("    5) Eliminar Medico ");
                    System.out.println("    6) Salir ");
                    System.out.print("    Opcion: ");
                    opcMed=sc.nextInt();
                    System.out.println("");
                    switch(opcMed){
                        case 1: //Mostrar
                            consultasM.mostrar();
                            break;
                        case 2: //Consultas
                            consultasM.mostrar();
                            System.out.print("Id del Medico: ");
                            consultasM.consultas(sc.nextInt());     
                            break;
                        case 3: //Crear
                            consultasM.crear();
                            break;
                        case 4: //Modificar
                            consultasM.mostrar();
                            System.out.print("Id del Medico: ");
                            consultasM.modificar(sc.nextInt());
                            break;
                        case 5: //Eliminar
                            consultasM.mostrar();
                            System.out.print("Id del Medico: ");
                            consultasM.eliminar(sc.nextInt());
                            break;
                        case 6: //Salir
                            break;
                        default: //Default
                            System.out.println("   Opcion Incorrecta\n"); break;
                }}while(opcMed != 6);
                break;

                case 3: // P A C I E N T E S
                ConsultasPacientes consultasP = new ConsultasPacientes();
                int opcPaci=0;
                do{
                    System.out.println("    P A C I E N T E S ");
                    System.out.println("    1) Mostrar Paciente ");
                    System.out.println("    2) Consultas ");
                    System.out.println("    3) Crear Paciente ");
                    System.out.println("    4) Modificar Paciente ");
                    System.out.println("    5) Eliminar Paciente ");
                    System.out.println("    6) Salir ");
                    System.out.print("    Selecciona una opcion: ");
                    opcPaci=sc.nextInt();
                    System.out.println("");
                    switch(opcPaci){
                    case 1: //Mostrar
                        consultasP.mostrar();
                        break;
                    case 2: //Consultas
                        consultasP.mostrar();
                        System.out.print("Id del Paciente: ");
                        consultasP.consultas(sc.nextInt());  
                        break;
                    case 3: //Crear
                        consultasP.crear();
                        break;
                    case 4: //Modificar
                        consultasP.mostrar();
                        System.out.print("Id del Paciente: ");
                        consultasP.modificar(sc.nextInt());
                        break;
                    case 5: //Eliminar
                        consultasP.mostrar();
                        System.out.print("Id del Paciente: ");
                        consultasP.eliminar(sc.nextInt());
                        break;
                    case 6: //Salir
                        break;
                    default: //Default
                        System.out.println("   Opcion Incorrecta\n"); break;
                    }}while(opcPaci != 6);
                break;

                case 4: // S A L I R
                    System.out.println("Adios!\n");break;
                
                default: // D E F A U L T
                    System.out.println("Opcion incorrecta\n"); break;
            }
        } while (opc!=4);         
	}
}