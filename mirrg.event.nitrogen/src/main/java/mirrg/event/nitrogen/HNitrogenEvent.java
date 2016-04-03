package mirrg.event.nitrogen;

import mirrg.event.nitrogen.api.INitrogenEventManager;

public class HNitrogenEvent
{

	public static INitrogenEventManager createInstance()
	{
		return new NitrogenEventManagerImpl();
	}

}
