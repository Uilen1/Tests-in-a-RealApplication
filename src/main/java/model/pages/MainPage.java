package model.pages;

import model.core.BasePage;

public class MainPage extends BasePage {
	
	public MainPage() {
		
	}

	public void register(String nameStep) {
		utils.click("elementosForm:cadastrar", nameStep);
	}

	public void writeName(String name,String nameStep) {
		utils.write("elementosForm:nome", name, nameStep);
	}

	public void writeSurname(String surname,String nameStep) {
		utils.write("elementosForm:sobrenome", surname, nameStep);
	}

	public void selectUserSex(String sexo, String nameStep) {
		if (sexo == "Masculino") {
			utils.click("elementosForm:sexo:0", nameStep);
		} else if (sexo == "Feminino") {
			utils.click("elementosForm:sexo:1", nameStep);
		}
	}

	public void selectUserFood(String food, String nameStep) {
		if (food == "Carne") {
			utils.click("elementosForm:comidaFavorita:0",nameStep);
		} else if (food == "Frango") {
			utils.click("elementosForm:comidaFavorita:1",nameStep);
		} else if (food == "Pizza") {
			utils.click("elementosForm:comidaFavorita:2",nameStep);
		} else if (food == "Vegetariano") {
			utils.click("elementosForm:comidaFavorita:3",nameStep);
		}

	}

	public void choiceScholarLevel(String ScholarLevel,String nameStep) {
		utils.selectItemCombo(ScholarLevel, "elementosForm:escolaridade",nameStep);
	}

	public void selectUserSports(String nameStep,String... sports) {
		for (String sport : sports) {
			utils.selectItemCombo(sport, "elementosForm:esportes",nameStep);
		}

	}

	public void writeSugestions(String sugestion,String nameStep) {
		utils.write("textarea", "elementosForm:sugestoes", sugestion, nameStep);
	}

}
