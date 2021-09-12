/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

/**
 *
 * @author acob1
 */
public class Player extends Entity{
    
    private static final float RUN_SPEED = 60;
    private static final float TURN_SPEED = 160;
    private static final float GRAVITY = -150;
    private static final float JUMP_POWER = 60;
    
    private static final float TERRAIN_HEIGHT = 3;
    
    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardSpeed = 0;
    
    private boolean isInAir = false;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }
    
    public void move(){
        checkInputs();
        super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0 , dz);
        upwardSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardSpeed * DisplayManager.getFrameTimeSeconds(), 0);
        if(super.getPosition().y<TERRAIN_HEIGHT){
            upwardSpeed = 0;
            isInAir =  false;
            super.getPosition().y = TERRAIN_HEIGHT;
        }
    }
    
    private void jump(){
        if(!isInAir){
            isInAir = true;
            this.upwardSpeed = JUMP_POWER;
        }   
    }
    
    private void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            this.currentSpeed = RUN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            this.currentSpeed = -RUN_SPEED;
        }else{
            this.currentSpeed = 0;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            this.currentTurnSpeed = -TURN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            this.currentTurnSpeed = TURN_SPEED;
        }else{
            this.currentTurnSpeed = 0;
        }
        
        if(Mouse.isButtonDown(0)){
            jump();
        }
    }
    
    
}
