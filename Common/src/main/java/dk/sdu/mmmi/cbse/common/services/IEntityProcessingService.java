package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *  A processing method provider
 */

public interface IEntityProcessingService {

    /**
     * This IEntityProcessingService is tied to the gameloop
     * and will be running the code tied to the process method
     * everytime the game updates
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
