package be.davidcorp.loaderSaver.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.loaderSaver.mapper.spriteToString.TriggerToStringMapper;

public class TriggerFileWriter {

	private File triggerLinksFile;

	public TriggerFileWriter(File triggerLinksFile) {
		this.triggerLinksFile = triggerLinksFile;
	}

	public void saveTriggerLinks(List<Trigger> triggers) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(triggerLinksFile);
			for (Trigger trigger : triggers) {
				String spriteString = new TriggerToStringMapper().mapTriggerToString(trigger);
				printWriter.println(spriteString);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null)
				printWriter.close();
		}
	}
	
}
