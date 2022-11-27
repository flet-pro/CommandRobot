package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * Input Output Class
 */
public class IO {
    public static final XboxController mainController = new XboxController(Constants.PORT.MAIN_CONTROLLER);
    public static final XboxController subController = new XboxController(Constants.PORT.SUB_CONTROLLER);
}
