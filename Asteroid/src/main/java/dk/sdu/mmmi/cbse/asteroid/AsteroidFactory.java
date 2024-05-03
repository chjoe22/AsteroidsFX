package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidFactory {

    private Random random = new Random();

    public Asteroid createAsteroid(GameData gameData, World world){
        Asteroid asteroid = new Asteroid();
        asteroid.setSpeed(random.nextDouble(1,3));
        asteroid.setRadius(random.nextInt(20,30));

        generateAsteroidShape(asteroid);

        asteroid.setX(gameData.getDisplayWidth() - random.nextInt(50, 750));
        asteroid.setY(gameData.getDisplayHeight() - random.nextInt(50, 750));

        asteroid.setRotation(180);

        return asteroid;
    }

    private void generateAsteroidShape(Asteroid asteroid){
        asteroid.setPolygonCoordinates(
                0, 1 * asteroid.getRadius(),
                0.65 * asteroid.getRadius(), 0.65 * asteroid.getRadius()
        );
    }
}
