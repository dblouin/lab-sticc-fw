package fr.labsticc.framework.service.settings;

import java.util.List;

import fr.labsticc.framework.core.settings.IListItem;
import fr.labsticc.framework.service.IServiceConfigurable;

public interface ISettingsAS extends IServiceConfigurable {

	<T> List<IListItem<T>> getItems( Class<T> p_type );
}
