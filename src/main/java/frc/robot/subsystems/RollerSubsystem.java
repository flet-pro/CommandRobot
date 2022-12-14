package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RollerSubsystem extends SubsystemBase {
    private final VictorSPX rollerMotor;
    private final Solenoid rollerSolenoid;
    private double rollerMotorSpeed;
    private boolean solenoidOpen;


    public RollerSubsystem() {
        rollerMotor = new VictorSPX(Constants.PORT.ROLLER_MOTOR);
        rollerMotor.setInverted(true);
        rollerSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.PORT.ROLLER_SOLENOID);
        solenoidOpen = false;
    }

    public void toggleSolenoidOpen(){
        solenoidOpen  = !solenoidOpen;
    }

    public void setRollerMotorSpeed(double m_speed){
        rollerMotorSpeed = m_speed;
    }

    @Override
    public void periodic() {
        rollerSolenoid.set(solenoidOpen);
        rollerMotor.set(ControlMode.PercentOutput, rollerMotorSpeed);
    }
}

