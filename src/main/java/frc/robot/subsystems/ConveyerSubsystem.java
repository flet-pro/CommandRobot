// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyerSubsystem extends SubsystemBase {
    WPI_TalonSRX controlIntakeBelt;
    VictorSPX controlIntakeRoller;
    CANSparkMax controlShootingMotor;
    Solenoid controlIntakeSolenoid;

    private double beltSpeed;
    private double rollerSpeed;
    private boolean isIntakeOpened;
    private double shootingMotorSpeed;
    
    public ConveyerSubsystem() {
        controlIntakeRoller = new VictorSPX(5);
        controlIntakeRoller.setInverted(true);

        controlIntakeBelt = new WPI_TalonSRX(4);

        controlShootingMotor = new CANSparkMax(6, MotorType.kBrushless);
        controlShootingMotor.setInverted(true);

        controlIntakeSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
    }

    @Override
    public void periodic() {
        controlIntakeSolenoid.set(isIntakeOpened);
        controlShootingMotor.set(shootingMotorSpeed);
        controlIntakeRoller.set(ControlMode.PercentOutput, rollerSpeed);
        controlIntakeBelt.set(ControlMode.PercentOutput, beltSpeed);
    }
}
