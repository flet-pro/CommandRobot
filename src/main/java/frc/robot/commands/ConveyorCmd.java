package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConveyerSubsystem;


public class ConveyorCmd extends CommandBase {
    private ConveyerSubsystem conveyerSubsystem;

    public ConveyorCmd(ConveyerSubsystem m_conveyerSubsystem) {
        conveyerSubsystem = m_conveyerSubsystem;
        addRequirements(conveyerSubsystem);
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
