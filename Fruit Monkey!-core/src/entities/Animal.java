package entities;

import engine.Values;

/* Created by David Lasry : 10/26/14 */
public class Animal extends Entity {

	public enum AnimalType {
		FLY, SNAKE, SPIDER
	}

	private AnimalType animalType;

	public Animal(float x, float y, EntityType entityType,
			ScrollableType scrollableType) {
		super(x, y, entityType, scrollableType);
	}

	public void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
		switch (animalType) {
		case SNAKE:
			setWidth(Values.Snake_Width - 16 * Values.Scalar_Width);
			setHeight(Values.Snake_Height - 16 * Values.Scalar_Height);
			break;
		case FLY:
			setWidth(Values.Fly_Width - 16 * Values.Scalar_Height);
			setHeight(Values.Fly_Height - 16 * Values.Scalar_Height);
			break;
		case SPIDER:
			setWidth(Values.Spider_Width - 16 * Values.Scalar_Height);
			setHeight(Values.Spider_Height - 16 * Values.Scalar_Height);
			break;
		}
	}

	public AnimalType getAnimalType() {
		return animalType;
	}
}
