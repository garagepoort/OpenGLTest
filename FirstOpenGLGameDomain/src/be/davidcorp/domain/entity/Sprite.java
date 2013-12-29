package be.davidcorp.domain.entity;

import java.util.List;

import be.davidcorp.domain.components.Component;

import com.google.common.collect.Lists;

public class Sprite {

	private List<Component> components =  Lists.newArrayList();

	public Sprite(Component... components){
		this.components = Lists.newArrayList(components);
	}
	
	public boolean hasComponent(Class<? extends Component> componentClass) {
		for(Component component : components){
			if(component.getClass() == componentClass){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public <COMPONENT extends Component> COMPONENT getComponent(Class<COMPONENT> componentClass) {
		for(Component component : components){
			if(component.getClass() == componentClass){
				return (COMPONENT) component;
			}
		}
		return null;
	}
}
