/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author acob1
 */
public class Camera {
    
    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;
    
    private Vector3f position = new Vector3f(0,2,0);
    private float pitch = 20;
    private float yaw;
    private float roll;
    
    private Player player;
    
    public Camera(Player player){
        this.player = player;
    }
    
    public void move(){
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            position.z -= .5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            position.z += .5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            position.x += .5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            position.x -= .5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_R)){
            position.y += .5f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){
            position.y = java.lang.Math.max(.05f, position.y-.5f);   
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
    
    private void calculateCameraPosition(float horizDistance, float verticDistance){
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.y = player.getPosition().y + verticDistance;
        position.z = player.getPosition().z - offsetZ;
    }
    
    private float calculateHorizontalDistance(){
        return (float)(distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }
    
    private float calculateVerticalDistance(){
        return (float)(distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }
    
    private void calculateZoom(){
        float zoomLevel = Mouse.getDWheel() * .1f;
        distanceFromPlayer -= zoomLevel;
    }
    
    private void calculatePitch(){
        if(Mouse.isButtonDown(2)){
            float pitchChange = Mouse.getDY() * .1f;
            pitch -= pitchChange;
        }
    }
    
    private void calculateAngleAroundPlayer(){
        if(Mouse.isButtonDown(2)){
            float angleChange = Mouse.getDX() * .3f;
            angleAroundPlayer -= angleChange;
        }
    }
}
