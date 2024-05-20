package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Is a process provider
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     * The IPostEntityProcessingService is tied to gameloop
     * and will therefore run everytime the game update happens.
     * This process would also happen after the entityProcessingService is running
     * @param gameData
     * @param world
    */
    void process(GameData gameData, World world);
}
