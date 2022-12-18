package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class GyroDriveCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final double targetDirection;

    public GyroDriveCmd(DriveSubsystem m_driveSubsystem, double m_targetDirection) {
        driveSubsystem = m_driveSubsystem;
        addRequirements(driveSubsystem);

        targetDirection = m_targetDirection;
    }

    @Override
    public void initialize() {
        driveSubsystem.setGyroDriveMode(true);
        driveSubsystem.setGyroDriveFinished(false);
        driveSubsystem.gyroReset();
        driveSubsystem.setGyroTargetDirection(targetDirection);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return driveSubsystem.getGyroDriveFinished();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.setPidDriveFinished(true);
        driveSubsystem.setGyroDriveMode(false);
    }
}
