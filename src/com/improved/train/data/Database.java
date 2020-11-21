package com.improved.train.data;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.improved.train.rest.RestConnectionHandler;

/**
 * 
 * @author Count
 *
 *	Code is still broken. (*^v^)	
 *
 */
public class Database {
	
	private HashMap<String, Race> races = new HashMap<String, Race>();
	private HashMap<String, Language> languages = new HashMap<String, Language>();
	private HashMap<String, Proficiencies> proficiencies = new HashMap<String, Proficiencies>();
	private HashMap<String, Trait> traits = new HashMap<String, Trait>();
	private HashMap<String, AbilityScore> abilityScores = new HashMap<String, AbilityScore>();
	
	public Database() {
		
		//JSONObject obj2 = new JSONObject(recieveConnection(QUERY_URL + dataUrls.get("languages")));
		loadAbilityScores();
		loadLanguages();
		loadTraits();
		loadProficiencies();
		
		loadRaces();
		
	}
	
	private void loadLanguages() {
		
	}
	
	private void loadTraits() {
		
	}
	
	private void loadAbilityScores() {
		
	}
	
	private void loadProficiencies() {
		
	}
	
	private void loadRaces() {
		JSONObject races = RestConnectionHandler.getObject("races");
		int count = races.getInt("count");
		JSONArray raceList = races.getJSONArray("results");
		for(int i = 0; i < count; i++) {
			JSONObject race = raceList.getJSONObject(i);
			String index = race.getString("index");
			this.races.put(index, new Race(index, race.getString("url"), this));
		}
	}
	
	public Race getRace(String index) {
		return races.get(index);
	}
	
	public Language getLanguage(String index) {
		return languages.get(index);
	}
	
	public Proficiencies getProficiencies(String index) {
		return proficiencies.get(index);
	}
	
	public Trait getTrait(String index) {
		return traits.get(index);
	}
	
	public AbilityScore getAbilityScores(String index) {
		return abilityScores.get(index);
	}

}
