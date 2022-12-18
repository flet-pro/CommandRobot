// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    WPI_TalonSRX driveRightFrontMotor, driveLeftFrontMotor;
    VictorSPX driveRightBackMotor, driveLeftBackMotor;
    DifferentialDrive drive;
    ADXRS450_Gyro gyro;

    private double xSpeed;
    private double zRotation;
    private boolean pidDriveMode;
    private boolean pidDriveFinished;
    private double pidTargetPosition;
    private boolean gyroDriveMode;
    private double gyroTargetDirection;
    private boolean gyroDriveFinished;


    public DriveSubsystem() {
        Constants.motorConfigsInit();
        gyroInit();
        driveRightFrontMotor = new WPI_TalonSRX(0);
        driveLeftFrontMotor = new WPI_TalonSRX(2);
        driveRightBackMotor = new VictorSPX(1);
        driveLeftBackMotor = new VictorSPX(3);

        driveRightFrontMotor.setInverted(true);
        driveRightBackMotor.setInverted(true);
        driveRightBackMotor.follow(driveRightFrontMotor);
        driveLeftBackMotor.follow(driveLeftFrontMotor);

        driveRightFrontMotor.setNeutralMode(NeutralMode.Brake);
        driveLeftFrontMotor.setNeutralMode(NeutralMode.Brake);
        driveRightBackMotor.setNeutralMode(NeutralMode.Brake);
        driveLeftBackMotor.setNeutralMode(NeutralMode.Brake);

        driveRightFrontMotor.configAllSettings(Constants.MOTOR_CONFIGS.DRIVE_RIGHT);
        driveLeftFrontMotor.configAllSettings(Constants.MOTOR_CONFIGS.DRIVE_LEFT);
        driveRightFrontMotor.setSensorPhase(true);
        driveLeftFrontMotor.setSensorPhase(true);

        drive = new DifferentialDrive(driveLeftFrontMotor, driveRightFrontMotor);
        pidDriveMode = false;
        pidDriveFinished = true;

        gyro = new ADXRS450_Gyro();
        gyroDriveMode = false;
        gyroDriveFinished = true;
    }

    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setZRotation(double zRotation) {
        this.zRotation = zRotation;
    }

    public void setPidTargetPosition(double m_pidTargetPosition){
        pidTargetPosition = m_pidTargetPosition;
    }

    public boolean getPidDriveFinished(){
        return pidDriveFinished;
    }

    public void setPidDriveFinished(boolean m_pidDriveFinished){
        pidDriveFinished = m_pidDriveFinished;
    }

    public void setPidDriveMode(boolean m_pidDriveMode){
        pidDriveMode = m_pidDriveMode;
    }

    public void setGyroTargetDirection(double m_geroTargetDirection){
        gyroTargetDirection = m_geroTargetDirection;
    }

    public void setGyroDriveFinished(boolean m_gyroDriveFinished){
        gyroDriveFinished = m_gyroDriveFinished;
    }

    public boolean getGyroDriveFinished(){
        return gyroDriveFinished;
    }

    public void setGyroDriveMode(boolean m_gyroDriveMode){
        gyroDriveMode = m_gyroDriveMode;
    }

    public void pidReset(){
        driveRightFrontMotor.setSelectedSensorPosition(0);
        driveLeftFrontMotor.setSelectedSensorPosition(0);
        driveLeftFrontMotor.setIntegralAccumulator(0);
        driveRightFrontMotor.setIntegralAccumulator(0);
    }

    public void gyroReset(){
        gyro.reset();
    }

    @Override
    public void periodic() {
        if (pidDriveMode) {
            driveToPosition(pidTargetPosition);
            pidDriveFinished = judgePidDrive();
        } else if (gyroDriveMode) {
            turnTo(gyroTargetDirection);
            gyroDriveFinished = judgeGyroDirection();
            System.out.println(gyro.getAngle());
        } else {
            drive.arcadeDrive(xSpeed, zRotation);
        }
    }

    private void driveToPosition(double targetPosition){
        if(Math.abs(targetPosition) < Constants.PID.DRIVE_SHORT_THRESHOLD){
            driveRightFrontMotor.selectProfileSlot(Constants.PID.DRIVE_SHORT_SLOT, Constants.PID.DRIVE_PID_INDEX);
            driveLeftFrontMotor.selectProfileSlot(Constants.PID.DRIVE_SHORT_SLOT, Constants.PID.DRIVE_PID_INDEX);
        }else {
            driveRightFrontMotor.selectProfileSlot(Constants.PID.DRIVE_LONG_SLOT, Constants.PID.DRIVE_PID_INDEX);
            driveLeftFrontMotor.selectProfileSlot(Constants.PID.DRIVE_LONG_SLOT, Constants.PID.DRIVE_PID_INDEX);
        }
        driveRightFrontMotor.set(ControlMode.Position, getPointsOfMeters(targetPosition));
        driveLeftFrontMotor.set(ControlMode.Position, getPointsOfMeters(targetPosition));
    }

    private double getPointsOfMeters(double m_meters) {
        return m_meters * Constants.PID.DRIVE_POINTS_PER_METER;
    }

    private double getMetersOfPoints(double m_points){
        return m_points / Constants.PID.DRIVE_POINTS_PER_METER;
    }

    private boolean judgePidDrive(){
        return judgeRightPosition() && judgeLeftPosition();
    }

    private double getRightPosition(){
        System.out.println(getMetersOfPoints(driveRightFrontMotor.getSelectedSensorPosition()));
        return getMetersOfPoints(driveRightFrontMotor.getSelectedSensorPosition());
    }

    private boolean judgeRightPosition(){
        return Math.abs(getRightPosition() - pidTargetPosition) < Constants.PID.DRIVE_TOLERANCE;
    }

    private double getLeftPosition(){
        return getMetersOfPoints(driveLeftFrontMotor.getSelectedSensorPosition());
    }
    private boolean judgeLeftPosition(){
        return Math.abs(getLeftPosition() - pidTargetPosition) < Constants.PID.DRIVE_TOLERANCE;
    }
    private void gyroInit() {
        gyro.reset();
        gyro.calibrate();
    }

    private boolean judgeGyroDirection(){
        return Constants.MOTOR_CONFIGS.gyroPidController.atSetpoint();
    }

    public void turnTo(double direction) {
        drive.arcadeDrive(0, Constants.MOTOR_CONFIGS.gyroPidController.calculate(getCurrentDirection(), direction));
    }

    public static double mod(double a, double b) {
        if(a < 0) {
            a += ((int)(-a / b) + 1) * b;
        }
        return a % b;
    }

    // 時計回りに回るかを、反時計回りに回るかを、曲がりたい方向の大きさによって決める。
    public static double determineDirection(double a) {
        double k = mod(a, 360);
        if(k>180){
            return(k-360);
        } else{
            return(k);
        }
    }

    public double getCurrentDirection(){
        double c = gyro.getAngle();
        return determineDirection(c);
    }
}
