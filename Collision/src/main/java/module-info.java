import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroid;
    requires java.net.http;
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.Collision;
}