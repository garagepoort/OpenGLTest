package be.davidcorp.engine;

import com.google.common.collect.Lists;

import be.davidcorp.metric.Point;

public class ModelDrawer {

	private ModelContainer modelContainer;

	public ModelDrawer(ModelContainer modelContainer) {
		this.modelContainer = modelContainer;
	}

	public void drawTriangle(Point v1, Point v2, Point v3){
		modelContainer.addModel(new Model(Lists.newArrayList(v1, v2, v3)));
	}
}
