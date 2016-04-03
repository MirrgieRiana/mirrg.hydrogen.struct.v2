package mirrg.event.nitrogen;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Test;

import mirrg.event.nitrogen.HNitrogenEvent;
import mirrg.event.nitrogen.api.INitrogenEventManager;

public class TestNitrogenEvent
{

	@Test
	public void test()
	{
		String[] message = {
			"",
		};

		INitrogenEventManager nitrogenEventManager = HNitrogenEvent.createInstance();
		nitrogenEventManager.register(Integer.class, event -> {
			message[0] += event;
		});

		nitrogenEventManager.post(Integer.valueOf(4));
		assertEquals("4", message[0]);
	}

	@Test
	public void test2()
	{
		String[] message = {
			"",
		};

		INitrogenEventManager nitrogenEventManager = HNitrogenEvent.createInstance();
		nitrogenEventManager.register(Integer.class, event -> {
			message[0] += event;
		});
		nitrogenEventManager.register(String.class, event -> {
			message[0] += event;
		});

		nitrogenEventManager.post(Integer.valueOf(4));
		assertEquals("4", message[0]);

		nitrogenEventManager.post("G");
		assertEquals("4G", message[0]);

	}

	@Test
	public void test3()
	{
		String[] message = {
			"",
		};

		INitrogenEventManager nitrogenEventManager = HNitrogenEvent.createInstance();
		nitrogenEventManager.register(Integer.class, event -> {
			message[0] += "I";
		});
		nitrogenEventManager.register(String.class, event -> {
			message[0] += "S";
		});
		nitrogenEventManager.register(Double.class, event -> {
			message[0] += "D";
		});
		nitrogenEventManager.register(Number.class, event -> {
			message[0] += "N";
		});

		nitrogenEventManager.post(Integer.valueOf(4));
		assertEquals("IN", message[0]);

		nitrogenEventManager.post("G");
		assertEquals("INS", message[0]);

		nitrogenEventManager.post((Object) Integer.valueOf(4));
		assertEquals("INSIN", message[0]);

		nitrogenEventManager.post(Double.valueOf(4));
		assertEquals("INSINDN", message[0]);

	}

	@Test
	public void test4()
	{
		String[] message = {
			"",
		};

		INitrogenEventManager nitrogenEventManager = HNitrogenEvent.createInstance();

		nitrogenEventManager.post(new Object());
		assertEquals("", message[0]);

		nitrogenEventManager.register(Object.class, event -> {
			message[0] += "A";
		});

		nitrogenEventManager.post(new Object());
		assertEquals("A", message[0]);

		nitrogenEventManager.registerRemovable(Object.class, event -> {
			message[0] += "B";
			return false;
		});

		nitrogenEventManager.post(new Object());
		assertEquals("AAB", message[0]);

		nitrogenEventManager.registerRemovable(Object.class, new Predicate<Object>() {

			private boolean first = true;

			@Override
			public boolean test(Object event)
			{
				message[0] += "C";

				if (first) {
					first = false;
					return true;
				} else {
					return false;
				}
			}

		});

		nitrogenEventManager.post(new Object());
		assertEquals("AABAC", message[0]);

		nitrogenEventManager.post(new Object());
		assertEquals("AABACAC", message[0]);

		nitrogenEventManager.post(new Object());
		assertEquals("AABACACA", message[0]);

		nitrogenEventManager.post(new Object());
		assertEquals("AABACACAA", message[0]);

	}

}
