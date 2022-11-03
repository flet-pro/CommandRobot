// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    WPI_TalonSRX driveRightFrontMotor, driveLeftFrontMotor;
    VictorSPX driveRightBackMotor, driveLeftBackMotor;
    DifferentialDrive drive;

    private double xSpeed;
    private double zRotation;


    public DriveSubsystem() {
        driveRightFrontMotor = new WPI_TalonSRX(0);
        driveLeftFrontMotor = new WPI_TalonSRX(2);
        driveRightBackMotor = new VictorSPX(1);
        driveLeftBackMotor = new VictorSPX(3);

        driveRightFrontMotor.setInverted(true);
        driveRightBackMotor.setInverted(true);
        driveRightBackMotor.follow(driveRightFrontMotor);
        driveLeftBackMotor.follow(driveLeftFrontMotor);

        drive = new DifferentialDrive(driveLeftFrontMotor, driveRightFrontMotor);
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setzRotation(double zRotation) {
        this.zRotation = zRotation;
    }

    @Override
    public void periodic() {
        drive.arcadeDrive(xSpeed, zRotation);
    }
}
