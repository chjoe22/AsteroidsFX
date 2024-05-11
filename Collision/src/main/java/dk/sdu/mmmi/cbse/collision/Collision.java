package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityTags;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class Collision implements IPostEntityProcessingService {
    HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity startEntity : world.getEntities()) {
            for (Entity collideEntity : world.getEntities()) {
                if (startEntity.getID().equals(collideEntity.getID())) {
                    continue;
                }


                double distance = Math.sqrt(Math.pow(startEntity.getX() - collideEntity.getX(), 2) + Math.pow(startEntity.getY() - collideEntity.getY(), 2));

                if (distance <= (startEntity.getRadius() + collideEntity.getRadius())) {
                    if (startEntity.getTag().equals(EntityTags.PLAYER_BULLET) && collideEntity.getTag().equals(EntityTags.ENEMY)){
                        world.removeEntity(collideEntity);

                        getEnemySPI().stream().findFirst().ifPresent(
                                enemySPI -> enemySPI.respawn(collideEntity ,gameData, world)
                        );
                    }

                    if (startEntity.getTag().equals(EntityTags.ENEMY_BULLET) && collideEntity.getTag().equals(EntityTags.PLAYER)){
                        world.removeEntity(collideEntity);
                    }

                    if (startEntity.getTag().equals(EntityTags.ENEMY) && collideEntity.getTag().equals(EntityTags.PLAYER)) {
                        world.removeEntity(startEntity);
                        world.removeEntity(collideEntity);
                    }

                    if (startEntity.getTag().equals(EntityTags.PLAYER) && (collideEntity.getTag().equals(EntityTags.ASTEROID) || collideEntity.getTag().equals(EntityTags.ASTEROID_SPLIT))) {
                        world.removeEntity(startEntity);
                    }

                    if ((startEntity.getTag().equals(EntityTags.PLAYER_BULLET) || startEntity.getTag().equals(EntityTags.ENEMY_BULLET)) && (collideEntity.getTag().equals(EntityTags.ASTEROID) || collideEntity.getTag().equals(EntityTags.ASTEROID_SPLIT))){
                        world.removeEntity(startEntity);
                        world.removeEntity(collideEntity);

                        System.out.println("Split: " + collideEntity.getTag());

                        if (startEntity.getTag().equals(EntityTags.PLAYER_BULLET))
                            updateScoring(20);

                        if (collideEntity.getTag().equals(EntityTags.ASTEROID_SPLIT)) continue;

                        getAsteroidSPI().stream().findFirst().ifPresent(
                                asteroidSPI -> asteroidSPI.asteroidSplitter(collideEntity, world)
                        );
                    }

                    if (startEntity.getTag().equals(EntityTags.ASTEROID) && (collideEntity.getTag().equals(EntityTags.ASTEROID) || collideEntity.getTag().equals(EntityTags.ASTEROID_SPLIT))) {
                        collider(startEntity, collideEntity);
                    }

                    if (startEntity.getTag().equals(EntityTags.ENEMY) && (collideEntity.getTag().equals(EntityTags.ASTEROID) || collideEntity.getTag().equals(EntityTags.ASTEROID_SPLIT))){
                        collider(startEntity, collideEntity);
                    }
                }
            }
        }
    }

    private void collider(Entity entity1, Entity entity2){
        double startAsteroidVX = Math.cos(Math.toRadians(entity1.getRotation())) * entity1.getSpeed();
        double startAsteroidVY = Math.sin(Math.toRadians(entity1.getRotation())) * entity1.getSpeed();

        double collideAsteroidVX = Math.cos(Math.toRadians(entity2.getRotation())) * entity2.getSpeed();
        double collideAsteroidVY = Math.sin(Math.toRadians(entity2.getRotation())) * entity2.getSpeed();

        double dx = entity2.getX() - entity1.getX();
        double dy = entity2.getY() - entity1.getY();

        double distanceBetween = Math.sqrt(dx * dx + dy * dy);

        double normalX = dx / distanceBetween;
        double normalY = dy / distanceBetween;

        double relativeVX = collideAsteroidVX - startAsteroidVX;
        double relativeVY = collideAsteroidVY - startAsteroidVY;

        double normalVDot = relativeVX * normalX + relativeVY * normalY;

        if (normalVDot > 0) return;

        double newStartVX = startAsteroidVX + normalVDot * normalX;
        double newStartVY = startAsteroidVY + normalVDot * normalY;

        double newCollideVX = collideAsteroidVX - normalVDot * normalX;
        double newCollideVY = collideAsteroidVY - normalVDot * normalY;

        updateVelocity(entity1, newStartVX, newStartVY);
        updateVelocity(entity2, newCollideVX, newCollideVY);
    }

    private static void updateVelocity(Entity entity, double entityX, double entityY) {
        double newSpeed = Math.sqrt(entityX * entityX + entityY * entityY);
        double newRotation = Math.toDegrees(Math.atan2(entityY, entityX));
        newRotation = (newRotation < 0) ? newRotation + 360 : newRotation; // Normalize rotation

        entity.setSpeed(newSpeed);
        entity.setRotation(newRotation);
    }

    public void updateScoring(int score){
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/attributes/score/update/" + score)).PUT(HttpRequest.BodyPublishers.ofString("")).build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<? extends AsteroidSPI> getAsteroidSPI(){
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends EnemySPI> getEnemySPI(){
        return ServiceLoader.load(EnemySPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
