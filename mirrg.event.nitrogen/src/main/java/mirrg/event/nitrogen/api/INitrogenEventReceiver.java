package mirrg.event.nitrogen.api;

public interface INitrogenEventReceiver
{

	/**
	 * 登録時のクラスがこのイベントと代入互換であるハンドラを、
	 * 登録時の順番に起動します。
	 */
	public <E> void post(E event);

}
