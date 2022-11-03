// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCmd extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private XboxController controller;
    // Supplier<Double> speedModeFunction;
    private double speedMode = 0.5; 
    

    public ArcadeDriveCmd(DriveSubsystem m_driveSubsystem, XboxController m_controller, double speed) {
        driveSubsystem = m_driveSubsystem;
        controller = m_controller;
        addRequirements(driveSubsystem);

        speedMode = speed;

        // speedModeFunction = m_speedModeFunction;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveSubsystem.setxSpeed(-1 * controller.getLeftY() * speedMode);
        driveSubsystem.setzRotation(controller.getRightX() * speedMode);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    public void setSpeedMode(double change){
        speedMode *= change;
    }
}
