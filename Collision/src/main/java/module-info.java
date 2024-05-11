import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroid;
    requires java.net.http;
    requires CommonEnemy;
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
    uses dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.Collision;
}