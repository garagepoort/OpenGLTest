package be.davidcorp.domain.trigger;

public enum TriggerWhen {
	ONUSE("ONUSE"),
	ONHEALTHLOSS("ONHEALTHLOSS"),
	ONHEALTHGAIN("ONHEALTHGAIN"),
	ONDESTROY("ONDESTROY"),
	ONCREATE("ONCREATE"),
	ONLIGHTOFF("ONLIGHTOFF"),
	ONLIGHTON("ONLIGHTON");

	public final String type;

	TriggerWhen(String type) {
		this.type = type;
	}
}
