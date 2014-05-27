package be.davidcorp.database;

public enum Filename {
	SPRITES_FILE("sprites.ser"),
    GAMEFIELDLINKS_FILE("gamefieldLinks.txt"),
	GAMEFIELD_FILE("gamefield.txt"),
	SAVE_FILE_LOCATION("resources/saveFiles");
	
    private Filename(final String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}
