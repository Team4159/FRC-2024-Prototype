package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.FeederConstants.FeederState;
import frc.robot.Constants.IntakeConstants.IntakeState;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;

public class IntakeFeed extends Command{
    private Intake intake;
    private Feeder feeder;
    private IntakeState intakeState;
    private FeederState feederState;

    public IntakeFeed(Intake i, Feeder f, IntakeState is, FeederState fs){
        intake = i;
        feeder = f;
        intakeState = is;
        feederState = fs;
        addRequirements(intake);
        addRequirements(feeder);
    }

    @Override
    public void execute(){
        intake.setState(intakeState);
        feeder.setState(feederState);
    }

    public void end(boolean isInterupted){
        intake.setState(IntakeState.OFF);
        feeder.setState(FeederState.OFF);
    }
}
