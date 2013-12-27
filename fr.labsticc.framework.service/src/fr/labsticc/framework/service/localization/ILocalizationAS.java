package fr.labsticc.framework.service.localization;

import java.util.Collection;
import java.util.Locale;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.settings.IListItem;
import fr.labsticc.framework.service.IService;

public interface ILocalizationAS<T> extends IService {

	void setLocalizationData( 	T p_data,
								Locale p_locale )
	throws ResourceAccessException;


	<CT> void localizeItems( 	Collection<IListItem<CT>> p_items,
								Locale p_locale )
	throws ResourceAccessException;
}
