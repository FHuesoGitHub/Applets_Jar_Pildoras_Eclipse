package applets;

/*
 * Se procede a empaquetar este applet en un arcivo .jar para su m�s c�moda distribuci�n, ya que de esta
 * manera absolutamente todos los ficheros generados al compilar el applet, quedar�n dentro de un solo .jar
 * lo que facilitar� su distribuci�n. Este .jar por si s�lo no ser� ejecutable, si no que necesitar� su
 * llamada desde la p�gina web que ejecutaba el applet sustituyendo el la etiqueta "applet" el contenido
 * par�metro "code" por el nombre de la clase principal .class, tomando como directorio raiza en su ruta relativa
 * el nivel 0 del archivo comprimido .jar. Tambi�n se a�ade en el par�metro "archive" el nombre del fichero .jar donde 
 * est� esta clase, tomando como directorio principal en su ruta relativa, el mismo donde est� el archivo .html.
 * 
 * Los archivos generados los he guardado en la carpeta "dist" dentro del proyecto.
 * 
 *  Para empaquetar el applet es recomendable crear un proyecto s�lo para esta aplicaci�n. Una vez hecho
 *  desde Eclipse ser�a:
 *     - Bot�n derecho sobre proyecto
 *     - Export
 *     - Jar file
 *     - Selecciono proyecto a empaquetar
 *     - Elijo destino donde quiero el .jar.
 */

/*Esta aplicaci�n de calculadora hecha durante el bloque de swing,
 *la voy a transformar para poderla ejecutar como un applet desde 
 *una p�gina web.*/

/*
Ejemplo de funcionamiento de los distintos layouts o dise�os.
Desarroyo de una calculadora utilizando GridLayout.
La calculadora reponde a multiples fuentes. Botones y teclado.
*/

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class CalculadoraMultiplesFuentes extends JApplet {

	/*
	 * Un applet no tiene m�todo main, si no m�todo init. Adem�s no se puede
	 * utilizar el m�todo setVisible, ya que estar� siemprre visible. Tampoco se
	 * puede usar el m�todo SetDefaultCloseOperation ya que no se puede cerrar. La
	 * instaancia a la clase que hereda de JFrame tambi�n sobra porque no se puede
	 * abrir una ventana dentro de un applet l�gicamente, ya que es el applet el que
	 * se ejecuta dentro de una ventana.
	 */

	/*
	 * public static void main(String[] args) {
	 * 
	 * VentanaCalculadoraVariasFuentes ventana_calculadora = new
	 * VentanaCalculadoraVariasFuentes();
	 * 
	 * ventana_calculadora.setVisible(true);
	 * ventana_calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * }
	 */

	public void init() {

		// Contenido traido desde el constructor de la clase que hereda del JFrame para
		// adaptar la app al applet.
		// setTitle("Calculadora"); El applet no toene t�tulo. Este es que se d� en
		// .html
		// setBounds(500, 300, 450, 300); El applet o tiene tama�o ya que este se le
		// dar� en el .html

		PanelCalculadoraVariasFuentes pantalla_calculadora = new PanelCalculadoraVariasFuentes();

		add(pantalla_calculadora);

	}

}

/*
 * Como ya he comentado la clase que hereda de JFrame sobra porque no necesito
 * generar un ventana. El contenido de su constructor lo paso al m�todo init,
 * con alguna excepci�n que comentar� all�.
 */

//class VentanaCalculadoraVariasFuentes extends JFrame {

// public VentanaCalculadoraVariasFuentes() {

/*
 * setTitle("Calculadora"); setBounds(500, 300, 450, 300);
 * 
 * PanelCalculadoraVariasFuentes pantalla_calculadora = new
 * PanelCalculadoraVariasFuentes();
 * 
 * add(pantalla_calculadora);
 */
// pack(); Este m�todo le da al contenedor (ventana en este caso) el tama�o
// exacto de los compnentes que tiene dentro.

// }

// Clase interna del JFrame.
class PanelCalculadoraVariasFuentes extends JPanel {

