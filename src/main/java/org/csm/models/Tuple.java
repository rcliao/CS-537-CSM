package org.csm.models;

import com.google.gson.annotations.Expose;

public class Tuple<X, Y> {
	@Expose
	public X x;
	@Expose
	public Y y;

	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

}
