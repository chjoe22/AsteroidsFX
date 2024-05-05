package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {


    @Override
    public void process(GameData gameData, World world) {

        double bulletSpeed = 1.5;
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation())) * bulletSpeed;
            double changeY = Math.sin(Math.toRadians(bullet.getRotation())) * bulletSpeed;
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight())
                world.removeEntity(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        setShape(bullet);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());

        if (shooter.getTag().equals(EntityTags.ENEMY))
            bullet.setTag(EntityTags.ENEMY_BULLET);

        if (shooter.getTag().equals(EntityTags.PLAYER))
            bullet.setTag(EntityTags.PLAYER_BULLET);

        bullet.setRotation(shooter.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
    }

}
