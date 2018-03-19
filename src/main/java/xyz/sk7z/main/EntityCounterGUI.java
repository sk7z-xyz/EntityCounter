package xyz.sk7z.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Map;

public class EntityCounterGUI extends Gui {
    Minecraft mc;

    public EntityCounterGUI(Minecraft mc) {

        this.mc = mc;

    }

    public void drawMap(Map[] maps) {
        String[] entityTypes = {"Player", "Monster", "Animal", "Other", "item"};
        String[] entityColer = {"66cdaa","778899","66cdaa","778899","66cdaa"};
        String nameFormat = "%25s";
        String countFromat = "%03d";
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth() -120;
        int height = 5;
        for (int i = 0; i < maps.length; i++) {
            Map<String, Integer> map = maps[i];

            height += 5;
            drawText(String.format(nameFormat,entityTypes[i]),"", width, height,entityColer[i]);
            height += 10;

            for (String mapKey : map.keySet()) {
                String name = String.format(nameFormat,mapKey);

                String count = String.format(countFromat,map.get(mapKey));
                drawText(name,count,width, height,entityColer[i]);
                height += 10;
            }
        }
    }

    private void drawText(String name, String count, int x, int y, String color) {
        drawCenteredString(mc.fontRendererObj, name, x, y, Integer.parseInt(color, 16));
        drawCenteredString(mc.fontRendererObj, count, x+75, y, Integer.parseInt("c0c0c0", 16));
    }
}