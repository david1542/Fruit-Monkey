package views.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import engine.Values;
import engine.utils.general.AssetsLoader;

public class ParticleEffectsManager extends Actor{

	// Effects
	private Array<PooledEffect> effects;
	private ParticleEffectPool yellow_star_pool;
	private ParticleEffectPool green_star_pool;
	private ParticleEffectPool special_star_pool;

	public ParticleEffectsManager() {
		// Effects
		effects = new Array<ParticleEffectPool.PooledEffect>();
		yellow_star_pool = new ParticleEffectPool(AssetsLoader.getInstance().getAsset(
				"effects/star.p", ParticleEffect.class), 0, 10);
		green_star_pool = new ParticleEffectPool(AssetsLoader.getInstance().getAsset(
				"effects/star2.p", ParticleEffect.class), 0, 10);
		special_star_pool = new ParticleEffectPool(AssetsLoader.getInstance().getAsset(
				"effects/specialstar.p", ParticleEffect.class), 0, 10);
	}

	private void drawPooledParticles(float delta, SpriteBatch batch) {
		for(PooledEffect effect : effects) {
			effect.draw(batch, delta);
			if (effect.isComplete()) {
		        effect.free();
		        effects.removeValue(effect, true);
		        yellow_star_pool.obtain().reset();
		        green_star_pool.obtain().reset();
		        special_star_pool.obtain().reset();
		    }
		}
	}
	
	// Callback method
	public void addEffect(Vector2 position, boolean special) {
		// Callback Method for the particle effects
		PooledEffect effect;
		if (!special)
			effect = (MathUtils.random(0, 1) == 0) ? yellow_star_pool.obtain()
					: green_star_pool.obtain();
		else
			effect = special_star_pool.obtain();
		effect.setPosition(position.x, position.y);
		effect.scaleEffect(Values.Scalar_Width*1.3f);
		effects.add(effect);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawPooledParticles(Gdx.graphics.getDeltaTime(), (SpriteBatch) batch);
	}
}
