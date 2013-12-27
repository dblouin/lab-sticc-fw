package fr.labsticc.framework.compatibility;

import java.util.List;

public class UpdatedData implements IUpdatedData {
	
	private final ICompatibilityUpdater updater;
	
	private final List<String> updateInfo;

	private final byte[] updatedContent;
	
	private final boolean hasChanges;
	
	public UpdatedData( final ICompatibilityUpdater p_updater,
						final List<String> p_updateInfo,
						final byte[] p_updatedContent,
						final boolean pb_hasChanges ) {
		updater = p_updater;
		updateInfo = p_updateInfo;
		updatedContent = p_updatedContent;
		hasChanges = pb_hasChanges;
	}

	@Override
	public List<String> getUpdateInfo() {
		return updateInfo;
	}

	@Override
	public byte[] getUpdatedContent() {
		return updatedContent;
	}

	@Override
	public boolean hasChanges() {
		return hasChanges;
	}

	@Override
	public ICompatibilityUpdater getUpdater() {
		return updater;
	}
}
