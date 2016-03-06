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

	public void drawRectangle(Point v1, Point v2, Point v3, Point v4){
		modelContainer.addModel(new Model(Lists.newArrayList(v1, v2, v4)));
		modelContainer.addModel(new Model(Lists.newArrayList(v2, v3, v4)));
	}

	public void drawRectangle(Point v1, Point v2, Point v3, Point v4, Color color){
		modelContainer.addModel(new Model(Lists.newArrayList(v1, v2, v4), Lists.newArrayList(color, color, color)));
		modelContainer.addModel(new Model(Lists.newArrayList(v2, v3, v4), Lists.newArrayList(color, color, color)));
	}


	public void drawRectangle(Point position, float width, float height, Color color){
		Point v1 = new Point(position.x, position.y + height);
		Point v2 = new Point(position.x, position.y);
		Point v3 = new Point(position.x + width, position.y);
		Point v4 = new Point(position.x + width, position.y + height);

		modelContainer.addModel(new Model(Lists.newArrayList(v1, v2, v4), Lists.newArrayList(color, color, color)));
		modelContainer.addModel(new Model(Lists.newArrayList(v2, v3, v4), Lists.newArrayList(color, color, color)));
	}
}
