package frc.robot.utils;

import edu.wpi.first.cameraserver.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class to start USB camera capture data.
 * This camera initialization is only used if the camera is plugged in the roboRIO.
 * The final robot has the camera processed by the RPi coprocessor,
 * then send the image through Network Tables, showing the image in the Shuffleboard.
 */
public final class CameraSetup {

    private static final Logger logger = Logger.getLogger(CameraSetup.class.getName());
    public static final String DEFAULT_CAMERA_NAME = "Camera 0";
    public static final int DEFAULT_CAMERA_ID = 0;

    /**
     * No instantiation.
     */
    private CameraSetup() {
    }

    /**
     * Sets up the default camera.
     *
     * @see #setupCamera
     * @see #DEFAULT_CAMERA_NAME
     * @see #DEFAULT_CAMERA_ID
     */
    public static synchronized boolean setupDefaultCamera() {
        return setupCamera(DEFAULT_CAMERA_NAME, DEFAULT_CAMERA_ID);
    }

    /**
     * Sets up automatic capture for a camera with a given name and ID.
     *
     * @param name The name for the camera
     * @param id   The integral ID for the camera
     * @return True if successful, false otherwise
     */
    public static synchronized boolean setupCamera(String name, int id) {
        try {
            CameraServer.getInstance().startAutomaticCapture(name, id);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
        }

        logger.log(Level.WARNING, "Failed to start camera capture (" + name + ", " + id + ")");

        return false;
    }

}