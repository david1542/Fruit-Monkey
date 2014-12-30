package engine.managers.objects.scrollers;

import engine.managers.objects.EntitiesManager;
import engine.utils.EntityFactory;
import entities.Animal;
import entities.EntityType;

/* Created by David Lasry 10/26/14 */
public class AnimalsManager extends EntitiesManager<Animal> {

	public AnimalsManager(float interval, float Scroll_Speed) {
		super(interval, Scroll_Speed);
	}

	@Override
	public void update(float delta) {
		if (entities.size != 0) {
			for (Animal animal : entities) {
				animal.update(delta);
				if(animal.isNotVisible()) {
					removeValue(animal);
					EntityFactory.getInstance().getPools().free(animal);
				}
			}
		}
		if (timer.hasTimeElapsed()) {
			addObject();
			timer.reset();
		}
		timer.update(delta);
	}

	public void addObject() {
		Animal animal = (Animal) EntityFactory.getInstance().createEntity(
				EntityType.ANIMAL);
		entities.add(animal);
	}
}
