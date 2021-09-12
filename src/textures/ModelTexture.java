/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textures;

/**
 *
 * @author acob1
 */
public class ModelTexture {

        
    private int textureID;
    
    private float shineDamper = 1;
    private float reflectivity = 0;
    
    private boolean hasTransperancy = false;
    private boolean useFakeLighting = false;
     
    public ModelTexture(int id){
        this.textureID = id;
    }
  
    public int getID(){
        return this.textureID;
    }

    public boolean isUseFakeLighting() {
        return useFakeLighting;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }
    
    public boolean isHasTransparency() {
        return hasTransperancy;
    }

    public void setHasTransparency(boolean hasTransperancy) {
        this.hasTransperancy = hasTransperancy;
    }
    
    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
    
}
