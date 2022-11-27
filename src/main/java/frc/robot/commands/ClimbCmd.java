package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;


public class ClimbCmd extends CommandBase {
    private ClimbSubsystem climbSubsystem;

    public ClimbCmd(ClimbSubsystem m_climbSubsystem) {
        climbSubsystem = m_climbSubsystem;
        addRequirements(climbSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
