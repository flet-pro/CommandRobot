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
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}

//TODO PIDControlの基本的な仕組みの勉強 https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-pid.html?highlight=PID
//TODO PIDControlのライブラリの勉強 https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html

//TODO VisionControlについて https://docs.wpilib.org/en/stable/docs/software/vision-processing/introduction/strategies-for-vision-programming.html
