import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module SplitPackage {
    requires Common;
    requires CommonEnemy;
    exports dk.sdu.mmmi.cbse.enemysystem;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

}