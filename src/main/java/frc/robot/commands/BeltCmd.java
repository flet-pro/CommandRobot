package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BeltSubsystem;


public class BeltCmd extends CommandBase {
    private final BeltSubsystem beltSubsystem;
    private final double beltSpeed;

    public BeltCmd(BeltSubsystem m_beltSubsystem) {
        beltSubsystem = m_beltSubsystem;
        addRequirements(beltSubsystem);
        beltSpeed = Constants.BELT.DEFAULT_SPEED;
    }

    @Override
    public void initialize() {
        beltSubsystem.setBeltMotorSpeed(beltSpeed);
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
        beltSubsystem.setBeltMotorSpeed(0);
    }
}
