package xyz.sk7z.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class EntityCounter {

    private Minecraft mc;

    public EntityCounter(Minecraft mc){
        this.mc = mc;

    }


    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
        Map[] entityCounts = entitiyCount();

        new EntityCounterGUI(mc).drawMap(entityCounts);
    }

    private Map[] entitiyCount() {

        Map<String, Integer> EntityPlayerMap = new HashMap<String, Integer>();
        Map<String, Integer> EntityMonsterMap = new HashMap<String, Integer>();
        Map<String, Integer> EntityAnimalMap = new HashMap<String, Integer>();
        Map<String, Integer> EntityOtherMap = new HashMap<String, Integer>();
        Map<String, Integer> EntityItemMap = new HashMap<String, Integer>();

        World world = mc.theWorld;

        for (Entity entity : world.getLoadedEntityList()) {
            if(entity instanceof EntityPlayer){

                appendMap(entity,EntityPlayerMap);

            }else if(entity instanceof EntityAnimal){

                appendMap(entity,EntityAnimalMap);

            }else if(entity instanceof EntityCreature){

                appendMap(entity,EntityMonsterMap);

            }else if(entity instanceof EntityItem){
                appendMap(entity,EntityItemMap);
            }
            else{
                appendMap(entity,EntityOtherMap);
            }
        }

        //最初から配列でいい気がするけど 見やすくするため
        Map[] maps = new Map[5];

        maps[0] = EntityPlayerMap;
        maps[1] = EntityMonsterMap;
        maps[2] = EntityAnimalMap;
        maps[3] = EntityOtherMap;
        maps[4] = EntityItemMap;

        for(Map map:maps){
            sortMap(map);
        }

        return maps;
    }

    private void appendMap(Entity entity, Map<String,Integer> map) {
        String name = entity.getName();

        int count = 1;
        if(entity instanceof EntityOtherPlayerMP){
            EntityPlayerSP player = mc.thePlayer;
            count =(int) entity.getDistanceToEntity(player);
        }

        if(entity instanceof EntityItem){
            count = ((EntityItem)entity).getEntityItem().stackSize;
        }

        if (map.containsKey(name)) {
            count += map.get(name);
        }
        map.put(name, count);
    }

    private void sortMap(Map<String,Integer> map){
        Object[] eMapKey = map.keySet().toArray();
        Arrays.sort(eMapKey);
    }
}
