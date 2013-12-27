package fr.labsticc.framework.service.settings;

import java.util.ArrayList;
import java.util.List;

import fr.labsticc.framework.core.settings.IListItem;
import fr.labsticc.framework.core.settings.ListItem;
import fr.labsticc.framework.service.AbstractConfigurableAS;

public abstract class AbstractSettingsAS extends AbstractConfigurableAS implements ISettingsAS {

	@Override
	public <T> List<IListItem<T>> getItems( final Class<T> p_type ) {
		final List<IListItem<T>> items = new ArrayList<IListItem<T>>();
		
		if ( p_type != null && p_type.getEnumConstants() != null ) {
			for ( T enumValue : p_type.getEnumConstants() ) {
				items.add( new ListItem<T>( enumValue ) );
			}
		}

		return items;
	}
}
