package doodle;

import doodle.shapes.DoodleBox;
import doodle.shapes.DoodleEllipse;

public class DoodleFactory {
	private static final DoodleFactory instance;

	static {
		instance = new DoodleFactory();
	}

	public static DoodleFactory instance() {
		return instance;
	}

	private DoodleType type = DoodleType.BOX;

	private DoodleFactory() {
	}

	public void switchType(DoodleType type) {
		this.type = type;
	}

	public Doodle create() {
		Doodle inst = null;

		switch(this.type) {
			case BOX:
				inst = new DoodleBox();
				break;
			case ELLIPSE:
				inst = new DoodleEllipse();
				break;
		}

		return inst;
	}

	public static enum DoodleType {
		BOX,
		ELLIPSE
	}
}
