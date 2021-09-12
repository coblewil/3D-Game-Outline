/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outline3D;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class Practice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
        
        float[] vertices2 = {
            -.5f, -.5f, -.05f,
            .5f, -.5f, -.05f,
            0f, .5f, -.05f,
            
            -.5f, -.5f, .05f,
            .5f, -.5f, .05f,
            0, .5f, .05f,
            
            0, .5f, -.05f,
            -.5f, -.5f, -.05f,
            -.5f, -.5f, .05f,
            0, .5f, .05f,
            
            0, .5f, .05f,
            .5f, -.5f, .05f,
            .5f, -.5f, -.05f,
            0, .5f, -.05f,
            
            -.5f, -.5f, -.05f,
            .5f, -.5f, -.05f,
            .5f, -.5f, .05f,
            -.5f, -.5f, .05f,
        };
        
        int[] indices2 = {
            0,1,2,
            3,4,5,
            6,7,8,
            6,8,9,
            10,11,12,
            10,12,13,
            14,15,16,
            14,16,17
        };
        
        float[] textureCoords2 = {
            0,0,
            0,1,
            .5f,1,
            1,0,
            0,0,
            .5f,1,
            .5f,1,
            1,0,
            0,0,
            .5f,1,
            .5f,1,
            1,0,
            0,0,
            .5f,1,
            1,1,
            1,0,
            0,0,
            0,1,
        };
        
        float[] vertices = {
            -0.5f, .5f, -.5f,
            -0.5f, -0.5f, -.5f,
            0.5f, -0.5f, -.5f,
            0.5f, 0.5f, -.5f,
            
            -0.5f, .5f, .5f,
            -0.5f, -0.5f, .5f,
            0.5f, -0.5f, .5f,
            0.5f, 0.5f, .5f,
            
            0.5f, 0.5f, -.5f,
            0.5f, -0.5f, -.5f,
            0.5f, -0.5f, .5f,
            0.5f, 0.5f, .5f,
            
            -0.5f, 0.5f, -.5f,
            -0.5f, -0.5f, -.5f,
            -0.5f, -0.5f, .5f,
            -0.5f, 0.5f, .5f,
            
            -0.5f, 0.5f, .5f,
            -0.5f, 0.5f, -.5f,
            0.5f, 0.5f, -.5f,
            0.5f, 0.5f, .5f,
            
            -0.5f, -0.5f, .5f,
            -0.5f, -0.5f, -.5f,
            0.5f, -0.5f, -.5f,
            0.5f, -0.5f, .5f,
        };
        
        int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22
        };
        
        float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0
        };
        
        RawModel monkey = OBJLoader.loadObjModel("monkey", loader);
        RawModel tree = OBJLoader.loadObjModel("tree", loader);
        //RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        //RawModel model2 = loader.loadToVAO(vertices2, textureCoords2, indices2);
        ModelTexture monkTexture = new ModelTexture(loader.loadTexture("red"));
        ModelTexture treeTexture = new ModelTexture(loader.loadTexture("tree"));
        //ModelTexture texture = new ModelTexture(loader.loadTexture("link1"));
        //ModelTexture texture2 = new ModelTexture(loader.loadTexture("triforceColor"));
        //TexturedModel staticModel = new TexturedModel(model, texture);
        //TexturedModel staticModel2 = new TexturedModel(model2, texture2);
        TexturedModel monkeyModel = new TexturedModel(monkey, monkTexture);
        TexturedModel treeModel = new TexturedModel(tree, treeTexture);
        TexturedModel grassModel = new TexturedModel(OBJLoader.loadObjModel("grass", loader), new ModelTexture(loader.loadTexture("grassTexture")));
        grassModel.getTexture().setHasTransparency(true);
        grassModel.getTexture().setUseFakeLighting(true);
        TexturedModel fernModel = new TexturedModel(OBJLoader.loadObjModel("fern", loader), new ModelTexture(loader.loadTexture("fern")));
        fernModel.getTexture().setHasTransparency(true);
        ModelTexture texture3 = monkeyModel.getTexture();
        TexturedModel tree2Model = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree")));
        texture3.setShineDamper(2000);
        texture3.setReflectivity(100);
        //Entity entity = new Entity(staticModel, new Vector3f(0,0,-5),0,0,0,1);
        Entity monkEnt = new Entity(monkeyModel, new Vector3f(-8,5,-10),0,0,0,2);
        //Entity box2 = new Entity(staticModel, new Vector3f(2,0, -5), 0, 0, 0, 1);
        //Entity box3 = new Entity(staticModel, new Vector3f(1,2, -5), 0, 0, 0, 1);
        //Entity triangle = new Entity(staticModel2, new Vector3f(-2, 0, -5), 0, 0, 0, 1);
        //Entity triangle2 = new Entity(staticModel2, new Vector3f(-3, 0, -5), 0, 0, 0, 1);
        //Entity triangle3 = new Entity(staticModel2, new Vector3f(-2.5f, 1, -5), 0, 0, 0, 1);
        Entity monkEnt2 = new Entity(monkeyModel, new Vector3f(0, 5, -10), 0, 0, 0, 2);
        Light light = new Light(new Vector3f(500, 5000, 0), new Vector3f(1,1,1));
        
        TexturedModel boxModel = new TexturedModel(OBJLoader.loadObjModel("box", loader), new ModelTexture(loader.loadTexture("box")));
        
        TexturedModel monkeyPlayer = new TexturedModel(OBJLoader.loadObjModel("monkey", loader), new ModelTexture(loader.loadTexture("triforceColor")));
        
        Player player = new Player(monkeyPlayer, new Vector3f(0,3,-20), 0, 100, 0, 3);
        
        List<Entity> allEntities = new ArrayList<Entity>();
        Random random = new Random();
        
        for (int i=0; i < 500; i++){
            //allMonks.add(new Entity(monkeyModel, new Vector3f(x, y, z), random.nextFloat()*180f, random.nextFloat()*180f, 0f, 1f));
            allEntities.add(new Entity(treeModel, new Vector3f(random.nextFloat()*800-400,0,random.nextFloat()*800-400), 0,0,0,3));
            allEntities.add(new Entity(grassModel, new Vector3f(random.nextFloat()*800-400,0,random.nextFloat()*800-400), 0,0,0,1));
            allEntities.add(new Entity(tree2Model, new Vector3f(random.nextFloat()*800-400,0,random.nextFloat()*800-400), 0,0,0,.4f));
            allEntities.add(new Entity(fernModel, new Vector3f(random.nextFloat()*800-400,0,random.nextFloat()*800-400), 0,0,0,.6f));
            if(i%25 == 0){
                allEntities.add(new Entity(boxModel, new Vector3f(random.nextFloat()*800-400,0,random.nextFloat()*800-400), 0,0,0,4));
            }
        }
            
        ModelTexture flatgrass = new ModelTexture(loader.loadTexture("grass"));
        
        Terrain terrain = new Terrain(0, 0, loader, texturePack, blendMap);
        Terrain terrain3 = new Terrain(-1, 0, loader, texturePack, blendMap);
        Terrain terrain4 = new Terrain(0, -1, loader, texturePack, blendMap);
        Terrain terrain5 = new Terrain(-1, -1, loader, texturePack, blendMap);
        
        Camera camera = new Camera(player);
        
        boolean start = true;
        boolean left = false;
        int k = 0;
        monkEnt.increaseRotation(-20, 0, 0);
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){    
            if(start){
                monkEnt.increaseRotation(1f, 0, 0);
                if(k==50){
                    left = true;
                    start = false;
                    k = 0;
                }
                k++;
            }
            else if(left){
                monkEnt.increaseRotation(-1f, 0, 0);
                if(k == 100){
                    left = false;
                    k = 0;
                }
                k++;
            }
            else{
                monkEnt.increaseRotation(1f, 0, 0);
                if(k == 100){
                    left = true;
                    k = 0;
                }
                k++;
            }
            monkEnt2.increaseRotation(0, 0, 1);
            /*entity.increaseRotation(0, .4f, .4f);
            box2.increaseRotation(0, .4f, .4f);
            box3.increaseRotation(0, .4f, .4f);
            triangle.increaseRotation(0, .8f, 0);
            triangle2.increaseRotation(0, .8f, 0);
            triangle3.increaseRotation(0, .8f, 0);*/
            camera.move();
            player.move();
            for(Entity entity : allEntities){
                renderer.processEntity(entity);
            }
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain3);
            renderer.processTerrain(terrain4);
            renderer.processTerrain(terrain5);
            renderer.processEntity(monkEnt);
            renderer.processEntity(monkEnt2);
            renderer.processEntity(player);
            //game logic
            //renderer.render(entity, shader);
            /*renderer.render(box2, shader);
            renderer.render(box3, shader);
            renderer.render(triangle, shader);
            renderer.render(triangle2, shader);
            renderer.render(triangle3, shader);*/
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
        // TODO code application logic here
    }
    
}