	public PanelCalculadoraVariasFuentes() {

		setLayout(new BorderLayout());

		pantalla = new JButton(" ");
		botonera = new JPanel();
		ultima_operacion = " ";
		resultado = 0.0;
		valor = 0.0;
		operacion = false;

		pantalla.setEnabled(false);
		add(pantalla, BorderLayout.NORTH);

		botonera.setLayout(new GridLayout(4, 4));

		// Creo objetos Action (interface implementada por la clase AbstracAction de la
		// cual hereda mi clase AccionBotonesNumericos) con
		// las caracter�sticas que tendr�n los botones. Al pasar este objeto por
		// constructor a los JButton les doy caracter�sticas
		// y les agrego acci�n a la vez.
		AccionBotonesNumericos accion_siete = new AccionBotonesNumericos("7");
		AccionBotonesNumericos accion_ocho = new AccionBotonesNumericos("8");
		AccionBotonesNumericos accion_nueve = new AccionBotonesNumericos("9");
		AccionBotonesFunciones accion_dividir = new AccionBotonesFunciones("/");
		AccionBotonesNumericos accion_cuatro = new AccionBotonesNumericos("4");
		AccionBotonesNumericos accion_cinco = new AccionBotonesNumericos("5");
		AccionBotonesNumericos accion_seis = new AccionBotonesNumericos("6");
		AccionBotonesFunciones accion_multiplicar = new AccionBotonesFunciones("*");
		AccionBotonesNumericos accion_uno = new AccionBotonesNumericos("1");
		AccionBotonesNumericos accion_dos = new AccionBotonesNumericos("2");
		AccionBotonesNumericos accion_tres = new AccionBotonesNumericos("3");
		AccionBotonesFunciones accion_restar = new AccionBotonesFunciones("-");
		AccionBotonesNumericos accion_cero = new AccionBotonesNumericos("0");
		AccionBotonesNumericos accion_decimal = new AccionBotonesNumericos(".");
		AccionBotonesFunciones accion_sumar = new AccionBotonesFunciones("+");
		AccionBotonesFunciones accion_resultado = new AccionBotonesFunciones("=");
		AccionBotonBorrar accion_borrar = new AccionBotonBorrar("C");

		botonera.add(new JButton(accion_siete));
		botonera.add(new JButton(accion_ocho));
		botonera.add(new JButton(accion_nueve));
		botonera.add(new JButton(accion_dividir));
		botonera.add(new JButton(accion_cuatro));
		botonera.add(new JButton(accion_cinco));
		botonera.add(new JButton(accion_seis));
		botonera.add(new JButton(accion_multiplicar));
		botonera.add(new JButton(accion_uno));
		botonera.add(new JButton(accion_dos));
		botonera.add(new JButton(accion_tres));
		botonera.add(new JButton(accion_restar));
		botonera.add(new JButton(accion_cero));
		botonera.add(new JButton(accion_decimal));
		botonera.add(new JButton(accion_sumar));
		botonera.add(new JButton(accion_resultado));

		borrar = new JButton(accion_borrar);

		add(botonera, BorderLayout.CENTER);

		add(borrar, BorderLayout.SOUTH);

		// Para asociar la acci�n a las pulsaciones de las teclas, el primer paso es
		// crear un mapa de entrada, es decir,
		// informar de sobre que objeto actuar� el evento al pulsar la combinaci�n de
		// teclas, ya que por defecto actuar� el objeto que tiene el foco.
		// La clase InputMap asocia una acci�n a un evento. El m�todo getInputMap de
		// la clase JComponent devuelve el InputMap que fu� usado
		// durante la condici�n pasada por par�metros. Estas condiciones existen ya
		// como constantes.
		InputMap mapaEntrada = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW); // Cuando est� dentro de la ventana
																				// con foco.

