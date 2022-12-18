// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class PORT {
        public static final int MAIN_CONTROLLER = 0;
        public static final int SUB_CONTROLLER = 1;
        public static final int ROLLER_MOTOR = 0;
        public static final int ROLLER_SOLENOID = 3;
        public static final int BELT_MOTOR = 4;
        public static final int SHOOTER_MOTOR = 6;
    }

    public final class DRIVE {
        public static final double DEFAULT_SPEED = 0.5;
        public static final double FAST_SPEED = 0.8;
        public static final double SLOW_SPEED = 0.3;
    }

    public final class ROLLER{

        public static final double DEFAULT_SPEED = 0.5;
    }

    public final class BELT{
        public static final double DEFAULT_SPEED = 0.5;
    }

    public static final class PID {
        /** PID Drive の超短距離の閾値*/
        public static final double DRIVE_SHORT_THRESHOLD = 0.5;

        public static final int DRIVE_SHORT_SLOT = 1;
        public static final int DRIVE_LONG_SLOT = 0;
        public static final int DRIVE_PID_INDEX = 0;

        private static final double ENCODER_POINTS_PER_REVOLUTION = 4096;
        private static final double DRIVE_WHEEL_DIAMETER = Units.inchesToMeters(6.0);
        private static final double DRIVE_WHEEL_LENGTH = DRIVE_WHEEL_DIAMETER * Math.PI;
        /**1mあたりのPoints
         * 計算方法 : (エンコーダーが1回転あたりに数えるPoints) / (タイヤの円周[m])
         */
        public static final double DRIVE_POINTS_PER_METER = ENCODER_POINTS_PER_REVOLUTION / DRIVE_WHEEL_LENGTH;
        /** PID Drive の誤差の許容範囲(Tolerance)*/
        public static final double DRIVE_TOLERANCE = 0.1;
    }

    public static final class MOTOR_CONFIGS {
        public static final TalonSRXConfiguration DRIVE_RIGHT = new TalonSRXConfiguration();
        public static final TalonSRXConfiguration DRIVE_LEFT = new TalonSRXConfiguration();
        public static PIDController gyroPidController;
    }

    public static void motorConfigsInit() {
        MOTOR_CONFIGS.DRIVE_RIGHT.slot0.kP = 0.051;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot0.kI = 0.000006;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot0.kD = 0.00054;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot0.maxIntegralAccumulator = 1023*0.014/ MOTOR_CONFIGS.DRIVE_RIGHT.slot0.kI;

        MOTOR_CONFIGS.DRIVE_LEFT.slot0.kP = 0.048;
        MOTOR_CONFIGS.DRIVE_LEFT.slot0.kI = 0.000009;
        MOTOR_CONFIGS.DRIVE_LEFT.slot0.kD = 0.00054;
        MOTOR_CONFIGS.DRIVE_LEFT.slot0.maxIntegralAccumulator =  1023*0.014/ MOTOR_CONFIGS.DRIVE_LEFT.slot0.kI;

        MOTOR_CONFIGS.DRIVE_RIGHT.slot1.kP = 0.2;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot1.kI = 0.004;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot1.kD = 0.000;
        MOTOR_CONFIGS.DRIVE_RIGHT.slot1.maxIntegralAccumulator = 1023*0.1/ MOTOR_CONFIGS.DRIVE_RIGHT.slot1.kI;

        MOTOR_CONFIGS.DRIVE_LEFT.slot1.kP = 0.2;
        MOTOR_CONFIGS.DRIVE_LEFT.slot1.kI = 0.0004;
        MOTOR_CONFIGS.DRIVE_LEFT.slot1.kD = 0.000;
        MOTOR_CONFIGS.DRIVE_LEFT.slot1.maxIntegralAccumulator =  1023*0.1/ MOTOR_CONFIGS.DRIVE_LEFT.slot1.kI;


        MOTOR_CONFIGS.DRIVE_RIGHT.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        MOTOR_CONFIGS.DRIVE_LEFT.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        MOTOR_CONFIGS.gyroPidController = new PIDController(0.01, gyrokI, 0);
        MOTOR_CONFIGS.gyroPidController.setIntegratorRange(-0.1/gyrokI, 0.1/gyrokI);
        MOTOR_CONFIGS.gyroPidController.setTolerance(3);
    }

    public static final double gyrokI = 0.0021;
}

//TODO 内部クラス内の静的宣言 は言語レベル '11' ではサポートされていませんについて Units.inchesToMeters(6.0)を使うと出る模様
