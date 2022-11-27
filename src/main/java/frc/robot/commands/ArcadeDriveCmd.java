// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IO;
import frc.robot.subsystems.DriveSubsystem;

public class ArcadeDriveCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final double speedMode;
    

    public ArcadeDriveCmd(DriveSubsystem m_driveSubsystem, double m_speedMode) {
        driveSubsystem = m_driveSubsystem;
        speedMode = m_speedMode;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        driveSubsystem.setxSpeed(-1 * IO.mainController.getLeftY() * speedMode);
        driveSubsystem.setzRotation(IO.mainController.getRightX() * speedMode);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
