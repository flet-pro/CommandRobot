// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.util.function.Supplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.ChangeDriveSpeedMode;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.ConveyerSubsystem;
import frc.robot.subsystems.DriveSubsystem;


public class RobotContainer {
    private XboxController controller = new XboxController(0);

    private DriveSubsystem s_drive;
    private ConveyerSubsystem s_conveyer;
    private ClimbSubsystem s_climb;

    private ArcadeDriveCmd c_arcadeDrive;

    // private final Supplier<Double> = new Supplier<Double>(XboxController m_controller){
    //     double get()
    // }

    private final JoystickButton yButton = new JoystickButton(controller, XboxController.Button.kY.value);
    private final JoystickButton xButton = new JoystickButton(controller, XboxController.Button.kX.value);

    public RobotContainer() {
        s_drive = new DriveSubsystem();
        s_conveyer = new ConveyerSubsystem();
        s_climb = new ClimbSubsystem();

        c_arcadeDrive = new ArcadeDriveCmd(s_drive, controller);

        configureSetDefaultCmd();
        configureButtonBindings();
    }

    private void configureSetDefaultCmd() {
        s_drive.setDefaultCommand(c_arcadeDrive);
    }

    private void configureButtonBindings() {
        yButton.whileActiveContinuous(new ChangeDriveSpeedMode(c_arcadeDrive, 0.8));
        xButton.whileActiveContinuous(new ChangeDriveSpeedMode(c_arcadeDrive, 0.25));
    }
}

// class speedModeFunction implements Supplier<Double> {
//     private XboxController controller;

//     speedModeFunction(XboxController m_controller){
//         controller = m_controller;
//     }

//     @Override
//     public Double get() {
//         if(controller.getYButton()){
//             return 0.5;
//         }else if(controller.getXButton()){
//             return 0.25;
//         }else{
//             return 0.8;
//         }
//     }
// }
