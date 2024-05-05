package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidFactory {

    private Random random = new Random();

    public Asteroid createAsteroid(GameData gameData){
        Asteroid asteroid = new Asteroid();
        asteroid.setSpeed(random.nextDouble(0.5,1));

        asteroid.setRadius(random.nextInt(20,30));

        asteroid.setTag(EntityTags.ASTEROID);

        asteroid.setX(gameData.getDisplayWidth() - random.nextInt(50, 750));
        asteroid.setY(gameData.getDisplayHeight() - random.nextInt(50, 750));

        generateAsteroidShape(asteroid);

        asteroid.setRotation(random.nextInt(0, 360));

        return asteroid;
    }

    public void generateAsteroidShape(Asteroid asteroid){
        asteroid.setPolygonCoordinates(
                0, 1 * asteroid.getRadius(),
                0.75 * asteroid.getRadius(), 0.75 * asteroid.getRadius(),
                1 * asteroid.getRadius(), 0,
                0.75 * asteroid.getRadius(), -0.75 * asteroid.getRadius(),
                0, -1 * asteroid.getRadius(),
                -0.75 * asteroid.getRadius(), -0.75 * asteroid.getRadius(),
                -1 * asteroid.getRadius(), 0,
                -0.75 * asteroid.getRadius(), 0.75 * asteroid.getRadius()
        );
    }
}
