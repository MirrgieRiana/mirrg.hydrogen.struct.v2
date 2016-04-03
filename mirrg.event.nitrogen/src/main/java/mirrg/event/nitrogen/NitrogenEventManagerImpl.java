package mirrg.event.nitrogen;

import java.util.ArrayList;
import java.util.function.Predicate;

import mirrg.event.nitrogen.api.INitrogenEventManager;

class NitrogenEventManagerImpl implements INitrogenEventManager
{

	private ArrayList<Class<?>> classes = new ArrayList<>();
	private ArrayList<Predicate<?>> handlers = new ArrayList<>();

	@Override
	public <E> void registerRemovable(Class<E> clazz, Predicate<E> handler)
	{
		classes.add(clazz);
		handlers.add(handler);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> void post(E event)
	{
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).isInstance(event)) {

				if (!((Predicate<E>) handlers.get(i)).test(event)) {
					classes.remove(i);
					handlers.remove(i);
					i--;
				}

			}
		}
	}

}
