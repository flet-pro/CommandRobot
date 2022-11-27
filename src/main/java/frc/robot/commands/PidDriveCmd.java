package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class PidDriveCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    public PidDriveCmd(DriveSubsystem m_driveSubsystem) {
        driveSubsystem = m_driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
