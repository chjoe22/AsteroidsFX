package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    public World world;
    @Override
    public void process(GameData gameData, World world) {
        //this.world = world;
        double bulletSpeed = 5;
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation())) * bulletSpeed;
            double changeY = Math.sin(Math.toRadians(bullet.getRotation())) * bulletSpeed;
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(-5, -5, 10, 0, -5, -5);
        //world.addEntity(bullet);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX);
        bullet.setY(shooter.getY() + changeY);
        bullet.setRotation(shooter.getRotation());
        return bullet;
    }

    private void setShape(Entity entity) {
    }

}
