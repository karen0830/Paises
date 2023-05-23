import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Procesos {
	ArrayList<String> array = new ArrayList<String>();
	HashMap<Integer, ArrayList<String>> paisesCiudades = new HashMap<Integer, ArrayList<String>>();
	public void menu(){
		int opcion = 0;
		String menu = "1. Registrar País\n";
		menu += "2. Registrar Ciudades\n";
		menu += "3. Consultar lista de un País\n";
		menu += "4. Consultar ciudad\n";
		menu += "5. Salir";
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			opcion(opcion);
		}while(opcion != 5);
	}
	
	public void opcion(int opcion) {
		switch (opcion) {
		case 1:
			registrarPais();
			System.out.println(paisesCiudades);
			break;
		case 2: 
			registrarCiudades();
			break;
		case 3: 
			consultarListaPais();
			break;
		case 4: 
			consultarCiudad();
			break;
		case 5:
			System.out.println("ADIOS");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Ingrese opción correcta", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void registrarPais() {
		int i = paisesCiudades.size() + 1, opcion = 0;
		String pais = "";
		int numeroPais = 0;
		do {
			array = new ArrayList<String>();
			numeroPais = Integer.parseInt(JOptionPane.showInputDialog("Ingrese numero que identifique al país " + i));
			pais = JOptionPane.showInputDialog("Ingrese nombre del país " + i);
			array.add(numeroPais + "");
			array.add(pais);
			paisesCiudades.put(numeroPais, array);
			i++;
			opcion = validar("Ingrese: \n1. Continuar con otro País\n2. Salir");
		}while(opcion == 1);
	}
	
	public void registrarCiudades() {
		int llave = Integer.parseInt(JOptionPane.showInputDialog(menuPais()));
		int opcion = 0;
		String ciudad = "";
		if(paisesCiudades.containsKey(llave)) {
			do {
				ciudad = JOptionPane.showInputDialog("Ingrese ciudad de el país " + paisesCiudades.get(llave).get(1));
				paisesCiudades.get(llave).add(ciudad);
				opcion = validar("Ingrese: \n1. Ingresar otra ciudad\n2. Salir");
			}while(opcion == 1);
		}else JOptionPane.showMessageDialog(null,"El pais con la llave: "+ llave + " No se encuentra");
	}
	
	public String menuPais() {
		Iterator<Integer> itera = paisesCiudades.keySet().iterator();
		String menu = "Ingrese opción correcta para registrar ciudad\n";
		for(int i = 1; i <= paisesCiudades.size(); i++) {
			int llave = itera.next();
			menu += paisesCiudades.get(llave).get(0)+ ". "+ paisesCiudades.get(llave).get(1) + "\n";
		}
		return menu;
	}

	
	public int validar(String msj) {
		int x  = 0;
		do {
			x = Integer.parseInt(JOptionPane.showInputDialog(msj));
			if(x < 1 || x > 2) {
				JOptionPane.showMessageDialog(null, "Ingrese opción correcta", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}while(x < 1 || x > 2);
		return x; 
	}
	
	private void consultarListaPais() {
		Iterator<Integer> itera = paisesCiudades.keySet().iterator();
		int llaves = Integer.parseInt(JOptionPane.showInputDialog("Ingrese codigo de pais para consultar lista de ciudades\n"));
		String paisyCiudad = "";
		if(paisesCiudades.containsKey(llaves)) {
			while(itera.hasNext()) {
				Integer llave = itera.next();
				if(llave == llaves) {
					if(paisesCiudades.get(llave).size() <= 2) {
						System.out.println("No contiene ciudades");
					}else {
						paisyCiudad += llave +": " +  paisesCiudades.get(llave).get(1) +  "\n CIUDADES:";
						for(int i = 2; i < paisesCiudades.get(llaves).size(); i++) {
							paisyCiudad += "\n" + paisesCiudades.get(llave).get(i);
						}
					}
				}
			}
		}else System.out.println(llaves + " No está registrado");
		System.out.println(paisyCiudad);
	}
	

	private void consultarCiudad() {
		String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad a buscar");
		Iterator<Integer> itera = paisesCiudades.keySet().iterator();
		while(itera.hasNext()) {
			Integer llave = itera.next();
			for(int i = 2; i < paisesCiudades.get(llave).size(); i++) {
				if(paisesCiudades.get(llave).get(i).equalsIgnoreCase(ciudad)) {
					System.out.println("Ciudad: " + paisesCiudades.get(llave).get(i) + " pertenece a el país: " 
								+ paisesCiudades.get(llave).get(1));
				}
			}
		}
	}
	
}
