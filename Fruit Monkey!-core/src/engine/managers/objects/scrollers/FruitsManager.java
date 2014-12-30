package engine.managers.objects.scrollers;

import com.badlogic.gdx.math.Rectangle;

import engine.interfaces.InformationProvider;
import engine.managers.objects.EntitiesManager;
import engine.utils.EntityFactory;
import entities.Fruit;
import entities.EntityType;

public class FruitsManager extends EntitiesManager<Fruit> {
	
	private InformationProvider provider;
	public FruitsManager(float interval, float Scroll_Speed,
			InformationProvider provider) {
		super(interval, Scroll_Speed);
		this.provider = provider;
	}

	@Override
	public void update(float delta) {
		if (entities.size != 0) {
			for (Fruit fruit : entities) {
				fruit.update(delta);
				if(fruit.isNotVisible()) {
					removeValue(fruit);
					EntityFactory.getInstance().getPools().free(fruit);
					provider.getGameListener().notifyStrikesDecreasment();
				}
			}
		}
		if (timer.hasTimeElapsed()) {
			addObject();
			timer.reset();
		}
		timer.update(delta);
	}

	public int isCollidedWithMonkeyFruits(Rectangle bodyMonkey) {
		for (Fruit entity : entities) {
			if (bodyMonkey.overlaps(entity.getBody())) {
				removeValue(entity);
				EntityFactory.getInstance().getPools().free(entity);
				return entity.getPoints();
			}
		}
		return 0;
	}

	public void addObject() {
		
		Fruit fruit = (Fruit) EntityFactory.getInstance().createEntity(EntityType.FRUIT);
		entities.add(fruit);
	}
}
