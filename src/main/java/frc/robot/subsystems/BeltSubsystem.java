package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BeltSubsystem extends SubsystemBase {
    private final WPI_TalonSRX beltMotor;
    private double beltMotorSpeed;

    public BeltSubsystem() {
        beltMotor = new WPI_TalonSRX(Constants.PORT.BELT_MOTOR);
    }

    public void setBeltMotorSpeed(double m_speed){
        beltMotorSpeed = m_speed;
    }

    @Override
    public void periodic() {
        beltMotor.set(ControlMode.PercentOutput, beltMotorSpeed);
    }
}

