package mirrg.event.nitrogen.api;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface INitrogenEventRegistry
{

	/**
	 * 登録した順番に呼び出されます。
	 */
	public default <E> void register(Class<E> clazz, Consumer<E> handler)
	{
		registerRemovable(clazz, e -> {
			handler.accept(e);
			return true;
		});
	}

	/**
	 * 登録した順番に呼び出されます。
	 *
	 * @param handler
	 *            falseを返した場合、このイベントハンドラは無効となります。
	 */
	public <E> void registerRemovable(Class<E> clazz, Predicate<E> handler);

}
