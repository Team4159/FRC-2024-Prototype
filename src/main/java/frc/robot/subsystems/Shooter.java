package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants.ShooterState;

public class Shooter extends SubsystemBase {
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private CANSparkMax motor3;
    private CANSparkMax motor4;
    private CANSparkMax feederMotor;

    private ShooterState shooterState;

    public Shooter() {
        motor1 = new CANSparkMax(0, MotorType.kBrushless);
        motor2 = new CANSparkMax(0, MotorType.kBrushless);
        motor3 = new CANSparkMax(0, MotorType.kBrushless);
        motor4 = new CANSparkMax(0, MotorType.kBrushless);
        feederMotor = new CANSparkMax(0, MotorType.kBrushless);

        shooterState = ShooterState.OFF;
    }

    @Override
    public void periodic() {
        motor1.set(shooterState.speed1);
        motor2.set(-shooterState.speed1);
        motor3.set(shooterState.speed2);
        motor4.set(-shooterState.speed2);
        feederMotor.set(-shooterState.speedFeeder);
    }

    public void setState(ShooterState state) {
        shooterState = state;
    }

    public ShooterState getState() {
        return shooterState;
    }

    public void stopMotors() {
        motor1.stopMotor();
        motor2.stopMotor();
        motor3.stopMotor();
        motor4.stopMotor();
        feederMotor.stopMotor();
    }
}