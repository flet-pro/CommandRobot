// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCmd;
import frc.robot.commands.ConveyorCmd;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.PidDriveCmd;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ConveyerSubsystem;
import frc.robot.subsystems.DriveSubsystem;


public class RobotContainer {
    private final DriveSubsystem s_drive;
//    private final ConveyerSubsystem s_conveyor;
//    private final ClimbSubsystem s_climb;

    private final JoystickButton yButton = new JoystickButton(IO.mainController, XboxController.Button.kY.value);
    private final JoystickButton xButton = new JoystickButton(IO.mainController, XboxController.Button.kX.value);
    private final JoystickButton aButton = new JoystickButton(IO.mainController, XboxController.Button.kA.value);
    private final JoystickButton bButton = new JoystickButton(IO.mainController, XboxController.Button.kB.value);

    public RobotContainer() {
        s_drive = new DriveSubsystem();
//        s_conveyor = new ConveyerSubsystem();
//        s_climb = new ClimbSubsystem();

        setDefaultCmd();
        configureButtonBindings();
    }

    private void setDefaultCmd() {
        s_drive.setDefaultCommand(new ArcadeDriveCmd(s_drive, Constants.DRIVE.DEFAULT_SPEED));
//        s_conveyor.setDefaultCommand(new ConveyorCmd(s_conveyor));
//        s_climb.setDefaultCommand(new ClimbCmd(s_climb));
    }

    private void configureButtonBindings() {
        yButton.whileActiveContinuous(new ArcadeDriveCmd(s_drive, Constants.DRIVE.FAST_SPEED), false);
        xButton.whileActiveContinuous(new ArcadeDriveCmd(s_drive, Constants.DRIVE.SLOW_SPEED), false);
        aButton.whenPressed(new PidDriveCmd(s_drive, 10), false);
    }
}