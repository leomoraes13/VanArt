package ca.leomoraes.vanart.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * This is the service that provides the factory to be bound to the collection service.
 */
public class AppService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);
    }
}