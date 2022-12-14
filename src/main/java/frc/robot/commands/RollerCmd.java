package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.RollerSubsystem;


public class RollerCmd extends CommandBase {
    private final RollerSubsystem rollerSubsystem;
    private final double rollerSpeed;
    private final boolean controlMotor;

    public RollerCmd(RollerSubsystem m_rollerSubsystem) {
        rollerSubsystem = m_rollerSubsystem;
        addRequirements(rollerSubsystem);
        rollerSpeed = Constants.ROLLER.DEFAULT_SPEED;
        controlMotor = true;
    }

    public RollerCmd(RollerSubsystem m_rollerSubsystem, double m_rollerSpeed){
        rollerSubsystem = m_rollerSubsystem;
        addRequirements(rollerSubsystem);
        rollerSpeed = m_rollerSpeed;
        controlMotor = true;
    }

    public RollerCmd(RollerSubsystem m_rollerSubsystem, boolean m_controlSolenoid){
        rollerSubsystem = m_rollerSubsystem;
        addRequirements(rollerSubsystem);
        rollerSpeed = 0;
        controlMotor = m_controlSolenoid;
    }

    @Override
    public void initialize() {
        if(controlMotor){
            rollerSubsystem.setRollerMotorSpeed(rollerSpeed);
        }else {
            rollerSubsystem.toggleSolenoidOpen();
        }
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
        rollerSubsystem.setRollerMotorSpeed(0);
    }
}
