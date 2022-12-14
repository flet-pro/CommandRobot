package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax shootMotor;
    private double shootMotorSpeed;
    private final SparkMaxPIDController shooterPidController;

    public ShooterSubsystem() {
        shootMotor = new CANSparkMax(Constants.PORT.SHOOTER_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        shootMotor.setInverted(true);
        shooterPidController = shootMotor.getPIDController();
    }

    public void setShootMotorSpeed(double m_speed){
        shootMotorSpeed = m_speed;
    }

    @Override
    public void periodic() {
        shootMotor.set(shootMotorSpeed);
    }
}

