package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)){

            ((Enemy) enemy).tick(gameData.getDeltaSeconds());

            if (Math.random() > 0.7)
                if (Math.random() > 0.5)
                    enemy.setRotation(enemy.getRotation() + 3);
                else
                    enemy.setRotation(enemy.getRotation() - 3);

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);

            if (((Enemy) enemy).fire())
                for (BulletSPI bulletSPI : getBulletSPIs()){
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(bulletSPI.createBullet(enemy, gameData));
                            }
                    );
                }

            if (enemy.getX() < 0 || enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setRotation(180 - enemy.getRotation()); // Reflect horizontally
                enemy.setX(Math.max(1, Math.min(enemy.getX(), gameData.getDisplayWidth() - 1)));
            }

            // handle vertical boundary collisions
            if (enemy.getY() < 0 || enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setRotation(-enemy.getRotation()); // Reflect vertically
                enemy.setY(Math.max(1, Math.min(enemy.getY(), gameData.getDisplayHeight() - 1)));
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