		// Ahora creo las combinaciones de teclas.
		// La clase KeyStroke representa una acci�n con una tecla o teclas de mi
		// teclado. Tiene sobrecarga de m�todos. En este le paso un String
		// con un modificador, el c�digo de la tecla o el car�cter unicode. Consultar
		// mas opciones en la API.
		// Ahora en mi objeto KeyStroke tengo registradada la tecla.
		KeyStroke boton_siete = KeyStroke.getKeyStroke("7");
		KeyStroke boton_siete_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0);
		KeyStroke boton_ocho = KeyStroke.getKeyStroke("8");
		KeyStroke boton_ocho_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0);
		KeyStroke boton_nueve = KeyStroke.getKeyStroke("9");
		KeyStroke boton_nueve_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0);
		KeyStroke boton_dividir = KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0);
		KeyStroke boton_cuatro = KeyStroke.getKeyStroke("4");
		KeyStroke boton_cuatro_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0);
		KeyStroke boton_cinco = KeyStroke.getKeyStroke("5");
		KeyStroke boton_cinco_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0);
		KeyStroke boton_seis = KeyStroke.getKeyStroke("6");
		KeyStroke boton_seis_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0);
		KeyStroke boton_multiplicar = KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0);
		KeyStroke boton_uno = KeyStroke.getKeyStroke("1");
		KeyStroke boton_uno_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0);
		KeyStroke boton_dos = KeyStroke.getKeyStroke("2");
		KeyStroke boton_dos_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0);
		KeyStroke boton_tres = KeyStroke.getKeyStroke("3");
		KeyStroke boton_tres_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0);
		KeyStroke boton_restar = KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0);
		KeyStroke boton_cero = KeyStroke.getKeyStroke("0");
		KeyStroke boton_cero_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0);
		KeyStroke boton_decimal_block_num = KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0);
		KeyStroke boton_sumar = KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0);
		KeyStroke boton_resultado = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		KeyStroke boton_borrar = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);

		// Ahora asigno la combinaci�n de teclas a un objeto.
		// Con el m�todo put de la clase InputMap, asigno a mi objeto ya creado de
		// InputMap la combinaci�n de teclas.
		mapaEntrada.put(boton_siete, "boton_siete");
		mapaEntrada.put(boton_siete_block_num, "boton_siete");
		mapaEntrada.put(boton_ocho, "boton_ocho");
		mapaEntrada.put(boton_ocho_block_num, "boton_ocho");
		mapaEntrada.put(boton_nueve, "boton_nueve");
		mapaEntrada.put(boton_nueve_block_num, "boton_nueve");
		mapaEntrada.put(boton_dividir, "boton_dividir");
		mapaEntrada.put(boton_cuatro, "boton_cuatro");
		mapaEntrada.put(boton_cuatro_block_num, "boton_cuatro");
		mapaEntrada.put(boton_cinco, "boton_cinco");
		mapaEntrada.put(boton_cinco_block_num, "boton_cinco");
		mapaEntrada.put(boton_seis, "boton_seis");
		mapaEntrada.put(boton_seis_block_num, "boton_seis");
		mapaEntrada.put(boton_multiplicar, "boton_multiplicar");
		mapaEntrada.put(boton_uno, "boton_uno");
		mapaEntrada.put(boton_uno_block_num, "boton_uno");
		mapaEntrada.put(boton_dos, "boton_dos");
		mapaEntrada.put(boton_dos_block_num, "boton_dos");
		mapaEntrada.put(boton_tres, "boton_tres");
		mapaEntrada.put(boton_tres_block_num, "boton_tres");
		mapaEntrada.put(boton_restar, "boton_restar");
		mapaEntrada.put(boton_cero, "boton_cero");
		mapaEntrada.put(boton_cero_block_num, "boton_cero");
		mapaEntrada.put(boton_decimal_block_num, "boton_decimal");
		mapaEntrada.put(boton_sumar, "boton_sumar");
		mapaEntrada.put(boton_resultado, "boton_resultado");
		mapaEntrada.put(boton_borrar, "boton_borrar");

		// Ahora asigno el objeto a la acci�n.
		// La clase ActionMap crea una mapa de objetos asociados a acciones.
		// El m�todo getActionMap de la clase JComponent devuelve un ActionMap
		// utilizado para determinar que acci�n se asocia a una
		// combinaci�n de teclas.
		ActionMap mapaAccion = getActionMap();

		// El m�todo put de la clase ActionMap asocia el objeto de acci�n a la
		// combinaci�n de teclas.
		mapaAccion.put("boton_siete", accion_siete);
		mapaAccion.put("boton_ocho", accion_ocho);
		mapaAccion.put("boton_nueve", accion_nueve);
		mapaAccion.put("boton_dividir", accion_dividir);
		mapaAccion.put("boton_cuatro", accion_cuatro);
		mapaAccion.put("boton_cinco", accion_cinco);
		mapaAccion.put("boton_seis", accion_seis);
		mapaAccion.put("boton_multiplicar", accion_multiplicar);
		mapaAccion.put("boton_uno", accion_uno);
		mapaAccion.put("boton_dos", accion_dos);
		mapaAccion.put("boton_tres", accion_tres);
		mapaAccion.put("boton_restar", accion_restar);
		mapaAccion.put("boton_cero", accion_cero);
		mapaAccion.put("boton_decimal", accion_decimal);
		mapaAccion.put("boton_sumar", accion_sumar);
		mapaAccion.put("boton_resultado", accion_resultado);
		mapaAccion.put("boton_borrar", accion_borrar);

	}

	// Clase interna del JPanel. Define la acci�n de los botones num�ricos.
	// Clase oyente, debe implementar la interface Action y sobreescribir todos sus
	// m�todos
	// o heredar de la clase AbstractAction y sobreescribir los que necesite
	// utilizar, adem�s del
	// m�todo accionPerformed el cual esta clase no lo implementa da la interface
	// Action ya que
	// esta no lo declara si no que lo hereda, por esto no se puede considerar una
	// clase adaptadora.
	class AccionBotonesNumericos extends AbstractAction {

		// El constructor recibir� como par�metros las caracter�sticas que tendr�n
		// los JButton
		public AccionBotonesNumericos(String nombre_boton) {

			// El m�todo putValue asigna un valor a una clave. por ejemplo, a la constante
			// NAME le asigona el
			// contenido del string nombre.
			putValue(Action.NAME, nombre_boton);

		}

		@Override // Este m�todo se ejecuta cuando se desencadena el evento de acci�n.
		public void actionPerformed(ActionEvent e) {

			String texto_boton = e.getActionCommand();

			try {

				if (!operacion) {

					pantalla.setText(pantalla.getText() + texto_boton);
					valor = Double.parseDouble(pantalla.getText());

				} else {

					pantalla.setText(texto_boton);
					valor = Double.parseDouble(pantalla.getText());
					operacion = false;

				}

			} catch (NumberFormatException ne) {

				if (valor != 0) {

					pantalla.setText(Double.toString(valor));

				} else {

					pantalla.setText(" ");

				}

			}

		}

	}

	// Clase interna del JPanel. Define la acci�n de los botones num�ricos.
	// Clase oyente, debe implementar la interface Action y sobreescribir todos sus
	// m�todos
	// o heredar de la clase AbstractAction y sobreescribir los que necesite
	// utilizar, adem�s del
	// m�todo accionPerformed el cual esta clase no lo implementa da la interface
	// Action ya que
	// esta no lo declara si no que lo hereda, por esto no se puede considerar una
	// clase adaptadora.
	class AccionBotonesFunciones extends AbstractAction {

		// El constructor recibirá como parámetros las caracter�sticas que tendr�n
		// los JButton
		public AccionBotonesFunciones(String nombre_boton) {

			// El m�todo putValue asigna un valor a una clave. por ejemplo, a la constante
			// NAME le asigona el
			// contenido del string nombre.
			putValue(Action.NAME, nombre_boton);

		}

		@Override // Este m�todo se ejecuta cuando se desencadena el evento de acci�n.
		public void actionPerformed(ActionEvent e) {

			operacion = true;

			String operacion = e.getActionCommand();

			if (operacion.equals("\n")) { // Se ha pulsado enter.

				operacion = "=";

			}

			calcular(valor);
			ultima_operacion = operacion;

		}

		private void calcular(double x) {

			pantalla.setText(" ");

			if (ultima_operacion.equals(" ")) {

				resultado = x;

			} else {

				if (ultima_operacion.equals("+")) {

					resultado += x;
					pantalla.setText(Double.toString(resultado));

				} else if (ultima_operacion.equals("-")) {

					resultado -= x;

				} else if (ultima_operacion.equals("*")) {

					resultado *= x;

				} else if (ultima_operacion.equals("/")) {

					resultado /= x;

				} else if (ultima_operacion.equals("=")) {

					resultado = x;

				}

				int resultado_entero = (int) resultado;
				// Double resultado_aux = (double) resultado_entero;

				if (resultado == resultado_entero) {

					pantalla.setText(String.format("%.0f", resultado)); // Imprime sin deciamles. Para quitar el .0

				} else {

					pantalla.setText(Double.toString(resultado));

				}

			}

			valor = resultado;

		}

	}

	// Clase interna del JPanel. Define la acci�n del bot�n de borrar pantalla.
	// Clase oyente, debe implementar la interface Action y sobreescribir todos sus
	// m�todos
	// o heredar de la clase AbstractAction y sobreescribir los que necesite
	// utilizar, adem�s del
	// m�todo accionPerformed el cual esta clase no lo implementa da la interface
	// Action ya que
	// esta no lo declara si no que lo hereda, por esto no se puede considerar una
	// clase adaptadora.
	class AccionBotonBorrar extends AbstractAction {

		// El constructor recibir� como parámetros las caracter�sticas que tendr� el
		// JButton
		public AccionBotonBorrar(String nombre_boton) {

			// El m�todo putValue asigna un valor a una clave. por ejemplo, a la constante
			// NAME le asigna el
			// contenido del string nombre.
			putValue(Action.NAME, nombre_boton);

		}

		@Override // Este m�todo se ejecuta cuando se desencadena el evento de acci�n.
		public void actionPerformed(ActionEvent e) {

			String texto_boton_borrar = e.getActionCommand();

			// Reseteo variables y borro pantalla.
			ultima_operacion = " ";
			resultado = 0.0;
			valor = 0.0;
			operacion = false;

			pantalla.setText(" ");

		}

	}

	JButton pantalla, borrar;
	JPanel botonera;
	String ultima_operacion;
	double resultado, valor;
	boolean operacion;

}
//}

