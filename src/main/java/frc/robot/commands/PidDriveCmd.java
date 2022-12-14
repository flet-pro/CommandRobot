package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class PidDriveCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final double targetPosition;

    public PidDriveCmd(DriveSubsystem m_driveSubsystem, double m_targetPosition) {
        driveSubsystem = m_driveSubsystem;
        addRequirements(driveSubsystem);

        targetPosition = m_targetPosition;
    }

    @Override
    public void initialize() {
        driveSubsystem.setPidDriveMode(true);
        driveSubsystem.setPidTargetPosition(targetPosition);
        System.out.println(923849);
    }

    @Override
    public void execute() {
        System.out.println(123);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println(111111111);
        // driveSubsystem.setPidDriveMode(false);
    }

    @Override
    public boolean isFinished() {
        return driveSubsystem.getPidDriveFinished();
    }
}
