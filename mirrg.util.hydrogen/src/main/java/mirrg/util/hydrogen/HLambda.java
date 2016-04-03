package mirrg.util.hydrogen;

import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HLambda
{

	public static <T> T get(Supplier<T> supplier)
	{
		return supplier.get();
	}

	public static <T> void forEach(Stream<T> stream, ObjIntConsumer<T> consumer)
	{
		Integer[] i = new Integer[] {
			0,
		};
		stream.sequential().forEach(object -> {
			consumer.accept(object, i[0]);
			i[0]++;
		});
	}

}
