package engine.utils.memory;

import views.screens.GameScreen;
import views.screens.ScreenHandler;
import views.screens.ScreenType;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import engine.Values;
import engine.utils.graphics.Scrollable.ScrollableType;
import entities.Animal;
import entities.Animal.AnimalType;
import entities.Entity;
import entities.EntityType;
import entities.Fruit;
import entities.Fruit.FruitType;

public class EntityPools {

	private Pool<Entity> fruits;
	private Pool<Entity> animals;

	public EntityPools() {
		initiatePools();
	}

	private void initiatePools() {
		fruits = new Pool<Entity>() {
			@Override
			protected Fruit newObject() {
				return new Fruit(0, Values.SCREEN_HEIGHT, EntityType.FRUIT,
						ScrollableType.VERTICAL);

			}
		};
		animals = new Pool<Entity>() {
			@Override
			protected Animal newObject() {
				return new Animal(0, Values.SCREEN_HEIGHT, EntityType.ANIMAL,
						ScrollableType.VERTICAL);

			}
		};
	}

	public Fruit obtainFruit() {
		Fruit fruit = (Fruit) fruits.obtain();
		fruit.setFruitType(EntityHelper.randomFruitType());
		fruit.setScrollableType(ScrollableType.VERTICAL);
		fruit.setPosition(EntityHelper.randomPosition(ScrollableType.VERTICAL));
		return fruit;
	}

	public Animal obtainAnimal() {
		Animal animal = (Animal) animals.obtain();
		ScrollableType type = EntityHelper.randomScrollableType();
		animal.setScrollableType(type);
		animal.setAnimalType(EntityHelper.randomAnimalType(type));
		animal.setPosition(EntityHelper.randomPosition(type));
		return animal;
	}

	public void free(Entity entity) {
		switch (entity.getType()) {
		case FRUIT:
			fruits.free((Fruit) entity);
			break;
		case ANIMAL:
			animals.free((Animal) entity);
			break;
		}
	}

	public void clearAll() {
		fruits.clear();
		animals.clear();
	}

	private final static class EntityHelper {

		public static FruitType randomFruitType() {
			switch (MathUtils.random(0, 5)) {
			case 0:
				return FruitType.NORMAL_BANANA;
			case 1:
				return FruitType.PINEAPPLE;
			case 2:
				return FruitType.SPECIAL_BANANA;
			case 3:
				return FruitType.STRAWBERRY;
			case 4:
				return FruitType.WATERMELON;
			case 5:
				return FruitType.GRAPES;
			}
			return null;
		}

		public static AnimalType randomAnimalType(ScrollableType scrollableType) {
			switch (scrollableType) {
			case VERTICAL:
				switch (MathUtils.random(0, 1)) {
				case 0:
					return AnimalType.SNAKE;
				case 1:
					return AnimalType.FLY;
				}
			case HORIZONTAL_LEFT:
				switch (MathUtils.random(0, 1)) {
				case 0:
					return AnimalType.SNAKE;
				case 1:
					return AnimalType.SPIDER;
				}
			case HORIZONTAL_RIGHT:
				return AnimalType.SPIDER;
			}
			return null;
		}

		public static ScrollableType randomScrollableType() {
			switch (MathUtils.random(0, 2)) {
			case 0:
				return ScrollableType.VERTICAL;
			case 1:
				return ScrollableType.HORIZONTAL_RIGHT;
			case 2:
				return ScrollableType.HORIZONTAL_LEFT;
			default:
				return ScrollableType.VERTICAL;
			}
		}

		public static Vector2 randomPosition(ScrollableType type) {

			Vector2 position = new Vector2();
			while(true) {
				switch (type) {
				case HORIZONTAL_LEFT:
					position.x = Values.SCREEN_WIDTH;
					position.y = MathUtils.random(0, Values.SCREEN_HEIGHT
							- Values.Fly_Height);
					break;
				case HORIZONTAL_RIGHT:
					position.x = 0 - Values.Fly_Width;
					position.y = MathUtils.random(0, Values.SCREEN_HEIGHT
							- Values.Fly_Height);
					break;
				case VERTICAL:
					position.x = MathUtils.random(0, Values.SCREEN_WIDTH
							- Values.Fly_Width);
					position.y = Values.SCREEN_HEIGHT;
					break;
				}
				if(isAreaClean(position, 1))
					break;
			}
			return position;
		}
		
		public static boolean isAreaClean(Vector2 position, int type) {
			return ((GameScreen) ScreenHandler.getInstance().getScreen(ScreenType.GAME)).getWorld().getScrollManager().isAreaClean(position);
		}
	}
}
