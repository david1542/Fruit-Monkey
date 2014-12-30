package engine.utils;

import engine.utils.memory.EntityPools;
import entities.Animal;
import entities.Entity;
import entities.EntityType;
import entities.Fruit;

/**
 * EntityFactory. It creates the entities from a selection of pools.
 * This class hides the creation of different 
 * entities and thus, simplifies the process.
 * @author David
 *
 */
public class EntityFactory {
	
	private static EntityFactory instance = new EntityFactory();
	private EntityPools pools;
	private EntityFactory() {
		pools = new EntityPools();
	}
	
	
	public Entity createEntity(EntityType type) {
		switch (type) {
		case FRUIT:
			Fruit fruit = pools.obtainFruit();
			return fruit;
		case ANIMAL:
			Animal animal = pools.obtainAnimal();
			return animal;
		}
		return null;
	}
	
	
	public static EntityFactory getInstance() {
		return instance;
	}
	
	public EntityPools getPools() {
		return pools;
	}
}
