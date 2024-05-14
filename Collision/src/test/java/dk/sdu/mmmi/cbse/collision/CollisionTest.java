package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {

    private Collision collision;

    @BeforeEach
    public void setup(){
        collision = new Collision();
    }

    @Test
    public void collisionDetectionTest(){
        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.setX(5);
        entity2.setY(5);
        entity2.setRadius(10);

        assertTrue(collision.collision(entity1, entity2));
        System.out.println("Collision between 2 entities has been detected");
    }

    @Test
    public void noCollisionDetectionTest(){
        Entity entity1 = new Entity();
        entity1.setX(0);
        entity1.setY(0);
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.setX(15);
        entity2.setY(15);
        entity2.setRadius(10);

        assertFalse(collision.collision(entity1, entity2));
        System.out.println("Collision between 2 entities has not been detected");
    }
}