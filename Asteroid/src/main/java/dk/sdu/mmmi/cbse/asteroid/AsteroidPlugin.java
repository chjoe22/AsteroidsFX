package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {

    }

    public void createAsteroid(GameData gameData, World world) {
        Entity asteroid = new Asteroid();
        asteroid.setTag(EntityTags.ASTEROID);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }
}
