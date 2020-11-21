package com.improved.train.data;

import org.json.JSONArray;
import org.json.JSONObject;

import com.improved.train.rest.RestConnectionHandler;

public class Race {

	private String index;
	private String name;
	private String url;
	private int speed;
	
	private String alignment;
	private String ageDescription;
	private String size;
	private String sizeDescription;
	
	private Language[] languages;
	private String languageDescription;
	
	private Subrace[] subraces;
	
	private AbilityScore[] abilityBonuses;
	private int[] abilityBonusNumbers;
	
	private Proficiencies[] startingProficiencies;
	private int amountOfProficienciesOneCanChoose;
	private Proficiencies[] startingProficienciesOptions;
	
	private Trait[] traits;

	public Race(String index, String url, Database dataBase) {
		this.index = index;
		this.url = url;
		
		JSONObject obj = RestConnectionHandler.getObjectURL(url);
		JSONObject starting_proficiencies_options = obj.getJSONObject("starting_proficiency_options");
		
		System.out.println(obj);
		
		this.name = obj.getString("name");
		this.speed = obj.getInt("speed");
		this.alignment = obj.getString("alignment");
		this.ageDescription = obj.getString("age");
		this.size = obj.getString("size");
		this.sizeDescription = obj.getString("size_description");
		this.languageDescription = obj.getString("language_desc");
		this.amountOfProficienciesOneCanChoose = starting_proficiencies_options.getInt("choose");
		
		this.languages = loadLanguages(obj.getJSONArray("languages"), dataBase);
		this.subraces = loadSubraces(obj.getJSONArray("subraces"));
		this.abilityBonuses = loadAbilityScores(obj.getJSONArray("ability_bonuses"), dataBase);
		this.startingProficiencies = loadStartingProficiencies(obj.getJSONArray("starting_proficiencies"), dataBase);
		this.startingProficienciesOptions = loadStartingProficienciesOptions(obj.getJSONObject("starting_proficiency_options"), dataBase);
		this.traits = loadTraits(obj.getJSONArray("traits"), dataBase);
	}
	
	private Proficiencies[] loadStartingProficiencies(JSONArray array, Database dataBase) {
		Proficiencies[] proficiencies = new Proficiencies[array.length()];
		for(int i = 0; i < proficiencies.length; i++) {
			proficiencies[i] = dataBase.getProficiencies(array.getJSONObject(i).getString("index"));
		}
		return proficiencies;
	}
	private Proficiencies[] loadStartingProficienciesOptions(JSONObject obj, Database dataBase) {
		JSONArray array = obj.getJSONArray("from");
		this.amountOfProficienciesOneCanChoose = obj.getInt("choose");
		Proficiencies[] proficiencies = new Proficiencies[array.length()];
		for(int i = 0; i < proficiencies.length; i++) {
			proficiencies[i] = dataBase.getProficiencies(array.getJSONObject(i).getString("index"));
		}
		return proficiencies;
	}
	private Trait[] loadTraits(JSONArray array, Database dataBase) {
		Trait[] traits = new Trait[array.length()];
		for(int i = 0; i < traits.length; i++) {
			traits[i] = dataBase.getTrait(array.getJSONObject(i).getString("index"));
		}
		return traits;
	}
	
	private AbilityScore[] loadAbilityScores(JSONArray array, Database dataBase) {
		AbilityScore[] abilityScores = new AbilityScore[array.length()];
		abilityBonusNumbers = new int[array.length()];
		for(int i = 0; i < abilityScores.length; i++) {
			abilityBonusNumbers[i] = array.getJSONObject(i).getInt("bonus");
			abilityScores[i] = dataBase.getAbilityScores(array.getJSONObject(i).getJSONObject("ability_score").getString("index"));
		}
		return abilityScores;
	}
	
	private Language[] loadLanguages(JSONArray array, Database dataBase) {
		Language[] languages = new Language[array.length()];
		for(int i = 0; i < languages.length; i++) {
			languages[i] = dataBase.getLanguage(array.getJSONObject(i).getString("index"));
		}
		return languages;
	}
	
	private Subrace[] loadSubraces(JSONArray array) {
		Subrace[] races = new Subrace[array.length()];
		for(int i = 0; i < races.length; i++) {
			JSONObject obj = array.getJSONObject(i);
			races[i] = new Subrace(obj.getString("index"), obj.getString("url"));
		}
		return races;
	}

	public String getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public int getSpeed() {
		return speed;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getAgeDescription() {
		return ageDescription;
	}

	public String getSize() {
		return size;
	}

	public String getSizeDescription() {
		return sizeDescription;
	}

	public Language[] getLanguages() {
		return languages;
	}

	public String getLanguageDescription() {
		return languageDescription;
	}

	public Subrace[] getSubraces() {
		return subraces;
	}

	public AbilityScore[] getAbilityBonuses() {
		return abilityBonuses;
	}

	public Proficiencies[] getStartingProficiencies() {
		return startingProficiencies;
	}

	public int getAmountOfProficienciesOneCanChoose() {
		return amountOfProficienciesOneCanChoose;
	}

	public Proficiencies[] getStartingProficienciesOptions() {
		return startingProficienciesOptions;
	}

	public Trait[] getTraits() {
		return traits;
	}
	
}
