package Utils;

import java.util.Arrays;
import java.util.List;

public class NameMaster {
	static NameMaster instance;

	List<String> names = Arrays.asList("Isabella","Daniel","Olivia","David","Alexis","Gabriel","Sof�a","Benjam�n","Victoria","Samuel","Amelia","Lucas","Alexa","�ngel","Julia","Jos�","Camila","Adri�n","Alexandra","Sebasti�n","Maya","Xavier","Andrea","Juan","Ariana","Luis","Mar�a","Diego","Eva","�liver","Angelina","Carlos","Valeria","Jes�s","Natalia","Alex","Isabel","Max","Sara","Alejandro","Liliana","Antonio","Adriana","Miguel","Juliana","V�ctor","Gabriela","Joel","Daniela","Santiago","Valentina","El�as","Lila","Iv�n","Vivian","�scar","Nora","Leonardo","�ngela","Eduardo","Elena","Alan","Clara","Nicol�s","Eliana","Jorge","Alana","Omar","Miranda","Pa�l","Amanda","Andr�s","Diana","Juli�n","Ana","Josu�","Pen�lope","Rom�n","Aurora","Fernando","Alexandr�a","Javier","Lola","Abraham","Alicia","Ricardo","Amaya","Francisco","Alexia","C�sar","Jazm�n","Mario","Mariana","Manuel","Alina","�dgar","Luc�a","Alexis","F�tima","Israel","Ximena","Mateo","Laura","H�ctor","Cecilia","Sergio","Alejandra","Emiliano","Esmeralda","Sim�n","Ver�nica","Rafael","Daniella","Mart�n","Miriam","Marco","Carmen","Roberto","Iris","Pedro","Guadalupe","Emanuel","Selena","Abel","Fernanda","Rub�n","Ang�lica","Fabi�n","Emilia","Emilio","L�a","Joaqu�n","Tatiana","Marcos","M�nica","Lorenzo","Carolina","Armando","Jimena","Ad�n","Dulce","Ra�l","Tal�a","Julio","Estrella","Enrique","Brenda","Gerardo","Lilian","Pablo","Paola","Jaime","Serena","Sa�l","Celeste","Esteban","Viviana","Gustavo","Elisa","Rodrigo","Melina","Arturo","Gloria","Mauricio","Claudia","Orlando","Sandra","Hugo","Marisol","Salvador","Asia","Alfredo","Ada","Maximiliano","Rosa","Ram�n","Isabela","Ernesto","Regina","Tob�as","Elsa","Abram","Perla","No�","Raquel","Guillermo","Virginia","Ezequiel","Patricia","Luci�n","Linda","Alonzo","Marina","Felipe","Leila","Mat�as","Am�rica","Tom�s","Mercedes","Jairo");
	
	private NameMaster() {
		super();
	}

	public static NameMaster getInstance() {
		if (instance == null)
			instance = new NameMaster();
		return instance;
	}
	
	public String getName() {
		return names.get((int) Math.floor(Math.random() * names.size()));
	}
}
