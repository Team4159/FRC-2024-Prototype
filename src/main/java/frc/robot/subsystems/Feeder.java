package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FeederConstants.FeederState;

public class Feeder extends SubsystemBase {
    private CANSparkMax motor;
    private FeederState feederState;

    public Feeder() {
        motor = new CANSparkMax(Constants.FeederConstants.motorID, MotorType.kBrushless);
        feederState = FeederState.OFF;
    }

    @Override
    public void periodic() {
        motor.set(feederState.percentage);
    }

    public void setState(FeederState state) {
        feederState = state;
    }

    public FeederState getState() {
        return feederState;
    }

    public void stopMotor() {
        motor.stopMotor();
    }
}