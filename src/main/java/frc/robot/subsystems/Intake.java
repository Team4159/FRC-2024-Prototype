package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants.IntakeState;

public class Intake extends SubsystemBase {
    // Prototype uses falcons, final design uses neo vortex
    private TalonFX motor1;
    //private TalonFX motor2;
    private IntakeState intakeState;

    public Intake() {
        motor1 = new TalonFX(Constants.IntakeConstants.motor1ID);
        //motor2 = new TalonFX(Constants.IntakeConstants.motor2ID);
        intakeState = IntakeState.OFF;
    }

    @Override
    public void periodic() {
        motor1.set(intakeState.percentage);
        //motor2.set(-intakeState.percentage);
    }

    public void setState(IntakeState state) {
        intakeState = state;
    }

    public void stopMotors() {
        motor1.stopMotor();
        //motor2.stopMotor();
    }
}
