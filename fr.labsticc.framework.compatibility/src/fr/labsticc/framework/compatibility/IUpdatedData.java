package fr.labsticc.framework.compatibility;

import java.util.List;

public interface IUpdatedData {
	
	ICompatibilityUpdater getUpdater();
	
	List<String> getUpdateInfo();
	
	byte[] getUpdatedContent();
	
	boolean hasChanges();
}
