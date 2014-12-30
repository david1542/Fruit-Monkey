package entities;

import engine.Values;

public class Fruit extends Entity {

	private int points;
	public enum FruitType {
		NORMAL_BANANA,
		SPECIAL_BANANA,
		PINEAPPLE,
		STRAWBERRY,
		GRAPES,
		WATERMELON
	}
	private FruitType fruitType;
	public Fruit(float x, float y, EntityType entityType, ScrollableType scrollableType) {
		super(x, y, entityType, scrollableType);
		// Some customizations
		body.x += 3 * Values.Scalar_Width;
		body.y += 3 * Values.Scalar_Height;
		body.width -= 6 * Values.Scalar_Width;
		body.height -= 6 * Values.Scalar_Height;
	}

	public int getPoints() {
		return points;
	}

	public void setFruitType(FruitType type) {
		this.fruitType = type;
		switch (fruitType) {
		case SPECIAL_BANANA:
			setWidth(Values.Banana_Width_Special);
			setHeight(Values.Banana_Height_Special);
			points = 15;
			break;
		case NORMAL_BANANA:
			setWidth(Values.Banana_Width_Normal);
			setHeight(Values.Banana_Height_Normal);
			points = 5;
			break;
		case GRAPES:
			setWidth(Values.Grapes_Width);
			setHeight(Values.Grapes_Height);
			points = 7;
			break;
		case WATERMELON:
			setWidth(Values.Watermelon_Width);
			setHeight(Values.Watermelon_Height);
			points = 9;
			break;
		case STRAWBERRY:
			setWidth(Values.Strawberry_Width);
			setHeight(Values.Strawberry_Height);
			points = 5;
			break;
		case PINEAPPLE:
			setWidth(Values.Pineapple_Width);
			setHeight(Values.Pineapple_Height);
			points = 10;
			break;
		default:
			break;
		}
	}
	
	public FruitType getFruitType() {
		return fruitType;
	}
}
