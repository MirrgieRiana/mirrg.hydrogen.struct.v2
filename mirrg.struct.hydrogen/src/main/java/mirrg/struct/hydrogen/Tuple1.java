package mirrg.struct.hydrogen;

import java.io.Serializable;

public class Tuple1<X> implements Serializable
{

	private static final long serialVersionUID = 494865600797541340L;

	private X x;

	public Tuple1(X x)
	{
		this.x = x;
	}

	public X getX()
	{
		return x;
	}

	@Override
	public String toString()
	{
		return "Tuple1 [x=" + x + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Tuple1<?> other = (Tuple1<?>) obj;
		if (x == null) {
			if (other.x != null) return false;
		} else if (!x.equals(other.x)) return false;
		return true;
	}

}
