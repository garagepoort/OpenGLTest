package be.davidcorp.component;

public class TimeToLiveComponent implements Component {

	public int TimeToLiveTime;

	public TimeToLiveComponent(int timeToLiveTime) {
		super();
		TimeToLiveTime = timeToLiveTime;
	}

	@Override
	public ComponentType getType() {
		return ComponentType.TIME_TO_LIVE;
	}
	
}
