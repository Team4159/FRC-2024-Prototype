package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants.IntakeState;

public class Intake extends SubsystemBase {
    // Prototype also uses vortexes now
    private CANSparkFlex motor1;
    private IntakeState intakeState;

    public Intake() {
        motor1 = new CANSparkFlex(Constants.IntakeConstants.motor1ID, MotorType.kBrushless);
        intakeState = IntakeState.OFF;
    }

    @Override
    public void periodic() {
        motor1.set(intakeState.percentage);
    }

    public void setState(IntakeState state) {
        intakeState = state;
    }

    public void stopMotors() {
        motor1.stopMotor();
    }
}