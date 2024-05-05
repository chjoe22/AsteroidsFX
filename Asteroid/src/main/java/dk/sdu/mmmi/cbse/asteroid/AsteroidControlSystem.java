package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private Random random = new Random();
    private AsteroidFactory asteroidFactory = new AsteroidFactory();

    @Override
    public void process(GameData gameData, World world) {

        if (world.getEntities(Asteroid.class).stream().filter(a -> a.getTag().equals(EntityTags.ASTEROID)).count() <= 10){
            world.addEntity(asteroidFactory.createAsteroid(gameData));
            System.out.println(world.getEntities(Asteroid.class).size());
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * asteroid.getSpeed());
            asteroid.setY(asteroid.getY() + changeY * asteroid.getSpeed());

            // handle horizontal boundary collisions
            if (asteroid.getX() < 0 || asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setRotation(180 - asteroid.getRotation()); // Reflect horizontally
                asteroid.setX(Math.max(1, Math.min(asteroid.getX(), gameData.getDisplayWidth() - 1)));
            }

            // handle vertical boundary collisions
            if (asteroid.getY() < 0 || asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setRotation(-asteroid.getRotation()); // Reflect vertically
                asteroid.setY(Math.max(1, Math.min(asteroid.getY(), gameData.getDisplayHeight() - 1)));
            }

            if (asteroid.getX() < 0 || asteroid.getX() > gameData.getDisplayWidth() || asteroid.getY() < 0 || asteroid.getY() > gameData.getDisplayHeight())
                world.removeEntity(asteroid);

        }
    }

    @Override
    public void asteroidSplitter(Entity entity, World world) {

        for (int i = 0; i < random.nextInt(2,4); i++) {
            Asteroid asteroidSplit = new Asteroid();
            asteroidSplit.setRadius(entity.getRadius()/random.nextDouble(1.7,2.2));

            asteroidSplit.setTag(EntityTags.ASTEROID_SPLIT);

            asteroidFactory.generateAsteroidShape(asteroidSplit);

            asteroidSplit.setSpeed(entity.getSpeed());

            asteroidSplit.setRotation(entity.getRotation() + random.nextInt(-90, 90));
            asteroidSplit.setX(entity.getX());
            asteroidSplit.setY(entity.getY());

            world.addEntity(asteroidSplit);
        }
    }
}
