package be.davidcorp.engine;

import java.util.ArrayList;
import java.util.List;

public class ModelContainer {

	private List<Model> models = new ArrayList<>();


	public List<Model> getModels() {
		return models;
	}

	public void addModel(Model model){
		models.add(model);
	}

	public void reset(){
		models.clear();
	}
}
